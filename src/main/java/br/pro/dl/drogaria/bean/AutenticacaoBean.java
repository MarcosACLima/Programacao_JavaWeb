package br.pro.dl.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.UsuarioDAO;
import br.pro.dl.drogaria.domain.Pessoa;
import br.pro.dl.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private Usuario usuario;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void inicar() {
		usuario =  new Usuario();
		usuario.setPessoa(new Pessoa());
	}
	
	public void autenticar() {
		try {
			usuarioLogado = new UsuarioDAO().autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
			
			if(usuarioLogado == null) {
				Messages.addGlobalError("CPF e/ou senha incorretos");
				return;
			} 
			
			Faces.redirect("./pages/principal.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
}

