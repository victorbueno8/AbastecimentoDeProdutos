package classes;

import java.util.ArrayList;

import classesDAO.ProdutoDAO;

public class Produto {
	private String codBarras;
	private String nome;
	private Fornecedor fabricante;
	private String descricao;
	private double preco;
	
	public Produto(String codBarras, String nome, Fornecedor fabricante, String descricao, double preco) {
		this.codBarras = codBarras;
		this.nome = nome;
		this.fabricante = fabricante;
		this.descricao = descricao;
		this.preco = preco;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	public Fornecedor getFabricante() {
		return fabricante;
	}

	
	

	
	
}
