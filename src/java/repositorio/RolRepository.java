/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class RolRepository {
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
    public int buscarIdPorNombreRol(String nombre){
        int idRol=0;
        String sql="SELECT id,nombre FROM roles WHERE LOWER(nombre)=LOWER(?)";
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, nombre.trim());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                idRol=rs.getInt("id");
            }
        } catch(SQLException e){
            throw new RuntimeException("error al buscar el cliente:"+e.getMessage(),e);
        } return idRol;        
    }
public List<String> roles(){
    String sql = "SELECT nombre FROM roles";
    List<String> roles = new ArrayList<>();
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) { 
            roles.add(rs.getString("nombre"));
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al buscar los roles: " + e.getMessage(), e);
    }
    return roles;
}

    public String buscarNombreRolPorIdRol(int id){
        String nombre="";
        String sql="SELECT r.nombre FROM roles r WHERE r.id=?";
        try(Connection conn = getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                nombre=rs.getString("r.nombre");
            }
        } catch(SQLException e){
            throw new RuntimeException("no se pudo encontrar el rol con ese id"+e.getMessage()+e);
        }
        
        
        return nombre;
    }
}
