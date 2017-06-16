package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Estoque;
import classes.Fornecedor;
import classes.Venda;

public class VendaDAO {
	private Connection connection;
	
	public VendaDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Venda v) {
		String sql = "INSERT INTO Venda(id,codBarras,unidades) " +
				"VALUES(?,?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, v.getId());
			stmt.setString(2, v.getProduto().getProduto().getCodBarras());
			stmt.setInt(3, v.getUnidades());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Venda> select(Estoque estoque) {
		String sql = "SELECT * FROM Venda;";
		ArrayList<Venda> ve = new ArrayList<>();
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			ProdutoEstoqueDAO pDAO = new ProdutoEstoqueDAO();
			while(rs.next()) {
				ve.add(new Venda(rs.getInt("id"), pDAO.selectProdutoEstoque(rs.getString("codBarras")), estoque, rs.getInt("unidades")));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ve;
	}

}
