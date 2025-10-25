<%@page import="dto.UsuarioDto"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Usuario buscado</title>
</head>
<body>
    <h1>Usuario Buscado</h1>

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
                UsuarioDto usuario = (UsuarioDto) request.getAttribute("usuario");
                if (usuario != null ) {
            %>
                        <tr>
                            <td><%= usuario.id() %></td>
                            <td><%= usuario.nombre() %></td>
                            <td><%= usuario.correo() %></td>
                            <td><%= usuario.rol() %></td>
                        </tr>
            <% 
                } else {
            %>
                    <tr><td colspan="5">No hay usuarios registrados.</td></tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <a href="<%= request.getContextPath() %>/UsuarioServlet?action=crear">Volver al inicio</a>
</body>
</html>
