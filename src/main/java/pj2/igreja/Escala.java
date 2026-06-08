package pj2.igreja;

public class Escala {
    private String data;
    private String setor;
    private String responsavel;

    public Escala(String data, String setor, String responsavel) {
        this.data = data;
        this.setor = setor;
        this.responsavel = responsavel;
    }

    // Getters são ESSENCIAIS para o Spring mostrar no navegador
    public String getData() { return data; }
    public String getSetor() { return setor; }
    public String getResponsavel() { return responsavel; }
}