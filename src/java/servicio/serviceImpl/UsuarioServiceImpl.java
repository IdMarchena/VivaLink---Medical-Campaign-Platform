package servicio.serviceImpl;

import dto.UsuarioDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import servicio.service.UsuarioService;
import mapper.UsuarioMapper;
import repositorio.UsuarioRepository;
import repositorio.RolRepository;
import modelo.Usuario;

public class UsuarioServiceImpl implements UsuarioService{
    
    private final RolRepository rolRepositorio;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    public UsuarioServiceImpl() throws SQLException{
        this.rolRepositorio = new RolRepository();
        this.usuarioRepository = new UsuarioRepository();
        this.usuarioMapper = new UsuarioMapper();
    }
    @Override
    public void crearUsuario(UsuarioDto dto) {
        if(dto!=null){
            Usuario usuario=usuarioMapper.toEntity(dto);
            int idRol=rolRepositorio.buscarIdPorNombreRol(usuario.get_rol());
            if(idRol!=0){
                usuarioRepository.guardar(usuario, idRol);
            }
        }
    }

    @Override
    public UsuarioDto buscarUsuarioPorId(int id) {
        UsuarioDto usuarioDto= new UsuarioDto(id, null, null, null,null);
        if(id>0){
            Usuario usuario=usuarioRepository.buscarPorId(id);
            usuarioDto=usuarioMapper.toDto(usuario);
        }
        return usuarioDto;
    }

    @Override
    public void actualizarUsuario(int id, UsuarioDto dto) {
        System.out.println("antes de mappear el usuariodto tiene este rol"+dto.rol());
        if(id>0 && dto!=null){
            Usuario usuario=usuarioMapper.toEntity(dto);
            System.out.println("este es el nombre del usuario mappeado"+usuario.get_nombre());
            System.out.println("este es el nombre del usuario mappeado"+usuario.get_correo());
            System.out.println("este es el nombre del usuario mappeado"+usuario.get_contrasenia());
            System.out.println("este es el nombre del usuario mappeado"+usuario.get_rol());
            System.out.println("este es el rol en string"+usuario.get_rol());
            int idRol=buscarIdRolPorNombre(usuario.get_rol());
            System.out.println("este es el idrol luego de buscar "+idRol);
            usuarioRepository.actualizarUsuario(id, usuario, idRol);
        }
    }

    @Override
    public void eliminarUsuario(int id) {
        if(id>0){
            usuarioRepository.eliminarUsuario(id);
        }
    }

    @Override
    public List<UsuarioDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.listarTodosLosUsuarios();
        List<UsuarioDto> usuariosDtos = usuarioMapper.toListDto(usuarios);
        return usuariosDtos;
    }
    @Override
    public List<String> listarRoles(){
        List<String> roles= new ArrayList<>();
        roles=rolRepositorio.roles();
        return roles;
    }
    @Override
    public List<UsuarioDto> buscaUsuariosPorRol(String rol){
        List<UsuarioDto> usuarios = new ArrayList<>();
        List<Usuario> usuarioss = new ArrayList<>();        
        usuarioss=usuarioRepository.buscarUsuariosPorRol(rol);
        usuarios=usuarioMapper.toListDto(usuarioss);
        return usuarios;
    }
    @Override
    public int buscarIdRolPorNombre(String rol){
        return rolRepositorio.buscarIdPorNombreRol(rol);
    }
    @Override
    public String buscarNombreRolPorIdRol(int id){
        return rolRepositorio.buscarNombreRolPorIdRol(id);
    }
    
}
