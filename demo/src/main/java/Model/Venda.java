package Model;

public class Venda {
    // atributos
    private String codigo;
    private String quantidade;

    //construtor
    public Venda(String codigo, String quantidade) {
        this.codigo = codigo;
        this.quantidade = quantidade;
    }
    //getters and setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
    

}