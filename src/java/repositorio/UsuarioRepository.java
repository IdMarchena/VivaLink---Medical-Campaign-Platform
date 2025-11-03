/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import modelo.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.UsuarioDaoPostgre;
import dao.UsurioDao;
import dao.DaoFactory;

/**
 *
 * @author Usuario
 */
public class UsuarioRepository {
    private final UsurioDao usuarioDao;
    public UsuarioRepository() throws SQLException{
        this.usuarioDao=DaoFactory.getUsuario("postgres");
    }
    public Usuario buscarPorId(int id){
        return usuarioDao.buscarPorId(id);
    }

    public void guardar(Usuario usuario,int idRol){
        usuarioDao.guardar(usuario, idRol);
    }
    public List<Usuario> buscarUsuariosPorRol(String rol){
        return usuarioDao.buscarUsuariosPorRol(rol);
    }
    public List<Usuario> listarTodosLosUsuarios() {
        return usuarioDao.listarTodosLosUsuarios();
    }

    public void actualizarUsuario(int id, Usuario usuario, int idRol) {
        usuarioDao.actualizarUsuario(id, usuario, idRol);
    }

    public void eliminarUsuario(int id){
        usuarioDao.eliminarUsuario(id);
    }
}
