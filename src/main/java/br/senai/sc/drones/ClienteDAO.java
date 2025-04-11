package br.senai.sc.drones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClienteDAO {

    private String comando;
    private final ConexaoBD conexao = new ConexaoBD();

    public Cliente criarObjeto(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();

        cliente.setCpf(rs.getLong("cpf"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setNome(rs.getString("nome"));
        cliente.setSenha(rs.getString("senha"));

        return cliente;
    }

    public Cliente findById(long cpf){
        comando = "select * from CLIENTE where cpf = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setLong(1, cpf);

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

    public List<Cliente> findAll(){
        comando = "select * from CLIENTE";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando);
            ResultSet rs = ps.executeQuery()){

            List<Cliente> clientes = new ArrayList<>();

            while(rs.next()){
                clientes.add(criarObjeto(rs));
            }
            return clientes;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Cliente save(Cliente cliente){
        try {
            findById(cliente.getCpf());
            return update(cliente);
        } catch (NoSuchElementException e){
            return create(cliente);
        }
    }

    public Cliente create(Cliente cliente){
        comando = "insert into CLIENTE (cpf, nome, endereco, senha) values (?, ?, ?, ?)";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getSenha());

            ps.execute();

            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente update(Cliente cliente){
        comando = "update CLIENTE set nome = ?, endereco = ?, senha = ? where cpf = ?";

        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getSenha());
            ps.setLong(4, cliente.getCpf());

            ps.execute();

            return cliente;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(long cpf){
        comando = "delete from CLIENTE where cpf = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            EntregaDAO entregaDAO = new EntregaDAO();

            ps.setLong(1,cpf);

            ps.execute();

        } catch (SQLException e){
            throw new IllegalArgumentException (e);
        }
    }
}
