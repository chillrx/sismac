package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {

    public void create(Cliente c) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO clients (name, email, birth_date, phone_number) VALUES(?,?,?,?)");
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getBirthdate());
            stmt.setString(4, c.getPhonenumber());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Cliente> read() {
        Connection con = ConnectionFactory.getConnection();

        PreparedStatement st = null;

        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            st = con.prepareStatement("SELECT * FROM clients");
            rs = st.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setName(rs.getString("name"));
                cliente.setPhonenumber(rs.getString("phone_number"));
                cliente.setBirthdate(rs.getString("birth_date"));
                cliente.setEmail(rs.getString("email"));

                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar clientes : " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, st, rs);
        }

        return clientes;
    }

    public void update(Cliente c) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE clients SET name = ? , email = ?, birth_date = ?, phone_number = ? WHERE id = ?");
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getEmail());
            stmt.setString(3, c.getBirthdate());
            stmt.setString(4, c.getPhonenumber());
            stmt.setInt(5, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void delete(Cliente c) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM clients WHERE id = ?");
            stmt.setInt(1, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
