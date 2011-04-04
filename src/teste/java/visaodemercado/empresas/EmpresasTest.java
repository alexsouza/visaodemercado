package teste.java.visaodemercado.empresas;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.visaodemercado.empresas.entidades.Empresa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class EmpresasTest {

	@Test 
	public void teste_buscarTodasEmpresasCobertas() {
		try {
			Document listaEmpresasDoc = Jsoup.connect("http://www.fundamentus.com.br/" +
					"detalhes.php?papel=").get();
			Element tableEmpresas = listaEmpresasDoc.getElementById("test1");
			Elements trEmpresas = tableEmpresas.getElementsByTag("tbody").first().getElementsByTag("tr");
//			System.out.println("Total de empresas = " + trEmpresas.size());
			List<Empresa> empresas = new ArrayList<Empresa>();
			for (Element trEmpresa : trEmpresas) {
				Elements tdsEmpresa = trEmpresa.getElementsByTag("td");
				String[] info = new String[3];
				int i = 0;
				for (Element tdEmpresa : tdsEmpresa) {
					info[i] = tdEmpresa.text();
					i++;
				}
//				MessageFormat mensagem = new MessageFormat("Empresa = [papel = {0};" +
//						" Nome comercial = {1}; Razão Social = {2}]");
//				System.out.println(mensagem.format(info));
				empresas.add(new Empresa(info[0], info[1], info[2]));
			}
			System.out.println("Total de empresas = " + empresas.size());
			System.out.println(empresas);
			assertNotNull(empresas);
			assertTrue("Tamanhos divergem : tamanho definido [798] e tamanho obtido [" + empresas.size()+ "]", empresas.size() == 798);
//			System.out.println(empresas.html());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void teste_buscarIndicadoresDaEmpresa() {
		//por enquanto fixando uma empresa
		try {
			//PETR4
			Document indicadoresEmpresaDoc = Jsoup.connect("http://www.fundamentus.com.br/" +
			"detalhes.php?papel=PETR4").get();
			Elements tableIndicadores = indicadoresEmpresaDoc.select("table[class=w728]");
			int numeroDeIndicadores = 0;
			for (Element table : tableIndicadores) {
				Elements indicadores = table.select("td[class^=label],td[class^=data]");
//				System.out.println(indicadores);
				for (Iterator iterator = indicadores.iterator(); iterator.hasNext();) {
					Element label = (Element) iterator.next();
					String tip = label.select("span[title]").isEmpty() ? "" : label.select("span[title]").first().attr("title");
					Element valor = (Element) iterator.next();
					if (label.text() != null && !label.text().isEmpty()) {
						System.out.println("indicador : [" + label.text().replace('?', ' ').trim() + "]{" + tip + "} valor [" + valor.text() + "]");
						numeroDeIndicadores++;
					}
				}
			}
			System.out.println("Total de indicadores = " + numeroDeIndicadores);
//			System.out.println(tableIndicadores.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("implementar");
	}
}
