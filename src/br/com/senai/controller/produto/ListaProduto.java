package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;

import br.com.dao.DataBaseConnection;

public class ListaProduto {
	
	private Connection connection;
	
	public ListaProduto() {
	connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarProdutos() {
		PreparedStatement preparedStatement;
		try {
			String sql = "select * from produtos";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery(sql);
			
			if(!resultSet.next()) {
				System.out.println("Não possui dados cadastrados");
				return null;
			}
			
			System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
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
