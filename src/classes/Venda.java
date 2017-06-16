package classes;

import java.util.ArrayList;

public class Venda {
	private int id;
	private ProdutoEstoque produto;
	private Estoque estoque;
	private int unidades;
	
	public Venda(int id, ProdutoEstoque produto, Estoque estoque, int unidades) {
		this.id = id;
		this.produto = produto;
		this.estoque = estoque;
		this.unidades = unidades;
	}
	
	@Override
	public String toString() {
		return "Venda nro." + id + " - " + produto.getProduto().getNome() + " - qtd: " + unidades + " - Total:" + produto.getPrecoTotal();
	}
	
	//MANDA VERIFICAR SE O PRODUTOESCOLHIDO PARA VENDA ESTÁ CONTIDO NO ESTOQUE 
	public boolean validarVenda() {
		if(this.estoque.buscaProduto(produto.getProduto()) != null) {
			return true;
		}
		return false;
	}
	
	//EXECUTA A VENDA MANDANDO VALIDAR O PRODUTO NO ESTOQUE
	public boolean executaVenda() {
		if(validarVenda()) {
			if(this.estoque.decrementaQuantidade(produto.getProduto(),unidades)) {
				classesDAO.VendaDAO vDAO = new classesDAO.VendaDAO();
				vDAO.insert(this);
				return true;
			}
		}
		return false;
	}
	
	public int getId() {
		return id;
	}

	public ProdutoEstoque getProduto() {
		return produto;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public int getUnidades() {
		return unidades;
	}
}
