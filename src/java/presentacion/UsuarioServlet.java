package presentacion;
import servicio.service.UsuarioService;
import servicio.serviceImpl.UsuarioServiceImpl;
import dto.UsuarioDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import repositorio.RolRepository;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioServiceImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("esta es la accion que lleog"+action);

        if ("crear".equals(action)) {
            crearUsuario(request, response);
        } else if ("listar".equals(action)) {
            listarUsuarios(request, response);
        } else if ("buscar".equals(action)) {
            buscarUsuario(request, response);
        } else if ("actualizar".equals(action)) {
            actualizarUsuario(request, response);
        } else if ("eliminar".equals(action)) {
            eliminarUsuario(request, response);
        } else if ("listarRoles".equals(action)){
            verListaRoles(request,response);
        } else if("editar".equals(action)){
            editarUsuario(request,response);
        } else if("buscarUsuarioPorRol".equals(action)){
            buscarUsuarioPorRol(request,response);
        } else if("buscarUsuarioPorRol".equals(action)){
            buscarUsuarioPorRol(request,response);
        } else {
            // Otras acciones
        }
    }

    
    private void verListaRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> roles = new ArrayList<>();
        roles=usuarioService.listarRoles();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/jsp/listarRol.jsp").forward(request, response);
    }   
    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idd = request.getParameter("id");
        int id = Integer.parseInt(idd);
        UsuarioDto usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/jsp/editarUsuario.jsp").forward(request, response);
        } else {
            response.sendRedirect("/jsp/usuarioNoEncontrado.jsp");
        }
    }




    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<UsuarioDto> usuarios = usuarioService.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/jsp/listarUsuarios.jsp").forward(request, response);
        
    }

    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("este es el id del usuario a buscar "+request.getParameter("id"));
        String idd = request.getParameter("id");
        int id = Integer.parseInt(idd);
        UsuarioDto usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("/jsp/verUsuario.jsp").forward(request, response);
        } else {
            response.sendRedirect("/jsp/usuarioNoEncontrado.jsp");
        }
    }
private void buscarUsuarioPorRol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String rol = request.getParameter("rol");
    System.out.println("este es el rol que llego para ser buscado :  "+rol);
    
    if (rol != null && !rol.isEmpty()) {
        List<UsuarioDto> usuarios = usuarioService.buscaUsuariosPorRol(rol); // Llama al servicio para buscar usuarios por rol
        
        if (usuarios != null && !usuarios.isEmpty()) {
            request.setAttribute("usuarios", usuarios);
            request.setAttribute("mensaje", "Usuarios encontrados con éxito.");
            request.getRequestDispatcher("/jsp/listarUsuariosRol.jsp").forward(request, response); // Redirige al JSP que muestra los usuarios
        } else {
            request.setAttribute("mensaje", "No se encontraron usuarios con ese rol.");
            request.getRequestDispatcher("/jsp/listarUsuariosRol.jsp").forward(request, response); // Redirige al JSP con mensaje de error
        }
    } else {
        request.setAttribute("mensaje", "Por favor ingrese un rol.");
        request.getRequestDispatcher("/jsp/buscarUsuarioPorRol.jsp").forward(request, response); // Redirige si no se ingresó rol
    }
}


private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String nombre = request.getParameter("nombre");
    String correo = request.getParameter("correo");
    String contrasenia = request.getParameter("contrasenia");
    String rol = request.getParameter("rol");

    UsuarioDto usuarioDto = new UsuarioDto(0, nombre, correo, contrasenia, rol);
    try {
        usuarioService.crearUsuario(usuarioDto);
        // Establecer el mensaje de éxito
        request.setAttribute("mensaje", "Usuario creado con éxito.");
        listarUsuarios(request, response);
    } catch (Exception e) {
        request.setAttribute("mensaje", "Hubo un error al crear el usuario.");
        request.getRequestDispatcher("/jsp/crearUsuario.jsp").forward(request, response);
    }
}

private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String idd = request.getParameter("id");
    int id = Integer.parseInt(idd);
    String nombre = request.getParameter("nombre");
    String correo = request.getParameter("correo");
    String contrasenia = request.getParameter("contrasenia");
    String rol = request.getParameter("rol");

    UsuarioDto usuarioDto = new UsuarioDto(id, nombre, correo, contrasenia, rol);
    try {
        usuarioService.actualizarUsuario(id, usuarioDto);
        // Establecer el mensaje de éxito
        request.setAttribute("mensaje", "Usuario actualizado con éxito.");
        listarUsuarios(request, response);
    } catch (Exception e) {
        request.setAttribute("mensaje", "Hubo un error al actualizar el usuario.");
        request.getRequestDispatcher("/jsp/editarUsuario.jsp").forward(request, response);
    }
}

private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
        String idd = request.getParameter("id");
        int id = Integer.parseInt(idd);
        if (id != 0 && id > 0) {
            usuarioService.eliminarUsuario(id);
            // Establecer el mensaje de éxito
            request.setAttribute("mensaje", "Usuario eliminado con éxito.");
            listarUsuarios(request, response);
        }
    } catch (Exception e) {
        request.setAttribute("mensaje", "Hubo un error al eliminar el usuario.");
        request.getRequestDispatcher("/jsp/listarUsuarios.jsp").forward(request, response);
    }
}

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


}
