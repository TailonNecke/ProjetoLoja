package br.com.senai.controller.cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import br.com.dao.DataBaseConnection;

import java.sql.Connection;

public class AdicionaCliente {
	Scanner entrada = new Scanner(System.in);
private Connection connection;
	
	public AdicionaCliente() {
		connection = DataBaseConnection.getInstance().getConnection();
	}
	public int definirCliente() throws Exception {
		PreparedStatement preparedStatement;
		
		
		System.out.print("Informe o nome do cliente: ");
		String nome = entrada.next();
		String sql = "SELECT * FROM clientes WHERE nome = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nome);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(!resultSet.next()) {
			try {
				sql = "INSERT INTO clientes (nome)"
						+ " VALUES(?)";
				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setString(1, nome);
				
				preparedStatement.execute();
				sql = "SELECT cod_cliente FROM clientes WHERE nome = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, nome);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				return resultSet.getInt("cod_cliente");
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		
		} else {
			System.out.println("usuario existente");
			return resultSet.getInt("cod_cliente");
			
		}
		
	}
}
