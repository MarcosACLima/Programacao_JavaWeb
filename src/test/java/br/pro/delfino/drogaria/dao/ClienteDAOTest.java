package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Cliente;
import br.pro.delfino.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	
	@Ignore
	@Test
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2019"));
		cliente.setLiberado(false);
		cliente.setPessoa(pessoa);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
	}
	
	@Ignore
	@Test
	public void listar() {
		ClienteDAO clienteDAO = new ClienteDAO();
		List<Cliente> resultado = clienteDAO.listar();
		
		for (Cliente cliente : resultado) {
			System.out.println("\n Codigo Cliente: " + cliente.getCodigo()
							+ "\n Data do Cadastro: " + cliente.getDataCadastro()
							+ "\n Situacao do Cadastro: " + cliente.getLiberado()
							+ "\n Nome Cliente: " + cliente.getPessoa().getNome()
							+ "\n Cidade: " + cliente.getPessoa().getCidade().getNome()
							+ "\n UF: " + cliente.getPessoa().getCidade().getEstado().getNome());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 2L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);
		
		System.out.println("\n Codigo Cliente: " + cliente.getCodigo()
						+ "\n Data do Cadastro: " + cliente.getDataCadastro()
						+ "\n Situacao do Cadastro: " + cliente.getLiberado()
						+ "\n Nome Cliente: " + cliente.getPessoa().getNome()
						+ "\n Cidade: " + cliente.getPessoa().getCidade().getNome()
						+ "\n UF: " + cliente.getPessoa().getCidade().getEstado().getNome());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 3L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigo);
		
		clienteDAO.excluir(cliente);
		
		System.out.println("Cliente Removido: ");
		System.out.println("\n Codigo Cliente: " + cliente.getCodigo()
						+ "\n Data do Cadastro: " + cliente.getDataCadastro()
						+ "\n Situacao do Cadastro: " + cliente.getLiberado()
						+ "\n Nome Cliente: " + cliente.getPessoa().getNome()
						+ "\n Cidade: " + cliente.getPessoa().getCidade().getNome()
						+ "\n UF: " + cliente.getPessoa().getCidade().getEstado().getNome());
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoCliente = 1L;
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		System.out.println("Cliente Dados antes: ");
		System.out.println("\n Codigo Cliente: " + cliente.getCodigo()
						+ "\n Data do Cadastro: " + cliente.getDataCadastro()
						+ "\n Situacao do Cadastro: " + cliente.getLiberado()
						+ "\n Nome Cliente: " + cliente.getPessoa().getNome()
						+ "\n Cidade: " + cliente.getPessoa().getCidade().getNome()
						+ "\n UF: " + cliente.getPessoa().getCidade().getEstado().getNome());
	
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		
		clienteDAO.editar(cliente);
		
		System.out.println("Dados Cliente apos editar: ");
		System.out.println("\n Codigo Cliente: " + cliente.getCodigo()
						+ "\n Data do Cadastro: " + cliente.getDataCadastro()
						+ "\n Situacao do Cadastro: " + cliente.getLiberado()
						+ "\n Nome Cliente: " + cliente.getPessoa().getNome()
						+ "\n Cidade: " + cliente.getPessoa().getCidade().getNome()
						+ "\n UF: " + cliente.getPessoa().getCidade().getEstado().getNome());
	}

}
