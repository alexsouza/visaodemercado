package main.java.visaodemercado.empresas.driver;

import java.util.List;

import main.java.visaodemercado.empresas.entidades.Empresa;
import main.java.visaodemercado.empresas.entidades.Indicador;

public interface DriverEmpresas {

	public List<Empresa> obterTodasCobertas();
	public List<Indicador> buscarIndicadores(Empresa empresa);    
}
