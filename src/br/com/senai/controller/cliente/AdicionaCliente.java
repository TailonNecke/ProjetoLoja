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
		PreparedStatement preparedStatement1;
		
		System.out.print("Informe o nome do cliente: ");
		String nome = entrada.next();
		String sql = "SELECT * FROM cliente WHERE nome = ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nome);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(!resultSet.next()) {
			try {
				sql = "INSERT INTO cliente (nome)"
						+ " VALUES(?)";
				preparedStatement1 = connection.prepareStatement(sql);
				
				preparedStatement1.setString(1, nome);
				
				preparedStatement1.execute();
				return resultSet.getInt("cod_cliente");
			} catch (Exception e) {
				return 0;
			}
		
		} else {
			System.out.println("usuario existente");
			System.out.println(resultSet.getInt("cod_cliente"));
			return resultSet.getInt("cod_cliente");
			
		}
	}
}
