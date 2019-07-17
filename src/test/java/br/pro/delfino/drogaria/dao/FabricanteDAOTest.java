package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Probiotica");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}
	
	@Ignore
	@Test
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		
		System.out.println("Total de Registros Encontrados: "
				+ resultado.size());	
		for (Fabricante fabricante : resultado) {
			System.out.println("Fabricante: " + fabricante.getDescricao());
		}
	}
	
	@Test
	public void buscar() {
		Long codigo = 5L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}

}
