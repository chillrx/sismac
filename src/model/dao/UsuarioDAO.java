package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produto;
import model.bean.Usuario;

public class UsuarioDAO {

    public void create(Usuario u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO users (name,login,password) VALUES(?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getPassword());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Usuario> read() {
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement st = null;

        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();
        try {
            st = con.prepareStatement("SELECT * FROM users");
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(rs.getInt("id"));
                usuario.setName(rs.getString("name"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPassword(rs.getString("password"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar usuários: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }
        return usuarios;
    }

    public void update(Usuario u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE users SET name = ? ,login = ?,password = ? WHERE id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getPassword());
            stmt.setInt(4, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Usuario u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM users WHERE id = ?");
            stmt.setInt(1, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public boolean login(String user, String pass) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement st = null;

        ResultSet rs = null;
        Boolean valid = false;

        try {
            st = con.prepareStatement("SELECT * FROM users WHERE login = ?");
            st.setString(1, user);
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setPassword(rs.getString("password"));
                valid = usuario.getPassword().equals(pass);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar usuários: " + ex);
            valid = false;
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }

        return valid;
    }

}
