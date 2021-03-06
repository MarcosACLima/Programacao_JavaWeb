package br.pro.dl.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Baruel");
		
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
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 5L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
		}
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 5L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		}
		fabricanteDAO.excluir(fabricante); // comando
		System.out.println("Registro removido: ");
		System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigo = 5L;
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro antes: ");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
			fabricante.setDescricao("Universal");
			fabricanteDAO.editar(fabricante); // comando
			System.out.println("Registro editado: ");
			System.out.println(fabricante.getCodigo() + " - " + fabricante.getDescricao());
		}
	}
	
	@Ignore
	@Test
	public void mergeSalvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante Y");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.merge(fabricante);
	}
	
	@Ignore
	@Test
	public void merge() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(8L);
		fabricante.setDescricao("Umbrella");
		fabricanteDAO.merge(fabricante);
	}
	
}
