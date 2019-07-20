package br.pro.delfino.drogaria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.delfino.drogaria.domain.Funcionario;
import br.pro.delfino.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {

	@Ignore
	@Test
	public void salvar() throws ParseException {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(3L);

		Funcionario funcionario = new Funcionario();
		funcionario.setCarteiraTrabalho("1799115");
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("22/07/2019"));
		funcionario.setPessoa(pessoa);

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		funcionarioDAO.salvar(funcionario);
	}

	@Ignore
	@Test
	public void listar() {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<Funcionario> resultado = funcionarioDAO.listar();

		for (Funcionario funcionario : resultado) {
			System.out.println("\n Codigo Funcionario: " + funcionario.getCodigo() + "\n Carteira de Trabalho: "
							+ funcionario.getCarteiraTrabalho() + "\n Data de Admissao: " + funcionario.getDataAdmissao()
							+ "\n Nome do Funcionario: " + funcionario.getPessoa().getNome() + "\n Cidade e Estado: "
							+ funcionario.getPessoa().getCidade().getNome() + ", "
							+ funcionario.getPessoa().getCidade().getEstado().getNome());
		}
	}

	@Ignore
	@Test
	public void buscar() {
		Long codigo = 2L;

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);

		System.out.println("\n Codigo Funcionario: " + funcionario.getCodigo() + "\n Carteira de Trabalho: "
						+ funcionario.getCarteiraTrabalho() + "\n Data de Admissao: " + funcionario.getDataAdmissao()
						+ "\n Nome do Funcionario: " + funcionario.getPessoa().getNome() + "\n Cidade e Estado: "
						+ funcionario.getPessoa().getCidade().getNome() + ", "
						+ funcionario.getPessoa().getCidade().getEstado().getNome());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 4L;

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		funcionarioDAO.excluir(funcionario);

		System.out.println("\n Codigo Funcionario: " + funcionario.getCodigo() + "\n Carteira de Trabalho: "
						+ funcionario.getCarteiraTrabalho() + "\n Data de Admissao: " + funcionario.getDataAdmissao()
						+ "\n Nome do Funcionario: " + funcionario.getPessoa().getNome() + "\n Cidade e Estado: "
						+ funcionario.getPessoa().getCidade().getNome() + ", "
						+ funcionario.getPessoa().getCidade().getEstado().getNome());
	}
	
	@Ignore
	@Test
	public void editar() throws ParseException {
		Long codigo = 3L;

		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigo);
		
		System.out.println("\n Dados do Funcionario antes de editar: ");
		System.out.println("\n Codigo Funcionario: " + funcionario.getCodigo() + "\n Carteira de Trabalho: "
						+ funcionario.getCarteiraTrabalho() + "\n Data de Admissao: " + funcionario.getDataAdmissao()
						+ "\n Nome do Funcionario: " + funcionario.getPessoa().getNome() + "\n Cidade e Estado: "
						+ funcionario.getPessoa().getCidade().getNome() + ", "
						+ funcionario.getPessoa().getCidade().getEstado().getNome());
		
		funcionario.setDataAdmissao(new SimpleDateFormat("dd/MM/yyyy").parse("22/07/2019"));
		funcionario.setCarteiraTrabalho("012535499");
		
		funcionarioDAO.editar(funcionario);
		
		System.out.println("\n Dados do Funcionario apos de editar: ");
		System.out.println("\n Codigo Funcionario: " + funcionario.getCodigo() + "\n Carteira de Trabalho: "
						+ funcionario.getCarteiraTrabalho() + "\n Data de Admissao: " + funcionario.getDataAdmissao()
						+ "\n Nome do Funcionario: " + funcionario.getPessoa().getNome() + "\n Cidade e Estado: "
						+ funcionario.getPessoa().getCidade().getNome() + ", "
						+ funcionario.getPessoa().getCidade().getEstado().getNome());
	}

}
