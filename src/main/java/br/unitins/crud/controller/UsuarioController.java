package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.dao.UsuarioDAO;
import br.unitins.crud.model.Endereco;
import br.unitins.crud.model.Perfil;
import br.unitins.crud.model.Sexo;
import br.unitins.crud.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 7544251872019170822L;
	private Usuario usuario;
	private List<Usuario> listaUsuarios;

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setEndereco(new Endereco());
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {

		if (listaUsuarios == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listaUsuarios = dao.getAll();
			if (listaUsuarios == null)

				listaUsuarios = new ArrayList<Usuario>();

		}
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Sexo[] getListaSexo() {

		return Sexo.values();

	}
	public Perfil[] getListaPerfil() {
		
		return Perfil.values();
		
	}

	public boolean validar() {

		if (usuario.getNome().trim().equals("")) {

			Util.addMessageError("O nome deve ser informado.");
			return false;
		}

		if (getUsuario().getNome().trim().length() < 3) {

			Util.addMessageError("O nome deve ter pelo menos 3 caracteres.");
			return false;
		}

		return true;
	}

	public void cadastrar() {
		UsuarioDAO dao = new UsuarioDAO();

		String senha = getUsuario().getLogin() + getUsuario().getSenha();
		senha = Util.hash(senha);
		getUsuario().setSenha(senha);
		
		if (!validar())
			return;
		
		if (!dao.insert(getUsuario())) {
			Util.addMessageInfo("Erro ao tentar incluir o usuário.");
			return;
		}
		limpar();
		setListaUsuarios(null);
		Util.addMessageInfo("Inclusão realizada com sucesso.");

	}

	public void alterar() {
		UsuarioDAO dao= new UsuarioDAO();
		
		String senha = getUsuario().getLogin() + getUsuario().getSenha();
		senha = Util.hash(senha);
		getUsuario().setSenha(senha);
		
		if (!validar())
			return;
		
		if(dao.update(getUsuario())) {
			
			limpar();
			Util.addMessageInfo("Alteração realizada com sucesso!");
		}
		else 
			Util.addMessageError("Erro ao realizar alteração!");
	}
	
	
	public void excluir() {
		remover(getUsuario());
		limpar();
	}

	public void remover(Usuario usuario) {
		
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.delete(usuario.getId())) {
			Util.addMessageInfo("Exclusão realizada com sucesso.");
			limpar();
		} else 
			Util.addMessageError("Erro ao excluir.");
		
	}

	public void editar(Usuario u) {

		setUsuario(u.getClone());
	}

	public void limpar() {

		usuario = null;
	}

}
