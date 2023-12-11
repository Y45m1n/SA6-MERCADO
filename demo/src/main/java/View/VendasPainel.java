package View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendasPainel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabelaProdutos;
    private JTextField nomeProdutoField;
    private JTextField precoProdutoField;
    private JTextField codigoProdutoField;
    private JTextField quantidadeField;
    private JButton btnNovaVenda;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnFormaPagamento;
    private JButton btnDesconectar;

    public VendasPainel() {

        JPanel painelGeral = new JPanel(new BorderLayout());
        JPanel painelLista = new JPanel(new GridLayout(1, 1));
        painelLista.setBorder(BorderFactory.createTitledBorder("Produtos"));
        painelLista.add(new JScrollPane());
        painelGeral.add(painelLista, BorderLayout.CENTER);
        add(painelGeral);

         btnNovaVenda = new JButton("Nova Venda");
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnFormaPagamento = new JButton("Forma de Pagamento");
        btnDesconectar = new JButton("Desconectar");
        JPanel rightPanel = new JPanel(new GridLayout(1, 4));
        rightPanel.add(btnNovaVenda);
        rightPanel.add(btnAdicionar);
        rightPanel.add(btnRemover);
        rightPanel.add(btnFormaPagamento);
        rightPanel.add(btnDesconectar);
        add(rightPanel, BorderLayout.SOUTH);


        JTextField nomeProduto = new JTextField(15);
        JTextField valorProduto = new JTextField(15);
        JLabel nome = new JLabel("Nome: ");
        JLabel valor = new JLabel("Valor: R$");
        JPanel leftPanel = new JPanel(new GridLayout(1, 1));
        leftPanel.add(nomeProdutoField);
        leftPanel.add(precoProdutoField);
        add(leftPanel, BorderLayout.WEST);

        JTextField digitarCodigo = new JTextField();
        JLabel codigo = new JLabel("Codigo: ");
        JTextField valorTotal = new JTextField();
        JLabel valorTotalL = new JLabel("Total: R$");
        JTextField quantidade = new JTextField("1");
        JLabel quantidadeL = new JLabel("Quantidade: ");
        JPanel painelCodigo = new JPanel(new GridLayout(1, 4));
        painelCodigo.add(codigo);
        painelCodigo.add(digitarCodigo); 
        painelCodigo.add(quantidadeL);
        painelCodigo.add(quantidade);
        painelCodigo.add(valorTotalL);
        painelCodigo.add(valorTotal);
        painelGeral.add(painelCodigo, BorderLayout.SOUTH);

        

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
