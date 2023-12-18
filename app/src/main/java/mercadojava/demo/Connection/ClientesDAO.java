package mercadojava.demo.Connection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mercadojava.demo.Model.Cliente;
import mercadojava.demo.Control.ClientesControl;
// Classe responsável pela comunicação com o banco de dados para operações relacionadas a clientes VIP
public class ClientesDAO {
    // Atributos
    private Connection connection;
    private List<Cliente> clientes;

    // Construtor
    public ClientesDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // Método para criar a tabela no banco de dados, se não existir
    public void criaTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS clientesvip_mercado (CPF VARCHAR(20) PRIMARY KEY, NOME VARCHAR(255), IDADE VARCHAR(3))";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            // Em caso de erro ao criar a tabela, lança uma exceção RuntimeException
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para listar todos os clientes VIP cadastrados no banco de dados
    public List<Cliente> listarTodos() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        clientes = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM clientesvip_mercado");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("idade"));
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs);
        }
        return clientes;
    }

    // Método para apagar a tabela do banco de dados
    public void apagarTabela() {
        String sql = "DROP TABLE clientesvip_mercado";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela apagada com sucesso.");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao apagar tabela.", e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Método para cadastrar um novo cliente VIP no banco de dados
    public void cadastrar(String cpf, String nomeCompleto, String idade) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO clientesvip_mercado(cpf, nome, idade) VALUES (?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf.toUpperCase().trim());
            stmt.setString(2, nomeCompleto.toUpperCase().trim());
            stmt.setString(3, idade.trim());
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para atualizar os dados de um cliente VIP no banco de dados
    public void atualizar(String cpf, String nomeCompleto, String idade) {
        PreparedStatement stmt = null;
        String sql = "UPDATE clientesvip_mercado SET nome = ?, idade = ? WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nomeCompleto.toUpperCase().trim());
            stmt.setString(2, idade.trim());
            stmt.setString(3, cpf);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    // Método para apagar um cliente VIP do banco de dados
    public void apagar(String cpf) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM clientesvip_mercado WHERE cpf = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
}
