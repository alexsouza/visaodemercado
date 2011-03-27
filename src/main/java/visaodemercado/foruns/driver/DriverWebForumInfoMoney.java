package main.java.visaodemercado.foruns.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.visaodemercado.foruns.entidades.Autor;
import main.java.visaodemercado.foruns.entidades.Forum;
import main.java.visaodemercado.foruns.entidades.Mensagem;
import main.java.visaodemercado.foruns.entidades.Topico;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DriverWebForumInfoMoney implements DriverWebForum {
	
	private static final String FORUM_INFO = "http://forum.infomoney.com.br/";
	//private static List<Forum> forunsInfo = new ArrayList<Forum>();
	
	@Override
	public List<Forum> obterForuns() {
		List<Forum> forunsInfo = new ArrayList<Forum>();
		Document infoMoney;
		try {
			infoMoney = Jsoup.connect(FORUM_INFO).get();
			Element tabelaForuns = infoMoney.select("table[width=100%] " +
					"[cellpadding=2]" +
					"[cellspacing=1]" +
					"[border=0]").first();
			
			Elements foruns = tabelaForuns.getElementsByClass("forumline");
			for (Element forum : foruns) {
				Element cabecalhoForum = forum.select("td[class=catHead][height=25]").first();
				Element tituloForum = cabecalhoForum.select("a[class=forumlink]").first();
				forunsInfo.add(new Forum(tituloForum.text(),
						cabecalhoForum.getElementsByClass("genmed").first().text(),
						tituloForum.absUrl("href")));
			}
			return forunsInfo;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Forum obterTopicosDoForum(Forum forum) {
//		List<Topico> topicos = forum.getTopicos();
		try {
			Document topicosForum = Jsoup.connect(forum.getUrl()).get();

			buscarTopicos(topicosForum,forum);
			String linkProximaPagina = obterLinkProximaPaginaDeTopicos(topicosForum);
			while (linkProximaPagina != null) {
				Document pagina = Jsoup.connect(linkProximaPagina).get();
				buscarTopicos(pagina,forum);
				linkProximaPagina = obterLinkProximaPaginaDeTopicos(pagina);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forum;
	}

	private void buscarTopicos(Document doc,Forum forum) {
		if (forum.getTopicos() == null) {
			forum.setTopicos(new ArrayList<Topico>());
		}
		Element tabelaTopicos = doc.select("table[class=forumline]").first();
		Elements trs = tabelaTopicos.getElementsByTag("tr");
		for (Element topico : trs) {
			Element tituloTopico = topico.select("span[class=topictitle]").first();
			Elements informacoesTopico = topico.select("td[class^=row]");
			if (tituloTopico != null) {
				Element urlTopico = tituloTopico.select("a[class=topictitle]").first();
				Topico tpc = new Topico(tituloTopico.text(), informacoesTopico.get(2).text(),
						informacoesTopico.get(4).text(), informacoesTopico.get(5).text(),
						urlTopico.absUrl("href"));
				if (!forum.getTopicos().contains(tpc)) {
					forum.getTopicos().add(tpc);
				} else {
					Topico topicoAntigo = forum.getTopicos().get(forum.getTopicos().indexOf(tpc));
					if (!topicoAntigo.getRespostas().equals(tpc.getRespostas())) {
						topicoAntigo.setExibicoes(tpc.getExibicoes());
						topicoAntigo.setRespostas(tpc.getRespostas());
						topicoAntigo.setUltimaMensagem(tpc.getUltimaMensagem());	
					}
				}
			}
		}
	}
	
	private void buscarMensagens (Document doc,Topico topico,boolean buscarTodos,int quantidade,int indice) {
		if (topico.getMensagens() == null) {
			topico.setMensagens(new ArrayList<Mensagem>());
		}
		Element tabelaMensagens = doc.getElementsByClass("forumline").first();
		Elements urlPerfil = tabelaMensagens.select("a[href^=profile.php?mode=viewprofile]");
		Iterator<Element> it = urlPerfil.iterator();
		List<Autor> autores = new ArrayList<Autor>();
		int i = indice;
		//informacoes do autor
		for (Element linha : tabelaMensagens.select("td[class^=row][align=left][valign=top]")) {
			if (i < quantidade || buscarTodos) {
				Element nome = linha.select("span[class=name").first();
				Element detalhes = linha.select("span[class=postdetails").first();
				String avatar = !detalhes.getElementsByTag("img").isEmpty() ? detalhes.getElementsByTag("img").first().absUrl("src") : null;
				Autor autor = new Autor(nome.text(), detalhes.text(), avatar, it.hasNext() ? it.next().absUrl("href") : null);
				autores.add(autor);
				i++;
			} else {
				break;
			}
		}
		Iterator<Autor> itAutor = autores.iterator();
		i = indice;
		//mensagem
		for (Element linha : tabelaMensagens.select("td[class^=row][width=100%][valign=top]")) {
			if (i < quantidade || buscarTodos) {
				//data de envio
				Element detalhesPost = linha.select("span[class=postdetails]").first();
				Element urlPost = linha.select("a[href^=posting.php?]").first();
				//mensagem
				Element mensagem = linha.select("td[colspan=2]").last();
				//recupera autor da mensagem.
				Autor autorTemp = itAutor.next();
				Mensagem msg = new Mensagem(detalhesPost.text().substring(0, 27), mensagem.html(), mensagem.text(), urlPost.absUrl("href")); 
				if(!topico.getMensagens().contains(msg)) {
					msg.setAutor(autorTemp);
					topico.getMensagens().add(msg);
				}
				i++;
			} else {
				break;
			}
		}
		indice = i;
	}
	
	private String obterLinkProximaPaginaDeMensagens(Document doc,String topicoId) {
		return obterLinkProximaPagina(doc.select("a[href^=viewtopic.php?t=" + topicoId + "]"));
	}
	
	private String obterLinkProximaPaginaDeTopicos(Document doc) {
		return obterLinkProximaPagina(doc.select("a[href^=viewforum.php?]"));
	}
	
	private String obterLinkProximaPagina(Elements elementos) {
		for (Element tmp : elementos) {
			if (tmp.hasText() && "Próximo".equals(tmp.text())) {
				return tmp.absUrl("href");
			}
		}
		return null;
	}

	private void obterMensagensDoTopico(Topico topico, boolean ordemAscendente,boolean buscarTodos,int quantidade) {
		try {
			Document infoMoney = Jsoup.connect(topico.getUrl()
					+ (ordemAscendente ? "&postorder=asc" : "&postorder=desc")).get();
			
			int i = 0;
			buscarMensagens(infoMoney,topico,buscarTodos,quantidade,i);
			String linkProximaPagina = obterLinkProximaPaginaDeMensagens(infoMoney,topico.getId());
			while (linkProximaPagina != null) {
				Document pagina = Jsoup.connect(linkProximaPagina).get();
				buscarMensagens(pagina,topico,buscarTodos,quantidade,i);
				linkProximaPagina = obterLinkProximaPaginaDeMensagens(pagina, topico.getId());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void obterTodasMensagensDoTopico(Topico topico, boolean ordemAscendente) {
		obterMensagensDoTopico(topico, ordemAscendente, true, 0);
	}

	@Override
	public void atualizarMensagensDoTopico(Topico topico) {
		int quantidadeAtualizar = Integer.parseInt(topico.getRespostas()) + 1 - topico.getMensagens().size();
		obterMensagensDoTopico(topico, true, false, quantidadeAtualizar);
		
	}

}
