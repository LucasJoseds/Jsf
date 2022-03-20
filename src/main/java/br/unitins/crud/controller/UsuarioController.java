package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.model.Carro;
import br.unitins.crud.model.Endereco;
import br.unitins.crud.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 7544251872019170822L;
	private Usuario usuario;
	private int cont=1;
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
	
	
	public Sexo[] getListaSexo() {
		
		return Sexo.values();
		
	}
	
	
public boolean validar() {
		
		
		if(usuario.getNome().trim().equals("")) {
			
			Util.addMsg("O nome deve ser informado.");	
			return false;
		}
		
		
		if(getUsuario().getNome().trim().length()<3) {
			
			Util.addMsg("O nome deve ter pelo menos 3 caracteres.");	
			return false;
		}
		
		return true;
	}
	
	public void cadastrar() {
		
		if(!validar()) {
			return ;
		}
		
	
			getUsuario().setId(cont++);
			getListaUsuarios().add(usuario);
			limpar();
			
			Util.addMsg("Cadastro realizado com sucesso!");
		
	}
	
	public void alterar() {
		
		
		if(!validar())
			return;
			
			
		int index = listaUsuarios.indexOf(getUsuario());
		listaUsuarios.set(index, getUsuario());
		
		limpar();
		
		Util.addMsg("Alteração realizada com sucesso!");
	}

	
	
	
	
	public void remover(Usuario usuario) {
		listaUsuarios.remove(usuario);
	}

	public void editar(Usuario u) {

		setUsuario(u.getClone());
	}
		public void limpar() {
			
			usuario=null;
	}
	
	
	
	
}
