<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.UsuarioDto"%>
<%
    UsuarioDto usuario = (UsuarioDto) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Crear Usuario</title>
</head>
<body>

    <h1>Crear Nuevo Usuario</h1>
    
    <form action="<%= request.getContextPath() %>/UsuarioServlet?action=crear" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required><br>

        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" required><br>

        <label for="contrasenia">Contraseña:</label>
        <input type="password" id="contrasenia" name="contrasenia" required><br>

        <label for="rol">Rol:</label>
        <input type="text" id="rol" name="rol" required><br>

        <input type="submit" value="Crear Usuario">
    </form>

    <a href="<%= request.getContextPath() %>/UsuarioServlet?action=listar">Ver lista de usuarios</a> <!-- Cambiado para que use el servlet -->
    <a href="<%= request.getContextPath() %>/UsuarioServlet?action=listarRoles">Ver lista de roles</a>
    <a href="<%= request.getContextPath() %>/jsp/buscarUsuarioPorRol.jsp"> buscar usuarios por rol </a> 
</body>
</html>
