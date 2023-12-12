package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendasPainel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabelaProdutos;
    private JLabel nomeProdutoLabel;
    private JLabel precoProdutoLabel;
    private JTextField codigoProdutoField;
    private JTextField quantidadeField;
    private JButton btnNovaVenda;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnFormaPagamento;
    private JButton btnDesconectar;

    public VendasPainel() {
        setLayout(new BorderLayout());

        // Componentes à esquerda
        JPanel leftPanel = new JPanel(new GridLayout(2, 2));
        nomeProdutoLabel = new JLabel("Nome do Produto:");
        precoProdutoLabel = new JLabel("Preço do Produto:");
        leftPanel.add(nomeProdutoLabel);
        leftPanel.add(precoProdutoLabel);
        add(leftPanel, BorderLayout.WEST);

        // Componentes no centro (tabela)
        tableModel = new DefaultTableModel();
        tabelaProdutos = new JTable(tableModel);
        add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);

        // Campos de entrada de dados
        JPanel inputPanel = new JPanel(new GridLayout(1, 1));
        codigoProdutoField = new JTextField();
        quantidadeField = new JTextField();
        
        inputPanel.add(new JLabel("Código do Produto:"));
        inputPanel.add(codigoProdutoField);
        inputPanel.add(new JLabel("Quantidade:"));
        inputPanel.add(quantidadeField);
        add(inputPanel, BorderLayout.SOUTH);

        // Componentes à direita
        JPanel rightPanel = new JPanel(new GridLayout(8, 1));
        btnNovaVenda = new JButton("Nova Venda");
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnFormaPagamento = new JButton("Forma de Pagamento");
        btnDesconectar = new JButton("Desconectar");
        rightPanel.add(btnNovaVenda);
        rightPanel.add(btnAdicionar);
        rightPanel.add(btnRemover);
        rightPanel.add(btnFormaPagamento);
        rightPanel.add(btnDesconectar);
        add(rightPanel, BorderLayout.EAST);

        // Configuração dos botões
        btnAdicionar.addActionListener(e -> adicionarProduto());
        btnRemover.addActionListener(e -> removerProduto());
        // Adicione as configurações dos outros botões conforme necessário
    }

    private void adicionarProduto() {
        // Lógica para adicionar produto
    }

    private void removerProduto() {
        // Lógica para remover produto
    }

    // Adicione métodos para as ações dos outros botões conforme necessário
}
