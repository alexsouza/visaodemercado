package main.java.visaodemercado.empresas.entidades;

import java.util.ArrayList;
import java.util.List;

public class Indicador {

	public static enum ClassificacaoIndicador {
		GERAL,OSCILACOES,FUNDAMENTALISTAS,BALANCO_PATRIMONIAL,
		DEMONSTRATIVO_DE_RESULTADOS,DOZE_MESES,SEIS_MESES
	}

	private String nome;
	private String tip;
	private String valor;
	private List<ClassificacaoIndicador> tags;
	
	public Indicador(String nome, String tip, String valor) {
		super();
		this.nome = nome;
		this.tip = tip;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}
	
	public String getTip() {
		return tip;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void adicionarTag(ClassificacaoIndicador tag) {
		if (tags == null || tags.isEmpty()) {
			tags = new ArrayList<Indicador.ClassificacaoIndicador>();
		}
		tags.add(tag);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tip == null) ? 0 : tip.hashCode());
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
		Indicador other = (Indicador) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Indicador [nome=" + nome + ", tip=" + tip + ", valor=" + valor
				+ ", tags=" + tags + "]";
	}
	
}
