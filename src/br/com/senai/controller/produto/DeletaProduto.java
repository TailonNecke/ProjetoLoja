package br.com.senai.controller.produto;

import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import br.com.dao.DataBaseConnection;

public class DeletaProduto {
	private Connection connection;

	public DeletaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
public boolean verificaSeExisteProduto(int idDoProduto) {
	PreparedStatement preparedStatement;
	try {
		String sql = "SELECT * FROM produtos WHERE codigoDoProduto = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, idDoProduto);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(!resultSet.next()) {
			System.out.println("Este produto não existe.");
			return false;
		} else {
			return true;
		}
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	
}
	Scanner entrada = new Scanner(System.in);
	PreparedStatement preparedStatement;
	ListaProduto listaProduto = new ListaProduto();
	
	public void removerProduto() {
		System.out.println("--- REMOVER PRODUTO ---");

		if(listaProduto.listarProdutos() == null) {
			return;
		}
		
		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();
		
		try {
			
			if (!verificaSeExisteProduto(idDoProduto)) {
				return;
			}
			
			String sql = "DELETE FROM produtos WHERE codigoDoProduto = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idDoProduto);
			preparedStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Não foi possivel excluir esta Informação");
			return;
		}
		
	}
}
