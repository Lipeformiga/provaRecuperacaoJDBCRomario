package br.senai.sc.drones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PacoteDAO {

    private String comando;
    private final ConexaoBD conexao = new ConexaoBD();

    public Pacote criarObjeto(ResultSet rs) throws SQLException {
        Pacote pacote = new Pacote();

        pacote.setCodigo(rs.getInt("codigo"));
        pacote.setDescricao(rs.getString("descricao"));
        pacote.setPeso(rs.getInt("peso"));
        pacote.setDestino(rs.getString("destino"));
        pacote.setFragil(rs.getBoolean("fragil"));

        return pacote;
    }

    public Pacote findById(int codigo){
        comando = "select * from PACOTE where codigo = ?";
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

    public List<Pacote> findAll(){
        comando = "select * from PACOTE";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando);
            ResultSet rs = ps.executeQuery()){

            List<Pacote> pacotes = new ArrayList<>();

            while(rs.next()){
                pacotes.add(criarObjeto(rs));
            }
            return pacotes;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Pacote save(Pacote pacote){
        try {
            findById(pacote.getCodigo());
            return update(pacote);
        } catch (NoSuchElementException e){
            return create(pacote);
        }
    }

    public Pacote create(Pacote pacote){
        comando = "insert into PACOTE (descricao, peso, destino, fragil) values (?, ?, ?, ?)";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pacote.getDescricao());
            ps.setInt(2, pacote.getPeso());
            ps.setString(3, pacote.getDestino());
            ps.setBoolean(4, pacote.isFragil());


            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    pacote.setCodigo(rs.getInt(1));
                }
            }

            return pacote;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pacote update(Pacote pacote){
        comando = "update PACOTE set descricao = ?, peso = ?, destino = ?, fragil = ? where codigo = ?";

        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setString(1, pacote.getDescricao());
            ps.setInt(2,pacote.getPeso());
            ps.setString(3,pacote.getDestino());
            ps.setBoolean(4,pacote.isFragil());
            ps.setInt(5, pacote.getCodigo());

            ps.execute();

            return pacote;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int codigo){
        comando = "delete from PACOTE where codigo = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setInt(1,codigo);

            ps.execute();

        } catch (SQLException e){
            throw new IllegalArgumentException (e);
        }
    }

}
