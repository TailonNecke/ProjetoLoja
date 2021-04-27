package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoCarrinho;
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
		System.out.print("Preço: ");
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
	
	public List<ProdutoModel> ListarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "produto", "preço", "Qtd", "R$ Total");

		// opção 1
		//		for(ProdutoModel produtoModel : produtos) {
//			System.out.printf("| %10s | %8s | %4s | %9s |\n", 
//					produtoModel.getNomeDoProduto(),
//					produtoModel.getPrecoDoProduto(),
//					produtoModel.getQuantidadeDeProduto(),
//					produtoModel.getSaldoEmEstoque());
//		}
		
		// opção 2
//		for(int i = 0;i < produtos.size(); i++) {
//		System.out.printf("| %10s | %8s | %4s | %9s |\n",
//				produtos.get(i).getNomeDoProduto(),
//				produtos.get(i).getPrecoDoProduto(),
//				produtos.get(i).getQuantidadeDeProduto(),
//				produtos.get(i).getSaldoEmEstoque());
//	}
		
		// opção 3
//		produtos.forEach(produto -> {
//			
//			System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n",
//					
//					produto.getNomeDoProduto(),
//					produto.getPrecoDoProduto(),
//					produto.getQuantidadeDeProduto(),
//					produto.getSaldoEmEstoque());
//
//		});
		for (int i = 0;i < produtos.size(); i++) {
					System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n",
					i + 1,
					produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(),
					produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}
		return produtos;
	}
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		if (produtos.size() <= 0) {
			System.out.println("Não possui nenhum produto para ser editado");
			return null;
		}
		
		ListarProdutos(produtos);
		
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		System.out.println("--- EDITAT DADOS DO PRODUTO ---");
		System.out.print("Infomorme o ID do produto: ");
		idDoProduto = sc.nextInt() - 1;
		if (idDoProduto > produtos.size()) {
			System.out.println("Este produto não existe");
			return null;
		}
		System.out.println(" --- Campos ---");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço do produto");
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
			System.out.print("Informe o preço do produto: ");
			
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(sc.nextDouble());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			
			produtos.set(idDoProduto, produto);
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());
			break;
		case 3:
			System.out.print("Informe a quantidade do produto: ");
			
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDeProduto(sc.nextInt());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
			produtos.set(idDoProduto, produto);
			break;
		default:
				System.out.println("Opção invalida!!");
			break;
		}
		return produto;
	}
	
	
	
	public ProdutoCarrinho addCarrinho() {
		ProdutoCarrinho addCarrinho = new ProdutoCarrinho();
		ProdutoModel produtos = ProdutoModel();
		System.out.println("--- ADICIONAR ITEM ---");
		System.out.print("Id Do produto Produto: ");
		int idDoProduto = sc.nextInt();
		if (idDoProduto > produtos.size()) {
			System.out.println("Este produto não existe");
			return null;
		}
		addCarrinho.setNomeDoProduto(sc.nextLine());
		System.out.print("Preço: ");
		addCarrinho.setPrecoDoProduto(sc.nextDouble());
		System.out.println("Quantidade: ");
		addCarrinho.setQuantidadeDeProduto(sc.nextInt());
		addCarrinho.setSaldoEmEstoque(addCarrinho.getQuantidadeDeProduto() * addCarrinho.getPrecoDoProduto());
		return addCarrinho;
	}
	
	public List<ProdutoCarrinho> ListarCarrinho(List<ProdutoCarrinho> carrinho) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "produto", "preço", "Qtd", "R$ Total");

		for (int i = 0;i < carrinho.size(); i++) {
					System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n",
					i + 1,
					carrinho.get(i).getNomeDoProduto(),
					carrinho.get(i).getPrecoDoProduto(),
					carrinho.get(i).getQuantidadeDeProduto(),
					carrinho.get(i).getSaldoEmEstoque());
		}
		return carrinho;
	}
	public void removerProduto(List<ProdutoModel> produtos) {
		ListarProdutos(produtos);
		if (produtos.size() <= 0) {
			System.out.println("Não possui nenhum produto para ser removido");
			return;
		}
		System.out.println("-- REMOVER PRODUTO ---");
		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = sc.nextInt();
		if (idDoProduto > produtos.size()) {
			System.out.println("Este ID não foi cadastrado");
			return;
		}
		produtos.remove(idDoProduto - 1);
	}
}
