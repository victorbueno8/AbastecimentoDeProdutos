package interfaceGrafica;

import javax.swing.JFrame;
import javax.swing.JTextField;

import classes.Venda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Realizar_Venda extends JFrame{
	private JTextField tfId;
	private JTextField tfcodBarras;
	private JTextField tfProduto;
	private JButton btnconfirma;
	private int lastId;
	
	public Realizar_Venda(classes.Estoque estoque) {
		setTitle("Painel de Vendas");
		getContentPane().setLayout(null);
		
		ArrayList<Venda> listaVendas = (new classesDAO.VendaDAO().select(estoque));
		lastId = listaVendas.size();
		
		tfId = new JTextField();
		tfId.setText(String.valueOf(lastId));
		tfId.setEditable(false);
		tfId.setBounds(50, 11, 86, 20);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 14, 35, 14);
		getContentPane().add(lblId);
		
		tfcodBarras = new JTextField();
		tfcodBarras.setBounds(144, 58, 141, 20);
		getContentPane().add(tfcodBarras);
		tfcodBarras.setColumns(10);
		
		JLabel lblCdigoDeBarras = new JLabel("C\u00F3digo de Barras:");
		lblCdigoDeBarras.setBounds(30, 61, 106, 14);
		getContentPane().add(lblCdigoDeBarras);
		
		JButton btnProcura = new JButton("Procura");
		btnProcura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tfProduto.setText(estoque.buscaProduto(tfcodBarras.getText()).toString());
				btnconfirma.setEnabled(true);
			}
		});
		btnProcura.setBounds(300, 57, 89, 23);
		getContentPane().add(btnProcura);
		
		tfProduto = new JTextField();
		tfProduto.setEditable(false);
		tfProduto.setBounds(30, 86, 359, 20);
		getContentPane().add(tfProduto);
		tfProduto.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(30, 120, 70, 14);
		getContentPane().add(lblQuantidade);
		
		JSpinner spQtd = new JSpinner();
		spQtd.setBounds(108, 117, 54, 20);
		getContentPane().add(spQtd);
		
		btnconfirma = new JButton("Confirma Venda");
		btnconfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classesDAO.ProdutoEstoqueDAO prDAO = new classesDAO.ProdutoEstoqueDAO();
				classes.Venda v = new classes.Venda(Integer.parseInt(tfId.getText()), prDAO.selectProdutoEstoque(tfcodBarras.getText()), estoque, (int)spQtd.getValue());
				
				if(v.executaVenda()) {
					JOptionPane.showMessageDialog(null, "Venda Realizada com sucesso!\n" + v, "Venda realizada", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Erro na Venda!", "Venda cancelada", JOptionPane.ERROR_MESSAGE);
				} 
				dispose();
			}
		});
		btnconfirma.setEnabled(false);
		btnconfirma.setBounds(178, 117, 211, 23);
		getContentPane().add(btnconfirma);
	}
}
