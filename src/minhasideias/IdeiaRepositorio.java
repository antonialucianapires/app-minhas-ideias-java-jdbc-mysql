package minhasideias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdeiaRepositorio {

	public static void cadastrarIdeia(Ideia ideia) {
		
		String sql = "INSERT INTO Ideias (Titulo, Descricao, DataCriacao) VALUES (?, ?, ?)";
		
		try (Connection conn = ConexaoBancoDeDados.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setString(1, ideia.getTitulo());
		        pstmt.setString(2, ideia.getDescricao());
		        pstmt.setString(3, ideia.getDataCriacao().toString());

		        pstmt.executeUpdate();

		    } catch (SQLException e) {
		    	System.out.println("Erro ao cadastrar ideia: " + e.getMessage());
		    }
		
	}
	
	public static List<Ideia> listarIdeias() {
	    List<Ideia> ideias = new ArrayList<>();
	    String sql = "SELECT * FROM Ideias";

	    try (Connection conn = ConexaoBancoDeDados.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            Ideia ideia = new Ideia();
	            ideia.setId(rs.getInt("ID"));
	            ideia.setTitulo(rs.getString("Titulo"));
	            ideia.setDescricao(rs.getString("Descricao"));
	            ideias.add(ideia);
	        }

	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }

	    return ideias;
	}

	
	public static Ideia consultarPorId(int id) {
	    String sql = "SELECT * FROM Ideias WHERE ID = ?";
	    Ideia ideia = null;

	    try (Connection conn = ConexaoBancoDeDados.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            ideia = new Ideia();
	            ideia.setId(rs.getInt("ID"));
	            ideia.setTitulo(rs.getString("Titulo"));
	            ideia.setDescricao(rs.getString("Descricao"));
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao consultar ideia: " + e.getMessage());
	    }

	    return ideia;
	}
	
	public static boolean atualizarIdeia(Ideia ideia) {
	    String sql = "UPDATE Ideias SET Titulo = ?, Descricao = ? WHERE ID = ?";

	    try (Connection conn = ConexaoBancoDeDados.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	             
	        pstmt.setString(1, ideia.getTitulo());
	        pstmt.setString(2, ideia.getDescricao());
	        pstmt.setInt(3, ideia.getId());

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;

	    } catch (SQLException e) {
	        System.out.println("Erro ao atualizar ideia: " + e.getMessage());
	        return false;
	    }
	}
	
	 public static boolean deletarIdeiaPorId(int id) {
	        String sql = "DELETE FROM Ideias WHERE ID = ?";

	        try (Connection conn = ConexaoBancoDeDados.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	                 
	            pstmt.setInt(1, id);

	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException e) {
	            System.out.println("Erro ao deletar ideia: " + e.getMessage());
	            return false;
	        }
	    }

	
	
}
