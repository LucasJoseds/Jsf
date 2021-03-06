package br.unitins.crud.model;

import java.time.LocalDate;
import java.util.Objects;

public class Carro implements Cloneable{

	private Integer id;
	private String nome;
	private Marca marca;
	private String cor;
	private String placa;
	private Cambio cambio;
	private String potencia;
	private LocalDate datalancamento;
	private double preco;
		
	public Cambio getCambio() {
		return cambio;
	}
	
	public void setCambio(Cambio cambio) {
		this.cambio = cambio;
	}
	public Carro getClone() {
		
		try {
			return (Carro)super.clone();
			
		} catch (CloneNotSupportedException e) {
			
			e.printStackTrace();
		}
		return null;
}

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getPotencia() {
		return potencia;
	}
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	
	public LocalDate getDatalancamento() {
		return datalancamento;
	}

	public void setDatalancamento(LocalDate datalancamento) {
		this.datalancamento = datalancamento;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
