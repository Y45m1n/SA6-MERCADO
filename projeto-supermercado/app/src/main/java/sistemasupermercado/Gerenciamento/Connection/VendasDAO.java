// Importação das bibliotecas necessárias para manipulação de banco de dados
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// Importação da classe de modelo Venda
import sistemasupermercado.Gerenciamento.Model.Venda;

// Classe que realiza operações no banco de dados relacionadas às Vendas
public class VendasDAO {
    // Atributos
    private Connection connection;  // Conexão com o banco de dados
    private List<Venda> vendas;     // Lista para armazenar objetos do tipo Venda

    // Construtor da classe
    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();  // Obtém a conexão com o banco de dados
    }

    // Método para criar a tabela no banco de dados se ela não existir
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS vendas_mercado (id serial not null PRIMARY KEY, cliente VARCHAR(255), valor VARCHAR(10), data VARCHAR(255), quantidade VARCHAR(10) )";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para listar todas as vendas cadastradas no banco de dados
    public List<Venda> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        vendas = new ArrayList<>();  // Inicializa a lista de vendas
        try {
            stmt = connection.prepareStatement("SELECT * FROM vendas_mercado");
            rs = stmt.executeQuery();

            // Itera sobre os resultados da consulta
            while (rs.next()) {
                Venda venda = new Venda(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getString("valor"),
                        rs.getString("data"),
                        rs.getString("quantidade"));
                vendas.add(venda);  // Adiciona cada venda à lista
            }
        } catch (SQLException ex) {
            System.out.println(ex);  // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return vendas;  // Retorna a lista de vendas recuperadas do banco de dados
    }

    // Método para apagar a tabela no banco de dados
    public void apagarTabela() {
        String sql = "DROP TABLE vendas_mercado";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela apagada com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar tabela.", e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para cadastrar uma nova venda no banco de dados
    public void cadastrar(String cliente, String valor, String data, String quantidadeDeProdutos) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO vendas_mercado(cliente, valor, data, quantidade) VALUES (?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.toUpperCase().trim());
            stmt.setString(2, valor.trim());
            stmt.setString(3, data.trim());
            stmt.setString(4, quantidadeDeProdutos.trim());
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para apagar uma venda do banco de dados pelo ID
    public void apagar(int id) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM vendas_mercado WHERE id = ?";
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
