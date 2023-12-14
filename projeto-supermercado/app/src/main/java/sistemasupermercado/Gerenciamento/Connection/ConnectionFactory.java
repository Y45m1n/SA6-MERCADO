// Classe responsável por gerenciar a conexão com o banco de dados PostgreSQL
public class ConnectionFactory {
    // Atributos
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String usuario = "postgres"; // Nome do administrador do banco
    private static final String senha = "postgres";

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            // Em caso de erro ao obter a conexão, lança uma exceção RuntimeException
            throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
        }
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            // Em caso de erro ao fechar a conexão, imprime o erro
            ex.printStackTrace();
        }
    }

    // Método para fechar a conexão e o objeto PreparedStatement
    public static void closeConnection(Connection connection, PreparedStatement stmt) {
        closeConnection(connection);  // Chama o método para fechar a conexão
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Método para fechar a conexão, o objeto PreparedStatement e o ResultSet
    public static void closeConnection(Connection connection, PreparedStatement stmt,
            ResultSet rs) {
        closeConnection(connection, stmt);  // Chama o método para fechar a conexão e o PreparedStatement
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
