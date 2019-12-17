package br.pro.dl.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.CidadeDAO;
import br.pro.dl.drogaria.dao.PessoaDAO;
import br.pro.dl.drogaria.domain.Cidade;
import br.pro.dl.drogaria.domain.Pessoa;

public class PessoaDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		Long codigoCidade = 1L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Linus Torvalds");
		pessoa.setCpf("253-235-555-01");
		pessoa.setRg("4755695-17");
		pessoa.setRua("Rua H");
		pessoa.setNumero(new Short("32"));
		pessoa.setBairro("Setor Norte");
		pessoa.setCep("826993145");
		pessoa.setComplemento("Proximo ao area 51");
		pessoa.setTelefone("(66)3325-5111");
		pessoa.setCelular("(66)9975-3365");
		pessoa.setEmail("linustorvalds@mail.com");
		pessoa.setCidade(cidade);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.salvar(pessoa);
	}
	
	@Ignore
	@Test
	public void listar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> resultado = pessoaDAO.listar();
		
		for (Pessoa pessoa : resultado) {
			System.out.println("\n Codigo Pessoa: " + pessoa.getCodigo()
							+ "\n Nome pessoa: " + pessoa.getNome()
							+ "\n CPF: " + pessoa.getCpf()
							+ "\n Cidade: " + pessoa.getCidade().getNome()
							+ "\n Estado: " + pessoa.getCidade().getEstado().getNome());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 2L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);
		
		System.out.println("\n Codigo Pessoa: " + pessoa.getCodigo()
		+ "\n Nome pessoa: " + pessoa.getNome()
		+ "\n CPF: " + pessoa.getCpf()
		+ "\n Cidade: " + pessoa.getCidade().getNome()
		+ "\n Estado: " + pessoa.getCidade().getEstado().getNome());
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoPessoa = 1L;
		Long codigoCidade = 4L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		System.out.println("\n Codigo: " + cidade.getCodigo()
		+ "\n Cidade: " + cidade.getNome()
		+ "\n Codigo Estado: " + cidade.getEstado().getCodigo()
		+ "\n Estado: " + cidade.getEstado().getNome()
		+ "\n UF: " + cidade.getEstado().getSigla());
			
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigoPessoa);
		
		System.out.println("Pessoa antes de editar:");
		System.out.println("\n Codigo Pessoa: " + pessoa.getCodigo()
		+ "\n Nome pessoa: " + pessoa.getNome()
		+ "\n CPF: " + pessoa.getCpf()
		+ "\n Cidade: " + pessoa.getCidade().getNome()
		+ "\n Estado: " + pessoa.getCidade().getEstado().getNome());

		pessoa.setNome("Linus Benedict");
		pessoa.setCidade(cidade);
		
		pessoaDAO.editar(pessoa);
		
		System.out.println("Pessoa antes de editar:");
		System.out.println("\n Codigo Pessoa: " + pessoa.getCodigo()
		+ "\n Nome pessoa: " + pessoa.getNome()
		+ "\n CPF: " + pessoa.getCpf()
		+ "\n Cidade: " + pessoa.getCidade().getNome()
		+ "\n Estado: " + pessoa.getCidade().getEstado().getNome());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 3L;
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(codigo);
		
		pessoaDAO.excluir(pessoa);
		
		System.out.println("Removida seguinte Pessoa: ");
		System.out.println("\n Codigo Pessoa: " + pessoa.getCodigo()
		+ "\n Nome pessoa: " + pessoa.getNome()
		+ "\n CPF: " + pessoa.getCpf()
		+ "\n Cidade: " + pessoa.getCidade().getNome()
		+ "\n Estado: " + pessoa.getCidade().getEstado().getNome());
	}
	
}
