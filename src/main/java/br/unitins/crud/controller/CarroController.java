package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.model.Cambio;
import br.unitins.crud.model.Carro;

@Named
@ViewScoped
public class CarroController implements Serializable {

	private static final long serialVersionUID = 6622012672983485067L;
	private Carro carro;
	private List<Carro> listaCarros;
	private int cont = 1;

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

	
	public Cambio[] getListaCambio(){
		return Cambio.values();
	}
	
	public boolean validar() {
		
		
		if(carro.getNome().trim().equals("")) {
			
			Util.addMessageError("O nome do veículo deve ser informado.");	
			return false;
		}
		if(carro.getMarca().trim().equals("")) {
			
			Util.addMessageError("A marca do veículo deve ser informado.");	
			return false;
		}
		
		if(getCarro().getNome().trim().length()<3) {
			
			Util.addMessageError("O nome do veículo deve ter pelo menos 3 caracteres.");	
			return false;
		}
		
		return true;
	}
	
	public void cadastrar() {
		
		if(!validar()) {
			return ;
		}
		
	
			getCarro().setId(cont++);
			getListaCarros().add(carro);
			limpar();
			
			Util.addMessageInfo("Cadastro realizado com sucesso!");
		
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
