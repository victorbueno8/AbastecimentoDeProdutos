package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.Pedido;

public class PedidoDAO {
	private Connection connection;
	
	public PedidoDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Pedido pd) {
		String sql = "INSERT INTO Pedido(id,codBarras,quantidade,fornecedor,descricao,dataEnvio) " +
				"VALUES(?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, pd.getId());
			stmt.setString(2, pd.getProduto().getCodBarras());
			stmt.setInt(3, pd.getQuantidade());
			stmt.setString(4, pd.getFornecedor().getNome());
			stmt.setString(5, pd.getDescricao());
			String currentDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(pd.getDataEnvio());
			stmt.setTimestamp(6, Timestamp.valueOf(currentDate));
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro na inserção", "Erro", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public ArrayList<Pedido> select() {
		String sql = "SELECT * FROM Pedido;";
		ArrayList<Pedido> pd = new ArrayList<>();
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
				pd.add(new Pedido(rs.getInt("id"), pDAO.selectProduto(codP), rs.getInt("quantidade"), fDAO.selectFornecedor(nomeF),
						rs.getString("descricao"), rs.getTimestamp("dataEnvio")));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return pd;
	}
	
	public int getLastId() {
		String sql = "SELECT MAX(id) FROM HistoricoRecebimento;";
		int lastId = 0;
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				lastId = rs.getInt("MAX(id)");
			}
			
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lastId;
	}
	
	
	public void delete(Pedido p) {
		try{
			String sql = "DELETE FROM Pedido WHERE id = ?;";
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setInt(1, p.getId());
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
