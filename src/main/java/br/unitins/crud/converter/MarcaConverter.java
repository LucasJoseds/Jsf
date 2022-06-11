package br.unitins.crud.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.crud.dao.MarcaDAO;
import br.unitins.crud.model.Marca;

@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter<Marca> {

	
	@Override
	public Marca getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank())
			return null;
		MarcaDAO dao = new MarcaDAO();
		return dao.getById(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Marca marca) {
		if (marca == null || marca.getId() == null)
			return null;
		return marca.getId().toString();
	}
}
