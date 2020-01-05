package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.pro.dl.drogaria.dao.HistoricoDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Historico;
import br.pro.dl.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable {

	private Produto produto;
	private Boolean exibeComponente;
	private Historico historico;

	@PostConstruct
	public void novo() {
		historico = new Historico();
		produto = new Produto();
		exibeComponente = false;
	}

	public void buscar() {
		try {
			Produto resultado = new ProdutoDAO().buscar(produto.getCodigo());

			if (resultado == null) {
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

	public void salvar() {
		try {
			historico.setHorario(new Date());
			historico.setProduto(produto);
			new HistoricoDAO().salvar(historico);
			Messages.addGlobalInfo("Histórico salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Histórico");
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
	
	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
