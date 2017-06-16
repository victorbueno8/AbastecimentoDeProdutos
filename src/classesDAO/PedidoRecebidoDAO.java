package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import classes.Estoque;
import classes.Pedido;
import classes.PedidoRecebido;
import classes.Venda;

public class PedidoRecebidoDAO {
	private Connection connection;
	
	public PedidoRecebidoDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insertHistorico(PedidoRecebido pd) {
		String sql = "INSERT INTO HistoricoRecebimento(id,codBarras,quantidade,fornecedor,descricao,dataEnvio,dataRecibo,qtdRecebida,descricaoRecebimento) " +
				"VALUES(?,?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pd.getPedido().getId());
			stmt.setString(2, pd.getPedido().getProduto().getCodBarras());
			stmt.setInt(3, pd.getPedido().getQuantidade());
			stmt.setString(4, pd.getPedido().getProduto().getFabricante().getNome());
			stmt.setString(5, pd.getPedido().getDescricao());
			String date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(pd.getPedido().getDataEnvio());
			stmt.setTimestamp(6, Timestamp.valueOf(date));
			String currentDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(pd.getDataRecibo());
			stmt.setTimestamp(7, Timestamp.valueOf(currentDate));
			stmt.setInt(8, pd.getQtdRecebida());
			stmt.setString(9, pd.getDescricaoRecebimento());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<PedidoRecebido> select() {
		String sql = "SELECT * FROM HistoricoRecebimento;";
		ArrayList<PedidoRecebido> prlist = new ArrayList<>();
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			ProdutoDAO pDAO = new ProdutoDAO();
			FornecedorDAO fDAO = new FornecedorDAO();
			String codP = new String();
			String nomeF = new String();
			while(rs.next()) {
				codP = rs.getString("codBarras");
				nomeF = rs.getString("fornecedor");
				prlist.add(new PedidoRecebido(new Pedido(rs.getInt("id"), pDAO.selectProduto(codP), rs.getInt("quantidade"), fDAO.selectFornecedor(nomeF), rs.getString("descricao"), rs.getTimestamp("dataEnvio")),
						rs.getInt("qtdRecebida"), rs.getString("descricaoRecebimento"), rs.getTimestamp("dataRecibo")));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return prlist;
	}
	
}
