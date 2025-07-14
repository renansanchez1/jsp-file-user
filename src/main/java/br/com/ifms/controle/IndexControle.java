package br.com.ifms.controle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import br.com.ifms.controle.i18n.I18nUtil;
import br.com.ifms.controle.util.ManipulacaoData;
import br.com.ifms.dao.PapelDAO;
import br.com.ifms.dao.UsuarioDAO;
import br.com.ifms.modelo.Papel;
import br.com.ifms.modelo.Usuario;
import br.com.ifms.seguranca.Criptografia;



/**
 * Servlet implementation class IndexControle
 */
@WebServlet("/publica")
public class IndexControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO usuarioDAO;
	private PapelDAO papelDAO;
	
	public IndexControle() {
		super();
	}	

	public void init() {
		usuarioDAO = new UsuarioDAO();
		papelDAO = new PapelDAO();
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
			case "novo":
				novoUsuario(request, response);
				break;
			case "inserir":
				gravarUsuario(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	}
	
	private void novoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
		dispatcher.forward(request, response);
	}
	
	private void gravarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, NoSuchAlgorithmException {		
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String login = request.getParameter("login");		
		String data = request.getParameter("nascimento");
		
		ManipulacaoData manipulacaoData = new ManipulacaoData();
		Date dataNascimento = manipulacaoData.converterStringData(data);
		
		
		String senhaCriptografada = Criptografia.converterParaMD5(password); 
		
		
		Usuario usuario = new Usuario(nome, cpf, dataNascimento, email, senhaCriptografada, login, false);
		
		Usuario usuarioGravado = usuarioDAO.inserirUsuario(usuario);
		
		
		Papel papel = papelDAO.buscarPapelPorTipo("USER");
		papelDAO.atribuirPapelUsuario(papel, usuarioGravado);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-novo-usuario.jsp");
		
		Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);

		String texto = I18nUtil.getMensagem(locale, "publica-novo-usuario.mensagem");
		
		
		request.setAttribute("mensagem", texto);
		dispatcher.forward(request, response);
	}

}
