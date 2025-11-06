/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public interface UsurioDao {
    Usuario buscarPorId(int id);
    boolean verificarSiUsuarioExiste(int id);
    void guardar(Usuario usuario,int idRol);
    List<Usuario> buscarUsuariosPorRol(String rol);
    List<Usuario> listarTodosLosUsuarios();
    void actualizarUsuario(int id, Usuario usuario, int idRol);
    void eliminarUsuario(int id);
    
    
}
