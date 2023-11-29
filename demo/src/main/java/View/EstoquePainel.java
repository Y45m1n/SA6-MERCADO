package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EstoquePainel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabelaEstoque;
    private JButton btnAtualizarQuantidade;
    private JButton btnAdicionarProduto;

    public EstoquePainel() {
        // Configuração da tabela de estoque
        String[] colunas = {"Produto", "Código de Barras", "Quantidade"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaEstoque = new JTable(tableModel);

        // Configuração dos botões
        btnAtualizarQuantidade = new JButton("Atualizar Quantidade");
        btnAdicionarProduto = new JButton("Adicionar Novo Produto");

        // Adicionando componentes ao painel
        add(new JScrollPane(tabelaEstoque));
        add(btnAtualizarQuantidade);
        add(btnAdicionarProduto);

        // Configurando ação para o botão Atualizar Quantidade
        btnAtualizarQuantidade.addActionListener(e -> atualizarQuantidade());

        // Configurando ação para o botão Adicionar Novo Produto
        btnAdicionarProduto.addActionListener(e -> adicionarNovoProduto());
    }

    private void atualizarQuantidade() {
        // Obtém a linha selecionada na tabela de estoque
        int linhaSelecionada = tabelaEstoque.getSelectedRow();
        if (linhaSelecionada != -1) {
            // Obtém o nome do produto e o código de barras na linha selecionada
            String produto = (String) tableModel.getValueAt(linhaSelecionada, 0);
            String codigoBarras = (String) tableModel.getValueAt(linhaSelecionada, 1);

            // Solicita ao usuário a nova quantidade
            String novaQuantidadeStr = JOptionPane.showInputDialog("Digite a nova quantidade para " + produto + ":");
            if (novaQuantidadeStr != null && !novaQuantidadeStr.isEmpty()) {
                try {
                    // Converte a nova quantidade para inteiro
                    int novaQuantidade = Integer.parseInt(novaQuantidadeStr);

                    // Atualiza a quantidade na tabela de estoque
                    tableModel.setValueAt(novaQuantidade, linhaSelecionada, 2);

                    // Exibe uma mensagem indicando que a quantidade foi atualizada
                    JOptionPane.showMessageDialog(this, "Quantidade de " + produto + " atualizada para " + novaQuantidade);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para atualizar a quantidade.");
        }
    }

    private void adicionarNovoProduto() {
        // Solicita ao usuário os dados do novo produto
        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do novo produto:");
        String codigoBarras = JOptionPane.showInputDialog("Digite o código de barras do novo produto:");

        if (nomeProduto != null && !nomeProduto.isEmpty() && codigoBarras != null && !codigoBarras.isEmpty()) {
            try {
                String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade inicial:");
                int quantidadeInicial = Integer.parseInt(quantidadeStr);

                // Adiciona o novo produto à tabela de estoque
                Object[] linha = {nomeProduto, codigoBarras, quantidadeInicial};
                tableModel.addRow(linha);

                // Exibe uma mensagem indicando que o novo produto foi adicionado
                JOptionPane.showMessageDialog(this, "Novo produto adicionado ao estoque: " + nomeProduto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
            }
        }
    }
}