package minhasideias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDeDados {
	
	private static final String URL = "jdbc:mysql://localhost:3306/MinhasIdeias";
    private static final String USER = "root";
    private static final String PASSWORD = "my-secret-pw";
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
          
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
        }
    }
}
