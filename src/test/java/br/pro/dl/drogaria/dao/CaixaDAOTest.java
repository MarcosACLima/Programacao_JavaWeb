package br.pro.dl.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.domain.Caixa;

public class CaixaDAOTest {
	
	@Test
	@Ignore
	public void salvar() throws ParseException {
		Caixa caixa = new Caixa();
		caixa.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2019"));
		caixa.setValorAbertura(new BigDecimal("150.00"));
		new CaixaDAO().salvar(caixa);
	}
	
	@Ignore
	@Test
	public void buscar() throws ParseException {
		Caixa caixa = new CaixaDAO().buscar(new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2020"));
		System.out.println(caixa.getCodigo());
		Assert.assertNull(caixa);
	}

}
