package interfaceGrafica;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import classes.Fornecedor;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Cadastra_Produto extends JFrame {
	private JTextField tbcodBarras;
	private JTextField tbNome;
	private JTextField tfPreco;
	private JTextField tfQtd;
	
	public Cadastra_Produto(classes.Estoque estoque) {
		setTitle("Cadastro de Produto");
		getContentPane().setLayout(null);
		
		JLabel lblCdigoDeBarras = new JLabel("C\u00F3digo de Barras : ");
		lblCdigoDeBarras.setBounds(10, 11, 111, 14);
		getContentPane().add(lblCdigoDeBarras);
		
		tbcodBarras = new JTextField();
		tbcodBarras.setBounds(131, 8, 145, 20);
		getContentPane().add(tbcodBarras);
		tbcodBarras.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 38, 46, 14);
		getContentPane().add(lblNome);
		
		tbNome = new JTextField();
		tbNome.setBounds(131, 39, 145, 20);
		getContentPane().add(tbNome);
		tbNome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 70, 145, 62);
		getContentPane().add(scrollPane);
		
		JTextArea tadescricao = new JTextArea();
		tadescricao.setLineWrap(true);
		scrollPane.setViewportView(tadescricao);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 75, 111, 14);
		getContentPane().add(lblDescrio);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o de Compra:");
		lblPreco.setBounds(10, 145, 111, 14);
		getContentPane().add(lblPreco);
		
		tfPreco = new JTextField();
		tfPreco.setBounds(131, 142, 145, 20);
		getContentPane().add(tfPreco);
		tfPreco.setColumns(10);
		
		JLabel lblQuantidadeInicial = new JLabel("Quantidade Inicial(Faixa Normal):");
		lblQuantidadeInicial.setBounds(10, 178, 170, 14);
		getContentPane().add(lblQuantidadeInicial);
		
		tfQtd = new JTextField();
		tfQtd.setBounds(190, 173, 86, 20);
		getContentPane().add(tfQtd);
		tfQtd.setColumns(10);
		
		JLabel lblFabricante = new JLabel("Fabricante:");
		lblFabricante.setBounds(10, 203, 86, 14);
		getContentPane().add(lblFabricante);
		
		JComboBox cbFabrica = new JComboBox<>((new classesDAO.FornecedorDAO()).select().toArray());
		cbFabrica.setBounds(131, 203, 145, 20);
		getContentPane().add(cbFabrica);
		
		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				estoque.addProduto(new classes.Produto(tbcodBarras.getText(), tbNome.getText(), (Fornecedor)cbFabrica.getSelectedItem(), tadescricao.getText(), 
													Double.parseDouble(tfPreco.getText()) ),Integer.parseInt(tfQtd.getText()) );
				JOptionPane.showMessageDialog(null, "Produto adicionado em estoque!", "Produto Adicionado", JOptionPane.PLAIN_MESSAGE);
				dispose();
				
			}
		});
		btnCadastrarProduto.setBounds(10, 241, 266, 23);
		getContentPane().add(btnCadastrarProduto);
	}
}
