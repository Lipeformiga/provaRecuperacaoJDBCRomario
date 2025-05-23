package br.senai.sc.drones;

public class Entrega {


    private int codigo;
    private Drone drone;
    private Pacote pacote;
    private String origem;
    private String destino;
    private String status;
    private int distancia;
    private int tempoEstimado;

    private Cliente cliente;

    public Entrega() {
    }

    public Entrega(Drone drone, Pacote pacote, String origem, String destino, String status, int distancia, int tempoEstimado, Cliente cliente) {
        this.drone = drone;
        this.pacote = pacote;
        this.origem = origem;
        this.destino = destino;
        this.status = status;
        this.distancia = distancia;
        this.tempoEstimado = tempoEstimado;
        this.cliente = cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Pacote getPacote() {
        return pacote;
    }

    public void setPacote(Pacote pacote) {
        this.pacote = pacote;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(int tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "codigo=" + codigo +
                ", drone=" + drone +
                ", pacote=" + pacote +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", status='" + status + '\'' +
                ", distancia=" + distancia +
                ", tempoEstimado=" + tempoEstimado +
                '}';
    }
}
