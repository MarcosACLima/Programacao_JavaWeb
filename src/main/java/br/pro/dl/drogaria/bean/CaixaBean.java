package br.pro.dl.drogaria.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.pro.dl.drogaria.dao.CaixaDAO;
import br.pro.dl.drogaria.dao.FuncionarioDAO;
import br.pro.dl.drogaria.domain.Caixa;
import br.pro.dl.drogaria.domain.Funcionario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CaixaBean implements Serializable {

	private Caixa caixa;
	private ScheduleModel caixas;
	private List<Funcionario> funcionarios;

	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	}

	public void novo(SelectEvent evento) {
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		funcionarios = new FuncionarioDAO().listar();
	}

	public void salvar() {
		try {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(caixa.getDataAbertura());
			calendario.add(Calendar.DATE, 1); // ++dia
			caixa.setDataAbertura(calendario.getTime());
			
			new CaixaDAO().salvar(caixa);
			Messages.addGlobalInfo("Caixa aberto com sucesso");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao abrir o caixa");
			e.printStackTrace();
		}
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
