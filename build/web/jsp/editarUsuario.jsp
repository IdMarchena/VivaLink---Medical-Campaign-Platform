<%@page import="dto.UsuarioDto"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsuarioDto usuario = (UsuarioDto) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
</head>
<body>
    <h1>Editar Usuario</h1>

    <form action="<%= request.getContextPath() %>/UsuarioServlet?action=actualizar" method="post">
        <label>ID:</label><br>
        <input type="text" name="id" value="<%= usuario.id() %>" readonly required onclick="alert('Esto no se puede editar')" /><br><br>

        <label>Nombre:</label><br>
        <input type="text" name="nombre" value="<%= usuario.nombre() %>" required><br><br>

        <label>Correo:</label><br>
        <input type="email" name="correo" value="<%= usuario.correo() %>" required><br><br>

        <label>Contraseña:</label><br>
        <input type="password" name="contrasenia" value="<%= usuario.contrasenia() %>" required><br><br>

        <label>Rol:</label><br>
        <input type="text" name="rol" value="<%= usuario.rol() %>" readonly required onclick="alert('Esto no se puede editar')" /><br><br>

        <input type="submit" value="Actualizar">
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/UsuarioServlet?action=listar">Volver a la lista</a>
</body>
</html>
