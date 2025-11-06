/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class DataBaseConnectionFactory {
    public static DataBaseConnection connection(String tipoDb) throws SQLException{
        switch (tipoDb.toLowerCase()) {
            case "postgre":
                return (DataBaseConnection) PostgreConnection.conexion();
            case "mysql":
                return (DataBaseConnection) MySqlConnection.conexion();
            default:
                throw new AssertionError();
        }
    }
    
}
