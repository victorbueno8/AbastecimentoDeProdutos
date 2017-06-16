package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import classes.Fornecedor;
import java.awt.SystemColor;

public class Cadastra_Fornecedor extends JFrame{
	private JTextField tbnomeFornecedor;
	private JTextField tblocalizacao;
	private JLabel lblNomeDoFornecedor;
	private JLabel lblLocalDeFabricao;
	private JTable table;
	private DefaultTableModel model;
	
	public Cadastra_Fornecedor() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setTitle("Cadastro de Fornecedor");
		
		tbnomeFornecedor = new JTextField();
		tbnomeFornecedor.setBounds(27, 41, 150, 20);
		tbnomeFornecedor.setColumns(10);
		
		tblocalizacao = new JTextField();
		tblocalizacao.setToolTipText("");
		tblocalizacao.setBounds(187, 41, 196, 20);
		tblocalizacao.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(tbnomeFornecedor);
		getContentPane().add(tblocalizacao);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model = (DefaultTableModel) table.getModel();
				if(!tbnomeFornecedor.getText().trim().equals("")) {
					model.addRow(new Object[] {tbnomeFornecedor.getText(),tblocalizacao.getText()});
					
					classesDAO.FornecedorDAO fDAO = new classesDAO.FornecedorDAO();
					fDAO.insert(new Fornecedor(tbnomeFornecedor.getText(), tblocalizacao.getText()));
					JOptionPane.showMessageDialog(null, "Fornecedor foi Adicionado com Sucesso!", "Fornecedor Adicionado", JOptionPane.PLAIN_MESSAGE);
					tbnomeFornecedor.setText("");
					tblocalizacao.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos campos", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdd.setBounds(389, 40, 61, 23);
		getContentPane().add(btnAdd);
		
		lblNomeDoFornecedor = new JLabel("Nome do Fornecedor:");
		lblNomeDoFornecedor.setBounds(27, 26, 111, 14);
		getContentPane().add(lblNomeDoFornecedor);
		
		lblLocalDeFabricao = new JLabel("Local de Fabrica\u00E7\u00E3o:");
		lblLocalDeFabricao.setBounds(187, 26, 121, 14);
		getContentPane().add(lblLocalDeFabricao);
		
		JScrollPane tabela = new JScrollPane();
		tabela.setBounds(27, 79, 401, 157);
		getContentPane().add(tabela);
		
		ArrayList<Fornecedor> fornecedores = (new classesDAO.FornecedorDAO()).select();
		table = new JTable();
		tabela.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Fornecedor", "Local de Fabrica\u00E7ao"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(223);
		table.getColumnModel().getColumn(1).setPreferredWidth(373);
		model = (DefaultTableModel) table.getModel();
		for(Fornecedor f: fornecedores){
			model.addRow(new Object[] {f.getNome(),f.getFabricacaoLocalidade()});
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
