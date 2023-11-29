
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Produto;
import Util.ConnectionFactory;

public class ProdutoDAO {
    // Método para buscar um produto por código de barras
    public Produto buscarPorCodigoBarras(String codigoBarras) {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = null;

        try {
            String sql = "SELECT * FROM produtos WHERE codigo_barras = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, codigoBarras);
            rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                    rs.getString("nome"),
                    rs.getString("codigo_barras"),
                    rs.getDouble("preco"),
                    rs.getInt("quantidade_estoque")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
        }

        return produto;
    }

    // Outros métodos de consulta, atualização, etc. podem ser adicionados conforme necessário
}