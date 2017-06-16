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
import classesDAO.PedidoDAO;
import classesDAO.ProdutoEstoqueDAO;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Enviar_Pedido extends JFrame{
	private JTextField tfId;
	private JTextField tfCodProduto;
	private JTextField tfNomeProduto;
	private JTextField tfFornecedor;
	private JSpinner spUnidades;
	private JButton btnConfirmar;
	private JTextArea taDesc;
	
	public Enviar_Pedido(Pedido p) {
		getContentPane().setLayout(null);
		
		JLabel lblEnvioDeProdutos = new JLabel("Envio de Produtos");
		lblEnvioDeProdutos.setBounds(196, 11, 139, 14);
		getContentPane().add(lblEnvioDeProdutos);
		
		tfId = new JTextField();
		tfId.setText(Integer.toString(p.getId()));
		tfId.setEditable(false);
		tfId.setBounds(132, 49, 104, 20);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		JLabel lblIdDoPedido = new JLabel("Id do Pedido:\r\n");
		lblIdDoPedido.setBounds(28, 52, 94, 14);
		getContentPane().add(lblIdDoPedido);
		
		JLabel lblIdDeProduto = new JLabel("Codigo de Produto:");
		lblIdDeProduto.setBounds(28, 103, 94, 14);
		getContentPane().add(lblIdDeProduto);
		
		tfCodProduto = new JTextField();
		tfCodProduto.setText(p.getProduto().getCodBarras());
		tfCodProduto.setEditable(false);
		tfCodProduto.setBounds(132, 100, 86, 20);
		getContentPane().add(tfCodProduto);
		tfCodProduto.setColumns(10);
		
		JLabel lblNomeDeProduto = new JLabel("Nome de Produto:");
		lblNomeDeProduto.setBounds(28, 134, 94, 14);
		getContentPane().add(lblNomeDeProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setText(p.getProduto().getNome());
		tfNomeProduto.setEditable(false);
		tfNomeProduto.setBounds(132, 131, 203, 20);
		getContentPane().add(tfNomeProduto);
		tfNomeProduto.setColumns(10);
		
		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(28, 165, 94, 14);
		getContentPane().add(lblFornecedor);
		
		tfFornecedor = new JTextField();
		tfFornecedor.setText(p.getProduto().getFabricante().getNome());
		tfFornecedor.setEditable(false);
		tfFornecedor.setBounds(132, 162, 203, 20);
		getContentPane().add(tfFornecedor);
		tfFornecedor.setColumns(10);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setBounds(28, 195, 104, 14);
		getContentPane().add(lblUnidades);
		
		spUnidades = new JSpinner();
		spUnidades.setValue(p.getQuantidade());
		spUnidades.setBounds(149, 192, 69, 20);
		getContentPane().add(spUnidades);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.setDescricao(taDesc.getText());
				p.setDataEnvio();
				p.setQuantidade((int) spUnidades.getValue());
				
				p.enviarPedido();
				JOptionPane.showMessageDialog(null, p, "Pedido Enviado", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		btnConfirmar.setBounds(28, 297, 307, 30);
		getContentPane().add(btnConfirmar);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(28, 220, 60, 14);
		getContentPane().add(lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 220, 237, 49);
		getContentPane().add(scrollPane);
		
		taDesc = new JTextArea();
		scrollPane.setViewportView(taDesc);
		taDesc.setLineWrap(true);
	}
}
