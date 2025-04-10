package br.senai.sc.drones;

import java.util.List;

public class GerenciadorOperacoes {

    DroneDAO droneDAO = new DroneDAO();
    PacoteDAO pacoteDAO = new PacoteDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    EntregaDAO entregaDAO = new EntregaDAO();



    // -------------------------------- DRONE ---------------------------------//
    public Drone saveDrone(Drone drone){
        return droneDAO.save(drone);
    }
    public Drone findDroneById(int id){
        return droneDAO.findById(id);
    }
    public List<Drone> findAllDrones(){
        return droneDAO.findAll();
    }
    public void deleteDrone(int id){
        droneDAO.delete(id);
    }

    // -------------------------------- PACOTE ---------------------------------//

    public Pacote savePacote(Pacote pacote){
        return pacoteDAO.save(pacote);
    }

    public Pacote findPacoteById(int id){
        return pacoteDAO.findById(id);
    }
    public List<Pacote> findAllPacotes(){
        return pacoteDAO.findAll();
    }
    public void deletePacote(int id){
        pacoteDAO.delete(id);
    }

    // -------------------------------- CLIENTE ---------------------------------//

    public Cliente saveCliente(Cliente cliente){
        return clienteDAO.save(cliente);
    }

    public Cliente findClienteById(int id){
        return clienteDAO.findById(id);
    }
    public List<Cliente> findAllClientes(){
        return clienteDAO.findAll();
    }
    public void deleteCliente(int id){
        clienteDAO.delete(id);
    }

    // -------------------------------- ENTREGA ---------------------------------//

    public Entrega saveEntrega(Entrega entrega){
        return entregaDAO.save(entrega);
    }

    public Entrega findEntregaById(int id){
        return entregaDAO.findById(id);
    }
    public List<Entrega> findAllEntrega(){
        return entregaDAO.findAll();
    }
    public void deleteEntrega(int id){
        entregaDAO.delete(id);
    }

}
