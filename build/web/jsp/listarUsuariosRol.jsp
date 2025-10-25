<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.UsuarioDto" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Usuarios por Rol</title>
</head>
<body>

    <h1>Lista de Usuarios con el Rol: <%= request.getParameter("rol") %></h1>

    <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
        <script>
            alert("<%= mensaje %>");
        </script>
    <%
        }
    %>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
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
                                <a href="<%= request.getContextPath() %>/UsuarioServlet?action=buscar&id=<%= usuario.id() %>">Ver</a> |
                                <a href="<%= request.getContextPath() %>/UsuarioServlet?action=editar&id=<%= usuario.id() %>">Editar</a> |
                                <a href="<%= request.getContextPath() %>/UsuarioServlet?action=eliminar&id=<%= usuario.id() %>"
                                   onclick="return confirm('¿Estás seguro de eliminar este usuario?')">Eliminar</a>
                            </td>
                        </tr>
            <% 
                    }
                } else {
            %>
                    <tr><td colspan="5">No se encontraron usuarios con el rol especificado.</td></tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <a href="<%= request.getContextPath() %>/jsp/buscarUsuarioPorRol.jsp">Buscar otro rol</a>
    <a href="<%= request.getContextPath() %>/UsuarioServlet?action=listar">Volver a la lista de usuarios</a>

</body>
</html>
