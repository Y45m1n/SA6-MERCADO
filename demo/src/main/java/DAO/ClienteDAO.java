package DAO;

import Model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Connection.ConnectionFactory;

public class ClienteDAO {

    private Connection connection;
    private List<Cliente> clientes;

    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientes (NOME VARCHAR(255),CPF VARCHAR(255) PRIMARY KEY, TELEFONE VARCHAR(255), ENDERECO VARCHAR(255), EMAIL VARCHAR(255))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    public List<Cliente> listarClientes() {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM clientes";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao);
        }

        return clientes;
    }

    public Cliente buscarPorCPF(String cpf) {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;

        try {
            String sql = "SELECT * FROM clientes WHERE cpf = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                   rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("endereco"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
        }

        return cliente;
    }

    public void cadastrarCliente(String nome, String cpf, String telefone,  String endereco, String email) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, endereco, email) VALUES (?, ?, ?, ?, ?)";
       

        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3,telefone);
             stmt.setString(4,endereco);
              stmt.setString(5, email);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar dados no banco de dados.", e);
        } 
    }

    

    public void apagarNovoCliente(String cpf) {
    }

    public void cadastrarNovoCliente(String nome, String cpf, String telefone, String endereco, String email) {
    }

    
}