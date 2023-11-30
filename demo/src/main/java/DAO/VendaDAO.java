// package DAO;

// import Model.Venda;
// import Util.ConnectionFactory;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// public class VendaDAO {
//     public void registrarVenda(Venda venda) {
//         Connection conexao = ConnectionFactory.getConnection();
//         PreparedStatement stmt = null;

//         try {
//             // Insira a lógica para registrar a venda no banco de dados
//             // Pode envolver a inserção de dados na tabela de vendas e na tabela de itens de venda

//             // Exemplo:
//             String sqlVenda = "INSERT INTO vendas (data, total) VALUES (?, ?)";
//             stmt = conexao.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);
//             stmt.setTimestamp(1, new java.sql.Timestamp(venda.getData().getTime()));
//             stmt.setDouble(2, venda.getTotal());
//             stmt.executeUpdate();

//             ResultSet generatedKeys = stmt.getGeneratedKeys();
//             if (generatedKeys.next()) {
//                 int idVenda = generatedKeys.getInt(1);

//                 // Agora, insira os itens de venda na tabela correspondente
//                 String sqlItens = "INSERT INTO itens_venda (id_venda, codigo_barras, quantidade) VALUES (?, ?, ?)";
//                 stmt = conexao.prepareStatement(sqlItens);

//                 for (Venda.ItemVenda item : venda.getItens()) {
//                     stmt.setInt(1, idVenda);
//                     stmt.setString(2, item.getCodigoBarras());
//                     stmt.setInt(3, item.getQuantidade());
//                     stmt.addBatch();
//                 }

//                 stmt.executeBatch();
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         } finally {
//             ConnectionFactory.closeConnection(conexao, stmt);
//         }
//     }

//     // Outros métodos de consulta, atualização, etc., podem ser adicionados conforme necessário
// }