package br.unitins.crud.model;

public enum Cambio {

	AUTOMATICO(1,"Automático"),
	MANUAL(2,"Manual");
	
	private int id;
	private String label;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getLabel() {
		return label;
	}



	public void setLabel(String label) {
		this.label = label;
	}



	Cambio(int id , String label){
		
		this.id=id;
		this.label=label;
	}
}
