package br.pro.dl.drogaria.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.pro.dl.drogaria.dao.FabricanteDAO;
import br.pro.dl.drogaria.dao.ProdutoDAO;
import br.pro.dl.drogaria.domain.Fabricante;
import br.pro.dl.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	@PostConstruct
	public void listar() {
		try {
			produtos = new ProdutoDAO().listar();
		} catch (RuntimeException e) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar lista ps produtos");
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

}
