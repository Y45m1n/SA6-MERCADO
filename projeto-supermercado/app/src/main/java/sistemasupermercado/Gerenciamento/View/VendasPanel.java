package sistemasupermercado.Gerenciamento.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import sistemasupermercado.Gerenciamento.Connection.VendasDAO;
import sistemasupermercado.Gerenciamento.Model.Venda;
import sistemasupermercado.Gerenciamento.Control.VendasControl;

public class VendasPanel extends JPanel {

    private JButton realizaVenda, cancelaVenda;
    private JTextField inputProduto, inputQuantidade, inputTotal, inputCliente, inputData;
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Venda> vendas = new ArrayList<>();
    private JScrollPane scrollPane;

    public VendasPanel() {
        super();

        setLayout(new BorderLayout());
      

        // Panel de entrada de dados
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Produto:"));
        inputPanel.add(inputProduto = new JTextField(10));

        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(inputQuantidade = new JTextField(10));

        inputPanel.add(new JLabel("Total:"));
        inputPanel.add(inputTotal = new JTextField(10));

        inputPanel.add(new JLabel("Cliente:"));
        inputPanel.add(inputCliente = new JTextField(10));

        inputPanel.add(new JLabel("Data:"));
        inputPanel.add(inputData = new JTextField(10));

        // Panel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        realizaVenda = new JButton("Realizar Venda");
        cancelaVenda = new JButton("Cancelar Venda");
        buttonPanel.add(realizaVenda);
        buttonPanel.add(cancelaVenda);

        // Tabela
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"id", "Cliente (CPF)", "Total", "Quantidade", "Data"});
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        // Adicionando os componentes ao painel principal
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        new VendasDAO().criaTabela();
        atualizarTabela();
       
    }

    public void atualizarTabela() {
        tableModel.setRowCount(0);
        vendas = new VendasDAO().listarTodos();
        for (Venda venda : vendas) {
            tableModel.addRow(new Object[]{venda.getId(), venda.getCliente(), venda.getValor(),
                    venda.getQuantidadeDeProdutos(), venda.getData()});
        }
    }
}
