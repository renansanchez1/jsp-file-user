package br.com.ifms.controle.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nUtil {
	
	// adicionado o static aula 16
	public static String getMensagem(Locale locale, String chave) {
		//aula 16
		if (locale == null) {
			locale = new Locale("pt", "BR");//padrão
		}
		
	    ResourceBundle bundle = ResourceBundle.getBundle("resources.message", locale);

	    String mensagem = bundle.getString(chave);
	    return mensagem;
	}
}
