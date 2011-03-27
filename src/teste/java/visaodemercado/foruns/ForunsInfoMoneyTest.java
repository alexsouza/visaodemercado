package teste.java.visaodemercado.foruns;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import main.java.visaodemercado.foruns.InfoMoneyWebForumBO;
import main.java.visaodemercado.foruns.InfoMoneyWebForumBO.ForumId;
import main.java.visaodemercado.foruns.entidades.Forum;


public class ForunsInfoMoneyTest {
	
	private static InfoMoneyWebForumBO bo = new InfoMoneyWebForumBO();
	
	@Test
	public void teste_buscarTodos() {
		List<Forum> forums = bo.obterForuns();
		System.out.println(forums);
		assertNotNull(forums);
		assertTrue(forums.size() > 12);
	}
	
	@Test
	public void teste_buscarForumGeral() {
		buscarForum("15","Geral",ForumId.GERAL);
	}

	@Test
	public void teste_buscarForumAcoesIbovespa() {
		buscarForum("2","Ações Ibovespa",ForumId.ACOES_IBOVESPA);
	}
	
	@Test
	public void teste_buscarForumMidCaps() {
		buscarForum("3","Mid Caps",ForumId.MID_CAPS);
	}

	@Test
	public void teste_buscarForumSmallCaps() {
		buscarForum("4","Small Caps & Micos",ForumId.SMALL_CAPS);
	}

	@Test
	public void teste_buscarForumAnaliseTecnica() {
		buscarForum("5","Análise Técnica",ForumId.ANALISE_TECNICA);
	}

	@Test
	public void teste_buscarForumAnaliseFuncamentalista() {
		buscarForum("6","Análise Fundam.",ForumId.ANALISE_FUNDAMENTALISTA);
	}

	@Test
	public void teste_buscarForumTradeSystems() {
		buscarForum("17","Trade Systems",ForumId.TRADE_SYSTEMS);
	}

	@Test
	public void teste_buscarForumEducacional() {
		buscarForum("7","Educacional & Iniciantes",ForumId.EDUCACIONAL);
	}

	@Test
	public void teste_buscarForumCorretoras() {
		buscarForum("8","Corretoras & HBs",ForumId.CORRETORAS);
	}

	@Test
	public void teste_buscarForumOpcoes() {
		buscarForum("10","Opções & Futuros",ForumId.OPCOES);
	}

	@Test
	public void teste_buscarForumTributacao() {
		buscarForum("11","Tributação",ForumId.TRIBUTACAO);
	}

	@Test
	public void teste_buscarForumFundos() {
		buscarForum("12","Fundos e Renda Fixa",ForumId.FUNDOS);
	}

	@Test
	public void teste_buscarForumTopicosAnteriores() {
		buscarForum("1","Tópicos Anteriores",ForumId.TOPICOS_ANTERIORES);
	}
	
	private void buscarForum(String id,String descricao,ForumId forumId) {
		Forum forum = bo.obterForum(forumId);
		System.out.println(forum);
		assertNotNull(forum);
		assertEquals(descricao, forum.getDescricao());
		assertEquals(id, forum.getId());
	}
}
