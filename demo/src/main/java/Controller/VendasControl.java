// package Controller;

// import Model.Produto;
// import Model.Venda;
// import DAO.ProdutoDAO;
// import DAO.VendaDAO;
// import View.VendasPainel;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;

// public class VendasControl {
//     private VendasPainel view; // Referência à interface gráfica

//     private ProdutoDAO produtoDAO;
//     private VendaDAO vendaDAO;

//     public VendasControl(VendasPainel view, ProdutoDAO produtoDAO, VendaDAO vendaDAO) {
//         this.view = view;
//         this.produtoDAO = produtoDAO;
//         this.vendaDAO = vendaDAO;
//         this.vendaAtual = new Venda(1, new Date(), null, new ArrayList<>(), 0.0); // Inicializa uma nova venda
//     }

//     // Método chamado quando o botão Adicionar é clicado na interface
//     public void adicionarProduto() {
//         String codigoBarras = view.obterCodigoBarras();

//         // Busca o produto no banco de dados
//         Produto produto = produtoDAO.buscarPorCodigoBarras(codigoBarras);

//         if (produto != null) {
//             vendaAtual.getProdutos().add(produto);
//             atualizarInterface();
//         } else {
//             view.exibirMensagemErro("Produto não encontrado.");
//         }
//     }

//     // Método auxiliar para atualizar a interface com os produtos da venda
//     private void atualizarInterface() {
//         view.exibirProdutosVenda(vendaAtual.getProdutos());
//         view.exibirTotalVenda(calcularTotalVenda());
//     }

//     // Método auxiliar para calcular o total da venda
//     private double calcularTotalVenda() {
//         double total = 0.0;
//         for (Produto produto : vendaAtual.getProdutos()) {
//             total += produto.getPreco();
//         }
//         return total;
//     }

//     // Método chamado quando o botão Concluir Compra é clicado na interface
//     public void concluirCompra() {
//         vendaAtual.setData(new Date());
//         vendaAtual.setTotal(calcularTotalVenda());

//         // Registra a venda no banco de dados
//         vendaDAO.registrarVenda(vendaAtual);

//         // Atualiza a interface e limpa a venda atual
//         atualizarInterface();
//         vendaAtual = new Venda(1, new Date(), null, new ArrayList<>(), 0.0);
//     }
// }