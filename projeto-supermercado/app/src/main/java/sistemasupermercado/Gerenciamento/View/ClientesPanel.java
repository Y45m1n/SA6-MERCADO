package sistemasupermercado.Gerenciamento.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import sistemasupermercado.Gerenciamento.Connection.ClientesDAO;
import sistemasupermercado.Gerenciamento.Control.ClientesControl;
import sistemasupermercado.Gerenciamento.Model.Cliente;

public class ClientesPanel extends JPanel {

    private JButton cadastraCliente, editaCliente, apagaCliente;
    private JTextField inputCpf, inputNome, inputIdade;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Cliente> clientes;
    private int linhaSelecionada = -1;
    private JScrollPane scrollPane;

    public ClientesPanel() {
        super();

        setLayout(new BorderLayout());

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputCpf = new JTextField(7);
        inputNome = new JTextField(4);
        inputIdade = new JTextField(12);

        inputPanel.add(new JLabel("Digite o CPF do cliente:"));
        inputPanel.add(inputCpf);
        inputPanel.add(new JLabel("Digite o nome completo do cliente:"));
        inputPanel.add(inputNome);
        inputPanel.add(new JLabel("Digite a idade do cliente:"));
        inputPanel.add(inputIdade);

        // Panel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cadastraCliente = new JButton("Cadastrar");
        editaCliente = new JButton("Editar");
        apagaCliente = new JButton("Excluir");

        buttonPanel.add(cadastraCliente);
        buttonPanel.add(editaCliente);
        buttonPanel.add(apagaCliente);

        // Tabela
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"CPF", "Nome Completo", "Idade"});
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        // Adicionando os componentes ao painel principal
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Estilização dos botões
        apagaCliente.setBackground(new Color(168, 3, 3));
        apagaCliente.setForeground(new Color(255, 255, 255));
        cadastraCliente.setBackground(new Color(46, 128, 32));
        cadastraCliente.setForeground(new Color(255, 255, 255));
        editaCliente.setBackground(new Color(109, 110, 109));
        editaCliente.setForeground(new Color(255, 255, 255));

        // Tratamento de eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                linhaSelecionada = table.rowAtPoint(e.getPoint());
                if (linhaSelecionada != -1) {
                    inputCpf.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputNome.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputIdade.setText((String) table.getValueAt(linhaSelecionada, 2));
                }
            }
        });

        ClientesControl control = new ClientesControl(clientes, tableModel, table);

        // Cadastrar um cliente
        cadastraCliente.addActionListener(e -> {
            if (!inputCpf.getText().isEmpty() && !inputNome.getText().isEmpty()
                    && !inputIdade.getText().isEmpty()) {

                control.cadastrarCliente(inputCpf.getText(), inputNome.getText(), inputIdade.getText());
                // Limpa os campos de entrada após a operação de cadastro
                inputCpf.setText("");
                inputNome.setText("");
                inputIdade.setText("");
            } else {
                JOptionPane.showMessageDialog(inputPanel,
                        "Preencha os campos corretamente para cadastrar um cliente!!", null,
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // Editar um cliente
        editaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Deseja atualizar as informações deste cliente?",
                        "Editar", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    control.atualizar(inputCpf.getText(), inputNome.getText(), inputIdade.getText());
                    // Limpa os campos de entrada após a operação de atualização
                }
            }
        });

        // Apagar um cliente
        apagaCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

        // Atualiza a tabela
        atualizarTabela();
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{cliente.getCpf(), cliente.getNome(), cliente.getIdade()});
        }
    }
}
