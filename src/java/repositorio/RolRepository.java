/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;
import java.sql.SQLException;
import java.util.List;
import dao.RolDao;
import factory.DaoFactory;
/**
 *
 * @author Usuario
 */
public class RolRepository {
    private final RolDao rolDao;
    public RolRepository() throws SQLException{
        this.rolDao=DaoFactory.getRol("postgres");
    }
    public int buscarIdPorNombreRol(String nombre){
        return rolDao.buscarIdPorNombreRol(nombre);
    }
    public List<String> roles(){
        return rolDao.roles();
    }

    public String buscarNombreRolPorIdRol(int id){
        return rolDao.buscarNombreRolPorIdRol(id);
    }
}
