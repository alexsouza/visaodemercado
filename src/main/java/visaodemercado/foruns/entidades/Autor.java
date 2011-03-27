package main.java.visaodemercado.foruns.entidades;

public class Autor {

	private String apelido;
	private String detalhes;
	private String urlAvatar;
	private String urlPerfil;
	
	public Autor(String apelido, String detalhes, String urlAvatar,
			String urlPerfil) {
		super();
		this.apelido = apelido;
		this.detalhes = detalhes;
		this.urlAvatar = urlAvatar;
		this.urlPerfil = urlPerfil != null ? urlPerfil.split("&sid")[0] : null;
	}
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public String getUrlAvatar() {
		return urlAvatar;
	}
	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}
	public String getUrlPerfil() {
		return urlPerfil;
	}
	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
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
		Autor other = (Autor) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Autor [apelido=" + apelido + ", detalhes=" + detalhes
				+ ", urlAvatar=" + urlAvatar + ", urlPerfil=" + urlPerfil + "]";
	}
	
}
