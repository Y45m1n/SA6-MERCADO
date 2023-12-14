// Importação de bibliotecas necessárias para a implementação do controle
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Importação de classes específicas do projeto relacionadas ao banco de dados, controle e modelo
import sistemasupermercado.Gerenciamento.Connection.ClientesDAO;
import sistemasupermercado.Gerenciamento.Model.Cliente;

// Classe que representa o controle para a funcionalidade de Clientes em uma interface gráfica
public class ClientesControl {

    // Declaração de atributos
    private List<Cliente> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor da classe
    public ClientesControl(List<Cliente> clientes, DefaultTableModel tableModel, JTable table) {
        // Inicialização dos atributos com os parâmetros fornecidos
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela com os dados do banco de dados
    public void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientes = new ClientesDAO().listarTodos(); // Obtém os clientes atualizados do banco de dados

        // Adiciona os dados de cada cliente como uma nova linha na tabela Swing
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getCpf(), cliente.getNome(), cliente.getIdade() });
        }
    }

    // Método para cadastrar um novo cliente
    public void cadastrarCliente(String cpf, String nomeCompleto, String idade) {
        try {
            // Verifica se o CPF e a idade fornecidos são válidos
            if (validaCpf(cpf) && validaIdade(idade)) {
                // Cria um objeto Cliente com os dados informados
                Cliente cliente = new Cliente(cpf.trim().toUpperCase(), nomeCompleto.trim().toUpperCase(),
                        idade.trim().toUpperCase());
                
                // Adiciona o cliente à lista local
                clientes.add(cliente);

                // Cadastra o cliente no banco de dados
                new ClientesDAO().cadastrar(cpf, nomeCompleto, idade);

                // Atualiza a tabela
                atualizarTabela();

                // Exibe mensagem de sucesso
                exibirMensagem("Cliente cadastrado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Exibe mensagem de alerta se os dados fornecidos não forem válidos
                exibirMensagem("Verifique se os dados escritos estão corretos e tente novamente!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            // Exibe mensagem de erro em caso de falha no cadastro do cliente
            exibirMensagem("Erro ao cadastrar cliente. Verifique os dados e tente novamente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para apagar um cliente
    public void apagar(String cpf) {
        // Remove o cliente do banco de dados
        new ClientesDAO().apagar(cpf);

        // Atualiza a tabela
        atualizarTabela();

        // Exibe mensagem informando que o cliente foi removido
        exibirMensagem("Cliente removido!", JOptionPane.ERROR_MESSAGE);
    }

    // Método para atualizar as informações de um cliente
    public void atualizar(String cpf, String nomeCompleto, String idade) {
        try {
            // Verifica se o CPF e a idade fornecidos são válidos
            if (validaCpf(cpf) && validaIdade(idade)) {
                // Atualiza as informações do cliente no banco de dados
                new ClientesDAO().atualizar(cpf, nomeCompleto, idade);

                // Exibe mensagem de sucesso
                exibirMensagem("Cliente atualizado", JOptionPane.INFORMATION_MESSAGE);

                // Atualiza a tabela
                atualizarTabela();
            } else {
                // Exibe mensagem de alerta se os dados fornecidos não forem válidos
                exibirMensagem("Verifique se os dados escritos estão corretos e tente novamente!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception err) {
            // Exibe mensagem de erro em caso de falha na atualização do cliente
            exibirMensagem("Erro ao atualizar cliente. Verifique os dados e tente novamente.", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para validar o formato e o valor do CPF
    public boolean validaCpf(String cpf) {
        return cpf.matches("[0-9]+") && cpf.length() == 11;
    }

    // Método para validar o formato e o valor da idade
    public boolean validaIdade(String idade) {
        return idade.matches("[0-9]+") && Integer.parseInt(idade) > 0;
    }

    // Método privado para exibir mensagens de acordo com o tipo (information, warning, error)
    private void exibirMensagem(String mensagem, int messageType) {
        JOptionPane.showMessageDialog(null, mensagem, null, messageType);
    }
}
