package br.com.ifms.controle;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import br.com.ifms.controle.i18n.I18nUtil;
import br.com.ifms.dao.UsuarioDAO;
import br.com.ifms.modelo.Usuario;
import br.com.ifms.seguranca.Autorizacao;
import br.com.ifms.seguranca.Criptografia;
import br.com.ifms.seguranca.DetalheUsuario;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO usuarioDAO;

	public void init() {
		usuarioDAO = new UsuarioDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processarRequisicao(request, response);
	}
	
	private void processarRequisicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("acao");

		try {
			switch (action) {
			case "login":
				login(request, response);
				break;
			case "formLogin":
				formLogin(request, response);
				break;
			case "logout":// aula 19
				logout(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
    	if(session != null){
    		session.invalidate();
    	}
    	RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-login.jsp");
		dispatcher.forward(request, response);		
	}

	private void formLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("publica/publica-login.jsp");
		dispatcher.forward(request, response);		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
		String username = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario usuario = usuarioDAO.buscarUsuarioPorLogin(username);
		String path = "";
		
		Locale locale = (Locale) Config.get(request.getSession(), Config.FMT_LOCALE);
				
		
		if (usuario != null && usuario.isAtivo()) { // login válido
			
			boolean comparacao = Criptografia.compararSenha(senha, usuario.getPassword());
			
			if(comparacao){ // senha válida
				
				DetalheUsuario detalheUsuario = new DetalheUsuario(usuario);
				
				HttpSession session = request.getSession();
				session.setAttribute("usuarioLogado", detalheUsuario);
				//path = "publica/publica-logado.jsp";
				//aula 18
				Autorizacao autorizacao = new Autorizacao();
				path = autorizacao.indexPerfil(detalheUsuario);
				
			} else { // senha inválida ou não ativo
				path = "publica/publica-login.jsp";				
				String texto = I18nUtil.getMensagem(locale, "publica-login-invalido");
				request.setAttribute("mensagem", texto);
			}
		} else {
			path = "publica/publica-login.jsp";
			String texto = I18nUtil.getMensagem(locale, "publica-login-invalido");
			request.setAttribute("mensagem", texto);			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}	

}