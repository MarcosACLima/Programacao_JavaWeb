package br.pro.delfino.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Ignore
	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
	
	@Ignore
	@Test
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		System.out.println("Total de Registros Encontrados: "
				+ resultado.size());
		for(Estado estado :resultado) {
			System.out.println(estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 10L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
	
		}
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 7L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			estadoDAO.excluir(estado); // comando
			System.out.println("Registro removido: ");
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
		}
	}
	
	@Test
	public void editar() {
		Long codigo = 4L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro antes: ");
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
			estado.setNome("Distrito Federal");
			estado.setSigla("DF");
			estadoDAO.editar(estado); // comando
			System.out.println("Registro editado: ");
			System.out.println(estado.getCodigo() + " - " + estado.getNome() + " - " + estado.getSigla());
		}
	}
	
}
