package main.java.visaodemercado.foruns.entidades;

import java.util.List;

public class Forum {

	protected String id;
	protected String descricao;
	protected List<Topico> topicos;
	protected String info;
	protected String url;
	
	public Forum(String descricao, String info,String url) {
		super();
		this.descricao = descricao;
		this.info = info;
		this.url = url != null ? url.split("&")[0] : null;
		this.id = this.url != null ? this.url.split("f=")[1] : null;
	}
	
	public Forum(String id) {
		super();
		this.id = id;
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
	
	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		Forum other = (Forum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Forum [id=" + id + ", descricao=" + descricao + ", url="
				+ url + "]";
	}
	
}
