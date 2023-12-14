package mercadojava.demo.Control;

// Importação de bibliotecas necessárias para a implementação do controle
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mercadojava.demo.Connection.VendasDAO;
import mercadojava.demo.Model.Venda;

// Classe que representa o controle para a funcionalidade de Vendas em uma interface gráfica
public class VendasControl {

    // Declaração de atributos
    private List<Venda> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor da classe
    public VendasControl(List<Venda> vendas, DefaultTableModel tableModel, JTable table) {
        // Inicialização dos atributos com os parâmetros fornecidos
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados do banco de dados
    public void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarTodos(); // Obtém as vendas atualizadas do banco de dados

        // Adiciona os dados de cada venda como uma nova linha na tabela Swing
        for (Venda venda : vendas) {
            tableModel.addRow(new Object[] {
                    venda.getId(),
                    venda.getCliente(),
                    venda.getQuantidadeDeProdutos(),
                    venda.getValor(),
                    venda.getData()
            });
        }
    }

    // Método para realizar uma venda
    public void realizarVenda(int id, String cliente, String quantidadeDeProdutos, String valor, String data) {
        try {
            // Cria um objeto Venda com os dados informados
            Venda venda = new Venda(id, cliente.trim().toUpperCase(), valor.trim(), data.trim(), quantidadeDeProdutos.trim());

            // Adiciona a venda à lista local
            vendas.add(venda);

            // Cadastra a venda no banco de dados
            new VendasDAO().cadastrar(cliente.trim().toUpperCase(), valor.trim(), data.trim(), quantidadeDeProdutos.trim());

            // Atualiza a tabela
            atualizarTabela();

            // Exibe mensagem de sucesso
            JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
        } catch (Exception err) {
            // Exibe mensagem de erro em caso de falha na realização da venda
            System.out.println(err.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Verifique se os dados escritos estão corretos e tente novamente!", "ERRO!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
