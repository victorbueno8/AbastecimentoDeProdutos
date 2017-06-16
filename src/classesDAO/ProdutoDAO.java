package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.Fornecedor;
import classes.Produto;

public class ProdutoDAO {
	private Connection connection;
	
	public ProdutoDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Produto p) {
		String sql = "INSERT INTO Produto(codBarras,nome,fabricante,descricao,preco)" +
				" VALUES (?,?,?,?,?);";
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, p.getCodBarras());
			stmt.setString(2, p.getNome());
			stmt.setString(3, p.getFabricante().getNome());
			stmt.setString(4, p.getDescricao());
			stmt.setDouble(5, p.getPreco());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Produto selectProduto(String codBarras) {
		String sql = "SELECT * FROM Produto WHERE codBarras= ?;";
		Produto p = null;
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, codBarras);
			ResultSet rs = stmt.executeQuery();
			
			FornecedorDAO fsDAO = new FornecedorDAO();
			while(rs.next()) {
				p = new Produto(rs.getString("codBarras"),rs.getString("nome"),fsDAO.selectFornecedor(rs.getString("fabricante")),
						rs.getString("descricao"),rs.getDouble("preco"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void updateProduto(Produto p) {
		String sql= "UPDATE Produto SET nome = ?,fabricante = ?,descricao = ?,preco = ?" +
				" WHERE codBarras = ?;";
		try{
			PreparedStatement stmt= connection.prepareStatement(sql);
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getFabricante().getNome());
			stmt.setString(3, p.getDescricao());
			stmt.setDouble(4, p.getPreco());
			stmt.setString(5, p.getCodBarras());
			stmt.execute();				
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
