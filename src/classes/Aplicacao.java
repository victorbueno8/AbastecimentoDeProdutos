package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import interfaceGrafica.Menu_Principal;

public class Aplicacao {

	public static void main(String[] args) {
		
		ArrayList<ProdutoEstoque> listaProdutos = (new classesDAO.ProdutoEstoqueDAO()).select();
		ArrayList<Pedido> listaPedidos = (new classesDAO.PedidoDAO().select());
		Estoque estoque = null;	
		if((new classesDAO.AjustesDAO()).select() == 0) {
			String mudança = JOptionPane.showInputDialog(null, "Insira porcentagem que ativa a reposição de produtos");
			double min = Double.parseDouble(mudança);
			estoque = new Estoque(listaProdutos, listaPedidos, min);
			(new classesDAO.AjustesDAO()).insert(min);
		} else {
			estoque = new Estoque(listaProdutos, listaPedidos, (new classesDAO.AjustesDAO()).select());
		}
		
			//criação de um estoque que possui uma taxa limítrofe padrão minima de 25%

		interfaceGrafica.Menu_Principal menu = new Menu_Principal(estoque);
		menu.setVisible(true);

		
		
/*
		int operacao;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("----------------------------Abastecimento De Produtos--------------------------------");
			System.out.println("1 - Cadastrar Fornecedor");
			System.out.println("2 - Cadastrar produtos");
			System.out.println("3 - Realizar Vendas");
			System.out.println("4 - Verificar Estoque");
			System.out.println("5 - Lista de pedidos");
			System.out.println("6 - Confirmar recebimento de pedidos");
			System.out.println("10 - Sair");
			System.out.println("-------------------------------------------------------------------------------------");
			
			
			operacao = input.nextInt();
			input.nextLine();
			
			
			switch (operacao) {
			
			case 1:
				System.out.println("            Realizar Cadastro de Fornecedor       ");
				System.out.println("Nome: ");
				String nomefabricante = input.nextLine();
				System.out.println("Localização da Fábrica: ");
				String local = input.nextLine();

				System.out.println("Fornecedores Cadastrados:");
				for(Fornecedor a : fornecedores) {
					System.out.println(a.getNome() + " - " + a.getFabricacaoLocalidade());
				}
				break;
				
			case 2:
				System.out.println("            Realizar Cadastro de produto       ");
				System.out.println("Código de barras: ");
				String codBarras = input.nextLine();
				System.out.println("Nome: ");
				String nome = input.nextLine();
				System.out.println("Descrição: ");
				String descricao = input.nextLine();
				System.out.println("Preço: ");
				double preco = input.nextDouble();
				System.out.println("Quantidade inicial(faixa normal de produtos):");
				int quantidadeini = input.nextInt();
				input.nextLine();
				System.out.println("Fabricante: ");
				String fabricante = input.nextLine();
				classesDAO.FornecedorDAO fs = new classesDAO.FornecedorDAO();
				Fornecedor a = fs.selectFornecedor(fabricante);
				
				estoque.addProduto(new Produto(codBarras, nome, a, descricao, preco),quantidadeini);
				
//				
//				System.out.println("Produtos Cadastrados:");
//				System.out.println(estoque);
				
				break;
			
			
				
			case 3:
				System.out.println("            Realizar Venda de produto        \nEscolha um produto do estoque:");
				System.out.println(estoque);
				
				System.out.println("ID venda: ");
				int id = input.nextInt();
				input.nextLine();
				System.out.println("Código de Barras do Produto: ");
				String codP = input.nextLine();	
				System.out.println("Quantidade: ");
				int un = input.nextInt();
				
				classesDAO.ProdutoEstoqueDAO prDAO = new classesDAO.ProdutoEstoqueDAO();
				Venda v = new Venda(id, prDAO.selectProdutoEstoque(codP), estoque, un);
				System.out.println(v);
				
				if(v.executaVenda()) {
					System.out.println("Venda efetuada!");
				} else {
					System.out.println("Venda Cancelada");
				}
				
				break;
				
			case 4:
				System.out.println("                    Estoque                ");
				System.out.println(estoque);
				break;
				
			case 5:
				System.out.println("               Visualizar pedidos               ");
				for(Pedido pd : listaPedidos) {
					System.out.println(pd);
				}
				break;
			
			case 6:
				System.out.println("               Confirmar recebimento de pedidos               ");
				for(Pedido pd : listaPedidos) {
					System.out.println(pd);
				}
				System.out.printf("Id do pedido: ");
				int idPedido = input.nextInt();
				for(Pedido pd : listaPedidos) {
					if(idPedido == pd.getId()) {
						pd.confirmaRecebimento(estoque);
					}
				}
				break;
				
			}
			input.nextLine();
		} while(operacao != 10);
		
		
		
		
		input.close();
		*/
	}

}
