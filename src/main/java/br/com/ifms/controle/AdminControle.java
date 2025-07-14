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
import br.com.ifms.modelo.Papel;
import br.com.ifms.modelo.Usuario;


 
/**
 * Servlet implementation class AdminControle
 */
@WebServlet("/auth/admin")
public class AdminControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	private UsuarioDAO usuarioDAO;
	private PapelDAO papelDAO; // aula 14

	public AdminControle() {
		super();
	}	

	public void init() {
		usuarioDAO = new UsuarioDAO();
		papelDAO = new PapelDAO(); // aula 14
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}
	
	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		try {
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
			case "editarPapel":
				edicaoPapel(request, response); // aula 15
				break;			
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	} 

	private void listarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		List<Usuario> usuarios = usuarioDAO.listarTodosUsuarios();
		
		request.setAttribute("listaUsuarios", usuarios);
		
		String path =  request.getServletPath() + "/admin-listar-usuario.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);
	}
	
	private void apagarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuarioDAO.apagarUsuario(usuario);
		
		String path = request.getContextPath() + request.getServletPath() + "?acao=listar";
		response.sendRedirect(path);
		
	}
	
	
	private void iniciarEdicaoPapel(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
		List<Papel> papeisUsuario = papelDAO.buscarPapelUsuario(usuario);
		usuario.setPapeis(papeisUsuario);	
		request.setAttribute("usuario", usuario);
		
		
		List<Papel> papeis = papelDAO.buscarTodosPapeis();
		request.setAttribute("listaPapeis", papeis);
		
		String path =  request.getServletPath() + "/admin-papel-usuario.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
		dispatcher.forward(request, response);
		
	}
	
	// aula 15
	private void edicaoPapel(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
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
