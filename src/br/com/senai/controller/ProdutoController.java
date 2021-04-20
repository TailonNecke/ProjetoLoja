package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class ProdutoController {

	private Scanner sc;

	public ProdutoController() {
		sc = new Scanner(System.in);
	}

	public static void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens\n" + "2) Listar estoque\n" + "3) Editar item\n" + "4) Remover item\n"
				+ "5) realizar vendas\n" + "9) sair do sistema");
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();
		System.out.println("--- CADASTRAR ITENS ---");
		System.out.print("Produto: ");
		sc.nextLine();
		produtoModel.setNomeDoProduto(sc.nextLine());
		System.out.print("Pre�o: ");
		produtoModel.setPrecoDoProduto(sc.nextDouble());
		System.out.println("Quantidade: ");
		produtoModel.setQuantidadeDeProduto(sc.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
		return produtoModel;
	}

	public int opcao() {
		System.out.println("> ");
		return sc.nextInt();
	}
	
	public void ListarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "produto", "pre�o", "Qtd", "R$ Total");

		// op��o 1
		//		for(ProdutoModel produtoModel : produtos) {
//			System.out.printf("| %10s | %8s | %4s | %9s |\n", 
//					produtoModel.getNomeDoProduto(),
//					produtoModel.getPrecoDoProduto(),
//					produtoModel.getQuantidadeDeProduto(),
//					produtoModel.getSaldoEmEstoque());
//		}
		
		// op��o 2
//		for(int i = 0;i < produtos.size(); i++) {
//		System.out.printf("| %10s | %8s | %4s | %9s |\n",
//				produtos.get(i).getNomeDoProduto(),
//				produtos.get(i).getPrecoDoProduto(),
//				produtos.get(i).getQuantidadeDeProduto(),
//				produtos.get(i).getSaldoEmEstoque());
//	}
		
		// op��o 3
		
		produtos.forEach(produto -> {
			System.out.printf("| %10s | %8s | %4s | %9s |\n",
					produto.getNomeDoProduto(),
					produto.getPrecoDoProduto(),
					produto.getQuantidadeDeProduto(),
					produto.getSaldoEmEstoque());
		});
	}
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		System.out.println("--- EDITAT DADOS DO PRODUTO ---");
		System.out.print("Infomorme o ID do produto: ");
		idDoProduto = sc.nextInt();
		
		System.out.println(" --- Campos ---");
		System.out.println("1) Nome do produto");
		System.out.println("2) Pre�o do produto");
		System.out.println("3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = sc.nextInt();
		
		switch(indexDoCampo) {
		case 1: 
			System.out.print("Informe o novo nome do produto: ");
			
			produto.setNomeDoProduto(sc.next());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			
			produtos.set(idDoProduto, produto);
			break;
		case 2: 
			System.out.print("Informe o pre�o do produto: ");
			
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(sc.nextDouble());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			
			produtos.set(idDoProduto, produto);
			break;
		case 3:
			System.out.print("Informe a quantidade do produto: ");
			
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDeProduto(sc.nextInt());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			
			produtos.set(idDoProduto, produto);
			break;
		default:
				
			break;
		}
		return null;
	}
}
