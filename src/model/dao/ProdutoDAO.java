package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;

public class ProdutoDAO {

    public void create(Produto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO products (name,price,storage) VALUES(?,?,?)");
            stmt.setString(1, p.getName());
            stmt.setInt(3, p.getStorage());
            stmt.setDouble(2, p.getPrice());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Produto> read() {
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement st = null;

        ResultSet rs = null;

        List<Produto> produtos = new ArrayList<>();
        try {
            st = con.prepareStatement("SELECT * FROM products");
            rs = st.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();

                produto.setId(rs.getInt("id"));
                produto.setName(rs.getString("name"));
                produto.setPrice(rs.getDouble("price"));
                produto.setStorage(rs.getInt("storage"));

                produtos.add(produto);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar produtos: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }
        return produtos;
    }

    public void update(Produto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE products SET name = ? ,storage = ?,price = ? WHERE id = ?");
            stmt.setString(1, p.getName());
            stmt.setInt(2, p.getStorage());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Produto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM products WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void setstorage(Produto p) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE products SET storage = ? WHERE id = ?");
            stmt.setInt(1, p.getStorage());
            stmt.setInt(2, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
