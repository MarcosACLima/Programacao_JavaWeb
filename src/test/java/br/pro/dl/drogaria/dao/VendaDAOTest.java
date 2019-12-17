package br.pro.dl.drogaria.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.pro.dl.drogaria.dao.ClienteDAO;
import br.pro.dl.drogaria.dao.FuncionarioDAO;
import br.pro.dl.drogaria.dao.VendaDAO;
import br.pro.dl.drogaria.domain.Cliente;
import br.pro.dl.drogaria.domain.Funcionario;
import br.pro.dl.drogaria.domain.Venda;

public class VendaDAOTest {
	
	@Ignore
	@Test
	public void salvar() throws ParseException {
		Long codigoFuncionario = 3L;
		Long codigoCliente = 1L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		Venda venda = new Venda();
		venda.setHorario(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/06/2019 10:45:33"));
		venda.setPrecoTotal(new BigDecimal("74.25"));
		venda.setFuncionario(funcionario);
		venda.setCliente(cliente);
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.salvar(venda);
	}
	
	@Ignore
	@Test
	public void listar() {
		VendaDAO vendaDAO = new VendaDAO();
		List<Venda> resultado = vendaDAO.listar();
		
		for (Venda venda : resultado) {
			if(venda.getCliente() != null) {
				System.out.print("Venda com cadastro do Cliente");
				System.out.println("\n Codigo Venda: " + venda.getCodigo()
								+ "\n Horario: " + venda.getHorario()
								+ "\n Preco Total: " + venda.getPrecoTotal()
								+ "\n Funcionario: " + venda.getFuncionario().getPessoa().getNome()
								+ "\n Numero Carteira de Trabalho: " + venda.getFuncionario().getCarteiraTrabalho()
								+ "\n Nome Cliente: " + venda.getCliente().getPessoa().getNome());
			} else {
				System.out.print("Venda a vista: ");
				System.out.println("\n Codigo Venda: " + venda.getCodigo()
								+ "\n Horario: " + venda.getHorario()
								+ "\n Preco Total: " + venda.getPrecoTotal()
								+ "\n Funcionario: " + venda.getFuncionario().getPessoa().getNome()
								+ "\n Numero Carteira de Trabalho: " + venda.getFuncionario().getCarteiraTrabalho());
			}
		}
	}
	
	@Ignore
	@Test
	public void buscar() {
		Long codigo = 3L;
		
		VendaDAO  vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);
		if(venda.getCliente() != null) {
			System.out.print("Venda com cadastro do Cliente");
			System.out.println("\n Codigo Venda: " + venda.getCodigo()
							+ "\n Horario: " + venda.getHorario()
							+ "\n Preco Total: " + venda.getPrecoTotal()
							+ "\n Funcionario: " + venda.getFuncionario().getPessoa().getNome()
							+ "\n Numero Carteira de Trabalho: " + venda.getFuncionario().getCarteiraTrabalho()
							+ "\n Nome Cliente: " + venda.getCliente().getPessoa().getNome());
		} else {
			System.out.print("Venda a vista: ");
			System.out.println("\n Codigo Venda: " + venda.getCodigo()
							+ "\n Horario: " + venda.getHorario()
							+ "\n Preco Total: " + venda.getPrecoTotal()
							+ "\n Funcionario: " + venda.getFuncionario().getPessoa().getNome()
							+ "\n Numero Carteira de Trabalho: " + venda.getFuncionario().getCarteiraTrabalho());
		}
	}
	
	@Ignore
	@Test
	public void excluir() {
		Long codigo = 5L;
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigo);
		
		vendaDAO.excluir(venda);
	}
	
	@Ignore
	@Test
	public void editar() {
		Long codigoVenda = 1L;
		Long codigoCliente = 2L;
		Long codigoFuncionario = 3L;
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDAO.buscar(codigoFuncionario);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = clienteDAO.buscar(codigoCliente);
		
		VendaDAO vendaDAO = new VendaDAO();
		Venda venda = vendaDAO.buscar(codigoVenda);
		
		venda.setHorario(new Date());
		venda.setPrecoTotal(new BigDecimal("105.99"));
		
		venda.setCliente(cliente);
		
		venda.setFuncionario(funcionario);
		
		vendaDAO.editar(venda);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
