package br.pro.dl.drogaria.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
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
		Pessoa pessoa = pessoaDAO.buscar(3L);
		
		
		Usuario usuario = new Usuario();
		usuario.setSenhaSemCriptografia("123456");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		
		usuario.setTipo('A');
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
	
	@Ignore
	@Test
	public void editar() {
		Long codigo = 6L;
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(codigo);
		
		System.out.println("Usuario antes de editar: ");
		System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
						+ "\n Senha: " + usuario.getSenha()
						+ "\n Tipo: " + usuario.getTipo()
						+ "\n Ativo: " + usuario.getAtivo()
						+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(10L);
		
		usuario.setAtivo(true);
		usuario.setSenhaSemCriptografia("q1r4e3w2");
		
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		
		usuario.setTipo('B');
		usuario.setPessoa(pessoa);
		
		usuarioDAO.editar(usuario);
		
		System.out.println("Usuario apos de editar: ");
		System.out.println("\n Codigo Usuario: " + usuario.getCodigo()
						+ "\n Senha: " + usuario.getSenha()
						+ "\n Tipo: " + usuario.getTipo()
						+ "\n Ativo: " + usuario.getAtivo()
						+ "\n Nome do Usuario: " + usuario.getPessoa().getNome());
	}
	
	@Ignore
	@Test
	public void autenticar() {
		String cpf = "256.611.111-22";
		String senha = "123456";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(cpf, senha);
		
		System.out.println("Usuário autenticado: " + usuario);
		
	}
	
}
