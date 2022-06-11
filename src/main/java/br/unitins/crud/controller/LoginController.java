package br.unitins.crud.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.crud.application.Session;
import br.unitins.crud.application.Util;
import br.unitins.crud.dao.UsuarioDAO;
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
		
		String hash = Util.hash(getUsuario());
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.verificarLogin(getUsuario().getLogin(), hash);
		
		if (usuario == null) {
			Util.addMessageError("Login ou Senha inválido.");
			return;
		}
		
		Session.getInstance().set("usuarioLogado",usuario);
		
		Util.redirect("menu-principal.xhtml");
		
	}
	
	public void novoCadastro(){
		
		Util.redirect("cadastro-usuario.xhtml");
	}
	
	

}
