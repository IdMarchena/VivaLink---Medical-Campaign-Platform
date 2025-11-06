/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dao.UsurioDao;
import dao.UsuarioDaoPostgre;
import dao.UsuarioDaoMongo;
import java.sql.SQLException;
import dao.RolDaoMongo;
import dao.RolDaoPostgre;

/**
 *
 * @author Usuario
 */
public class DaoFactory {
    public static UsurioDao getUsuario(String tipo) throws SQLException{
        switch(tipo.toLowerCase()){
            case "postgres" -> {
                return new UsuarioDaoPostgre();
            }
            case "mysql" -> {
                return new UsuarioDaoMySql();
            }
            case "mongo" ->{
                return new UsuarioDaoMongo();
            }
            default -> throw new IllegalArgumentException("tipo de dao no soportado: "+ tipo);
        }
    }
    public static RolDao getRol(String tipo) throws SQLException{
        switch(tipo.toLowerCase()){
            case "postgres" -> {
                return new RolDaoPostgre();
            }
            case "mysql" -> {
                return new RolDaoMysql();
            }
            case "mongo" -> {
                return new RolDaoMongo();
            }
            default -> throw new IllegalArgumentException("tipo de dao no soportado: "+tipo);
        }
    }
    
}
