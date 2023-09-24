package minhasideias;

import java.time.LocalDateTime;

public class Comentario {
	private int id;
    private String textoComentario;
    private LocalDateTime dataCriacao;
    private int ideiaID;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public int getIdeiaID() {
		return ideiaID;
	}
	public void setIdeiaID(int ideiaID) {
		this.ideiaID = ideiaID;
	}
	public String getTextoComentario() {
		return textoComentario;
	}
	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}
	@Override
    public String toString() {
        return "Comentario [id=" + id + ", textoComentario=" + textoComentario + ", ideiaID=" + ideiaID + "]";
    }

}
