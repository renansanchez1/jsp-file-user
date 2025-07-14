package br.com.ifms.controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ifms.dao.PapelDAO;
import br.com.ifms.dao.UsuarioDAO;
import br.com.ifms.dao.FilmeDAO;
import br.com.ifms.modelo.Papel;
import br.com.ifms.modelo.Filme;
import br.com.ifms.modelo.Usuario;

/**
 * Servlet implementation class AdminControle
 */
@WebServlet("/auth/admin")
public class AdminControle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;
    private PapelDAO papelDAO;
    private FilmeDAO filmeDAO;

    public AdminControle() {
        super();
    }

    public void init() {
        usuarioDAO = new UsuarioDAO();
        papelDAO = new PapelDAO();
        filmeDAO = new FilmeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            System.out.println("Ação recebida no doGet: " + acao);
            if (acao == null) {
                acao = "";
            }
            switch (acao) {
                case "listar":
                    listarUsuario(request, response);
                    break;
                case "apagar":
                    apagarUsuario(request, response);
                    break;
                case "iniciarEditarPapel":
                    iniciarEdicaoPapel(request, response);
                    break;
                case "listar-filmes":
                    listarFilmes(request, response);
                    break;
                case "novo-filme":
                    novoFilme(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/auth/admin?acao=novo-filme");
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            System.out.println("Ação recebida no doPost: " + acao);
            if (acao == null) {
                acao = "";
            }
            switch (acao) {
                case "inserirFilme":
                    gravarFilme(request, response);
                    break;
                case "editarPapel":
                    edicaoPapel(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/auth/admin?acao=novo-filme");
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void novoFilme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/admin-adicionar-filme.jsp");
        dispatcher.forward(request, response);
    }

    private void gravarFilme(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String formularioEnviado = request.getParameter("formularioEnviado");
        if (!"true".equals(formularioEnviado)) {
            request.setAttribute("mensagem", "Erro: Requisição inválida!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/admin-adicionar-filme.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String nome = request.getParameter("nome");
        String classificacao = request.getParameter("classificacao");

        // Log para depuração
        System.out.println("Nome recebido: " + nome);
        System.out.println("Classificação recebida: " + classificacao);

        // Validação
        if (nome == null || nome.trim().isEmpty()) {
            request.setAttribute("mensagem", "Erro: O nome do filme é obrigatório!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("auth/admin/admin-adicionar-filme.jsp");
            dispatcher.forward(request, response);
            return;
        }
        if (classificacao == null || classificacao.trim().isEmpty()) {
            request.setAttribute("mensagem", "Erro: A classificação é obrigatória!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("auth/admin/admin-adicionar-filme.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Filme filme = new Filme(nome, classificacao);
        filmeDAO.inserirFilme(filme);

        request.setAttribute("mensagem", "Filme cadastrado com sucesso!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("auth/admin/admin-adicionar-filme.jsp");
        dispatcher.forward(request, response);
    }

    private void listarFilmes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Filme> filmes = filmeDAO.listarTodosFilmes();
        request.setAttribute("listaFilmes", filmes);
        String path = request.getServletPath() + "/admin-listar-filme.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void listarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listarTodosUsuarios();
        request.setAttribute("listaUsuarios", usuarios);
        String path = request.getServletPath() + "/admin-listar-usuario.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void apagarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuarioDAO.apagarUsuario(usuario);
        String path = request.getContextPath() + request.getServletPath() + "?acao=listar";
        response.sendRedirect(path);
    }

    private void iniciarEdicaoPapel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
        List<Papel> papeisUsuario = papelDAO.buscarPapelUsuario(usuario);
        usuario.setPapeis(papeisUsuario);
        request.setAttribute("usuario", usuario);
        List<Papel> papeis = papelDAO.buscarTodosPapeis();
        request.setAttribute("listaPapeis", papeis);
        String path = request.getServletPath() + "/admin-papel-usuario.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void edicaoPapel(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        usuario.setAtivo(ativo);
        usuarioDAO.editarUsuario(usuario);
        String[] idsPapeis = request.getParameterValues("pps");
        papelDAO.apagarPapeisUsuario(usuario);
        for (int i = 0; i < idsPapeis.length; i++) {
            Papel papel = new Papel();
            long idPapel = Long.valueOf(idsPapeis[i]);
            papel.setId(idPapel);
            papelDAO.atribuirPapelUsuario(papel, usuario);
        }
        String path = request.getContextPath() + request.getServletPath() + "?acao=listar";
        response.sendRedirect(path);
    }
}