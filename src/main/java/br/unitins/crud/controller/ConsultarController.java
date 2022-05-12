package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.dao.CarroDAO;
import br.unitins.crud.model.Carro;

@Named
@ViewScoped
public class ConsultarController implements Serializable{

	private static final long serialVersionUID = 4990676088730157416L;
	private List<Carro> listaCarros;
	private String filtro;
	
	
	
	public List<Carro> getListaCarros() {

		return listaCarros;
	}
	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
		
	public void novo() {
		Util.redirect("cadastro.xhtml");
	}
	
	public void editar(int id) {

		CarroDAO dao = new CarroDAO();
		Carro carro= dao.getById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("carroFlash", carro);
		
		Util.redirect("editar.xhtml");
		
	}
	
	
	public void excluir(int id) {

		CarroDAO dao = new CarroDAO();
		if (!dao.delete(id)) {
			Util.addMessageError("Erro ao deletar");
			return;
		}
		setListaCarros(null);
		Util.addMessageInfo("Deletado com sucesso");
	}
	
	
	

	public void pesquisar() {
		CarroDAO dao = new CarroDAO();
		setListaCarros(dao.findByNome(getFiltro()));
	}
	
	
}
