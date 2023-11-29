package Controller;

import Model.Cliente;
import DAO.ClienteDAO;
import View.ClienteVIP;

public class ClienteControl {
    private ClienteVIP view; // Referência à interface gráfica
    private ClienteDAO clienteDAO;

    public ClienteControl(ClienteVIP view, ClienteDAO clienteDAO) {
        this.view = view;
        this.clienteDAO = clienteDAO;
    }

    // Método chamado quando o botão Verificar VIP é clicado na interface
    public void verificarClienteVIP() {
        String cpf = view.obterCPF();

        // Busca o cliente no banco de dados
        Cliente cliente = clienteDAO.buscarPorCPF(cpf);

        if (cliente != null) {
            if (cliente.isVip()) {
                view.exibirStatusVIP(true);
            } else {
                view.exibirStatusVIP(false);
            }
        } else {
            view.exibirMensagemErro("Cliente não encontrado.");
        }
    }

    // Método chamado quando o botão Cadastrar Cliente é clicado na interface
    public void cadastrarNovoCliente() {
        String nome = view.obterNome();
        String cpf = view.obterCPF();

        // Cria um novo cliente
        Cliente novoCliente = new Cliente(nome, cpf, true); // Assume que todo cliente cadastrado é VIP

        // Adiciona o novo cliente ao banco de dados
        clienteDAO.cadastrarCliente(novoCliente);

        // Atualiza a interface
        view.exibirMensagem("Novo cliente cadastrado com sucesso!");
    }
}