package br.unitins.crud.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Session;
import br.unitins.crud.application.Util;
import br.unitins.crud.dao.VendaDAO;
import br.unitins.crud.model.ItemVenda;
import br.unitins.crud.model.Usuario;
import br.unitins.crud.model.Venda;


@Named
@ViewScoped
public class CarrinhoController implements Serializable {


	private static final long serialVersionUID = -8644439689715429158L;
    private List<ItemVenda> listaItemVenda;
	
	public CarrinhoController() {
		listaItemVenda = (List<ItemVenda>) Session.getInstance().get("carrinho");
	}
	
	public void finalizar() {
		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuarioLogado == null) {
			Util.addMessageError("Faça o Login para finalizar a compra.");
			return;
		}
		if (getListaItemVenda() == null || getListaItemVenda().isEmpty()) {
			Util.addMessageError("Selecione um livro antes de finalizar uma compra.");
			return;
		}
		
		Venda venda = new Venda();
		venda.setUsuario(usuarioLogado);
		venda.setDataVenda(LocalDateTime.now());
		venda.setListaItemVenda(getListaItemVenda());
		
		VendaDAO dao = new VendaDAO();
		dao.insert(venda);
		
		Util.addMessageInfo("Compra realizada com sucesso.");
		
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
}
