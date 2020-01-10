package br.pro.dl.drogaria.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Fabricante;
import br.pro.dl.drogaria.domain.Produto;
import br.pro.dl.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	
	private StreamedContent foto;

	@PostConstruct
	public void listar() {
		try {
			produtos = new ProdutoDAO().listar("descricao");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar lista os produtos");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			produto = new Produto();

			fabricantes = new FabricanteDAO().listar("descricao");

		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma novo Produto");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			if(produto.getCaminho() == null) {
				Messages.addGlobalError("O campo foto é obrigatório");
				return;
			}
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);
			
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("/home/marcos/Programacao_JavaWeb/UploadsFotos/" + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			produto = new Produto();

			fabricantes = new FabricanteDAO().listar("descricao");

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto salvo com sucesso!");
		} catch (RuntimeException | IOException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma novo Produto");
			e.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			Path caminho = Paths.get("/home/marcos/Programacao_JavaWeb/UploadsFotos/" + produto.getCodigo() + ".png");
			Files.deleteIfExists(caminho);

			produtos = produtoDAO.listar();

			Messages.addGlobalInfo("Produto removido com sucesso!");
		} catch (RuntimeException | IOException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Produto");
			e.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			produto.setCaminho("/home/marcos/Programacao_JavaWeb/UploadsFotos/" + produto.getCodigo() + ".png");
			
			fabricantes = new FabricanteDAO().listar("descricao");
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar editar o Produto");
			e.printStackTrace();
		}
	}
	
	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
			
			Messages.addFlashGlobalInfo("Upload realizado com sucesso");
		} catch (IOException e) {
			Messages.addGlobalError("Ocorreu um erro ao tentar realizar o upload de arquivo");
			e.printStackTrace();
		}
	}
	
	public void imprimir() {
		try {
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String, Object> filtros = tabela.getFilters();
			String pdescricao = (String) filtros.get("descricao");
			String fdescricao = (String) filtros.get("fabricante.descricao");
			
			String caminho = Faces.getRealPath("/reports/produtos.jasper");

			Map<String, Object> parametros = new HashMap<>();
			if (pdescricao == null) {
				parametros.put("PRODUTO_DESCRICAO", "%%");
			} else {
				parametros.put("PRODUTO_DESCRICAO", "%" + pdescricao + "%");
			}
			
			if (fdescricao == null) {
				parametros.put("FABRICANTE_DESCRICAO", "%%");
			} else {
				parametros.put("FABRICANTE_DESCRICAO", "%" + pdescricao + "%");
			}
			
			Connection conexao = HibernateUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			
			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
			erro.printStackTrace();
		}
	}
	
	public void download(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			InputStream	stream = new FileInputStream("/home/marcos/Programacao_JavaWeb/UploadsFotos/" + produto.getCodigo() + ".png");
			foto = new DefaultStreamedContent(stream, "image/png", produto.getCodigo() + ".png");
		} catch (FileNotFoundException e) {
			Messages.addGlobalError("Ocorreu um erro ao efetuar o download da foto");
			e.printStackTrace();
		}
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

}
