package teste.java.visaodemercado.empresas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import main.java.visaodemercado.empresas.driver.DriverEmpresas;
import main.java.visaodemercado.empresas.driver.DriverEmpresasFundamentus;
import main.java.visaodemercado.empresas.entidades.Empresa;
import main.java.visaodemercado.empresas.entidades.Indicador;

import org.junit.Test;

public class EmpresasTest {

	private static List<Empresa> empresas = null;
	private static DriverEmpresas driverEmpresas = new DriverEmpresasFundamentus();
	
	@Test 
	public void teste_buscarTodasEmpresasCobertas() {
			empresas = driverEmpresas.obterTodasCobertas();
			System.out.println("Total de empresas = " + empresas.size());
			System.out.println(empresas);
			assertNotNull(empresas);
			assertTrue("Tamanhos divergem : tamanho definido [799] e tamanho obtido [" + empresas.size()+ "]", empresas.size() == 799);
	}
	
	@Test
	public void teste_buscarIndicadoresDaEmpresa() {
			Random random = new Random();
			Empresa empresa = empresas.get(random.nextInt(empresas.size()));
			List<Indicador> indicadores = driverEmpresas.buscarIndicadores(empresa);
			empresa.setIndicadores(indicadores);
			System.out.println("Total de indicadores = " + empresa.getIndicadores().size());
			System.out.println(empresa);
			assertNotNull(empresa.getIndicadores().get(0).getNome());
			assertTrue("Tamanho obtido de [" + empresa.getIndicadores().size() + "] diverge de 57",empresa.getIndicadores().size() == 57);
	}
}
