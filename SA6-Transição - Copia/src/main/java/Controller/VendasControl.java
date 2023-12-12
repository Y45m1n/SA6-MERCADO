package Controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Model.Vendas;

public class VendasControl {
    // Atributos
    private List<Vendas> vendas;
    private DefaultTableModel tableModel;
    private JTable table;

    // Construtor
    public VendasControl(List<Vendas> vendas, DefaultTableModel tableModel, JTable table) {
        this.vendas = vendas;
        this.tableModel = tableModel;
        this.table = table;
    }

    // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarTodos();
        // Obtém os vendas atualizados do banco de dados
        for (Vendas vendas : vendas) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { vendas.getNome(), vendas.getPrecoUni(),
                    vendas.getQuantidade(), vendas.getPrecoTotal(), vendas.getCodigo() });
        }
    }
 // Método para ação concluída
 private void acaoFeita(String mensagem) {
    JOptionPane.showMessageDialog(null, mensagem, "Ação Concluída", JOptionPane.INFORMATION_MESSAGE);
}

    // Método para validar o codigo como numero
    private boolean verificarCodigo(String codigo) {
        try {
            Integer.parseInt(codigo);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para validar o valor como numero
    private boolean verificarPreco(String preco) {
        try {
            Double.parseDouble(preco);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // // metodo para verificar se a cor esta completada como valor alfabetico
    // private boolean verificarCor(String cor) {
    //     return cor.chars().noneMatch(Character::isDigit);
    // }

 // Método para cadastrar um novo carro no banco de dados
    public void cadastrar(String nome, String preco, String quantidade, String total,  String codigo) {
        if (!verificarCodigo(codigo) || !verificarPreco(preco)) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new VendasDAO().cadastrar(nome, preco, quantidade, total, codigo);

        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro

        acaoFeita("Produto cadastrado!");
    }

   // Método para atualizar os dados de um carro no banco de dados
   public void atualizar(String nome, String preco, String quantidade, String total,  String codigo) {
    int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar o cadastro?", "Confirmação", JOptionPane.YES_NO_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
          if (!verificarCodigo(codigo) || !verificarPreco(preco)) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique as informações.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new VendasDAO().atualizar(nome, preco, quantidade, total, codigo);
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização

        acaoFeita("Cadastro atualizado com sucesso!");
    }
}
    
 // Método para apagar um produto do banco de dados
 public void apagar(String codigo) {
    int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o produto?", "Confirmação", JOptionPane.YES_NO_OPTION);
    if (resposta == JOptionPane.YES_OPTION) {
        new VendasDAO().apagar(codigo);
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão

        acaoFeita("Produto apagado com sucesso!");
    }
}
   
}
