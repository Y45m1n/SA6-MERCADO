// ClientesPainel.java
package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientesPainel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabelaClientes;
    private JButton btnCadastrarCliente;
    private JButton btnVerificarVIP;

    public ClientesPainel() {
        // Configuração da tabela de clientes
        String[] colunas = {"Nome", "CPF", "VIP"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaClientes = new JTable(tableModel);

        // Configuração dos botões
        btnCadastrarCliente = new JButton("Cadastrar Novo Cliente");
        btnVerificarVIP = new JButton("Verificar se é VIP");

        // Adicionando componentes ao painel
        add(new JScrollPane(tabelaClientes));
        add(btnCadastrarCliente);
        add(btnVerificarVIP);

        // Configurando ação para o botão Cadastrar Novo Cliente
        btnCadastrarCliente.addActionListener(e -> cadastrarNovoCliente());

        // Configurando ação para o botão Verificar se é VIP
        btnVerificarVIP.addActionListener(e -> verificarClienteVIP());
    }

    private void cadastrarNovoCliente() {
        // Solicita ao usuário os dados do novo cliente
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do novo cliente:");
        if (nomeCliente != null && !nomeCliente.isEmpty()) {
            String cpfCliente = JOptionPane.showInputDialog("Digite o CPF do novo cliente:");
            if (cpfCliente != null && !cpfCliente.isEmpty()) {
                // Adiciona o novo cliente à tabela de clientes
                Object[] linha = {nomeCliente, cpfCliente, "Não"};
                tableModel.addRow(linha);

                // Exibe uma mensagem indicando que o novo cliente foi cadastrado
                JOptionPane.showMessageDialog(this, "Novo cliente cadastrado: " + nomeCliente);
            }
        }
    }

    private void verificarClienteVIP() {
        // Obtém a linha selecionada na tabela de clientes
        int linhaSelecionada = tabelaClientes.getSelectedRow();
        if (linhaSelecionada != -1) {
            // Obtém o nome do cliente na linha selecionada
            String nomeCliente = (String) tableModel.getValueAt(linhaSelecionada, 0);

            // Simula a verificação se o cliente é VIP
            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "O cliente " + nomeCliente + " é VIP?",
                    "Verificação VIP",
                    JOptionPane.YES_NO_OPTION
            );

            // Atualiza o status VIP na tabela de clientes
            if (confirmacao == JOptionPane.YES_OPTION) {
                tableModel.setValueAt("Sim", linhaSelecionada, 2);
            } else {
                tableModel.setValueAt("Não", linhaSelecionada, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para verificar o status VIP.");
        }
    }
}
