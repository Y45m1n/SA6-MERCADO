package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionFactory;
import Model.Vendas;

public class VendasDAO {
    // atributo
    private Connection connection;
    private List<Vendas> vendas;

    // construtor
    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS mercado_vendas (NOME VARCHAR(255),PRECO VARCHAR(255),QUANTIDADE VARCHAR(255), TOTAL VARCHAR(255), CODIGO VARCHAR(255) PRIMARY KEY  )";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Vendas> listarTodos() {
        PreparedStatement stmt = null;
        // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null;
        // Declaração do objeto ResultSet para armazenar os resultados da consulta
        vendas = new ArrayList<>();
        // Cria uma lista para armazenar os carros recuperados do banco de dados
        try {
            stmt = connection.prepareStatement("SELECT * FROM mercado_vendas");
            // Prepara a consulta SQL para selecionar todos os registros da tabela
            rs = stmt.executeQuery();
            // Executa a consulta e armazena os resultados no ResultSet
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do
                // registro

                Vendas vendas = new Vendas( rs.getString("nome"), rs.getString("preco"), rs.getString("quantidade"), rs.getString("total"), rs.getString("codigo"));
                vendas.add(vendas); // Adiciona o objeto Carros à lista de carros
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);

            // Fecha a conexão, o PreparedStatement e o ResultSet
        }
        return vendas; // Retorna a lista de carros recuperados do banco de dados
    }

    // Cadastrar Carro no banco
    public void cadastrar(String nome, String preco, String quantidade, String total,  String codigo) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para cadastrar na tabela
        String sql = "INSERT INTO carros_lojacarros (marca, modelo, ano, preco, cor, placa) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, preco);
            stmt.setString(3, quantidade);
            stmt.setString(4, total);
            stmt.setString(5, codigo);
            
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Atualizar dados no banco
    public void atualizar(String nome, String preco, String quantidade, String total,  String codigo) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para atualizar dados pela placa
        String sql = "UPDATE carros_lojacarros SET marca = ?, modelo = ?, ano = ?, preco = ?, cor = ? WHERE placa = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, preco);
            stmt.setString(3, quantidade);
            stmt.setString(4, total);
            stmt.setString(5, codigo); // codigo é chave primaria não pode ser alterada.
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Apagar dados do banco
    public void apagar(String codigo) {
        PreparedStatement stmt = null;
        // Define a instrução SQL parametrizada para apagar dados pela placa
        String sql = "DELETE FROM carros_lojacarros WHERE codigo = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, codigo);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
