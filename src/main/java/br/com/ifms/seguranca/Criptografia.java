package br.com.ifms.seguranca;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class Criptografia {
	
	public static String converterParaMD5(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(senha.getBytes());
	    byte[] digest = md.digest();	    
	    String resultado = new String(Hex.encodeHex(digest));
		return resultado;
	}
	//aula 16
	public static boolean compararSenha(String senha, String senhaGravada) throws NoSuchAlgorithmException {
	    String resultado = converterParaMD5(senha);
	    boolean ehIgual = resultado.equals(senhaGravada);	   
		return ehIgual;
	}
}
