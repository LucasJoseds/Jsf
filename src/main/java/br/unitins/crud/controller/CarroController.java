package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.model.Carro;

@Named
@ViewScoped
public class CarroController implements Serializable {

	private static final long serialVersionUID = 6622012672983485067L;
	private Carro carro;
	private List<Carro> listaCarros;
	
	
	
	public Carro getCarro() {
		if(carro == null) {
			carro = new Carro();
		}
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}
	
	public List<Carro> getListaCarros() {
		if(listaCarros == null) {
			listaCarros= new ArrayList<Carro>();
		}
		return listaCarros;
	}
	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}
	
	
	public void cadastrar() {
		
		getListaCarros().add(carro);
		limpar();
	}
	
	public void limpar() {
		
		carro =null;
		
//		carro.setNome(null);
//		carro.setMarca(null);
//		carro.setCor(null);
//		carro.setPlaca(null);
//		carro.setPotencia(null);
	}
	
	
}
