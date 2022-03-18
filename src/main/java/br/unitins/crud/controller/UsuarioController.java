package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.model.Endereco;
import br.unitins.crud.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 7544251872019170822L;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;
	
	
	
	public Usuario getUsuario() {
		if(usuario==null) {
			usuario = new Usuario();
			usuario.setEndereco(new Endereco());
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getListaUsuarios() {
		
		if(listaUsuarios ==  null) {
			listaUsuarios= new ArrayList<Usuario>();
		}
		return listaUsuarios;
	}
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
	
	
	
	
}
