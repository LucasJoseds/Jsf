package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Session;
import br.unitins.crud.application.Util;
import br.unitins.crud.dao.CarroDAO;
import br.unitins.crud.model.Carro;
import br.unitins.crud.model.ItemVenda;

@Named
@ViewScoped
public class VendaController implements Serializable {


	private static final long serialVersionUID = 5576160337529657247L;
	private Integer tipoFiltro;
	private String filtro;
	private List<Carro> listaCarros;

	
	
	public void pesquisar() {
		CarroDAO dao = new CarroDAO();
		setListaCarros(dao.findByNome(getFiltro()));
	}
	
	public void comprar(Carro carro) {
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();
		
		ItemVenda item = new ItemVenda();
		item.setCarro(carro);
		item.setValor(carro.getPreco());
		item.setQuantidade(1);
		
	
		
		if (carrinho.contains(item)) {
			int index = carrinho.indexOf(item);
			int quantidade = carrinho.get(index).getQuantidade();
			double preco =carrinho.get(index).getValor();
			
			carrinho.get(index).setQuantidade(quantidade + 1);
			carrinho.get(index).setValor(preco+carro.getPreco());
		} else {
			carrinho.add(item);
		}

		Session.getInstance().set("carrinho", carrinho);
		
		Util.addMessageInfo("Carro adicionado na sessão");
		
	}

	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}
	
}
