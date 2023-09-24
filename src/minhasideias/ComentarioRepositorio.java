package minhasideias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioRepositorio {
	
	public static void cadastrarComentario(Comentario comentario) {
	    String sql = "INSERT INTO Comentarios (TextoComentario, DataCriacao, IdeiaID) VALUES (?, ?, ?)";

	    try (Connection conn = ConexaoBancoDeDados.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, comentario.getTextoComentario());
	        pstmt.setString(2, comentario.getDataCriacao().toString());
	        pstmt.setInt(3, comentario.getIdeiaID());

	        pstmt.executeUpdate();
	        System.out.println("Comentário cadastrado com sucesso!");

	    } catch (SQLException e) {
	        System.out.println("Erro ao cadastrar comentário: "+ e.getMessage());
	    }
	}
	
	public static List<Comentario> listarComentariosPorIdeiaId(int ideiaId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM Comentarios WHERE IdeiaID = ?";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, ideiaId);  // Definindo o valor do parâmetro IdeiaID na consulta SQL
            ResultSet rs = pstmt.executeQuery();

            // Iterando sobre o conjunto de resultados e preenchendo a lista de comentários
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getInt("ID"));
                comentario.setTextoComentario(rs.getString("TextoComentario"));
                comentario.setIdeiaID(rs.getInt("IdeiaID"));
                comentarios.add(comentario);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return comentarios;
    }

	
	public static Comentario consultarPorId(int id) {
        Comentario comentario = new Comentario();
        String sql = "SELECT * FROM Comentarios WHERE ID = ?";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);  // Definindo o valor do parâmetro ID na consulta SQL
            ResultSet rs = pstmt.executeQuery();

     
            while (rs.next()) {
                comentario.setId(rs.getInt("ID"));
                comentario.setTextoComentario(rs.getString("TextoComentario"));
                comentario.setIdeiaID(rs.getInt("IdeiaID"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return comentario;
    }
	
	public static boolean atualizarComentario(int comentarioId, int ideiaId, String novoTexto) {
	    String sql = "UPDATE Comentarios SET TextoComentario = ? WHERE ID = ? AND IdeiaID = ?";

	    try (Connection conn = ConexaoBancoDeDados.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	             
	        pstmt.setString(1, novoTexto);
	        pstmt.setInt(2, comentarioId);
	        pstmt.setInt(3, ideiaId);

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
	
	 public static boolean deletarComentariosPorIdeiaId(int ideiaId) {
	        String sql = "DELETE FROM Comentarios WHERE IdeiaID = ?";

	        try (Connection conn = ConexaoBancoDeDados.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	                 
	            pstmt.setInt(1, ideiaId);

	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            System.out.println("Erro ao deletar comentarios: " + e.getMessage());
	            return false;
	        }
	    }
}
