
package dao.connection;
import dao.connection.DataBaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Usuario
 */
// dao/PostgreConnection.java
public class PostgreConnection implements DataBaseConnection {
    private Connection connection;
    private static final String URL="jdbc://postgresql://localhost:5432/UsuarioService";
    private static final String USER="postgres";
    private static final String PASSWORD="12345678";

    public static Connection conexion() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Connection getConection() {
        try {
            return conexion();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

