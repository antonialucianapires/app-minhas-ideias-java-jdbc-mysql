package minhasideias;

import java.time.LocalDateTime;

public class Ideia {
	   private int id;
	    private String titulo;
	    private String descricao;
	    private LocalDateTime dataCriacao;
		
	    public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitulo() {
			return titulo;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public LocalDateTime getDataCriacao() {
			return dataCriacao;
		}
		public void setDataCriacao(LocalDateTime dataCriacao) {
			this.dataCriacao = dataCriacao;
		}
	    
		@Override
		public String toString() {
		    return "Ideia{" +
		           "ID=" + id +
		           ", Titulo='" + titulo + '\'' +
		           ", Descricao='" + descricao + '\'' +
		           ", DataCriacao=" + dataCriacao +
		           '}';
		}

	    
}
