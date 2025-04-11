package br.senai.sc.drones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class EntregaDAO {

    private String comando;
    private final ConexaoBD conexao = new ConexaoBD();

    public Entrega criarObjeto(ResultSet rs) throws SQLException {
        Entrega entrega = new Entrega();

        entrega.setCodigo(rs.getInt("codigo"));
        entrega.setOrigem(rs.getString("origem"));
        entrega.setDestino(rs.getString("destino"));
        entrega.setStatus(rs.getString("status"));
        entrega.setDistancia(rs.getInt("distancia"));
        entrega.setTempoEstimado(rs.getInt("tempo_estimado"));

        DroneDAO droneDAO = new DroneDAO();
        int drone_codigo = rs.getInt("drone_codigo");
        entrega.setDrone(droneDAO.findById(drone_codigo));

        PacoteDAO pacoteDAO = new PacoteDAO();
        int pacote_codigo = rs.getInt("pacote_codigo");
        entrega.setPacote(pacoteDAO.findById(pacote_codigo));

        ClienteDAO clienteDAO = new ClienteDAO();
        long cliente_cpf = rs.getLong("cliente_cpf");
        entrega.setCliente(clienteDAO.findById(cliente_cpf));

        return entrega;
    }

    public Entrega findById(int codigo){
        comando = "select * from ENTREGA where codigo = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setInt(1, codigo);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return criarObjeto(rs);
                } else {
                    throw new NoSuchElementException();
                }
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Entrega> findAll(){
        comando = "select * from ENTREGA";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando);
            ResultSet rs = ps.executeQuery()){

            List<Entrega> entregas = new ArrayList<>();

            while(rs.next()){
                entregas.add(criarObjeto(rs));
            }
            return entregas;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Entrega save(Entrega entrega){
        try {
            findById(entrega.getCodigo());
            return update(entrega);
        } catch (NoSuchElementException e){
            return create(entrega);
        }
    }

    public Entrega create(Entrega entrega){
        comando = "insert into ENTREGA (drone_codigo, pacote_codigo, cliente_cpf, origem, destino, status, distancia, tempo_estimado) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entrega.getDrone().getCodigo());
            ps.setInt(2, entrega.getPacote().getCodigo());
            ps.setLong(3, entrega.getCliente().getCpf());
            ps.setString(4, entrega.getOrigem());
            ps.setString(5, entrega.getDestino());
            ps.setString(6, entrega.getStatus());
            ps.setInt(7, entrega.getDistancia());
            ps.setInt(8, entrega.getTempoEstimado());

            if (!Objects.equals(entrega.getPacote().getDestino(), entrega.getDestino())){
                throw new IllegalArgumentException();
            } else {
                ps.execute();

                try (ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()){
                        entrega.setCodigo(rs.getInt(1));
                    }
                }

                return entrega;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Entrega update(Entrega entrega){
        comando = "update ENTREGA set drone_codigo = ?, pacote_codigo = ?, cliente_cpf = ?, origem = ?, destino = ?, status = ?, distancia = ?, tempo_estimado = ? where codigo = ?";

        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setInt(1, entrega.getDrone().getCodigo());
            ps.setInt(2, entrega.getPacote().getCodigo());
            ps.setLong(3, entrega.getCliente().getCpf());
            ps.setString(4, entrega.getOrigem());
            ps.setString(5, entrega.getDestino());
            ps.setString(6, entrega.getStatus());
            ps.setInt(7, entrega.getDistancia());
            ps.setInt(8, entrega.getTempoEstimado());
            ps.setInt(9, entrega.getCodigo());

            ps.execute();

            return entrega;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int codigo){
        comando = "delete from ENTREGA where codigo = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setInt(1,codigo);

            ps.execute();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
