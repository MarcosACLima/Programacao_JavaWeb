package br.pro.delfino.drogaria.dao;

import org.junit.Test;

import br.pro.delfino.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Distrito Federal");
		estado.setSigla("DF");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
	}
}
