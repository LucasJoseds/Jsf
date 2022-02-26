package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.model.Carro;

@Named
@ViewScoped
public class CarroController implements Serializable {

	private static final long serialVersionUID = 6622012672983485067L;
	private Carro carro;
	private List<Carro> listaCarros;
	private int cont =1;

	public Carro getCarro() {
		if (carro == null) {
			carro = new Carro();
		}
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getListaCarros() {
		if (listaCarros == null) {
			listaCarros = new ArrayList<Carro>();
		}
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}

	public void cadastrar() {

		getCarro().setId(cont++);
		getListaCarros().add(carro);
		limpar();
		
		Util.addMsg("Cadastro realizado com sucesso!");
	}
	
	public void alterar() {
		
		int index = listaCarros.indexOf(getCarro());
		listaCarros.set(index, getCarro());
		
	}

	public void remover(Carro carro) {
		listaCarros.remove(carro);
	}
	public void editar(Carro carro) {
	
		setCarro(carro.getClone());
	}
	public void limpar() {

		carro = null;

	}

}
