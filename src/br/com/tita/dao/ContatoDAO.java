package br.com.tita.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tita.jdbc.ConnectionFactory;
import br.com.tita.modelo.Contato;

public class ContatoDAO {
	
	private Connection connection;
	
	public ContatoDAO() {
		this.connection = new ConnectionFactory().getConnection();
		
	}
	
	
	public void adiciona(Contato contato) {
	    String sql = "insert into contatos " +
	            "(nome, telefone)" +
	            " values (?,?)";

	    try {
	        // prepared statement para inserção
	        PreparedStatement stmt = connection.prepareStatement(sql);

	        // seta os valores
	        stmt.setString(1, contato.getNome());
	        stmt.setString(2, contato.getTelefone());
	        
	        
	        // executa
	        stmt.execute();
	        stmt.close();
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public List<Contato> getLista() {
	     try {
	         List<Contato> contatos = new ArrayList<Contato>();
	         PreparedStatement stmt = this.connection.
	                 prepareStatement("select * from contatos");
	         ResultSet rs = stmt.executeQuery();
	 
	         while (rs.next()) {
	             // criando o objeto Contato
	             Contato contato = new Contato();
	             contato.setId(rs.getInt("id"));
	             contato.setNome(rs.getString("nome"));
	             contato.setTelefone(rs.getString("telefone"));
	 
	             // adicionando o objeto à lista
	             contatos.add(contato);
	         }
	         rs.close();
	         stmt.close();
	         return contatos;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	public void altera(Contato contato) {
	     String sql = "update contatos set nome=?, telefone=?" +
	             " where id=?";
	     try {
	         PreparedStatement stmt = connection.prepareStatement(sql);
	         stmt.setString(1, contato.getNome());
	         stmt.setString(2, contato.getTelefone());
	         stmt.setLong(3, contato.getId());
	         
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	public void remove(Contato contato) {
	     try {
	         PreparedStatement stmt = connection
	                 .prepareStatement("delete from contatos where id=?");
	         stmt.setLong(1, contato.getId());
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	

}
