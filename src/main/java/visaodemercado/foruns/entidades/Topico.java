package main.java.visaodemercado.foruns.entidades;

import java.util.List;


public class Topico {

	private String id;
	private String descricao;
	private String respostas;
	private String exibicoes;
	private String ultimaMensagem;
	private String url;
	private List<Mensagem> mensagens;
	
	public Topico(String descricao, String respostas, String exibicoes,
			String ultimaMensagem, String url) {
		super();
		this.descricao = descricao;
		this.respostas = respostas;
		this.exibicoes = exibicoes;
		this.ultimaMensagem = ultimaMensagem;
		this.url = url != null ? url.split("&")[0] : null;
		this.id = this.url != null ? this.url.split("t=")[1] : null;
	}

	public String getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getRespostas() {
		return respostas;
	}
	
	public void setRespostas(String respostas) {
		this.respostas = respostas;
	}
	
	public String getExibicoes() {
		return exibicoes;
	}
	
	public void setExibicoes(String exibicoes) {
		this.exibicoes = exibicoes;
	}
	
	public String getUltimaMensagem() {
		return ultimaMensagem;
	}
	
	public void setUltimaMensagem(String ultimaMensagem) {
		this.ultimaMensagem = ultimaMensagem;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
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
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Topico [id=" + id + ", descricao=" + descricao + ", respostas="
				+ respostas + ", exibicoes=" + exibicoes + ", ultimaMensagem="
				+ ultimaMensagem + ", url=" + url + ", mensagens=" + mensagens
				+ "]";
	}
	
}
