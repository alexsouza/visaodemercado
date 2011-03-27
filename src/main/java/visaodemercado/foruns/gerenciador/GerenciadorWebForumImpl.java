package main.java.visaodemercado.foruns.gerenciador;

import java.util.List;

import main.java.visaodemercado.foruns.driver.DriverWebForum;
import main.java.visaodemercado.foruns.driver.DriverWebForumInfoMoney;
import main.java.visaodemercado.foruns.entidades.Forum;
import main.java.visaodemercado.foruns.entidades.Topico;

public class GerenciadorWebForumImpl implements GerenciadorWebForum {
	
	private static DriverWebForum driver = new DriverWebForumInfoMoney();
	private static List<Forum> forunsInfo;
	
	@Override
	public List<Forum> obterForuns() {
		return driver.obterForuns();
	}

	@Override
	public Forum obterForumPorId(String id) {
		if (forunsInfo == null || forunsInfo.isEmpty()) {
			forunsInfo = driver.obterForuns();
		}
		return forunsInfo.get(forunsInfo.lastIndexOf(new Forum(id)));
	}

	@Override
	public Forum obterTopicosDoForum(Forum forum) {
		return driver.obterTopicosDoForum(forum);
	}

	@Override
	public void obterTodasMensagensDoTopico(Topico topico,boolean ordemAscendente) {
		driver.obterTodasMensagensDoTopico(topico, ordemAscendente);
	}

	@Override
	public void atualizarMensagensDoTopico(Topico topico) {
		driver.atualizarMensagensDoTopico(topico);
	}

	public static List<Forum> getForunsInfo() {
		return forunsInfo;
	}

	public static void setForunsInfo(List<Forum> forunsInfo) {
		GerenciadorWebForumImpl.forunsInfo = forunsInfo;
	}

}
