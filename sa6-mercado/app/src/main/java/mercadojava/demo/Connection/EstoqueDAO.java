package mercadojava.demo.Connection;

// Importações necessárias para a manipulação de banco de dados e a utilização da classe de modelo Estoque
import java.util.List;

import mercadojava.demo.Model.Estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// Classe responsável por realizar operações no banco de dados relacionadas ao Estoque
public class EstoqueDAO {
    // Atributos
    private Connection connection;  // Conexão com o banco de dados
    private List<Estoque> produtos;  // Lista para armazenar objetos do tipo Estoque

    // Construtor
    public EstoqueDAO() {
        this.connection = ConnectionFactory.getConnection();  // Obtém a conexão com o banco de dados
    }

    // Método para criar a tabela no banco de dados se ela não existir
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS estoque_mercado (id INTEGER PRIMARY KEY, nome VARCHAR(255), preco VARCHAR(255), quantidade VARCHAR(4))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para listar todos os produtos cadastrados no banco de dados
    public List<Estoque> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        produtos = new ArrayList<>();  // Inicializa a lista de produtos
        try {
            stmt = connection.prepareStatement("SELECT * FROM estoque_mercado");
            rs = stmt.executeQuery();

            // Itera sobre os resultados da consulta
            while (rs.next()) {
                Estoque produto = new Estoque(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("preco"),
                        rs.getString("quantidade"));
                produtos.add(produto);  // Adiciona cada produto à lista
            }
        } catch (SQLException ex) {
            System.out.println(ex);  // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos;  // Retorna a lista de produtos recuperados do banco de dados
    }

    // Método para listar um produto pelo ID
    public List<Estoque> listarUm(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        produtos = new ArrayList<>();  // Inicializa a lista de produtos
        try {
            stmt = connection.prepareStatement("SELECT * FROM estoque_mercado WHERE id=?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            // Itera sobre os resultados da consulta
            while (rs.next()) {
                Estoque produto = new Estoque(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("preco"),
                        rs.getString("quantidade"));
                produtos.add(produto);  // Adiciona o produto à lista
            }
        } catch (SQLException ex) {
            System.out.println(ex);  // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return produtos;  // Retorna a lista de produtos recuperados do banco de dados
    }

    // Método para apagar a tabela no banco de dados
    public void apagarTabela() {
        String sql = "DROP TABLE estoque_mercado";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela apagada com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar tabela.", e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para cadastrar um novo produto no banco de dados
    public void cadastrar(int id, String nome, String preco, String quantidade) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO estoque_mercado(id, nome, preco, quantidade) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, nome.toUpperCase().trim());
            stmt.setString(3, preco.toUpperCase().trim());
            stmt.setString(4, quantidade.trim());
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para atualizar dados de um produto no banco de dados
    public void atualizar(int id, String nome, String preco, String quantidade) {
        PreparedStatement stmt = null;
        String sql = "UPDATE estoque_mercado SET nome = ?, preco = ?, quantidade = ? WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome.toUpperCase().trim());
            stmt.setString(2, preco.trim());
            stmt.setString(3, quantidade.trim());
            stmt.setInt(4, id);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para atualizar apenas a quantidade de um produto no banco de dados
    public void atualizarQuantidade(int id, String quantidade) {
        PreparedStatement stmt = null;
        String sql = "UPDATE estoque_mercado SET quantidade = ? WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, quantidade.trim());
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para apagar um produto do banco de dados pelo ID
    public void apagar(int id) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM estoque_mercado WHERE id = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
