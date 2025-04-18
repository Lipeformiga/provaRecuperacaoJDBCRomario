package br.senai.sc.drones;

public class Pacote {

    private int codigo;
    private String descricao;
    private int peso;
    private String destino;
    private boolean fragil;

    public Pacote() {
    }

    public Pacote(String descricao, int peso, String destino, boolean fragil) {
        this.descricao = descricao;
        this.peso = peso;
        this.destino = destino;
        this.fragil = fragil;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    @Override
    public String toString() {
        return "Pacote{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", peso=" + peso +
                ", destino='" + destino + '\'' +
                ", fragil=" + fragil +
                '}';
    }
}