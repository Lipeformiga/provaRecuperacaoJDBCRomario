package br.senai.sc.drones;

public class Drone {

    private int codigo ;
    private String modelo;
    private boolean disponivel;
    private int bateria;
    private String localizacao;
    private boolean emVoo;
    private int cargaAtual;
    private int capacidadeCarga;
    private boolean permiteCargaFragil;

    public Drone() {
    }

    public Drone(int codigo, String modelo, boolean disponivel, int bateria, String localizacao, boolean emVoo, int cargaAtual, int capacidadeCarga, boolean permiteCargaFragil) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.disponivel = disponivel;
        this.bateria = bateria;
        this.localizacao = localizacao;
        this.emVoo = emVoo;
        this.cargaAtual = cargaAtual;
        this.capacidadeCarga = capacidadeCarga;
        this.permiteCargaFragil = permiteCargaFragil;
    }

    public Drone(String modelo, boolean disponivel, int bateria, String localizacao, boolean emVoo, int cargaAtual, int capacidadeCarga, boolean permiteCargaFragil) {
        this.modelo = modelo;
        this.disponivel = disponivel;
        this.bateria = bateria;
        this.localizacao = localizacao;
        this.emVoo = emVoo;
        this.cargaAtual = cargaAtual;
        this.capacidadeCarga = capacidadeCarga;
        this.permiteCargaFragil = permiteCargaFragil;
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isEmVoo() {
        return emVoo;
    }

    public void setEmVoo(boolean emVoo) {
        this.emVoo = emVoo;
    }

    public int getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(int cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }

    public boolean isPermiteCargaFragil() {
        return permiteCargaFragil;
    }

    public void setPermiteCargaFragil(boolean permiteCargaFragil) {
        this.permiteCargaFragil = permiteCargaFragil;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "codigo=" + codigo +
                ", modelo='" + modelo + '\'' +
                ", disponivel=" + disponivel +
                ", bateria=" + bateria +
                ", localizacao='" + localizacao + '\'' +
                ", emVoo=" + emVoo +
                ", cargaAtual=" + cargaAtual +
                ", capacidadeCarga=" + capacidadeCarga +
                ", permiteCargaFragil=" + permiteCargaFragil +
                '}';
    }
}
