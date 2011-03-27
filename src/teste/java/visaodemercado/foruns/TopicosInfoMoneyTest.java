package teste.java.visaodemercado.foruns;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import main.java.visaodemercado.foruns.InfoMoneyWebForumBO;
import main.java.visaodemercado.foruns.InfoMoneyWebForumBO.ForumId;
import main.java.visaodemercado.foruns.entidades.Forum;


public class TopicosInfoMoneyTest {

	private static InfoMoneyWebForumBO infoMoneyBO = new InfoMoneyWebForumBO();
	
	@Test
	public void teste_obterTopicosForumGeral() {
		testar_obterTopicosForum(ForumId.GERAL, 48, "Guia InfoMoney de Gestores - InfoMoney TV");
	}
	
	@Test
	public void teste_obterTopicosForumAcoesIbovespa() {
		testar_obterTopicosForum(ForumId.ACOES_IBOVESPA, 62, "Ultrapar - UGPA4");
	}
	
	@Test
	public void teste_obterTopicosForumMidCaps() {
		testar_obterTopicosForum(ForumId.MID_CAPS, 116, "Cia Providencia - PRVI3");
	}

	@Test
	public void teste_obterTopicosForumSmallCaps() {
		testar_obterTopicosForum(ForumId.SMALL_CAPS, 115, "Livraria Saraiva - SLED3 / SLED4");
	}

	@Test
	public void teste_obterTopicosForumAnaliseTecnica() {
		testar_obterTopicosForum(ForumId.ANALISE_TECNICA, 27, "Indicadores IBOV");
	}

	@Test
	public void teste_obterTopicosForumAnaliseFuncamentalista() {
		testar_obterTopicosForum(ForumId.ANALISE_FUNDAMENTALISTA, 47, "Ranking de ações atualizado diariamente");
	}

	@Test
	public void teste_obterTopicosForumTradeSystems() {
		testar_obterTopicosForum(ForumId.TRADE_SYSTEMS, 11, "HILO Tranquilo");
	}

	@Test
	public void teste_obterTopicosForumEducacional() {
		testar_obterTopicosForum(ForumId.EDUCACIONAL, 29, "Como altero meu nick name");
	}

	@Test
	public void teste_obterTopicosForumCorretoras() {
		testar_obterTopicosForum(ForumId.CORRETORAS, 41, "[ Enquete ] Citi Corretora");
	}

	@Test
	public void teste_obterTopicosForumOpcoes() {
		testar_obterTopicosForum(ForumId.OPCOES, 22, "Minha dica contra o CRASH: PETR4 E VALE5 + OPÇÕES");
	}

	@Test
	public void teste_obterTopicosForumTributacao() {
		testar_obterTopicosForum(ForumId.TRIBUTACAO, 16, "12 dicas de como economizar IR (legalmente)");
	}

	@Test
	public void teste_obterTopicosForumFundos() {
		testar_obterTopicosForum(ForumId.FUNDOS, 19, "Migração de fundos para ações");
	}

	@Test
	public void teste_obterTopicosForumTopicosAnteriores() {
		testar_obterTopicosForum(ForumId.TOPICOS_ANTERIORES, 2497, "MERCADO EM 27.08.2008");
	}
	
	private void testar_obterTopicosForum(ForumId forumId,int tamanho,String textoUltimoTpc) {
		Forum forum =  infoMoneyBO.obterTopicosDoForum(forumId);
		assertTrue("Tamanho não coincide : [" + forum.getTopicos().size() + "]",forum.getTopicos().size() == tamanho);
		assertNotNull(forum.getTopicos());
		//testa o ultimo topico
		assertEquals(textoUltimoTpc, forum.getTopicos().get(forum.getTopicos().size() - 1).getDescricao());
	}
}
