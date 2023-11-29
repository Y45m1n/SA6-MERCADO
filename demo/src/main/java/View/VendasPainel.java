package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class VendasPainel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable tabelaProdutos;
    private JButton btnAdicionar;
    private JButton btnRemover;
    private JButton btnConcluirCompra;
    private JLabel lblTotal;

    public VendasPainel() {
        // Configuração da tabela de produtos
        String[] colunas = {"Produto", "Quantidade", "Preço"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(tableModel);

        // Configuração dos botões
        btnAdicionar = new JButton("Adicionar");
        btnRemover = new JButton("Remover");
        btnConcluirCompra = new JButton("Concluir Compra");

        // Configuração do rótulo para exibir o total
        lblTotal = new JLabel("Total: R$ 0.00");

        // Adicionando componentes ao painel
        add(new JScrollPane(tabelaProdutos));
        add(btnAdicionar);
        add(btnRemover);
        add(btnConcluirCompra);
        add(lblTotal);

        // Configurando ação para o botão Adicionar
        btnAdicionar.addActionListener(e -> adicionarProduto());

        // Configurando ação para o botão Remover
        btnRemover.addActionListener(e -> removerProduto());

        // Configurando ação para o botão Concluir Compra
        btnConcluirCompra.addActionListener(e -> concluirCompra());
    }

    private void adicionarProduto() {
        String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
        if (nomeProduto != null && !nomeProduto.isEmpty()) {
            try {
                String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade:");
                int quantidade = Integer.parseInt(quantidadeStr);
    
                String precoUnitarioStr = JOptionPane.showInputDialog("Digite o preço unitário:");
                double precoUnitario = Double.parseDouble(precoUnitarioStr);
    
                // Adiciona o produto à tabela
                Object[] linha = {nomeProduto, quantidade, precoUnitario};
                tableModel.addRow(linha);
    
                // Atualiza o total
                atualizarTotal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantidade ou preço inválido(s).");
            }
        }
    }
    

    private void atualizarTotal() {
    }

    private void removerProduto() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada != -1) {
            int confirmacao = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente remover este produto?",
                    "Confirmação de Remoção",
                    JOptionPane.YES_NO_OPTION
            );
    
            if (confirmacao == JOptionPane.YES_OPTION) {
                // Remove a linha selecionada da tabela
                tableModel.removeRow(linhaSelecionada);
    
                // Atualiza o total
                atualizarTotal();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover.");
        }
    }
    

    private void concluirCompra() {
        // Calcula o total final
        double total = calcularTotalFinal();
    
        // Simula a impressão de um recibo
        String recibo = criarRecibo(total);
        System.out.println(recibo);  // Simula a impressão, você pode substituir por uma lógica de impressão real
    
        // Exibe uma mensagem indicando que a compra foi concluída
        JOptionPane.showMessageDialog(this, "Compra concluída! Total: " + String.format("R$ %.2f", total));
    
        // Limpa a tabela e o total após a conclusão da compra
        limparTabela();
        atualizarTotal();
    }
    
    private void limparTabela() {
    }

    private double calcularTotalFinal() {
        double total = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int quantidade = (int) tableModel.getValueAt(i, 1);
            double precoUnitario = (double) tableModel.getValueAt(i, 2);
            total += quantidade * precoUnitario;
        }
    
        // Aplica o desconto de 10% se o cliente for VIP
        // (simulação - adapte conforme a lógica real do seu projeto)
        if (clienteEhVIP()) {
            total *= 0.9;  // Aplica desconto de 10%
        }
    
        return total;
    }
    
    private String criarRecibo(double total) {
        StringBuilder recibo = new StringBuilder();
        recibo.append("=== RECIBO DE COMPRA ===\n");
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String produto = (String) tableModel.getValueAt(i, 0);
            int quantidade = (int) tableModel.getValueAt(i, 1);
            double precoUnitario = (double) tableModel.getValueAt(i, 2);
            recibo.append(String.format("%s - Quantidade: %d - Preço Unitário: R$ %.2f\n", produto, quantidade, precoUnitario));
        }
        recibo.append("=======================\n");
        recibo.append(String.format("Total a pagar: R$ %.2f\n", total));
        recibo.append("=======================\n");
    
        return recibo.toString();
    }
    
    private boolean clienteEhVIP() {
        // Simulação: retorna true se o cliente for VIP, ajuste conforme necessário
        return JOptionPane.showConfirmDialog(this, "O cliente é VIP?", "Verificação VIP", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
    
}