package classes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import classesDAO.PedidoDAO;
import classesDAO.ProdutoEstoqueDAO;
import interfaceGrafica.Enviar_Pedido;

public class Estoque {
	private ArrayList<ProdutoEstoque> catalogo;
	private ArrayList<Pedido> listaPedidos;
	private double porcentMinima;
	
	public Estoque(ArrayList<ProdutoEstoque> catalogo, ArrayList<Pedido> listaPedidos, double porcentMinima) {
		this.catalogo = catalogo;
		this.listaPedidos = listaPedidos;
		this.porcentMinima = porcentMinima/100;
	}
	
	@Override
	public String toString() {
		String s = new String();
		for(ProdutoEstoque a : catalogo) {
			s += " Código: " + a.getProduto().getCodBarras() + " - "+ a.getProduto().getNome() +" Fornecedor:"+a.getProduto().getFabricante().getNome()
					+ " - Quantidade: " + a.getQuantidade() + " - Preço unitário: " + a.getProduto().getPreco() + "\n";
		}
		return s;
	}
	
	public void atualizaListaPedidos() {
		setListaPedidos((new classesDAO.PedidoDAO()).select());
	}
	
	//CADASTRA NOVOS PRODUTOS NO ESTOQUE
	public void addProduto(Produto p, int quantidade) {
		this.catalogo.add(new ProdutoEstoque(p, quantidade, quantidade));
		
		classesDAO.ProdutoEstoqueDAO peDAO = new classesDAO.ProdutoEstoqueDAO();
		classesDAO.ProdutoDAO pDAO = new classesDAO.ProdutoDAO();
		pDAO.insert(p);
		peDAO.insert(new ProdutoEstoque(p, quantidade, quantidade));
		
	}
	
	//BUSCA QUANTIDADES DE PRODUTOS A PARTIR DE SEU OBJETO
	public ProdutoEstoque buscaProduto(Produto p) {
		for(ProdutoEstoque pe : catalogo) {
			if(pe.getProduto().getCodBarras().equals(p.getCodBarras())) {
				return pe;
			}
		}
		JOptionPane.showMessageDialog(null, "Produto não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	//BUSCA QUANTIDADES DE PRODUTOS A PARTIR DE SEU CODIGO DE BARRAS
	public ProdutoEstoque buscaProduto(String p) {
		for(ProdutoEstoque pe : catalogo) {
			if(pe.getProduto().getCodBarras().equals(p)) {
				return pe;
			}
		}
		JOptionPane.showMessageDialog(null, "Produto não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	//DECREMENTA QUANDIDADE DE PRODUTOS NO ESTOQUE APÓS A VENDA
	public boolean decrementaQuantidade(Produto p, int quantidade) {
		ProdutoEstoque pe = buscaProduto(p);
		if(verificaDisponibilidade(pe, quantidade)) {
			pe.setQuantidade(pe.getQuantidade() - quantidade);
			classesDAO.ProdutoEstoqueDAO peDAO = new ProdutoEstoqueDAO();
			peDAO.updateProdutoEstoque(pe);
			return true;
		}
		JOptionPane.showMessageDialog(null, "Quantidade do produto não disponível!", "Não Disponível", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	//VERIFICA DISPONIBILIDADE DE UM PRODUTO ANTES DA VENDA
	public boolean verificaDisponibilidade(ProdutoEstoque pe, int quantidadeVenda) {
		if(pe.getQuantidade() >= quantidadeVenda) {
			for(Pedido e : this.listaPedidos) {
				if(pe.getProduto().getCodBarras().equals(e.getProduto().getCodBarras())) {
					JOptionPane.showMessageDialog(null, "Produto em Falta Esperando Recebimento de Pedido");
					return true;
				}
			}
			if(pe.getQuantidade() - quantidadeVenda <= pe.getFaixaNormal() * porcentMinima) {
				JOptionPane.showMessageDialog(null, "Produto em falta no Estoque - Fazendo pedido de reposição...", "Falta de Produto", JOptionPane.INFORMATION_MESSAGE);
				
				int repo = pe.getFaixaNormal() - (pe.getQuantidade() - quantidadeVenda);
				classesDAO.PedidoDAO pdDAO = new PedidoDAO();
				Pedido pd = new Pedido(pdDAO.getLastId() + 1, pe.getProduto(), repo, pe.getProduto().getFabricante());
				
				
				interfaceGrafica.Enviar_Pedido enviarP = new Enviar_Pedido(pd);
				enviarP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				enviarP.setSize(516,400);
				enviarP.setResizable(false);
				enviarP.setVisible(true);
				
				listaPedidos.add(pd);
				
				//reposição até completar o estoque
			} 
			
			return true;
		}
		return false;
	}
	
	//INCREMENTA QUANTIDADES DE REPOSIÇÃO NO ESTOQUE 
	public boolean aumentaQuantidade(Produto p, int quantidade) {
		ProdutoEstoque pe = buscaProduto(p);
		int qtdAnterior = pe.getQuantidade();
		
		pe.setQuantidade(pe.getQuantidade() + quantidade);
		classesDAO.ProdutoEstoqueDAO peDAO = new ProdutoEstoqueDAO();
		peDAO.updateProdutoEstoque(pe);
		
		if(pe.getQuantidade() == qtdAnterior + quantidade) {
			return true;
		}
		return false;
	}
	
	public void setPorcentMinima(double porcentMinima) {
		this.porcentMinima = porcentMinima/100;
	}
	
	public double getPorcentMinima() {
		return this.porcentMinima;
	}
	
	public void setListaPedidos(ArrayList<Pedido> list) {
		this.listaPedidos = list;
	}

	public ArrayList<ProdutoEstoque> getCatalogo() {
		return catalogo;
	}

	public ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}


	
	
}
