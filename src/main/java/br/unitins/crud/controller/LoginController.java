package br.unitins.crud.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	
	public Usuario getUsuario() {
		if(usuario==null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public void login() {
		System.out.println(usuario.getLogin());
		System.out.println(usuario.getSenha());
	}
	
	public void novoCadastro(){
		
		Util.redirect("cadastro-usuario.xhtml");
	}
	
	

}
