package interfaceGrafica;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import classes.Pedido;

import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;

public class Menu_Principal extends JFrame {
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblTituloPainel;
	
	public Menu_Principal(classes.Estoque estoque) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Victor\\workspace\\AbastecimentoDeProdutos\\src\\Util\\1463791577_Dropbox_Social-Network-Communicate-Page-Curl-Effect-Circle-Glossy-Shadow-Shine.ico"));
		getContentPane().setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1306, 711));
		setTitle("Abastecimento de Produtos");
		getContentPane().setLayout(null);
		setResizable(false);
		
		JButton btnCadastrarFornecedor = new JButton("Cadastrar Fornecedor");
		btnCadastrarFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				interfaceGrafica.Cadastra_Fornecedor cFornecedor = new Cadastra_Fornecedor();
				cFornecedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				cFornecedor.setSize(470,300);
				cFornecedor.setVisible(true);
				cFornecedor.setResizable(false);
			}
		});
		btnCadastrarFornecedor.setBounds(32, 310, 139, 58);
		getContentPane().add(btnCadastrarFornecedor);
		
		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(new classesDAO.FornecedorDAO()).select().isEmpty()){
					interfaceGrafica.Cadastra_Produto cProduto = new Cadastra_Produto(estoque);
					cProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					cProduto.setSize(400,310);
					cProduto.setResizable(false);
					cProduto.setVisible(true);
				} else{
					JOptionPane.showMessageDialog(null, "Cadastre Fornecedores para Cadastrar Produto", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnCadastrarProduto.setBounds(32, 379, 139, 50);
		getContentPane().add(btnCadastrarProduto);
		
		JButton btnRealizarVenda = new JButton("Realizar Venda");
		btnRealizarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interfaceGrafica.Realizar_Venda rVenda = new Realizar_Venda(estoque);
				rVenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				rVenda.setSize(450,200);
				rVenda.setResizable(false);
				rVenda.setVisible(true);
			}
		});
		btnRealizarVenda.setBounds(32, 438, 139, 50);
		getContentPane().add(btnRealizarVenda);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(1178, 617, 100, 23);
		getContentPane().add(btnSair);
		
		JButton btnRecebimentoDeProdutos = new JButton("Receber Produto");
		btnRecebimentoDeProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				interfaceGrafica.Receber_Pedido rPedido = new Receber_Pedido(estoque);
				rPedido.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				rPedido.setSize(400,400);
				rPedido.setResizable(false);
				rPedido.setVisible(true);
			}
		});
		btnRecebimentoDeProdutos.setBounds(32, 499, 139, 58);
		getContentPane().add(btnRecebimentoDeProdutos);
		
		JButton btnVisualizarPedidos = new JButton("Pedidos");
		btnVisualizarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTituloPainel.setText("Lista de Pedidos em Espera");
				table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Id", "Codigo Barras", "Nome", "quantidade", "fornecedor", "descri\u00E7\u00E3o", "data de envio"
						}
					));
					table.getColumnModel().getColumn(0).setPreferredWidth(47);
					table.getColumnModel().getColumn(1).setPreferredWidth(87);
					table.getColumnModel().getColumn(2).setPreferredWidth(132);
					table.getColumnModel().getColumn(5).setPreferredWidth(181);
					table.getColumnModel().getColumn(6).setPreferredWidth(119);
					model = (DefaultTableModel) table.getModel();
					for(Pedido a: estoque.getListaPedidos()){
						model.addRow(new Object[] {a.getId(),a.getProduto().getCodBarras(),a.getProduto().getNome(),a.getQuantidade(),a.getFornecedor().getNome(),
								a.getDescricao(),a.getDataEnvio()});
					}
			}
		});
		btnVisualizarPedidos.setBounds(32, 32, 139, 50);
		getContentPane().add(btnVisualizarPedidos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 32, 1053, 565);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnVisualizarProdutos = new JButton("Produtos");
		btnVisualizarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTituloPainel.setText("Lista de Produtos Cadastrados em Estoque");
				table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"C\u00F3digo", "Nome", "fabricante", "descri\u00E7\u00E3o", "Pre\u00E7o", "Qtd Dispon\u00EDvel"
						}
					));
					table.getColumnModel().getColumn(0).setPreferredWidth(94);
					table.getColumnModel().getColumn(1).setPreferredWidth(132);
					table.getColumnModel().getColumn(2).setPreferredWidth(86);
					table.getColumnModel().getColumn(3).setPreferredWidth(177);
					model = (DefaultTableModel) table.getModel();
					for(classes.ProdutoEstoque pe: estoque.getCatalogo()){
						model.addRow(new Object[] {pe.getProduto().getCodBarras(),pe.getProduto().getNome(),pe.getProduto().getFabricante(),
								pe.getProduto().getDescricao(),pe.getProduto().getPreco(),pe.getQuantidade()});
					}
			}
		});
		btnVisualizarProdutos.setBounds(32, 93, 139, 50);
		getContentPane().add(btnVisualizarProdutos);
		
		JButton btnVisualizarVendas = new JButton("Vendas");
		btnVisualizarVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTituloPainel.setText("Lista de Vendas");
				table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Id da Venda", "Codigo Barras", "Nome do Produto", "Quantidade", "Pre\u00E7o total"
						}
					));
					table.getColumnModel().getColumn(0).setPreferredWidth(80);
					table.getColumnModel().getColumn(1).setPreferredWidth(109);
					table.getColumnModel().getColumn(2).setPreferredWidth(127);
					table.getColumnModel().getColumn(3).setPreferredWidth(82);
					table.getColumnModel().getColumn(4).setPreferredWidth(82);
					model = (DefaultTableModel) table.getModel();
					for(classes.Venda v: (new classesDAO.VendaDAO()).select(estoque)){
						model.addRow(new Object[] {v.getId(), v.getProduto().getProduto().getCodBarras(), v.getProduto().getProduto().getNome(),
								v.getUnidades(), (v.getUnidades()*v.getProduto().getProduto().getPreco())});
					}
			}
		});
		btnVisualizarVendas.setBounds(32, 154, 139, 50);
		getContentPane().add(btnVisualizarVendas);
		
		JButton btnHistricoDePedidos = new JButton("Hist\u00F3rico Pedidos");
		btnHistricoDePedidos.setToolTipText("Listar pedidos que j\u00E1 forem Recebidos");
		btnHistricoDePedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTituloPainel.setText("Histórico de Pedidos já recebidos");
				table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Id", "Codigo Barras", "Produto", "Qtd", "fornecedor", "descri\u00E7\u00E3o", "Data de Envio", "Data de Recibo", "qtd Recebida", "Descri\u00E7\u00E3o de Recebimento"
						}
					));
					table.getColumnModel().getColumn(0).setPreferredWidth(42);
					table.getColumnModel().getColumn(1).setPreferredWidth(91);
					table.getColumnModel().getColumn(6).setPreferredWidth(88);
					table.getColumnModel().getColumn(7).setPreferredWidth(98);
					table.getColumnModel().getColumn(9).setPreferredWidth(158);
					model = (DefaultTableModel) table.getModel();
					for(classes.PedidoRecebido pr: (new classesDAO.PedidoRecebidoDAO()).select() ){
						model.addRow(new Object[] {pr.getPedido().getId(), pr.getPedido().getProduto().getCodBarras(), pr.getPedido().getProduto().getNome(), pr.getPedido().getQuantidade(),
								pr.getPedido().getFornecedor().getNome(), pr.getPedido().getDescricao(), pr.getPedido().getDataEnvio(),
								pr.getDataRecibo(), pr.getQtdRecebida(), pr.getDescricaoRecebimento()});
					}
			}
		});
		btnHistricoDePedidos.setBounds(32, 214, 139, 50);
		getContentPane().add(btnHistricoDePedidos);
		
		lblTituloPainel = new JLabel("Escolha uma op\u00E7\u00E3o para Visualizar no painel");
		lblTituloPainel.setBounds(225, 11, 377, 14);
		getContentPane().add(lblTituloPainel);
		
		JLabel lblFunes = new JLabel("Fun\u00E7\u00F5es");
		lblFunes.setBounds(32, 285, 100, 14);
		getContentPane().add(lblFunes);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Novo");
		menuBar.add(mnCadastrar);
		
		JMenuItem mntmFornecedores = new JMenuItem("Fornecedor");
		mntmFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				interfaceGrafica.Cadastra_Fornecedor cFornecedor = new Cadastra_Fornecedor();
				cFornecedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				cFornecedor.setSize(470,300);
				cFornecedor.setVisible(true);
				cFornecedor.setResizable(false);
			}
		});
		mnCadastrar.add(mntmFornecedores);
		
		JMenuItem mntmProdutos = new JMenuItem("Produto");
		mntmProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(new classesDAO.FornecedorDAO()).select().isEmpty()){
					interfaceGrafica.Cadastra_Produto cProduto = new Cadastra_Produto(estoque);
					cProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					cProduto.setSize(400,310);
					cProduto.setResizable(false);
					cProduto.setVisible(true);
				} else{
					JOptionPane.showMessageDialog(null, "Cadastre Fornecedores para Cadastrar Produto", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnCadastrar.add(mntmProdutos);
		
		JMenuItem mntmVenda = new JMenuItem("Venda");
		mntmVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				interfaceGrafica.Realizar_Venda rVenda = new Realizar_Venda(estoque);
				rVenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				rVenda.setSize(450,200);
				rVenda.setResizable(false);
				rVenda.setVisible(true);
			}
		});
		mnCadastrar.add(mntmVenda);
		
		JMenu mnFerramentas = new JMenu("Ferramentas");
		menuBar.add(mnFerramentas);
		
		JMenuItem mntmAjustarAreaDe = new JMenuItem("Ajustar Area de Reposi\u00E7\u00E3o");
		mntmAjustarAreaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mudança = JOptionPane.showInputDialog(null, "Insira nova porcentagem que ativa a reposição de produtos", estoque.getPorcentMinima()*100);
				double min = Double.parseDouble(mudança);
				estoque.setPorcentMinima(min);
				(new classesDAO.AjustesDAO()).update(min);
			}
		});
		mnFerramentas.add(mntmAjustarAreaDe);
	}
}
