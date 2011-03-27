package teste.java.visaodemercado.foruns;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import main.java.visaodemercado.foruns.InfoMoneyWebForumBO;
import main.java.visaodemercado.foruns.InfoMoneyWebForumBO.ForumId;
import main.java.visaodemercado.foruns.entidades.Forum;
import main.java.visaodemercado.foruns.entidades.Topico;


public class MensagensInfoMoneyTest {

	private static InfoMoneyWebForumBO infoMoneyBO = new InfoMoneyWebForumBO();
	
	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumGeral(){
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.GERAL);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}
	
	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumAcoesIbovespa() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.ACOES_IBOVESPA);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}
	
	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumMidCaps() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.MID_CAPS);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumSmallCaps() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.SMALL_CAPS);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumAnaliseTecnica() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.ANALISE_TECNICA);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumAnaliseFuncamentalista() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.ANALISE_FUNDAMENTALISTA);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumTradeSystems() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.TRADE_SYSTEMS);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumEducacional() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.EDUCACIONAL);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumCorretoras() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.CORRETORAS);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumOpcoes() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.OPCOES);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumTributacao() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.TRIBUTACAO);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumFundos() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.FUNDOS);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}

	@Test
	public void teste_obterMensagensDeTopicoAleatorioDoForumTopicosAnteriores() {
		Forum forum = infoMoneyBO.obterTopicosDoForum(ForumId.TOPICOS_ANTERIORES);
		teste_obterMensagensDeTopicoAleatorio(forum);
	}
	
	@Test
	public void teste_atualizarMensagensDeTopico() {
		Forum forum =  infoMoneyBO.obterTopicosDoForum(ForumId.GERAL);
		Topico topico = teste_obterMensagensDeTopicoAleatorio(forum);
		topico.getMensagens().remove(topico.getMensagens().size() - 1);
		assertTrue("Tamanhos iguais",topico.getMensagens().size() != Integer.parseInt(topico.getRespostas()) + 1);
		List<Topico> topicos = infoMoneyBO.atualizarMensagensDosTopicosDoForum(forum);
		assertTrue(topicos.contains(topico));
		assertTrue("Tamanhos divergem : mensagens[" + topico.getMensagens().size() + "] || repostas ao tpc[" 
				+ (Integer.parseInt(topico.getRespostas()) + 1)  + "]", 
				topico.getMensagens().size() == Integer.parseInt(topico.getRespostas()) + 1);
	}
	
	private Topico teste_obterMensagensDeTopicoAleatorio(Forum forum){
		Random random = new Random();
		Topico topico = forum.getTopicos().get(random.nextInt(forum.getTopicos().size()));
		assertNull(topico.getMensagens());
		infoMoneyBO.obterMensagensDoTopicoRecentes(topico);
		assertNotNull(topico.getMensagens());
		assertTrue("Tamanhos divergem : mensagens[" + topico.getMensagens().size() + "] || repostas ao tpc[" 
				+ (Integer.parseInt(topico.getRespostas()) + 1)  + "]", 
				topico.getMensagens().size() == Integer.parseInt(topico.getRespostas()) + 1);
		System.out.println(topico);
		return topico;
	}
}
