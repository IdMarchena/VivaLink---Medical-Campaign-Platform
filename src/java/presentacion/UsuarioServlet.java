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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService;
    private final Gson gson;
    
    public UsuarioServlet(UsuarioServiceImpl usuarioServiceImpl) throws SQLException{
        this.usuarioService= new UsuarioServiceImpl();
        this.gson=new Gson();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("esta es la accion que lleog"+action);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

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
        } else if("verificarUsuarioPorNombreYrol".equals(action)){
        
        }else {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "accion no reconocida: "+ action,
                    null,
                    HttpServletResponse.SC_BAD_REQUEST
            );
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    }
    private void verificarUsuarioPorNombreYrol(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String rol = request.getParameter("rol");

        // Verificamos si el usuario y rol son válidos
        boolean esValido = usuarioService.VerificarUsuarioPorNombreYRol(nombre, rol);
        JsonResponse<Object> jsonResponse= new JsonResponse<>(
                true,
                "verificacion completada",
                esValido,
                HttpServletResponse.SC_OK
        );
        response.getWriter().write(gson.toJson(jsonResponse));
    }
    
    private void verListaRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> roles = new ArrayList<>();
        roles=usuarioService.listarRoles();
        JsonResponse<Object> jsonResponse = new JsonResponse<>(
                true,
                "roles obtenidos exitosamente",
                roles,
                HttpServletResponse.SC_OK
        );
        response.getWriter().write(gson.toJson(jsonResponse));
    }   
    private void editarUsuario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idd = request.getParameter("id");
        int id = Integer.parseInt(idd);
        UsuarioDto usuario = usuarioService.buscarUsuarioPorId(id);
        
        if(usuario!=null){
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    true,
                    "usuario editado exitosamente",
                    usuario,
                    HttpServletResponse.SC_NOT_FOUND
            );
            response.getWriter().write(gson.toJson(jsonResponse));
        }else{
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "usuario editado exitosamente",
                    null,
                    HttpServletResponse.SC_OK
            );
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    }




    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<UsuarioDto> usuarios = usuarioService.listarUsuarios();
        if(usuarios.isEmpty()){
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    true,
                    "usuarios encontrados",
                    usuarios,
                    HttpServletResponse.SC_OK
            );
            response.getWriter().write(gson.toJson(jsonResponse));
        } else {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "no se encontraron los usuarios",
                    null,
                    HttpServletResponse.SC_NOT_FOUND
            );
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    }

    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("este es el id del usuario a buscar "+request.getParameter("id"));
        String idd = request.getParameter("id");
        int id = Integer.parseInt(idd);
        UsuarioDto usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    true,
                    "usuario encontrado",
                    usuario,
                    HttpServletResponse.SC_OK
            );
            response.getWriter().write(gson.toJson(jsonResponse));
        } else {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "usuario no encontrado",
                    null,
                    HttpServletResponse.SC_NOT_FOUND
            );
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    }
private void buscarUsuarioPorRol(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String rol = request.getParameter("rol");
    System.out.println("este es el rol que llego para ser buscado :  "+rol);
    
    if (rol != null && !rol.isEmpty()) {
        List<UsuarioDto> usuarios = usuarioService.buscaUsuariosPorRol(rol); // Llama al servicio para buscar usuarios por rol
        if (usuarios != null && !usuarios.isEmpty()) {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    true,
                    "usuario encontrado con rol",
                    usuarios,
                    HttpServletResponse.SC_OK
            );
            response.getWriter().write(gson.toJson(jsonResponse));
        } else {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "usuario no encontrado",
                    null,
                    HttpServletResponse.SC_NOT_FOUND
            );
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    } else {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "usuario no encontrado",
                    null,
                    HttpServletResponse.SC_NOT_FOUND
            );
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(gson.toJson(jsonResponse));
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
        JsonResponse<Object> jsonResponse = new JsonResponse<>(
                true,
                "usuario creado con exito",
                usuarioDto,
                HttpServletResponse.SC_CREATED
        );
        response.getWriter().write(gson.toJson(jsonResponse));
    } catch (IOException e) {
        JsonResponse<Object> jsonResponse = new JsonResponse<>(
                false,
                "usuario no encontrado",
                null,
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        );
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(gson.toJson(jsonResponse));
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
        JsonResponse<Object> jsonResponse = new JsonResponse<>(
                true,
                "usuario actualizado exitosamente",
                null,
                HttpServletResponse.SC_OK
        );
        response.getWriter().write(gson.toJson(jsonResponse));
    } catch (IOException e) {
        JsonResponse<Object> jsonResponse = new JsonResponse<>(
                false,
                "usuario no actualizado",
                null,
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        );  
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(gson.toJson(jsonResponse));
    }
}

private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
        String idd = request.getParameter("id");
        int id = Integer.parseInt(idd);
        if (id != 0 && id > 0) {
            usuarioService.eliminarUsuario(id);
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    true,
                    "usuario eliminado exitosamente",
                    null,
                    HttpServletResponse.SC_OK
            );
            response.getWriter().write(gson.toJson(jsonResponse));
        } else {
            JsonResponse<Object> jsonResponse = new JsonResponse<>(
                    false,
                    "usuario no eliminado",
                    null,
                    HttpServletResponse.SC_BAD_REQUEST
            );
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson(jsonResponse));
        }
    } catch (IOException | NumberFormatException e) {
        JsonResponse<Object> jsonResponse =new JsonResponse<>(
                false,
                "usuario no eliminado",
                null,
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        );
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(gson.toJson(jsonResponse));
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
