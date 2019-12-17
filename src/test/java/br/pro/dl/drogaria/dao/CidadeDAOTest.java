package br.pro.dl.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.CidadeDAO;
import br.pro.dl.drogaria.dao.EstadoDAO;
import br.pro.dl.drogaria.domain.Cidade;
import br.pro.dl.drogaria.domain.Estado;

public class CidadeDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		Long codigoEstado = 3L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Raccoon City");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}
	
	@Ignore
	@Test
	public void listar() {
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		for (Cidade cidade : resultado) {
			System.out.println("\n Codigo: " + cidade.getCodigo()
							+ "\n Cidade: " + cidade.getNome()
							+ "\n Codigo Estado: " + cidade.getEstado().getCodigo()
							+ "\n Estado: " + cidade.getEstado().getNome()
							+ "\n UF: " + cidade.getEstado().getSigla());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 4L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		System.out.println("\n Codigo: " + cidade.getCodigo()
		+ "\n Cidade: " + cidade.getNome()
		+ "\n Codigo Estado: " + cidade.getEstado().getCodigo()
		+ "\n Estado: " + cidade.getEstado().getNome()
		+ "\n UF: " + cidade.getEstado().getSigla());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 7L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		cidadeDAO.excluir(cidade);
		
		System.out.println("Removida seguinte Cidade: ");
		System.out.println("\n Codigo: " + cidade.getCodigo()
		+ "\n Cidade: " + cidade.getNome()
		+ "\n Codigo Estado: " + cidade.getEstado().getCodigo()
		+ "\n Estado: " + cidade.getEstado().getNome()
		+ "\n UF: " + cidade.getEstado().getSigla());
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoCidade = 5L;
		Long codigoEstado = 3L;
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("\n Codigo Estado: " + estado.getCodigo()
				+ "\n Estado: " + estado.getNome()
				+ "\n UF: " + estado.getSigla());
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		System.out.println("Cidade antes de editar: ");
		System.out.println("\n Codigo: " + cidade.getCodigo()
		+ "\n Cidade: " + cidade.getNome()
		+ "\n Codigo Estado: " + cidade.getEstado().getCodigo()
		+ "\n Estado: " + cidade.getEstado().getNome()
		+ "\n UF: " + cidade.getEstado().getSigla());	
		
		cidade.setNome("Los Santos");
		cidade.setEstado(estado);
		
		cidadeDAO.editar(cidade);
		
		System.out.println("Cidade apos editar: ");
		System.out.println("\n Codigo: " + cidade.getCodigo()
		+ "\n Cidade: " + cidade.getNome()
		+ "\n Codigo Estado: " + cidade.getEstado().getCodigo()
		+ "\n Estado: " + cidade.getEstado().getNome()
		+ "\n UF: " + cidade.getEstado().getSigla());
	}
	
}
