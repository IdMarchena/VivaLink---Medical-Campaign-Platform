/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/UsuarioService";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // carga el driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error cargando el driver de postgress", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
}
