package br.unitins.crud.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.crud.application.Session;
import br.unitins.crud.dao.VendaDAO;
import br.unitins.crud.model.Usuario;
import br.unitins.crud.model.Venda;

@Named
@ViewScoped
public class HistoricoController implements Serializable {

	private static final long serialVersionUID = 6605190152670689841L;
	private List<Venda> listaVenda;
	
	public HistoricoController() {
	}

	public List<Venda> getListaVenda() {
		Usuario usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		if (usuarioLogado == null) {
			listaVenda = new ArrayList<Venda>();
		} else {
			if (listaVenda == null) {
				VendaDAO dao = new VendaDAO();
				listaVenda = dao.getByUsuario(usuarioLogado);
				if (listaVenda == null)
					listaVenda = new ArrayList<Venda>();
			}
		}
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
}
