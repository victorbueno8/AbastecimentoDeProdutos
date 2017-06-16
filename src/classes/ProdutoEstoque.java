package classes;

import java.util.ArrayList;

import classesDAO.ProdutoEstoqueDAO;

public class ProdutoEstoque {
	private Produto produto;
	private int quantidade;
	private int faixaNormal; //quantidade normal para o estoque
	private double precoTotal;
	
	@Override
	public String toString() {
		return getProduto().getCodBarras() + " - " + getProduto().getNome();
	}
	
	public ProdutoEstoque(Produto produto, int quantidade, int faixaNormal) { //Para reucuperação do banco
		this.produto = produto;
		this.quantidade = quantidade;
		this.faixaNormal = faixaNormal;	
		this.precoTotal = produto.getPreco() * quantidade;
	}
	

	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Produto getProduto(){
		return produto;
	}
	
	public int getQuantidade(){
		return quantidade;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public int getFaixaNormal() {
		return faixaNormal;
	}
	
}
