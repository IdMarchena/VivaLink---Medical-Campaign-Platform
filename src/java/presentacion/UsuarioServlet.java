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
import java.util.List;
import com.google.gson.Gson;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioService usuarioService;
    private final Gson gson;

    public UsuarioServlet() throws SQLException {
        this.usuarioService = new UsuarioServiceImpl();
        this.gson = new Gson();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println("Acción recibida: " + action);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        switch (action) {
            case "crear" -> crearUsuario(request, response);
            case "listar" -> listarUsuarios(request, response);
            case "buscar" -> buscarUsuario(request, response);
            case "actualizar" -> actualizarUsuario(request, response);
            case "eliminar" -> eliminarUsuario(request, response);
            case "listarRoles" -> listarRoles(request, response);
            case "buscarUsuarioPorRol" -> buscarUsuarioPorRol(request, response);
            case "verificarUsuarioPorNombreYrol" -> verificarUsuarioPorNombreYRol(request, response);
            default -> {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(gson.toJson("Acción no reconocida: " + action));
            }
        }
    }

    private void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");

        if (nombre == null || correo == null || contrasenia == null || rol == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("Todos los campos son requeridos"));
            return;
        }

        UsuarioDto usuario = new UsuarioDto(0, nombre, correo, contrasenia, rol);

        try {
            usuarioService.crearUsuario(usuario);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(gson.toJson("Usuario creado con éxito"));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Error al crear usuario: " + e.getMessage()));
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<UsuarioDto> usuarios = usuarioService.listarUsuarios();
            response.getWriter().write(gson.toJson(usuarios));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Error al listar usuarios: " + e.getMessage()));
        }
    }

    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("Parámetro id requerido"));
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            UsuarioDto usuario = usuarioService.buscarUsuarioPorId(id);

            if (usuario != null) {
                response.getWriter().write(gson.toJson(usuario));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(gson.toJson("Usuario no encontrado"));
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("ID inválido"));
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("Parámetro id requerido"));
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String contrasenia = request.getParameter("contrasenia");
            String rol = request.getParameter("rol");

            UsuarioDto usuario = new UsuarioDto(id, nombre, correo, contrasenia, rol);

            usuarioService.actualizarUsuario(id, usuario);
            response.getWriter().write(gson.toJson("Usuario actualizado con éxito"));

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("ID inválido"));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(gson.toJson("Error al actualizar usuario: " + e.getMessage()));
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);
            try{
                usuarioService.eliminarUsuario(id);
                response.getWriter().write(gson.toJson("usuario eliminaod con exito"));
            } catch (Exception e){
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("usuario no pudo ser encontrado por tanto no pudo ser eliminado");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("ID inválido"));
        }
    }

    private void listarRoles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> roles = usuarioService.listarRoles();
        response.getWriter().write(gson.toJson(roles));
    }

    private void buscarUsuarioPorRol(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rol = request.getParameter("rol");

        if (rol == null || rol.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(gson.toJson("El rol es requerido"));
            return;
        }

        List<UsuarioDto> usuarios = usuarioService.buscaUsuariosPorRol(rol);

        if (usuarios.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(gson.toJson("No existen usuarios con ese rol"));
        } else {
            response.getWriter().write(gson.toJson(usuarios));
        }
    }

    private void verificarUsuarioPorNombreYRol(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombre");
        String rol = request.getParameter("rol");

        boolean existe = usuarioService.VerificarUsuarioPorNombreYRol(nombre, rol);
        response.getWriter().write(gson.toJson(existe));
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
