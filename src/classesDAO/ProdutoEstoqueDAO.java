package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.Fornecedor;
import classes.Produto;
import classes.ProdutoEstoque;

public class ProdutoEstoqueDAO {
	private Connection connection;
	
	public ProdutoEstoqueDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(ProdutoEstoque pe) {
		String sql = "INSERT INTO ProdutoEstoque(codBarras,quantidade,faixaNormal,precoTotal) " +
				"VALUES(?,?,?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, pe.getProduto().getCodBarras());
			stmt.setInt(2, pe.getQuantidade());
			stmt.setInt(3, pe.getFaixaNormal());
			stmt.setDouble(4, pe.getPrecoTotal());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProdutoEstoque> select() {
		String sql = "SELECT * FROM ProdutoEstoque;";
		ArrayList<ProdutoEstoque> pe = new ArrayList<>();
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			ProdutoDAO pDAO = new ProdutoDAO();
			String codP = new String();
			while(rs.next()) {
				codP = rs.getString("codBarras");
				pe.add(new ProdutoEstoque(pDAO.selectProduto(codP), rs.getInt("quantidade"), rs.getInt("faixaNormal")));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return pe;
	}
	
	public ProdutoEstoque selectProdutoEstoque(String codBarras) {
		String sql = "SELECT * FROM ProdutoEstoque WHERE codBarras= ?;";
		ProdutoEstoque pe = null;
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, codBarras);
			ResultSet rs = stmt.executeQuery();
			
			ProdutoDAO pDAO = new ProdutoDAO();
			while(rs.next()) {
				
				pe = new ProdutoEstoque(pDAO.selectProduto(codBarras), rs.getInt("quantidade"), rs.getInt("faixaNormal"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Produto Não Existente", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return pe;
	}
	
	public void updateProdutoEstoque(ProdutoEstoque p) {
		String sql= "UPDATE ProdutoEstoque SET quantidade = ?,faixaNormal = ?,precoTotal = ?" +
				" WHERE codBarras = ?;";
		try{
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setInt(1, p.getQuantidade());
			stmt.setInt(2, p.getFaixaNormal());
			stmt.setDouble(3, p.getPrecoTotal());
			stmt.setString(4, p.getProduto().getCodBarras());
			stmt.execute();				
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
