<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.UsuarioDto"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Buscar Usuarios por Rol</title>
</head>
<body>

    <h1>Buscar Usuarios por Rol</h1>

    <!-- Formulario para buscar usuarios por rol -->
    <form action="<%= request.getContextPath() %>/UsuarioServlet" method="get">
        <label for="rol">Ingrese el Rol:</label>
        <input type="text" id="rol" name="rol" required>
        
        <!-- Campo oculto para enviar la acción al servlet -->
        <input type="hidden" name="action" value="buscarUsuarioPorRol">
        
        <input type="submit" value="Buscar">
    </form>

    <!-- Mostrar mensaje si hay resultados o si hubo algún error -->
    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
        <p><%= mensaje %></p>
    <% 
        }
    %>

    <!-- Mostrar la lista de usuarios filtrados por rol -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol</th>
            </tr>
        </thead>
        <tbody>
            <%
                // Obtener la lista de usuarios desde la request
                List<UsuarioDto> usuarios = (List<UsuarioDto>) request.getAttribute("usuarios");
                if (usuarios != null && !usuarios.isEmpty()) {
                    for (UsuarioDto usuario : usuarios) {
            %>
                        <tr>
                            <td><%= usuario.id() %></td>
                            <td><%= usuario.nombre() %></td>
                            <td><%= usuario.correo() %></td>
                            <td><%= usuario.rol() %></td>
                            <td>
                                <a href="<%= request.getContextPath() %>/UsuarioServlet?action=buscar&id=<%= usuario.id() %>">Ver</a>
                                <a href="<%= request.getContextPath() %>/UsuarioServlet?action=editar&id=<%= usuario.id() %>">Editar</a> |
                                <a href="<%= request.getContextPath() %>/UsuarioServlet?action=eliminar&id=<%= usuario.id() %>"
                                   onclick="return confirm('¿Estás seguro de eliminar este usuario?')">Eliminar</a>
                            </td>
                        </tr>
            <% 
                    }
                } else {
            %>
                    <tr><td colspan="5">No se encontraron usuarios con ese rol.</td></tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <!-- Enlace para volver a la lista general de usuarios -->
    <a href="<%= request.getContextPath() %>/UsuarioServlet?action=listar">Volver a la lista de usuarios</a>

</body>
</html>
