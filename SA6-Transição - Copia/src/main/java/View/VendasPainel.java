package View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import Controller.VendasControl;
import Controller.VendasDAO;
import Model.Vendas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VendasPainel extends JPanel {
    // Atributos(componentes)
    private JButton cadastrar, apagar, adicionar, pagamento;
    private JTextField nomeText, precoText, quantidadeText, codigoText, totalText;
    private List<Vendas> venda;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor(GUI-JPanel)
    public VendasPainel() {
        super();

        // entrada de dados
        setLayout(new BorderLayout());

          

      // Componentes no centro (tabela)
      tableModel = new DefaultTableModel();
      JTable tabelaProdutos = new JTable(tableModel);
      add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);

      // Campos de entrada de dados
      JPanel inputPanel = new JPanel(new GridLayout(1, 1));
      codigoText = new JTextField();
      quantidadeText = new JTextField();
      
      inputPanel.add(new JLabel("Código do Produto:"));
      inputPanel.add(codigoText);
      inputPanel.add(new JLabel("Quantidade:"));
      inputPanel.add(quantidadeText);
      add(inputPanel, BorderLayout.SOUTH);


       // Componentes à direita
       JPanel rightPanel = new JPanel(new GridLayout(8, 1));
       cadastrar = new JButton("Nova Venda");
       cadastrar.setBackground(Color.GREEN);
       adicionar = new JButton("Adicionar");
       adicionar.setBackground(Color.ORANGE);
       apagar = new JButton("Remover");
       apagar.setBackground(Color.RED);
       JButton btnFormaPagamento = new JButton("Forma de Pagamento");
    
       rightPanel.add(cadastrar);
       rightPanel.add(adicionar);
       rightPanel.add(apagar);
       rightPanel.add(btnFormaPagamento);
  
       add(rightPanel, BorderLayout.EAST);
        
       
        
       
        
        
       


        // tabela de vendas
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Nome", "Preço Unid", "Quantidade", "Preço Total", "Codigo"});
        table = new JTable(tableModel);
        jSPane.setViewportView(table);

        // Cria o banco de dados caso não tenha sido criado
        new VendasDAO().criaTabela();
        // incluindo elementos do banco na criação do painel
       atualizarTabela();

        // tratamento de Eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    nomeText.setText((String) table.getValueAt(linhaSelecionada, 0));
                    precoText.setText((String) table.getValueAt(linhaSelecionada, 1));
                    quantidadeText.setText((String) table.getValueAt(linhaSelecionada, 2));
                     totalText.setText((String) table.getValueAt(linhaSelecionada, 3));
                      codigoText.setText((String) table.getValueAt(linhaSelecionada, 4));
                   
                }
            }
        });

        // Cria um objeto operacoes da classe CarrosControl para executar operações no banco de dados
        VendasControl operacoes = new VendasControl(venda, tableModel, table);

        // Configura a ação do botão "cadastrar" para adicionar um novo registro no banco de dados

        
        cadastrar.addActionListener(e -> {
            String nome = nomeText.getText();
            String preco = precoText.getText();
            String quantidade = quantidadeText.getText();
             String total = totalText.getText();
              String codigo = codigoText.getText();
         
           
            // Verifica se os campos obrigatórios não estão vazios
            if (nome.isEmpty() || preco.isEmpty() || quantidade.isEmpty() || total.isEmpty() || codigo.isEmpty()) {
                // Exibe uma mensagem de erro ao usuário
                JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {

                //chamar o método cadastrar com os dados válidos
                operacoes.cadastrar(nome, preco, quantidade, total, codigo);

                // Limpa os campos após o cadastro bem-sucedido
                nomeText.setText("");
                precoText.setText("");
                quantidadeText.setText("");
                totalText.setText("");
                codigoText.setText("");
              
            } catch (NumberFormatException ex) {
                // Exibe uma mensagem de erro ao usuário se houver problemas na conversão
                JOptionPane.showMessageDialog(this, "Dados inválidos. Verifique as informações.", "Erro de Cadastro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tratamento do botao editar
        adicionar.addActionListener(e -> {
            operacoes.atualizar(nomeText.getText(), precoText.getText(), quantidadeText.getText(), totalText.getText(), codigoText.getText());

            // Limpa os campos após a atualização, independentemente do sucesso ou falha
           nomeText.setText("");
                precoText.setText("");
                quantidadeText.setText("");
                totalText.setText("");
                codigoText.setText("");
           
        });

        // Tratamento do botao apagar
        apagar.addActionListener(e -> {
            operacoes.apagar(codigoText.getText());

            // Limpa os campos após a exclusão, independentemente do sucesso ou falha
            nomeText.setText("");
                precoText.setText("");
                quantidadeText.setText("");
                totalText.setText("");
                codigoText.setText("");
        });
    }

    

    //Metodos (Atualizar tabela)
   // Método para atualizar a tabela de exibição com dados do banco de dados
    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        venda = new VendasDAO().listarTodos();
        // Obtém os carros atualizados do banco de dados
       
        for (Vendas vendas : venda) {

            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] {
                vendas.getNome(), 
                vendas.getPrecoUni(),
                vendas.getQuantidade(), 
                vendas.getPrecoTotal(), 
                vendas.getCodigo()
                   
            });
        }
    }

}