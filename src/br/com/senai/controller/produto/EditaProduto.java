package br.com.senai.controller.produto;

import java.util.Scanner;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;
import br.com.senai.model.ProdutoModel;

public class EditaProduto {
	
	private Scanner entrada = new Scanner(System.in);
	private Connection connection;
	
	public EditaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	
	public ProdutoModel editarProduto() {
		PreparedStatement preparedStatement;
		
		ProdutoModel produto = new ProdutoModel();
		ListaProduto listaProduto = new ListaProduto();
		int idDoProduto, indexDoCampo;
		
		if(listaProduto.listarProdutos() == null) {
			return null;
		}
		
		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
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
		
		System.out.println("--- CAMPOS --");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = entrada.nextInt();
		
		switch (indexDoCampo) {
		case 1:
			editarNomeDoProduto(produto, idDoProduto);
			break;
		case 2:
			EditarPrecoDoProduto(produto, idDoProduto);
			break;
		case 3:
			EditarQuantidadeDeProduto(produto, idDoProduto);
			break;
		default:
			System.out.println("Opção inválida!!!");
			break;
		}
		
		return produto;
	}

	private ProdutoModel EditarQuantidadeDeProduto( ProdutoModel produto, int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe a quantidade do produto: ");
		produto.setQuantidadeDeProduto(entrada.nextInt());

		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto. getQuantidadeDeProduto());
		
		try {
			String sql = "UPDATE produtos SET quantidadeDeProduto = ?, saldoEmEstoque = ?"
					+ " WHERE codigoDoProduto = ?";
preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, produto.getQuantidadeDeProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return produto;
	}

	private ProdutoModel EditarPrecoDoProduto( ProdutoModel produto, int idDoProduto) {
		PreparedStatement preparedStatement;
		
		
		System.out.print("Informe o novo preço para o produto: ");
		produto.setPrecoDoProduto(entrada.nextDouble());
		
		produto.setSaldoEmEstoque(produto.getPrecoDoProduto() * produto. getQuantidadeDeProduto());
		
		try {
			String sql = "UPDATE produtos SET precoDoProduto = ?, saldoEmEstoque = ?"
					+ " WHERE codigoDoProduto = ?";
preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDouble(1, produto.getPrecoDoProduto());
			preparedStatement.setDouble(2, produto.getSaldoEmEstoque());
			preparedStatement.setInt(3, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return produto;
	}

	private ProdutoModel editarNomeDoProduto( ProdutoModel produto, int idDoProduto) {
		PreparedStatement preparedStatement;
		
		System.out.print("Informe o novo nome para o produto: ");
		produto.setNomeDoProduto(entrada.next());
		
		try {
			String sql = "UPDATE produtos SET nomeDoProduto = ? WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, produto.getNomeDoProduto());
			preparedStatement.setInt(2, idDoProduto);
			
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
		return produto;
	}
}
