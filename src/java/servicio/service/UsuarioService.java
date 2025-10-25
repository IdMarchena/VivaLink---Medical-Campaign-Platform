
package servicio.service;
import dto.UsuarioDto;
import java.util.List;
public interface UsuarioService {
    void crearUsuario(UsuarioDto dto);
    UsuarioDto buscarUsuarioPorId(int id);
    void actualizarUsuario(int id, UsuarioDto dto);
    void eliminarUsuario(int id);
    List<UsuarioDto> listarUsuarios();
    List<String> listarRoles();
    int buscarIdRolPorNombre(String nombre);
    String buscarNombreRolPorIdRol(int id);
    List<UsuarioDto> buscaUsuariosPorRol(String rol);
}
