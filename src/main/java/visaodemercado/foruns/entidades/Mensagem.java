package main.java.visaodemercado.foruns.entidades;

public class Mensagem {

	private String id;
	private String detalhes;
	private String html;
	private String resumo;
	private String urlPost;
	private Autor autor;
	
	public Mensagem(String detalhes, String html, String resumo, String urlPost) {
		super();
		this.detalhes = detalhes;
		this.html = html;
		this.resumo = resumo;
		this.urlPost = urlPost != null ? urlPost.split("&sid")[0] : null;
		this.id = this.urlPost != null ? this.urlPost.split("p=")[1] : null;
	}

	public String getId() {
		return id;
	}

	public String getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
	public String getHtml() {
		return html;
	}
	
	public void setHtml(String html) {
		this.html = html;
	}
	
	public String getResumo() {
		return resumo;
	}
	
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getUrlPost() {
		return urlPost;
	}

	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [id=" + id + ", detalhes=" + detalhes + ", html="
				+ html + ", resumo=" + resumo + ", urlPost=" + urlPost
				+ ", autor=" + autor + "]";
	}
	
}
