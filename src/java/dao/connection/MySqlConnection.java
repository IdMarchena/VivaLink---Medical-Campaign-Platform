/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.connection;

import dao.connection.DataBaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class MySqlConnection implements DataBaseConnection{
    private Connection connection;
    private static final String URL="jdbc://mysql://localhost:5432/UsuarioService";
    private static final String USER="postgres";
    private static final String PASSWORD="12345678";

    public static Connection conexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Connection getConection() {
        try {
            return conexion();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
