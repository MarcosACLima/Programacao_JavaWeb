package br.pro.dl.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.PessoaDAO;
import br.pro.dl.drogaria.dao.UsuarioDAO;
import br.pro.dl.drogaria.domain.Pessoa;
import br.pro.dl.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	
	@Ignore
	@Test
	public void salvar() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		Usuario usuario = new Usuario();
		usuario.setSenha("abc123");
		usuario.setTipo('C');
		usuario.setAtivo(true);
		usuario.setPessoa(pessoa);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}
	
	@Ignore
	@Test
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();
		
		for (Usuario usuario : resultado) {
			System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
							+ "\n Senha: " + usuario.getSenha()
							+ "\n Tipo: " + usuario.getTipo()
							+ "\n Ativo: " + usuario.getAtivo()
							+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 4L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
						+ "\n Senha: " + usuario.getSenha()
						+ "\n Tipo: " + usuario.getTipo()
						+ "\n Ativo: " + usuario.getAtivo()
						+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 1L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		usuarioDAO.excluir(usuario);
		
		System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
						+ "\n Senha: " + usuario.getSenha()
						+ "\n Tipo: " + usuario.getTipo()
						+ "\n Ativo: " + usuario.getAtivo()
						+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
	}
	
//	@Ignore
	@Test
	public void editar() {
		Long codigo = 2L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		System.out.println("Usuario antes de editar: ");
		System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
						+ "\n Senha: " + usuario.getSenha()
						+ "\n Tipo: " + usuario.getTipo()
						+ "\n Ativo: " + usuario.getAtivo()
						+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(1L);
		
		usuario.setAtivo(true);
		usuario.setSenha("q1r4e3w2");
		usuario.setTipo('A');
		usuario.setPessoa(pessoa);
		
		usuarioDAO.editar(usuario);
		
		System.out.println("Usuario apos de editar: ");
		System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
						+ "\n Senha: " + usuario.getSenha()
						+ "\n Tipo: " + usuario.getTipo()
						+ "\n Ativo: " + usuario.getAtivo()
						+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
	}
	
}
