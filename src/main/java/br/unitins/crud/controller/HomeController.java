package br.unitins.crud.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Session;
import br.unitins.crud.application.Util;
import br.unitins.crud.model.Usuario;

@Named
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = -5188454573524348740L;

	private Usuario usuarioLogado;

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null)
			usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		return usuarioLogado;
	}

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}

}
