package br.com.ifms.seguranca;

import java.util.ArrayList;
import java.util.List;

import br.com.ifms.modelo.Papel;
import br.com.ifms.modelo.Usuario;

public class DetalheUsuario {

	private Usuario usuario;

	// aula 18
	private List<String> papeis;

	// aula 18
	public DetalheUsuario(Usuario usuario) {
		this.usuario = usuario;
		this.papeis = new ArrayList<>();
		for (Papel p : usuario.getPapeis()) {
			this.papeis.add(p.getTipoPapel());
		}
	}
	
	public boolean isAtivo() {
		return usuario.isAtivo();
	}
	
	public String getNome() {
		return usuario.getNome();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	// aula 18
	public List<String> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
	}
	
}
