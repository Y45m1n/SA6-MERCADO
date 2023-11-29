package Model;

public class Produto {
    // atributos
    private String Nome;
    private String Codigo;
    private String Quantidade;
    private String Preco;

    // construtor
    public Produto(String nome, String codigo, String quantidade, String preco) {
        Nome = nome;
        Codigo = codigo;
        Quantidade = quantidade;
        Preco = preco;
    }

    // getters and setters
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(String quantidade) {
        Quantidade = quantidade;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String preco) {
        Preco = preco;
    }

}
