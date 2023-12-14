// Importação de bibliotecas necessárias para a construção da interface gráfica e manipulação de eventos
package sistemasupermercado.Gerenciamento.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

// Importação de classes específicas do projeto relacionadas ao banco de dados, controle e modelo
import sistemasupermercado.Gerenciamento.Connection.ClientesDAO;
import sistemasupermercado.Gerenciamento.Control.ClientesControl;
import sistemasupermercado.Gerenciamento.Model.Cliente;

// Classe que representa um painel para exibição e gerenciamento de clientes em uma interface gráfica
public class ClientesPanel extends JPanel {

    // Declaração de componentes
    private JButton cadastraCliente, editaCliente, apagaCliente;
    private JTextField inputCpf, inputNome, inputIdade;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Cliente> clientes;
    private int linhaSelecionada = -1;
    private JScrollPane scrollPane;

    // Construtor da classe
    public ClientesPanel() {
        super();  // Chama o construtor da superclasse JPanel

        setLayout(new BorderLayout());  // Define o layout do painel como BorderLayout

        // Painel de entrada para dados do cliente
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicialização dos campos de entrada
        inputCpf = new JTextField(7);
        inputNome = new JTextField(4);
        inputIdade = new JTextField(12);

        // Adição dos campos e rótulos ao painel de entrada
        inputPanel.add(new JLabel("Digite o CPF do cliente:"));
        inputPanel.add(inputCpf);
        inputPanel.add(new JLabel("Digite o nome completo do cliente:"));
        inputPanel.add(inputNome);
        inputPanel.add(new JLabel("Digite a idade do cliente:"));
        inputPanel.add(inputIdade);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // Inicialização dos botões
        editaCliente = new JButton("Editar");
        apagaCliente = new JButton("Excluir");

        // Adição dos botões ao painel
        buttonPanel.add(editaCliente);
        buttonPanel.add(apagaCliente);

        // Configuração da tabela
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"CPF", "Nome Completo", "Idade"});
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        // Adição dos componentes ao painel principal
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Estilização dos botões
        apagaCliente.setBackground(Color.red);
        editaCliente.setBackground(Color.yellow);

        // Tratamento de eventos do mouse na tabela
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // Atualiza os campos de entrada com os dados da linha selecionada na tabela
                linhaSelecionada = table.rowAtPoint(e.getPoint());
                if (linhaSelecionada != -1) {
                    inputCpf.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputNome.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputIdade.setText((String) table.getValueAt(linhaSelecionada, 2));
                }
            }
        });

        // Criação do controle para manipulação dos dados
        ClientesControl control = new ClientesControl(clientes, tableModel, table);

        // Editar um cliente
        editaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmação antes de atualizar os dados do cliente
                int res = JOptionPane.showConfirmDialog(null, "Deseja atualizar as informações deste cliente?",
                        "Editar", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    control.atualizar(inputCpf.getText(), inputNome.getText(), inputIdade.getText());
                    // Limpa os campos de entrada após a operação de atualização
                }
                // Atualiza a tabela após a operação de edição
                atualizarTabela();
            }
        });

        // Apagar um cliente
        apagaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmação antes de excluir o cliente
                int res = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?",
                        "Excluir", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    control.apagar(inputCpf.getText());
                    // Limpa os campos de entrada após a operação de exclusão
                    inputCpf.setText("");
                    inputNome.setText("");
                    inputIdade.setText("");
                }
            }
        });

        // Atualiza a tabela com os dados do banco de dados
        atualizarTabela();
    }

    // Método para atualizar os dados na tabela da interface gráfica
    public void atualizarTabela() {
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{cliente.getCpf(), cliente.getNome(), cliente.getIdade()});
        }
    }
}
