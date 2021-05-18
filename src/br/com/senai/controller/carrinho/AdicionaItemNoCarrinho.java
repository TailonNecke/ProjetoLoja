package br.com.senai.controller.carrinho;

import java.util.Scanner;

import br.com.dao.DataBaseConnection;
import br.com.senai.controller.Controller;
import br.com.senai.controller.produto.ListaProduto;
import br.com.senai.model.CarrinhoModel;
import br.com.senai.model.ProdutoModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class AdicionaItemNoCarrinho {
	Scanner entrada = new Scanner(System.in);
	Controller produtoController = new Controller();
	ListaProduto listaProduto = new ListaProduto();
	private Connection connection;
	
	public AdicionaItemNoCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public CarrinhoModel cadastrarItemNoCarrinho(int idCliente){
		PreparedStatement preparedStatement;
		ProdutoModel produto = new ProdutoModel();
		ListaProduto listaProduto = new ListaProduto();
		int idDoProduto, indexDoCampo;
		
		if(listaProduto.listarProdutos() == null) {
			return null;
		}
		System.out.println("--- ADICIONAR ITEM AO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		
		idDoProduto = entrada.nextInt();
		try {
			String sql = "SELECT * FROM produtos WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Este produto não existe.");
				return null;
				
			} else {
				produto.setNomeDoProduto(resultSet.getString("nomeDoProduto"));
				produto.setPrecoDoProduto(resultSet.getDouble("precoDoProduto"));
				produto.setQuantidadeDeProduto(resultSet.getInt("quantidadeDeProduto"));
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.print("Informe a quantidade de itens que deseja adicionar");
		int qtd = entrada.nextInt();
		if(qtd > produto.getQuantidadeDeProduto()) {
			System.out.println("Não possui itens suficientes no estoque");
			return null;
		}
		
		try {
			String sql = "INSERT INTO itens_carrinho (codigoDoProduto, nomeDoProduto, precoDoProduto, quantidadeDeProduto, saldoEmEstoque, Cod_Cliente)"
					+ " VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			
			preparedStatement1.setInt(1, idDoProduto);
			preparedStatement1.setString(2, produto.getNomeDoProduto());
			preparedStatement1.setDouble(3, produto.getPrecoDoProduto());
			preparedStatement1.setInt(4, qtd);
			preparedStatement1.setDouble(5, qtd * produto.getPrecoDoProduto());
			preparedStatement1.setDouble(6, idCliente);
			
			preparedStatement1.execute();
			
			String sql2 = "UPDATE produtos SET quantidadeDeProduto = ?, saldoEmEstoque = ?"
					+ " WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql2);
			
			preparedStatement.setInt(1, produto.getQuantidadeDeProduto() - qtd);
			preparedStatement.setDouble(2, produto.getPrecoDoProduto()*(produto.getQuantidadeDeProduto() - qtd));
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println("erro ao transferir itens ao carrinho.");
		}
		return null;
	}
}
