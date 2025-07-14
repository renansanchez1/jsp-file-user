package br.com.ifms.modelo;

import java.util.List;

import br.com.ifms.modelo.Usuario;

public class Papel {

	private Long id;

	private String tipoPapel;

	private List<Usuario> usuarios;

	public Papel() {
	}


	public Papel(Long id, String tipoPapel) {
		super();
		this.id = id;
		this.tipoPapel = tipoPapel;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoPapel() {
		return tipoPapel;
	}

	public void setTipoPapel(String tipoPapel) {
		this.tipoPapel = tipoPapel;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
	
}
