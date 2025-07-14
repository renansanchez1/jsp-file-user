package br.com.ifms.controle.i18n;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

/**
 * Servlet implementation class I18nControle
 */
@WebServlet("/I18nControle")
public class I18nControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public I18nControle() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lingua = request.getParameter("lingua");
		
		String[] tk = new String[2];  
	    StringTokenizer tokenizer = new StringTokenizer(lingua, "_");
	    int i = 0;
	    while (tokenizer.hasMoreElements()) {
	        tk[i] = tokenizer.nextToken();
	        i++;
	    }
	  
	    Locale locale = new Locale(tk[0], tk[1]);

		Config.set(request.getSession(), Config.FMT_LOCALE, locale);
		Config.set(request.getSession(), Config.FMT_FALLBACK_LOCALE, locale);

		
		response.sendRedirect("index.jsp");
		
	}

}