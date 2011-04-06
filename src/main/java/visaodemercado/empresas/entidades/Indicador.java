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
	
}
