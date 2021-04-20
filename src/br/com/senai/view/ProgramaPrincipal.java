package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;
import br.com.senai.model.ProdutoModel;
import br.com.senai.controller.ProdutoController;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		ProdutoController produtoController = new ProdutoController();

		boolean sair = false;
		
		do {
			ProdutoController.menu();
			int opcao = produtoController.opcao();

			switch (opcao) {
			case 1:
				produtos.add(produtoController.cadastrarProduto());
				break;
			case 2:
				produtoController.ListarProdutos(produtos);
				break;
			case 3:
				produtoController.editarProduto(produtos);
				break;
			case 9:
				sair = true;
				break;
			default:
				System.out.println("opção invalida tente novamente");
			}
		} while (!sair);
	}

}
