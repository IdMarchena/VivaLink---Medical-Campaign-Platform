/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import dao.DataBaseConnection;

/**
 *
 * @author Usuario
 */
public class UsuarioDaoPostgre implements UsurioDao{
    private final Connection conn;
    
    public UsuarioDaoPostgre(DataBaseConnection conn) throws SQLException{
        this.conn=(Connection) conn;
    }
    @Override
    public Usuario buscarPorId(int id){
        String sql = """
            SELECT u.id, u.nombre, u.correo, u.contrasenia, r.nombre AS rol
            FROM usuarios u
            JOIN roles r ON u.idRol = r.id
            WHERE u.id=?
            """;
        try(
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Usuario usuario= new Usuario(rs.getInt("id"),
                                             rs.getString("nombre"),
                                             rs.getString("correo"),
                                             rs.getString("contrasenia"),
                                             rs.getString("rol"));
                return usuario;
            }
        } catch(SQLException e){
            throw new RuntimeException("error al buscar el cliente:"+e.getMessage(),e);
        } return null;        
    }
    @Override
    public boolean verificarSiUsuarioExiste(int id){
        Usuario usuario = null;
        boolean bandera=false;
        usuario=buscarPorId(id);
        if(usuario!=null){
            bandera=true;
        }
        return bandera;
    }
    @Override
    public void guardar(Usuario usuario,int idRol){
        String sql="Insert Into usuarios(nombre,correo,contrasenia,idRol) VALUES (?,?,?,?)";
        if(!verificarSiUsuarioExiste(usuario.get_id())){
            try(
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, usuario.get_nombre());
                ps.setString(2, usuario.get_correo());
                ps.setString(3, usuario.get_contrasenia());
                ps.setInt(4, idRol);
                ps.executeUpdate();
            } catch(SQLException e){
                throw new RuntimeException("error al guardar el usuario:" +e.getMessage()+e);
            }
        }
    }
    @Override
    public List<Usuario> buscarUsuariosPorRol(String rol){
        List<Usuario> usuarios =new ArrayList<>();
        String sql="""
                SELECT u.id, u.nombre, u.correo, u.contrasenia, r.nombre AS rol
                FROM usuarios u
                JOIN roles r ON u.idRol=r.id
                WHERE LOWER(r.nombre)=LOWER(?)
                """;
        try(
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, rol);
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                Usuario usuario= new Usuario(rs.getInt("id"),
                                             rs.getString("nombre"),
                                             rs.getString("correo"),
                                             rs.getString("contrasenia"),
                                             rs.getString("rol"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch(SQLException e ){
            throw new RuntimeException("no se encontraron usuarios con ese rol"+e.getMessage()+e);
                    
        }
    }
    @Override
    public List<Usuario> listarTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = """
            SELECT u.id, u.nombre, u.correo, u.contrasenia, r.nombre AS rol
            FROM usuarios u
            JOIN roles r ON u.idRol = r.id
            """;

        try (
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Usuario usuario = new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasenia"),
                rs.getString("rol")
            );
            usuarios.add(usuario);
        }
        return usuarios;

    } catch (SQLException e) {
        throw new RuntimeException("No se pudieron listar los usuarios: " + e.getMessage(), e);
    }
}

    @Override
    public void actualizarUsuario(int id, Usuario usuario, int idRol) {
        if (usuario == null || id <= 0) {
            throw new IllegalArgumentException("El usuario no debe tener id inválido ni nulo");
        }

        if (!verificarSiUsuarioExiste(id)) {
            throw new RuntimeException("El usuario con id " + id + " no existe.");
        }

        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, contrasenia = ?, idRol = ? WHERE id = ?";

        try (
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.get_nombre());
            ps.setString(2, usuario.get_correo());
            ps.setString(3, usuario.get_contrasenia());
            ps.setInt(4, idRol); // ⚠️ Asegúrate de que este idRol exista en la tabla roles
            ps.setInt(5, id);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas < 1) {
                throw new RuntimeException("El usuario no se pudo actualizar porque no se encontró ninguna fila.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("El usuario no se pudo actualizar: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarUsuario(int id){
        if(id<=0){
            throw new IllegalArgumentException("el usuario no debe tener id negativo");
        }
        if(verificarSiUsuarioExiste(id)){
            String sql="DELETE FROM usuarios WHERE id=?";
            try(
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, id);
                int filasAfectadas= ps.executeUpdate();
                if(filasAfectadas<1){
                    throw new SQLException("no se ejecuto la eliminación del usuario");
                }
            } catch(SQLException e){
                throw new RuntimeException("el usuario no pudo ser eliminado de la base de datos"+e.getMessage()+e);
            }
        }
    }
    
}
