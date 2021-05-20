package br.com.senai.controller.carrinho;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;
public class ListaCarrinho {
	Connection connection;
	public ListaCarrinho() {
		connection = DataBaseConnection.getInstance().getConnection();
		}
	public ResultSet listarItensNoCarrinho(int id) {
		PreparedStatement preparedStatement;
		try {
			System.out.println(id);
			String sql = "select * from itens_carrinho where Cod_Cliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui itens no carrinho");
				return null;
			}
			
			System.out.println("\n----- PRODUTOS NO CARRINHO -----\n");
			System.out.printf("| %2s | %30s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
						
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %30s | R$%6.2f | %4s | %9s |\n",
						resultSet.getInt("codigoDoProduto"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"),
						resultSet.getDouble("saldoEmEstoque"));
			}
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet gerarCupom(int id) {
		PreparedStatement preparedStatement;
		try {
			System.out.println(id);
			String sql = "select nome from clientes where cod_cliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui itens no carrinho");
				return null;
			}
			
			System.out.println("Cliente: " + resultSet.getString("nome"));
			
			sql = "select * from itens_carrinho where Cod_Cliente = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) {
				System.out.println("Não possui itens no carrinho");
				return null;
			}
			
			System.out.println("\n----- CUPOM -----\n");
			System.out.printf("| %2s | %30s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
						
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %30s | R$%6.2f | %4s | %9s |\n",
						resultSet.getInt("codigoDoProduto"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"),
						resultSet.getDouble("saldoEmEstoque"));
				 sql = "DELETE FROM itens_carrinho WHERE Cod_Cliente = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
			}
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("aaaaa");
			return null;
		}
	}
}