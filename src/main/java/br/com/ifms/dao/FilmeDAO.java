package br.com.ifms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ifms.dao.util.Conexao;
import br.com.ifms.modelo.Filme;

public class FilmeDAO {

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

    public Filme inserirFilme(Filme filme) throws SQLException {
        String sql = "INSERT INTO filme (nome, classificacao) VALUES (?, ?)";

        conectar();

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, filme.getNome());
        statement.setString(2, filme.getClassificacao());

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            long id = resultSet.getLong(1);
            filme.setId(id);
        }

        resultSet.close();
        statement.close();

        desconectar();

        return filme;
    }

    public List<Filme> listarTodosFilmes() throws SQLException {
        List<Filme> listaFilmes = new ArrayList<>();
        String sql = "SELECT * FROM filme";

        conectar();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String nome = resultSet.getString("nome");
            String classificacao = resultSet.getString("classificacao");

            Filme filme = new Filme(nome, classificacao);
            filme.setId(id);

            listaFilmes.add(filme);
        }

        resultSet.close();
        statement.close();

        desconectar();

        return listaFilmes;
    }

    public boolean apagarFilme(Filme filme) throws SQLException {
        String sql = "DELETE FROM filme WHERE id = ?";

        conectar();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, filme.getId());

        boolean linhaApagada = statement.executeUpdate() > 0;

        statement.close();
        desconectar();

        return linhaApagada;
    }

    public Filme buscarFilmePorId(long id) throws SQLException {
        Filme filme = null;
        String sql = "SELECT * FROM filme WHERE id = ?";

        conectar();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String classificacao = resultSet.getString("classificacao");

            filme = new Filme(nome, classificacao);
            filme.setId(id);
        }

        resultSet.close();
        statement.close();
        desconectar();

        return filme;
    }

    public boolean editarFilme(Filme filme) throws SQLException {
        String sql = "UPDATE filme SET nome = ?, classificacao = ? WHERE id = ?";

        conectar();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, filme.getNome());
        statement.setString(2, filme.getClassificacao());
        statement.setLong(3, filme.getId());

        boolean linhaAlterada = statement.executeUpdate() > 0;

        statement.close();
        desconectar();

        return linhaAlterada;
    }
}
