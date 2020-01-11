package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.PessoaDAO;
import br.pro.dl.drogaria.dao.UsuarioDAO;
import br.pro.dl.drogaria.domain.Pessoa;
import br.pro.dl.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	
	@PostConstruct
	public void listar() {
		try {
			usuarios = new UsuarioDAO().listar("tipoUsuario");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar ps usuarios");
		}
	}
	
	public void novo() {
		try {
			usuario = new Usuario();
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar um novo usuario");
			e.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
			usuarios = usuarioDAO.listar("tipoUsuario");
			novo();
			Messages.addGlobalInfo("Usuario salvo com sucesso!");
	} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo Usuario");
			e.printStackTrace();
	}
		
	}
	
	public void editar(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			pessoas = new PessoaDAO().listar("nome");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o Usuario");
			e.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
			new UsuarioDAO().excluir(usuario);
			usuarios = new UsuarioDAO().listar("tipoUsuario");
			Messages.addGlobalInfo("Usuario removido com sucesso!");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Usuario");
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
