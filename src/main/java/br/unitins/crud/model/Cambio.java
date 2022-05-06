package br.unitins.crud.model;

public enum Cambio {

	AUTOMATICO(1,"Automático"),
	MANUAL(2,"Manual");
	
	private int id;
	private String label;
	
	
	Cambio(int id , String label){
		
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


	public static Cambio valueOf(int id) {
		for (Cambio cambio : Cambio.values()) {
			if (id == cambio.getId())
				return cambio;
		}
		return null;
	}




	
}
