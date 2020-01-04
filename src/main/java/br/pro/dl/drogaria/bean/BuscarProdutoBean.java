package br.pro.dl.drogaria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class BuscarProdutoBean implements Serializable {

	private Produto produto;
	private Boolean exibeComponente;

	@PostConstruct
	public void novo() {
		produto = new Produto();
		exibeComponente = false;
	}

	public void buscar() {
		try {
			Produto resultado = new ProdutoDAO().buscar(produto.getCodigo());
			
			if(resultado == null) {
				exibeComponente = false;
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");
			} else {
				exibeComponente = true;
				produto = resultado;
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar buscar o produto");
			e.printStackTrace();
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Boolean getExibeComponente() {
		return exibeComponente;
	}

	public void setExibeComponente(Boolean exibeComponente) {
		this.exibeComponente = exibeComponente;
	}

}
