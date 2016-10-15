/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipr.projekt.program;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michal
 */
public class MySql {

    private static MySql instane = new MySql();
    private Connection connect = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    MySql() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String user = "root";
            String password = "projekt";
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/ipr?useUnicode=true&characterEncoding=utf-8", user, password);
            System.out.println("MySQL connection OK");;
        } catch (Exception e) {
            e.printStackTrace();
            closeConnection();
            JOptionPane.showMessageDialog(null, "Brak połączenia z bazą danych! Program zostanie zamknięty!");
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return connect;
    }

    // You need to close the resultSet
    public void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        }
        System.out.println("MySQL connection closed!");
    }

    public static MySql getInstance() {
        return instane;
    }
}
