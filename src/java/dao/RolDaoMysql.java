/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dao.connection.DataBaseConnection;
import factory.DataBaseConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RolDaoMysql implements RolDao {
    private final Connection conn;
    public RolDaoMysql(DataBaseConnection conn) throws SQLException{
                DataBaseConnection db= DataBaseConnectionFactory.connection("mysql");
        this.conn=db.getConnection();
    }
    @Override
    public int buscarIdPorNombreRol(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> roles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String buscarNombreRolPorIdRol(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
