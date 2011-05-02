package main.java.visaodemercado.empresas.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.java.visaodemercado.empresas.entidades.Empresa;
import main.java.visaodemercado.empresas.entidades.Indicador;

public class DriverEmpresasFundamentus implements DriverEmpresas {

	private static final String FUNDAMENTUS_SITE_URL = "http://www.fundamentus.com.br/detalhes.php?papel=";
	
	@Override
	public List<Empresa> obterTodasCobertas() {
		List<Empresa> empresas = null;
		try {
			Document listaEmpresasDoc = Jsoup.connect(FUNDAMENTUS_SITE_URL).get();
			Element tableEmpresas = listaEmpresasDoc.getElementById("test1");
			Elements trEmpresas = tableEmpresas.getElementsByTag("tbody").first().getElementsByTag("tr");
			empresas = new ArrayList<Empresa>();
			for (Element trEmpresa : trEmpresas) {
				Elements tdsEmpresa = trEmpresa.getElementsByTag("td");
				String[] info = new String[3];
				int i = 0;
				for (Element tdEmpresa : tdsEmpresa) {
					info[i] = tdEmpresa.text();
					i++;
				}
				empresas.add(new Empresa(info[0], info[1], info[2]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empresas;
	}

	@Override
	public List<Indicador> buscarIndicadores(Empresa empresa) {
		List<Indicador> listaIndicadores = null;
		try {
			Document indicadoresEmpresaDoc = Jsoup.connect(FUNDAMENTUS_SITE_URL + empresa.getCodigo()).get();
			Elements tableIndicadores = indicadoresEmpresaDoc.select("table[class=w728]");
			listaIndicadores = new ArrayList<Indicador>();
			for (Element table : tableIndicadores) {
				Elements indicadores = table.select("td[class^=label],td[class^=data]");
				for (Iterator<Element> iterator = indicadores.iterator(); iterator.hasNext();) {
					Element label = (Element) iterator.next();
					String tip = label.select("span[title]").isEmpty() ? "" : label.select("span[title]").first().attr("title");
					Element valor = (Element) iterator.next();
					if (label.text() != null && !label.text().isEmpty()) {
						listaIndicadores.add(new Indicador(label.text().replace('?', ' ').trim(), tip, valor.text()));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaIndicadores;
	}

}
