package Controller;

import Model.Produto;
import DAO.EstoqueDAO;
import View.EstoquePainel;

public class EstoqueControl {
    private EstoquePainel view; // Referência à interface gráfica
    private EstoqueDAO estoqueDAO;

    public EstoqueControl(EstoquePainel view, EstoqueDAO estoqueDAO) {
        this.view = view;
        this.estoqueDAO = estoqueDAO;
    }

    // Método chamado quando o botão Atualizar Quantidade é clicado na interface
    public void atualizarQuantidade() {
        String codigoBarras = view.obterCodigoBarras();
        int novaQuantidade = view.obterNovaQuantidade();

        // Atualiza a quantidade em estoque no banco de dados
        estoqueDAO.atualizarQuantidadeEmEstoque(codigoBarras, novaQuantidade);

        // Atualiza a interface
        view.exibirMensagem("Quantidade atualizada com sucesso!");
    }

    // Método chamado quando o botão Adicionar Novo Produto é clicado na interface
    public void adicionarNovoProduto() {
        String nome = view.obterNomeProduto();
        String codigoBarras = view.obterCodigoBarras();
        double preco = view.obterPrecoProduto();
        int quantidade = view.obterQuantidadeProduto();

        // Cria um novo produto
        Produto novoProduto = new Produto(nome, codigoBarras, preco, quantidade);

        // Adiciona o novo produto ao banco de dados
        estoqueDAO.adicionarNovoProduto(novoProduto);

        // Atualiza a interface
        view.exibirMensagem("Novo produto adicionado com sucesso!");
    }
}