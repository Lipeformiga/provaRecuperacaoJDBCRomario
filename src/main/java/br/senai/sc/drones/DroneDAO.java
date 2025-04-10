package br.senai.sc.drones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DroneDAO {

    private String comando;
    private final ConexaoBD conexao = new ConexaoBD();

    public Drone criarObjeto(ResultSet rs) throws SQLException {
        Drone drone = new Drone();

        drone.setCodigo(rs.getInt("codigo"));
        drone.setModelo(rs.getString("modelo"));
        drone.setDisponivel(rs.getBoolean("disponivel"));
        drone.setBateria(rs.getInt("bateria"));
        drone.setLocalizacao(rs.getString("localizacao"));
        drone.setEmVoo(rs.getBoolean("em_voo"));
        drone.setCargaAtual(rs.getInt("carga_atual"));
        drone.setCapacidadeCarga(rs.getInt("capacidade_carga"));
        drone.setPermiteCargaFragil(rs.getBoolean("permitir_carga_fragil"));

        return drone;
    }

    public Drone findById(int codigo){
        comando = "select * from DRONE where codigo = ?";
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

    public List<Drone> findAll(){
        comando = "select * from DRONE";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando);
            ResultSet rs = ps.executeQuery()){

            List<Drone> drones = new ArrayList<>();

            while(rs.next()){
                drones.add(criarObjeto(rs));
            }
            return drones;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Drone save(Drone drone){
        try {
            findById(drone.getCodigo());
            return update(drone);
        } catch (NoSuchElementException e){
            return create(drone);
        }
    }

    public Drone create(Drone drone){
        comando = "insert into DRONE ( modelo, disponivel, bateria, localizacao, em_voo, carga_atual, capacidade_carga, permitir_carga_fragil) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, drone.getModelo());
            ps.setBoolean(2,drone.isDisponivel());
            ps.setInt(3, drone.getBateria());
            ps.setString(4, drone.getLocalizacao());
            ps.setBoolean(5, drone.isEmVoo());
            ps.setInt(6, drone.getCapacidadeCarga());
            ps.setBoolean(7, drone.isPermiteCargaFragil());

            ps.execute();

            try (ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    drone.setCodigo(rs.getInt(1));
                }
            }

            return drone;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Drone update(Drone drone){
        comando = "update DRONE set modelo = ?, disponivel = ?, bateria = ?, localizacao = ?, em_voo = ?, carga_atual  = ?, capacidade_carga  = ?, permitir_carga_fragil = ? where codigo = ?";

        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setString(1, drone.getModelo());
            ps.setBoolean(2,drone.isDisponivel());
            ps.setInt(3, drone.getBateria());
            ps.setString(4, drone.getLocalizacao());
            ps.setBoolean(5, drone.isEmVoo());
            ps.setInt(6, drone.getCapacidadeCarga());
            ps.setBoolean(7, drone.isPermiteCargaFragil());
            ps.setInt(8, drone.getCodigo());

            ps.execute();

            return drone;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void delete(int codigo){
        comando = "delete from DRONE where codigo = ?";
        try(Connection con = conexao.obterConexao();
            PreparedStatement ps = con.prepareStatement(comando)){

            ps.setInt(1,codigo);

            ps.execute();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
