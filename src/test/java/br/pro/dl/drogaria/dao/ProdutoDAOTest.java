package br.pro.dl.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Fabricante;
import br.pro.dl.drogaria.domain.Produto;

public class ProdutoDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(1L);
		
		Produto produto = new Produto();
		produto.setDescricao("Dipirona 30mg com 15 compromidos");
		produto.setFabricante(fabricante);
		produto.setPreco(new BigDecimal("1.99"));
		produto.setQuantidade(new Short("25"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
		
		System.out.println("Produto salvo com sucesso!");
	}
	
	@Ignore
	@Test
	public void listar() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		
		for (Produto produto : resultado) {
			System.out.println("\n Codigo produto: " + produto.getCodigo()
							+ "\n Descricao: " + produto.getDescricao()
							+ "\n Quantidade: " + produto.getQuantidade()
							+ "\n Preco: " + produto.getPreco()
							+ "\n Codigo Fabricante: " + produto.getFabricante().getCodigo()
							+ "\n Fabricante: " + produto.getFabricante().getDescricao());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 6L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		System.out.println("\n Codigo produto: " + produto.getCodigo()
						+ "\n Descricao: " + produto.getDescricao()
						+ "\n Quantidade: " + produto.getQuantidade()
						+ "\n Preco: " + produto.getPreco()
						+ "\n Codigo Fabricante: " + produto.getFabricante().getCodigo()
						+ "\n Fabricante: " + produto.getFabricante().getDescricao());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 9L;
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		produtoDAO.excluir(produto);
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoProduto = 6L;
		Long codigoFabricante = 3L;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigoFabricante);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigoProduto);
		
		produto.setDescricao("BCAA 1200mg com 100 comprimidos");
		produto.setFabricante(fabricante);
		
		produtoDAO.editar(produto);
	}
	
}
