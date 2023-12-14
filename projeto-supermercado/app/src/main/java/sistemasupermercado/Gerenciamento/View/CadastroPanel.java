// Importação de bibliotecas necessárias para a construção da interface gráfica
package sistemasupermercado.Gerenciamento.View;

import javax.swing.*;
import java.awt.*;

// Importação de classes específicas do projeto relacionadas ao banco de dados
import sistemasupermercado.Gerenciamento.Connection.ClientesDAO;

// Classe que representa um painel para cadastrar clientes VIP em uma interface gráfica
public class CadastroPanel extends JPanel {

    // Declaração de componentes
    private JButton cadastraCliente;
    private JTextField inputCpf, inputNome, inputIdade;

    // Construtor da classe
    public CadastroPanel() {
        setLayout(new BorderLayout());  // Define o layout do painel como BorderLayout

        // Painel de título
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(new JLabel("Cadastro de Clientes VIP"));

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
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Inicialização do botão
        cadastraCliente = new JButton("Cadastrar");
        // Adição do botão ao painel
        buttonPanel.add(cadastraCliente);

        // Adição dos componentes ao painel principal usando o layout BorderLayout
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Tratamento de evento ao clicar no botão de cadastro
        cadastraCliente.addActionListener(e -> {
            // Verificação se os campos estão preenchidos antes de cadastrar
            if (!inputCpf.getText().isEmpty() && !inputNome.getText().isEmpty()
                    && !inputIdade.getText().isEmpty()) {
                // Chamada do método para cadastrar o cliente no banco de dados
                new ClientesDAO().cadastrar(inputCpf.getText(), inputNome.getText(), inputIdade.getText());
                // Exibição de mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Cliente cadastrado!");
            } else {
                // Exibição de mensagem de alerta caso algum campo não esteja preenchido
                JOptionPane.showMessageDialog(inputPanel,
                        "Preencha os campos corretamente para cadastrar um cliente!!", null,
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
