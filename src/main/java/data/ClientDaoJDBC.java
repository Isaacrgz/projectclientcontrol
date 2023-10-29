package data;

import java.sql.*;
import java.util.*;
import domain.Client;

public class ClientDaoJDBC {
    private static final String SQL_SELECT = "SELECT id_client, name, lastname, email, phone, balance FROM client";
    private static final String SQL_SELECT_BY_ID = "SELECT id_client, name, lastname, email, phone, balance FROM client WHERE id_client=?";
    private static final String SQL_INSERT = "INSERT INTO client(name, lastname, email, phone, balance) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE client SET name=?, lastname=?, email=?, phone=?, balance=? WHERE id_client=?";
    private static final String SQL_DELETE = "DELETE FROM client WHERE id_client=?";

    // crear una interface para mejorar el programa

    public List<Client> list() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Client client = null;
        List<Client> clients = new ArrayList<>();

        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                int idClient = rs.getInt("id_client");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                double balance = rs.getDouble("balance");

                client = new Client(idClient, name, lastname, email, phone, balance);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            ConnectionSQL.close(rs);
            ConnectionSQL.close(stmt);
            ConnectionSQL.close(conn);
        }

        return clients;
    }

    public Client find(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, client.getIdClient());
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto (NO FUNCIONA) 
            rs.next(); //nos posicionamos en el primer registro devuelto

            String name = rs.getString("name");
            String lastname = rs.getString("lastname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            double balance = rs.getDouble("balance");

            client.setName(name);
            client.setLastname(lastname);
            client.setEmail(email);
            client.setPhone(phone);
            client.setBalance(balance);
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            ConnectionSQL.close(rs);
            ConnectionSQL.close(stmt);
            ConnectionSQL.close(conn);
        }

        return client;
    }

    public int insert(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getLastname());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPhone());
            stmt.setDouble(5, client.getBalance());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            ConnectionSQL.close(stmt);
            ConnectionSQL.close(conn);
        }

        return rows;
    }

    public int update(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getLastname());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getPhone());
            stmt.setDouble(5, client.getBalance());
            stmt.setInt(6, client.getIdClient());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            ConnectionSQL.close(stmt);
            ConnectionSQL.close(conn);
        }

        return rows;
    }

    public int delete(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionSQL.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, client.getIdClient());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            ConnectionSQL.close(stmt);
            ConnectionSQL.close(conn);
        }

        return rows;
    }
}
