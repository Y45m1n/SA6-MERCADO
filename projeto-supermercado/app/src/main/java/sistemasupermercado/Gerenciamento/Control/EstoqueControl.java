// Importação de bibliotecas necessárias para a implementação do controle
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Importação de classes específicas do projeto relacionadas ao banco de dados, controle e modelo
import sistemasupermercado.Gerenciamento.Connection.EstoqueDAO;
import sistemasupermercado.Gerenciamento.Model.Estoque;

// Classe que representa o controle para a funcionalidade de Estoque em uma interface gráfica
public class EstoqueControl {
    
    // Declaração de atributos
    private List<Estoque> produtos;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor da classe
    public EstoqueControl(List<Estoque> produtos, DefaultTableModel tableModel, JTable table) {
        // Inicialização dos atributos com os parâmetros fornecidos
        this.produtos = produtos;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados do banco de dados
    public void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        produtos = new EstoqueDAO().listarTodos(); // Obtém os produtos atualizados do banco de dados

        // Adiciona os dados de cada produto como uma nova linha na tabela Swing
        for (Estoque produto : produtos) {
            tableModel.addRow(new Object[] { 
                produto.getId(), 
                produto.getNomeDoProduto(), 
                produto.getPreco(),
                produto.getQuantidade() 
            });
        }
    }

    // Método para cadastrar um novo produto no estoque
    public void cadastrarProduto(int id, String nomeDoProduto, String preco, String quantidade) {
        try {
            // Verifica se o preço e a quantidade fornecidos são válidos
            if (validaPreco(preco) && validaQuantidade(quantidade)) {
                // Cria um objeto Estoque com os dados informados
                Estoque produto = new Estoque(id, nomeDoProduto.trim().toUpperCase(), preco.trim().toUpperCase(),
                        String.valueOf(quantidade));
                
                // Adiciona o produto à lista local
                produtos.add(produto);

                // Cadastra o produto no banco de dados
                new EstoqueDAO().cadastrar(id, nomeDoProduto, preco, quantidade);

                // Atualiza a tabela
                atualizarTabela();

                // Exibe mensagem de sucesso
                exibirMensagem("Produto cadastrado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe mensagem de alerta se os dados fornecidos não forem válidos
                exibirMensagem("Verifique se os dados escritos estão corretos e tente novamente!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            // Exibe mensagem de erro em caso de falha no cadastro
            exibirMensagem("Erro ao cadastrar produto. Verifique os dados e tente novamente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para apagar um produto do estoque
    public void apagar(int id) {
        // Remove o produto do banco de dados
        new EstoqueDAO().apagar(id);

        // Atualiza a tabela
        atualizarTabela();

        // Exibe mensagem informando que o produto foi removido
        exibirMensagem("Produto removido!", JOptionPane.ERROR_MESSAGE);
    }

    // Método para atualizar as informações de um produto no estoque
    public void atualizar(int id, String nomeDoProduto, String preco, String quantidade) {
        try {
            // Verifica se o preço e a quantidade fornecidos são válidos
            if (validaPreco(preco) && validaQuantidade(quantidade)) {
                // Atualiza as informações do produto no banco de dados
                new EstoqueDAO().atualizar(id, nomeDoProduto, preco, quantidade);

                // Exibe mensagem de sucesso
                exibirMensagem("Produto atualizado", JOptionPane.INFORMATION_MESSAGE);

                // Atualiza a tabela
                atualizarTabela();
            } else {
                // Exibe mensagem de alerta se os dados fornecidos não forem válidos
                exibirMensagem("Verifique se os dados escritos estão corretos e tente novamente!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            // Exibe mensagem de erro em caso de falha na atualização do produto
            exibirMensagem("Erro ao atualizar produto. Verifique os dados e tente novamente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para validar o formato do preço
    public boolean validaPreco(String preco) {
        return preco.matches("[0-9]+([.][0-9]{1,2})?");
    }

    // Método para validar o formato e o valor da quantidade
    public boolean validaQuantidade(String quantidade) {
        return quantidade.matches("[0-9]+") && Integer.parseInt(quantidade) > 0;
    }

    // Método privado para exibir mensagens de acordo com o tipo (information, warning, error)
    private void exibirMensagem(String mensagem, int messageType) {
        JOptionPane.showMessageDialog(null, mensagem, null, messageType);
    }
}
