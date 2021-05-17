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
	public ResultSet listarItensNoCarrinho() {
		PreparedStatement preparedStatement;
		try {
			String sql = "select * from produto";
			preparedStatement = connection.prepareStatement("select * from itens_carrinho");
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			
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
			return null;
		}
	}
	public ResultSet gerarCupom(int id) {
		PreparedStatement preparedStatement = null;
		try {
			String sql = "select * from itens_carrinho where Cod_Cliente = ?";
			preparedStatement.setInt(1, id);
			preparedStatement = connection.prepareStatement("select * from itens_carrinho");
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			
			if(!resultSet.next()) {
				System.out.println("Não possui itens no carrinho");
				return null;
			}
			String sql2 = "select nome from cliente where Cod_Cliente = ?";
			preparedStatement.setInt(1, id);
			preparedStatement = connection.prepareStatement("select * from itens_carrinho");
			ResultSet resultSet2 = preparedStatement.executeQuery(sql2);
			System.out.println("Cliente: " + resultSet2.getString("nome"));
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
			}
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}