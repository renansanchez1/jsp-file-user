package br.com.ifms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ifms.dao.util.Conexao;
import br.com.ifms.modelo.Papel;
import br.com.ifms.modelo.Usuario;

public class UsuarioDAO {
	
	private Connection connection;
	
	private void conectar() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = Conexao.getConexao();
		}
	}

	private void desconectar() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public Usuario inserirUsuario(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuario (nome, cpf, data_nascimento, email, password, login, ativo)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";		    
		
		conectar();

		PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getCpf());
		Date nascimento = new Date(usuario.getDataNascimento().getTime());
		statement.setDate(3, nascimento);
		statement.setString(4, usuario.getEmail());
		statement.setString(5, usuario.getPassword());
		statement.setString(6, usuario.getLogin());
		statement.setBoolean(7, usuario.isAtivo());
		
		statement.executeUpdate();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		long id = 0;
		if(resultSet.next())
			id = resultSet.getInt("id");
		statement.close();

		desconectar();
		
		usuario.setId(id);
		return usuario;
	}
	
	public List<Usuario> listarTodosUsuarios() throws SQLException {
		PapelDAO papelDao = new PapelDAO(); // aula 13
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM usuario";

		conectar();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			long id = resultSet.getLong("id");
			String nome = resultSet.getString("nome");
			String cpf = resultSet.getString("cpf");
			Date nascimento = new Date(resultSet.getDate("data_nascimento").getTime());
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String login = resultSet.getString("login");
			boolean ativo = resultSet.getBoolean("ativo");

			Usuario usuario = new Usuario(nome, cpf, nascimento, email, password, login, ativo);
			usuario.setId(id);
			
			
			List<Papel> papeis = papelDao.buscarPapelUsuario(usuario);
			usuario.setPapeis(papeis);			
			
			
			listaUsuarios.add(usuario);
		}
		resultSet.close();
		statement.close();

		desconectar();

		return listaUsuarios;
	}
	
	public boolean apagarUsuario(Usuario usuario) throws SQLException {
        String sql = "DELETE FROM usuario where id = ?";
        
        conectar();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, usuario.getId());
         
        boolean linhaApagada = statement.executeUpdate() > 0;
        statement.close();
        
        desconectar();
        
        return linhaApagada;     
   }
	
	
	public Usuario buscarUsuarioPorId(long id) throws SQLException {
		PapelDAO papelDAO = new PapelDAO();
		Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";
         
        conectar();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	
        	String nome = resultSet.getString("nome");
			String cpf = resultSet.getString("cpf");
			Date nascimento = new Date(resultSet.getDate("data_nascimento").getTime());
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			String login = resultSet.getString("login");
			boolean ativo = resultSet.getBoolean("ativo");

			usuario = new Usuario(nome, cpf, nascimento, email, password, login, ativo);
			usuario.setId(id);			
			List<Papel> papeisUsuario = papelDAO.buscarPapelUsuario(usuario);
			usuario.setPapeis(papeisUsuario);
		}
         
        resultSet.close();
        statement.close();
        
        desconectar();
        
        return usuario;
    }
	
	
	public boolean editarUsuario(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuario SET nome = ?, cpf = ?, data_nascimento = ?, email = ?, password = ?, login = ?, ativo = ?"
				+ " WHERE id = ?";		    
		
		conectar();
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getCpf());
		statement.setDate(3, new java.sql.Date(usuario.getDataNascimento().getTime()));
		statement.setString(4, usuario.getEmail());
		statement.setString(5, usuario.getPassword());
		statement.setString(6, usuario.getLogin());
		statement.setBoolean(7, usuario.isAtivo());
		statement.setLong(8, usuario.getId());

		boolean linhaAlterada  = statement.executeUpdate() > 0;
		statement.close();

		desconectar();
		return linhaAlterada;
	}
	
	// aula 16
	public Usuario buscarUsuarioPorLogin(String login) throws SQLException {
		PapelDAO papelDAO = new PapelDAO();
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE login = ?";
         
        conectar();
         
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	long id = resultSet.getLong("id");
        	String nome = resultSet.getString("nome");
			String cpf = resultSet.getString("cpf");
			Date nascimento = new Date(resultSet.getDate("data_nascimento").getTime());
			String email = resultSet.getString("email");
			String password = resultSet.getString("password");
			boolean ativo = resultSet.getBoolean("ativo");

			usuario = new Usuario(nome, cpf, nascimento, email, password, login, ativo);
			usuario.setId(id);			
			List<Papel> papeisUsuario = papelDAO.buscarPapelUsuario(usuario);
			usuario.setPapeis(papeisUsuario);
		}
         
        resultSet.close();
        statement.close();
        
        desconectar();
        
        return usuario; 
    }

}

