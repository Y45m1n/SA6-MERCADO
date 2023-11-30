package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import java.awt.*;


public class ClientesPainel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabelaClientes;
    private JButton btnCadastrarCliente, btneditarCliente, btnApagarCliente;
    
    

    public ClientesPainel() {
        // Configuração da tabela de clientes
        String[] colunas = {"Nome", "CPF", "Telefone", "Endereço", "E-mail"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaClientes = new JTable(tableModel);

        // Configuração dos botões
        btnCadastrarCliente = new JButton("Cadastrar");
         btnCadastrarCliente.setBackground(Color.GREEN);
        btneditarCliente = new JButton("Editar");
        btneditarCliente.setBackground(Color.YELLOW);
        btnApagarCliente = new JButton("Apagar");
        btnApagarCliente.setBackground(Color.RED);


        // Adicionando componentes ao painel
        add(new JScrollPane(tabelaClientes));
        add(btnCadastrarCliente);
        add(btneditarCliente);
         add(btnApagarCliente);

        // Configurando ação para o botão Cadastrar Novo Cliente
        btnCadastrarCliente.addActionListener(e -> cadastrarNovoCliente());
        btneditarCliente.addActionListener(e -> editarNovoCliente());

    }
  
    private void cadastrarNovoCliente() {
        // Solicita ao usuário os dados do novo cliente
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");
        if (nomeCliente != null && !nomeCliente.isEmpty()) {
            String cpfCliente = JOptionPane.showInputDialog("Digite o CPF do cliente:");
            if (cpfCliente != null && !cpfCliente.isEmpty()) {
                 String telCliente = JOptionPane.showInputDialog("Digite o telefone do cliente:");
            if (telCliente != null && !telCliente.isEmpty()) {
                     String enderecoCliente = JOptionPane.showInputDialog("Digite o endereço do cliente:");
            if (enderecoCliente != null && !enderecoCliente.isEmpty()) {
                  String emailCliente = JOptionPane.showInputDialog("Digite o email do cliente:");
            if (emailCliente != null && !emailCliente.isEmpty()) {
                // Adiciona o novo cliente à tabela de clientes
                Object[] linha = {nomeCliente, cpfCliente, telCliente,enderecoCliente,emailCliente};
                tableModel.addRow(linha);

                // Exibe uma mensagem indicando que o novo cliente foi cadastrado
                JOptionPane.showMessageDialog(this, nomeCliente + " foi cadastrado com sucesso!" + "\n" + "Agora é um Cliente VIP");
            }
        }
    }
}
        }
    }

    private void editarNovoCliente() {
        int rowIndex = tabelaClientes.getSelectedRow();
    
        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
            return;
        }
    
        tabelaClientes.editCellAt(rowIndex, 0);
        tabelaClientes.getEditorComponent().requestFocus();
    }

    public String obterNome() {
        return null;
    }

    public String obterCPF() {
        return null;
    }

    public String obterTelefone() {
        return null;
    }

    public String obterEndereco() {
        return null;
    }

    public String obterEmail() {
        return null;
    }
    
    // // Método para finalizar a edição e atualizar os dados ao pressionar o botão "Editar"
    // private void finalizaEdicao() {
    //     int rowIndex = tabelaClientes.getSelectedRow();
    
    //     if (rowIndex == -1) {
    //         JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
    //         return;
    //     }
    
    //     tabelaClientes.getCellEditor().stopCellEditing();
    
    //     // Pode ser necessário ajustar a lógica abaixo dependendo dos nomes das colunas na sua tabela
    //     String novoNome = (String) tabelaClientes.getValueAt(rowIndex, 0);
    //     String novoCpf = (String) tabelaClientes.getValueAt(rowIndex, 1);
    //     String novoTel = (String) tabelaClientes.getValueAt(rowIndex, 2);
    //     String novoEndereco = (String) tabelaClientes.getValueAt(rowIndex, 3);
    //     String novoEmail = (String) tabelaClientes.getValueAt(rowIndex, 4);
    
    //     // Adicione aqui a lógica para processar os novos valores
    //     // Exemplo: operacoes.atualizar(novoNome, novoCpf, novoTel, novoEmail, novoEndereco);
    
    //     // Exibe mensagem de sucesso (opcional)
    //     JOptionPane.showMessageDialog(this, "Cliente editado com sucesso!");
    
    //     // Remove a seleção da tabela
    //     tabelaClientes.clearSelection();
    // }
    
  

    

 
}