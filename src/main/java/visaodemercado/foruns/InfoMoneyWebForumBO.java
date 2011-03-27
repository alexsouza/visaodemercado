package main.java.visaodemercado.foruns;

import java.util.ArrayList;
import java.util.List;

import main.java.visaodemercado.foruns.entidades.Forum;
import main.java.visaodemercado.foruns.entidades.Topico;
import main.java.visaodemercado.foruns.gerenciador.GerenciadorWebForum;
import main.java.visaodemercado.foruns.gerenciador.GerenciadorWebForumImpl;


public class InfoMoneyWebForumBO  {

	private static GerenciadorWebForum gerenciador = new GerenciadorWebForumImpl();
	
	public static enum ForumId {
		GERAL,ACOES_IBOVESPA,MID_CAPS,SMALL_CAPS,ANALISE_TECNICA,ANALISE_FUNDAMENTALISTA,
		TRADE_SYSTEMS,EDUCACIONAL,CORRETORAS,OPCOES,TRIBUTACAO,FUNDOS,TOPICOS_ANTERIORES
	}
	
	public List<Forum> obterForuns() {
		return gerenciador.obterForuns();
	}

	public Forum obterTopicosDoForum(ForumId forumId) {
		return gerenciador.obterTopicosDoForum(obterForum(forumId));
	}
	
	public Forum obterForum(ForumId forumId) {
		switch (forumId) {
		case GERAL:
			return gerenciador.obterForumPorId("15");
		case ACOES_IBOVESPA:
			return gerenciador.obterForumPorId("2");
		case MID_CAPS:
			return gerenciador.obterForumPorId("3");
		case SMALL_CAPS:
			return gerenciador.obterForumPorId("4");
		case ANALISE_TECNICA:
			return gerenciador.obterForumPorId("5");
		case ANALISE_FUNDAMENTALISTA:
			return gerenciador.obterForumPorId("6");
		case TRADE_SYSTEMS:
			return gerenciador.obterForumPorId("17");
		case EDUCACIONAL:
			return gerenciador.obterForumPorId("7");
		case CORRETORAS:
			return gerenciador.obterForumPorId("8");
		case OPCOES:
			return gerenciador.obterForumPorId("10");
		case TRIBUTACAO:
			return gerenciador.obterForumPorId("11");
		case FUNDOS:
			return gerenciador.obterForumPorId("12");
		case TOPICOS_ANTERIORES:
			return gerenciador.obterForumPorId("1");

		default:
			return null;
		}
	}
	
	public void obterMensagensDoTopicoRecentes(Topico topico) {
		gerenciador.obterTodasMensagensDoTopico(topico, false);
	}

	public void obterMensagensDoTopicoAntigas(Topico topico) {
		gerenciador.obterTodasMensagensDoTopico(topico, true);
	}

	public List<Topico> atualizarMensagensDosTopicosDoForum(Forum forum) {
		List<Topico> topicos = null;
		if (forum.getTopicos() != null && !forum.getTopicos().isEmpty()) {
			topicos = new ArrayList<Topico>();
			for (Topico topico : forum.getTopicos()) {
				if (topico.getMensagens() != null &&
						topico.getMensagens().size() != (Integer.parseInt(topico.getRespostas()) + 1)) {
					//atualiza mensagens
					gerenciador.atualizarMensagensDoTopico(topico);
					topicos.add(topico);
				}
			}
		}
		return topicos;
	}
	
	public void atualizarTodasAsMensagens(List<Forum> foruns) {
		for (Forum forum : foruns) {
			atualizarMensagensDosTopicosDoForum(forum);
		}
	}
	
}
