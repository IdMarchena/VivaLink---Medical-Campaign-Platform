/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;
import dto.UsuarioDto;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
/**
 *
 * @author Usuario
 */
public class UsuarioMapper {
    public UsuarioDto toDto(Usuario usuario){
        if(usuario==null){
            return null;
        }
        UsuarioDto usuarioDto= new UsuarioDto(usuario.get_id(), 
                                              usuario.get_nombre(), 
                                              usuario.get_correo(), 
                                              usuario.get_contrasenia(), 
                                              usuario.get_rol());
        return usuarioDto;
    }
    
    public Usuario toEntity(UsuarioDto usuarioDto){
        if(usuarioDto==null){
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.set_id(usuarioDto.id());
        usuario.set_nombre(usuarioDto.nombre());
        usuario.set_correo(usuarioDto.correo());
        usuario.set_contrasenia(usuarioDto.contrasenia());
        usuario.set_rol(usuarioDto.rol());
        
        return usuario;
    }
    public List<UsuarioDto> toListDto(List<Usuario> usuarios){
        if(usuarios ==null){
            return null;
        }
        List<UsuarioDto> usuariosDtos= new ArrayList<>();
        for(Usuario usuario:usuarios){
            UsuarioDto usuarioDto=toDto(usuario);
            usuariosDtos.add(usuarioDto);
        }
        return usuariosDtos;
    }
    public List<Usuario> toListEntity(List<UsuarioDto> usuariosDto){
        if(usuariosDto == null){
            return null;
        }
        List<Usuario> usuarios = new ArrayList<>();
        for(UsuarioDto usuarioDto:usuariosDto){
            Usuario usuario= toEntity(usuarioDto);
            usuarios.add(usuario);
        }
        return usuarios;
    } 
    
}
