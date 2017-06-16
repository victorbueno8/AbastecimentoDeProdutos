package classes;

import java.util.Date;

import classesDAO.PedidoDAO;
import classesDAO.PedidoRecebidoDAO;

public class PedidoRecebido{
	private Pedido pedido;
	private int qtdRecebida;
	private String descricaoRecebimento;
	private Date dataRecibo;
	
	public PedidoRecebido(Pedido pedido, int qtdRecebida, String descricaoRecebimento) {
		this.pedido = pedido;
		this.qtdRecebida = qtdRecebida;
		this.descricaoRecebimento = descricaoRecebimento;
		this.setDate();
	}
	
	public PedidoRecebido(Pedido pedido, int qtdRecebida, String descricaoRecebimento, Date dataRecibo) {
		this.pedido = pedido;
		this.qtdRecebida = qtdRecebida;
		this.descricaoRecebimento = descricaoRecebimento;
		this.dataRecibo = dataRecibo;
	}
	
	//USUÁRIO CONFIRMA O RECEBIMENTO DO PRODUTO PARA REPOSIÇÃO
		public void confirmaRecebimento(Estoque e) {
			classesDAO.PedidoDAO pdDAO = new PedidoDAO();
			classesDAO.PedidoRecebidoDAO prDAO = new PedidoRecebidoDAO();
			if(e.aumentaQuantidade(this.getPedido().getProduto(), this.getQtdRecebida())){
				System.out.println("Pedido nro."+this.getPedido().getId()+" recebido");
				prDAO.insertHistorico(this);
				pdDAO.delete(this.getPedido());
				e.atualizaListaPedidos();
			}
		}
	
	public Pedido getPedido() {
		return pedido;
	}
	public int getQtdRecebida() {
		return qtdRecebida;
	}
	public String getDescricaoRecebimento() {
		return descricaoRecebimento;
	}
	public Date getDataRecibo() {
		return dataRecibo;
	}
	
	public void setDate() {
		this.dataRecibo = new Date();
	}
	
}
