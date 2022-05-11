package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	

	public void pesquisar() {
		CarroDAO dao = new CarroDAO();
		setListaCarros(dao.findByNome(getFiltro()));
	}
	
	
}
