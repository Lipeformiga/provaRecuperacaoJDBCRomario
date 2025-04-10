package br.senai.sc.drones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

//        UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
//        int usuario_id = rs.getInt("usuario_id");
//        tarefa.setUsuario(usuarioCRUD.getOne(usuario_id
        return entrega;
    }

    public Entrega findById(int codigo){
        comando = "select * from entrega where codigo = ?";
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
        comando = "select * from entrega";
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
        comando = "insert into entrega (descricao, peso, destino, fragil) values (?, ?, ?, ?)";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entrega.getDescricao());
            ps.setInt(2, entrega.getPeso());
            ps.setString(3, entrega.getDestino());
            ps.setBoolean(4, entrega.isFragil());


            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    entrega.setCodigo(rs.getInt(1));
                }
            }

            return entrega;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Entrega update(Entrega entrega){
        comando = "update entrega set descricao = ?, peso = ?, destino = ?, fragil = ? where codigo = ?";

        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setString(1, entrega.getDescricao());
            ps.setInt(2,entrega.getPeso());
            ps.setString(3,entrega.getDestino());
            ps.setBoolean(4,entrega.isFragil());
            ps.setInt(5, entrega.getCodigo());

            ps.execute();

            return entrega;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int codigo){
        comando = "delete from entrega where codigo = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setInt(1,codigo);

            ps.execute();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
