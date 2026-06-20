package pj2.igreja;

public class Escala {
    private String data;
    private String portaria;
    private String som;
    private String dirigente;

    public Escala(String data, String portaria, String som, String dirigente) {
        this.data = data;
        this.portaria = portaria;
        this.som = som;
        this.dirigente = dirigente;
    }

    // Getters são ESSENCIAIS para o Spring mostrar no navegador
    public String getData() { return data; }
    public String getPortaria() { return portaria; }
    public String getSom() { return som; }
    public String getDirigente() { return dirigente; }
    
}