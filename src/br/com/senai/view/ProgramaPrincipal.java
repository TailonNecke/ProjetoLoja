package br.com.senai.view;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.controller.Controller;
import br.com.senai.controller.carrinho.AdicionaItemNoCarrinho;
import br.com.senai.controller.carrinho.ListaCarrinho;
import br.com.senai.controller.cliente.AdicionaCliente;
import br.com.senai.controller.produto.CadastraProduto;
import br.com.senai.controller.produto.DeletaProduto;
import br.com.senai.controller.produto.EditaProduto;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;

public class ProgramaPrincipal {
	public static void main(String[] args) throws Exception {
		List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
		List<CarrinhoModel> itensNoCarrinho = new ArrayList<CarrinhoModel>();
		
		Controller produtoController = new Controller();
		ListaCarrinho listaCarrinho = new ListaCarrinho();
		AdicionaItemNoCarrinho adiciornarItemNoCarrinho = new AdicionaItemNoCarrinho();
		CadastraProduto cadastraProduto = new CadastraProduto();
		ListaProduto listaProduto = new ListaProduto();
		EditaProduto editaProduto = new EditaProduto();
		DeletaProduto deletaProduto = new DeletaProduto();
		AdicionaCliente adicionaCliente = new AdicionaCliente();
		
		boolean sair = false;

		int id = adicionaCliente.definirCliente();
		
		do {
			produtoController.menu();
			int opc = produtoController.opcao();

			switch (opc) {
			case 1:
				cadastraProduto.cadastrarProduto();
				break;
			case 2:
				listaProduto.listarProdutos();
				break;
			case 3:
				editaProduto.editarProduto();
				break;
			case 4:
				deletaProduto.removerProduto();
				break;
			case 5:
				itensNoCarrinho.add(adiciornarItemNoCarrinho.cadastrarItemNoCarrinho(id));
				break;
			case 6:
				listaCarrinho.listarItensNoCarrinho(id);
				break;
			case 7:
				listaCarrinho.gerarCupom(id);
				break;
			case 9:
				sair = true;
				break;

			default:
				System.out.println("Op??o inv?lida!!!");
				break;
			}
		} while (!sair);

		System.out.println("Sistema encerrado!!!");
	}
}
