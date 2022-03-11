package br.unitins.crud.application;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {

	public static void addMsg (String msg) {
		

		FacesMessage m = new FacesMessage(msg);
		
		FacesContext.getCurrentInstance().addMessage(null, m);
		
	}
}
