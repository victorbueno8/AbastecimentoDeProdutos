package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classes.Fornecedor;

public class FornecedorDAO {
	private Connection connection;
	
	public FornecedorDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Fornecedor f) {
		String sql = "INSERT INTO Fornecedor(nome,localidade) " +
				"VALUES(?,?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getFabricacaoLocalidade());
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Fornecedor> select() {
		String sql = "SELECT * FROM Fornecedor;";
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				fornecedores.add(new Fornecedor(rs.getString("nome"),rs.getString("localidade")));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return fornecedores;
	}
	
	public Fornecedor selectFornecedor(String nome) {
		String sql = "SELECT * FROM Fornecedor WHERE nome= ?;";
		Fornecedor f = null;
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				f = new Fornecedor(rs.getString("nome"),rs.getString("localidade"));
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fornecedor Não Existente", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		return f;
	}
	
}
