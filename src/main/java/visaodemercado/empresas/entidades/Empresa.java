package main.java.visaodemercado.empresas.entidades;

import java.util.List;

public class Empresa {

	private String codigo;
	private String nomeComercial;
	private String razaoSocial;
	private List<Indicador> indicadores;
	
	public Empresa(String codigo, String nomeComercial, String razaoSocial) {
		super();
		this.codigo = codigo;
		this.nomeComercial = nomeComercial;
		this.razaoSocial = razaoSocial;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Empresa other = (Empresa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [codigo=" + codigo + ", nomeComercial=" + nomeComercial
				+ ", razaoSocial=" + razaoSocial + ", indicadores="
				+ indicadores + "]";
	}
	
}
