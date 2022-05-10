package br.unitins.crud.model;

public enum Sexo {

	M(1,"Masculino"),
	F(2,"Feminino");
	
	private int id;
	private String label;
	
	Sexo(int id , String label){
		
		this.id=id;
		this.label=label;
	}

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
	
	
	
}
