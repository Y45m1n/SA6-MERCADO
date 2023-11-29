package DAO;

import Model.Produto;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {
    public int obterQuantidadeEmEstoque(String codigoBarras) {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT quantidade_estoque FROM produtos WHERE codigo_barras = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, codigoBarras);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("quantidade_estoque");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt, rs);
        }

        // Retorna -1 se o produto não for encontrado
        return -1;
    }

    public void atualizarQuantidadeEmEstoque(String codigoBarras, int novaQuantidade) {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE produtos SET quantidade_estoque = ? WHERE codigo_barras = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, novaQuantidade);
            stmt.setString(2, codigoBarras);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    public void adicionarNovoProduto(Produto novoProduto) {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO produtos (nome, codigo_barras, preco, quantidade_estoque) VALUES (?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, novoProduto.getNome());
            stmt.setString(2, novoProduto.getCodigoBarras());
            stmt.setDouble(3, novoProduto.getPreco());
            stmt.setInt(4, novoProduto.getQuantidadeEstoque());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, stmt);
        }
    }

    // Outros métodos de consulta, atualização, etc., podem ser adicionados conforme necessário
}