package main.java.visaodemercado.foruns.driver;

import java.util.List;

import main.java.visaodemercado.foruns.entidades.Forum;
import main.java.visaodemercado.foruns.entidades.Topico;

public interface DriverWebForum {
	public List<Forum> obterForuns();
	public Forum obterTopicosDoForum(Forum forum);
	public void obterTodasMensagensDoTopico(Topico topico,boolean ordemAscendente);
	public void atualizarMensagensDoTopico(Topico topico);
}
