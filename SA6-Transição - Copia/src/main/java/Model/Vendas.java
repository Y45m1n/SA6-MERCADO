package Model;

public class Vendas {
    //Atributos 
    private String nome;
    private String precoUni;
    private String codigo;
     private String quantidade;
    private String precoTotal;
   
   
   

    //métodos
     public Vendas(String nome, String precoUni, String codigo, String quantidade, String precoTotal) {
        this.nome = nome;
        this.precoUni = precoUni;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
    }




    public String getNome() {
        return nome;
    }




    public void setNome(String nome) {
        this.nome = nome;
    }




    public String getPrecoUni() {
        return precoUni;
    }




    public void setPrecoUni(String precoUni) {
        this.precoUni = precoUni;
    }




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




    public String getPrecoTotal() {
        return precoTotal;
    }




    public void setPrecoTotal(String precoTotal) {
        this.precoTotal = precoTotal;
    }




    public void add(Vendas vendas) {
    }



    



    

  

    
}
