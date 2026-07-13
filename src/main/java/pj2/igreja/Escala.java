package pj2.igreja;

public class Escala {
    private String data;
    private String portaria;
    private String som;
    private String dirigente;
    private String culto;

    public Escala(String data, String portaria, String som, String dirigente, String culto) {
        this.data = data;
        this.portaria = portaria;
        this.som = som;
        this.dirigente = dirigente;
        this.culto = culto;
    }

    // Getters são ESSENCIAIS para o Spring mostrar no navegador
    public String getData() { return data; }
    public String getPortaria() { return portaria; }
    public String getSom() { return som; }
    public String getDirigente() { return dirigente; }
    public String getCulto() { return culto; }
}
