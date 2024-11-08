import java.sql.*;

public class DataBaseUtils {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //DRIVER
    private static final String URL = "jdbc:mysql://localhost:3306/"; //LOCALHOST -- 3306 PADRÃO WAMP MYSQL

    // OBTER CONEXÃO BD
    public static Connection getConnection(String nomeBanco, String usuario, String senha)
            throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);  // CARREGO O DRIVER
        return DriverManager.getConnection(URL + nomeBanco, usuario, senha);  // ESTABELECE CONEXÃO COM O BD
    }

    // INSERIR DADOS DO BD
    public static boolean insert(Connection conn, String sql) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            return true;  // SE DER CERTO RETURN TRUE
        }
    }

}
