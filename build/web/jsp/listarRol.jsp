<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    List<String> roles = (List<String>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ver Roles</title>
</head>
<body>
    <h1>Lista de Roles</h1>

    <% if (roles != null && !roles.isEmpty()) { %>
        <ul>
            <% for (String rol : roles) { %>
                <li><%= rol %></li>
            <% } %>
        </ul>
    <% } else { %>
        <p>No se encontraron roles.</p>
    <% } %>

    <br>
    <a href="/UsuarioService/jsp/crearUsuario.jsp">Volver a la pagina principal</a>
</body>
</html>
