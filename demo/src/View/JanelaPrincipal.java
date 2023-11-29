// JanelaPrincipal.java
package View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {
    private JTabbedPane jTPane;

    public JanelaPrincipal() {
        jTPane = new JTabbedPane();
        add(jTPane);

        // Abas
        jTPane.addTab("Vendas", new VendasPainel());
        jTPane.addTab("Gerenciamento de Estoque", new EstoquePainel());
        jTPane.addTab("Cadastro de Clientes", new ClientesPainel());

        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JanelaPrincipal janela = new JanelaPrincipal();
        janela.run();
    }
}
