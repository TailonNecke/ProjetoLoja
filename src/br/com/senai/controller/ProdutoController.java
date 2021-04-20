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
	
	public void consultarProdutos(List<ProdutoModel> produtos) {
		System.out.println("--- PRODUTOS CADASTRADOS ---");
		System.out.printf("| %10s | %8s | %4s | %9s |\n", "produto", "preço", "Quantidade", "R$ Total");
		for(ProdutoModel produtoModel : produtos) {
			System.out.println(produtoModel);
		}
		
	}
}
