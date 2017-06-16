package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import classes.Estoque;
import classes.Pedido;
import classes.PedidoRecebido;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Receber_Pedido extends JFrame{
	private JTextField tfId;
	private JTextField tfCodProduto;
	private JTextField tfNomeProduto;
	private JTextField tfFornecedor;
	private JSpinner spUnidades;
	private JButton btnConfirmar;
	private JTextArea Descr;
	
	public Receber_Pedido(Estoque estoque) {
		getContentPane().setLayout(null);
		
		JLabel lblRecebimentoDeProdutos = new JLabel("Recebimento de Produtos");
		lblRecebimentoDeProdutos.setBounds(196, 11, 139, 14);
		getContentPane().add(lblRecebimentoDeProdutos);
		
		tfId = new JTextField();
		tfId.setBounds(132, 49, 104, 20);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		JLabel lblIdDoPedido = new JLabel("Id do Pedido:");
		lblIdDoPedido.setBounds(28, 52, 94, 14);
		getContentPane().add(lblIdDoPedido);
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Pedido a : estoque.getListaPedidos()) {
					if(a.getId() == Integer.parseInt(tfId.getText())) {
						tfCodProduto.setText(a.getProduto().getCodBarras());
						tfNomeProduto.setText(a.getProduto().getNome());
						tfFornecedor.setText(a.getFornecedor().getNome());
						spUnidades.setValue(a.getQuantidade());
						btnConfirmar.setEnabled(true);
					}
				}
			}
		});
		btnProcurar.setBounds(246, 48, 89, 23);
		getContentPane().add(btnProcurar);
		
		JLabel lblIdDeProduto = new JLabel("Codigo de Produto:");
		lblIdDeProduto.setBounds(28, 103, 139, 14);
		getContentPane().add(lblIdDeProduto);
		
		tfCodProduto = new JTextField();
		tfCodProduto.setEditable(false);
		tfCodProduto.setBounds(150, 100, 86, 20);
		getContentPane().add(tfCodProduto);
		tfCodProduto.setColumns(10);
		
		JLabel lblNomeDeProduto = new JLabel("Nome de Produto:");
		lblNomeDeProduto.setBounds(28, 134, 122, 14);
		getContentPane().add(lblNomeDeProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setEditable(false);
		tfNomeProduto.setBounds(150, 128, 203, 20);
		getContentPane().add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(28, 165, 94, 14);
		getContentPane().add(lblFornecedor);
		
		tfFornecedor = new JTextField();
		tfFornecedor.setEditable(false);
		tfFornecedor.setBounds(150, 159, 203, 20);
		getContentPane().add(tfFornecedor);
		tfFornecedor.setColumns(10);
		
		JLabel lblUnidadesRecebidas = new JLabel("Unidades Recebidas:");
		lblUnidadesRecebidas.setBounds(28, 195, 139, 14);
		getContentPane().add(lblUnidadesRecebidas);
		
		spUnidades = new JSpinner();
		spUnidades.setBounds(178, 192, 69, 20);
		getContentPane().add(spUnidades);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Pedido a : estoque.getListaPedidos()) {
					if(a.getId() == Integer.parseInt(tfId.getText())) {
						classes.PedidoRecebido pr = new PedidoRecebido(a, (int)spUnidades.getValue(), Descr.getText());
						pr.confirmaRecebimento(estoque);
						JOptionPane.showMessageDialog(null, "Recebimento calculado");
						dispose();
					}
				}
			}
		});
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBounds(28, 303, 307, 30);
		getContentPane().add(btnConfirmar);
		
		JLabel lblDescrioDoRecebimento = new JLabel("Descri\u00E7\u00E3o do recebimento:");
		lblDescrioDoRecebimento.setBounds(10, 229, 157, 14);
		getContentPane().add(lblDescrioDoRecebimento);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(163, 220, 190, 63);
		getContentPane().add(scrollPane);
		
		Descr = new JTextArea();
		scrollPane.setViewportView(Descr);
		Descr.setLineWrap(true);
	}
}
