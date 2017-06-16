package classesDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Fornecedor;

public class AjustesDAO {
	private Connection connection;
	
	public AjustesDAO() {
		connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(double valor) {
		String sql = "INSERT INTO Ajustes(porcentagemLimite) " +
				"VALUES(?);";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, valor);
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double select() {
		String sql = "SELECT * FROM Ajustes;";
		double valor = 0;
		try{
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				valor = rs.getDouble("porcentagemLimite");
			}
			rs.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return valor;
	}
	
	public void update(double valor) {
		String sql = "UPDATE Ajustes SET porcentagemLimite = ?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDouble(1, valor);
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
