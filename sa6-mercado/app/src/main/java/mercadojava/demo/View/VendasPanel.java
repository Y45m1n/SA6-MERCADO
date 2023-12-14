// Importação de bibliotecas necessárias para a construção da interface gráfica e manipulação de dados
package mercadojava.demo.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import mercadojava.demo.Connection.VendasDAO;
import mercadojava.demo.Control.VendasControl;
import mercadojava.demo.Model.Venda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Classe que representa um painel para exibição e gerenciamento de vendas em uma interface gráfica
public class VendasPanel extends JPanel {

    // Declaração de variáveis de instância
    private DefaultTableModel tableModel;  // Modelo da tabela
    private JTable table;  // Componente de tabela
    private List<Venda> vendas = new ArrayList<>();  // Lista de objetos Venda
    private JScrollPane scrollPane;  // Componente de rolagem para a tabela

    // Construtor da classe
    public VendasPanel() {
        super();  // Chama o construtor da superclasse JPanel

        setLayout(new BorderLayout());  // Define o layout do painel como BorderLayout

        // Cria um painel para entrada de dados (não utilizado neste código)
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Configuração da tabela com um modelo padrão e colunas específicas
        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"id", "Cliente (CPF)", "Total", "Quantidade", "Data"});
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);  // Adiciona a tabela a um componente de rolagem

        // Adiciona os componentes ao painel principal usando o layout BorderLayout
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Cria a tabela no banco de dados e atualiza os dados na tabela da interface gráfica
        new VendasDAO().criaTabela();
        atualizarTabela();
    }

    // Método para atualizar os dados na tabela da interface gráfica
    public void atualizarTabela() {
        tableModel.setRowCount(0);  // Limpa todas as linhas existentes na tabela
        vendas = new VendasDAO().listarTodos();  // Obtém a lista de todas as vendas do banco de dados

        // Adiciona cada venda como uma nova linha na tabela
        for (Venda venda : vendas) {
            tableModel.addRow(new Object[]{venda.getId(), venda.getCliente(), venda.getValor(),
                    venda.getQuantidadeDeProdutos(), venda.getData()});
        }
    }
}
