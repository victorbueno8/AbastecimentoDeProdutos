package classes;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import classesDAO.PedidoDAO;
import classesDAO.PedidoRecebidoDAO;

public class Pedido {
	private int id;
	private Produto produto;
	private int quantidade;
	private Fornecedor fornecedor;
	private String descricao;
	private Date dataEnvio;
	
	public Pedido(int id, Produto produto, int quantidade, Fornecedor fornecedor) {
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.fornecedor = fornecedor;
		
	}
	
	public Pedido(int id, Produto produto, int quantidade, Fornecedor fornecedor, String descricao, Date dataEnvio) {
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
		this.fornecedor = fornecedor;
		this.descricao = descricao;
		this.dataEnvio = dataEnvio;
	}
	
	@Override
	public String toString() {
		return "Pedido : nro."+id+" - "+produto.getNome()+" - "+fornecedor.getNome()+" Quantidade:"+quantidade 
				+ "\n Data/Hora: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(this.getDataEnvio());
	}
	
	//MOSTRA OS DADOS DO PEDIDO E SIMULA UM ENVIO
	public void enviarPedido() {
		classesDAO.PedidoDAO pdDAO = new PedidoDAO();
		pdDAO.insert(this);
	}
	
	public Produto getProduto() {
		return produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setDataEnvio() {
		this.dataEnvio = new Date();
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
