package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Util;
import br.unitins.crud.dao.CarroDAO;
import br.unitins.crud.dao.MarcaDAO;
import br.unitins.crud.model.Cambio;
import br.unitins.crud.model.Carro;
import br.unitins.crud.model.Marca;

@Named
@ViewScoped
public class CarroController implements Serializable {



	private static final long serialVersionUID = 3082786856774448591L;
	private Carro carro;
	private String filtro;
	private List<Carro> listaCarros;
	private List<Marca> listaMarcas;

	
	public CarroController() {
		
		Flash flash= FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("carroFlash");
		setCarro((Carro)flash.get("carroFlash"));
		
	

	}
	
	
	public String formatPrice(Double price) {
		 return Util.formatPrice(price);
	}
	
	
	
	
	public Carro getCarro() {
		if (carro == null) {
			carro = new Carro();

			carro.setMarca(new Marca());
		}
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Carro> getListaCarros() {
		if (listaCarros == null) {
			CarroDAO dao = new CarroDAO();
			listaCarros = dao.getAll();

			if (listaCarros == null)
				listaCarros = new ArrayList<Carro>();
		}
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}

	public Cambio[] getListaCambio() {
		return Cambio.values();
	}
	
	

	public boolean validar() {

		if (carro.getNome().trim().equals("")) {

			Util.addMessageError("O nome do veículo deve ser informado.");
			return false;
		}

		if (getCarro().getNome().trim().length() < 3) {

			Util.addMessageError("O nome do veículo deve ter pelo menos 3 caracteres.");
			return false;
		}

		return true;
	}

	public void cadastrar() {

		if (!validar()) {
			return;
		}

		CarroDAO dao = new CarroDAO();
		if (!dao.insert(getCarro())) {
			Util.addMessageInfo("Erro ao realizar cadastro");
			return;
		}
		limpar();

		setListaCarros(null);
		Util.addMessageInfo("Inclusão realizada com sucesso");

	}

	public void alterar() {

		if (!validar()) {
			return;
		}

		CarroDAO dao = new CarroDAO();
		if (!dao.update(getCarro())) {
			Util.addMessageError("Erro ao atualizar os dados");
			return;
		}
		limpar();
		setListaCarros(null);
		
		FacesContext.getCurrentInstance().addMessage(
		        null, new FacesMessage("Alteração realizado com sucesso!"));
		 
		  FacesContext.getCurrentInstance()
		      .getExternalContext()
		      .getFlash().setKeepMessages(true);
		  
		Util.redirect("pesquisar.xhtml");
		
	}
	

	public void remover() {
		excluir(getCarro().getId());
		limpar();
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
	
	
	public void voltar() {
		
		Util.redirect("pesquisar.xhtml");
	}


	public void limpar() {

		carro = null;

	}

	public List<Marca> getListaMarcas() {
		if (listaMarcas == null) {
			MarcaDAO dao = new MarcaDAO();
			listaMarcas = dao.getAll();

			if (listaMarcas == null)
				listaMarcas = new ArrayList<Marca>();
		}
		return listaMarcas;

	}
}
