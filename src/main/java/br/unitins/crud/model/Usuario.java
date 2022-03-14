package br.unitins.crud.model;

import java.time.LocalDate;

public class Usuario implements Cloneable {

	private Integer id;
	private String nome;
	private Integer idade;
	private Endereco endereco;
	private String cpf;
	private String telefone;
	private String senha;
	private LocalDate dataNAscimento;
	
	

	public Usuario getClone() {

		try {
			return (Usuario) super.clone();

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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNAscimento() {
		return dataNAscimento;
	}

	public void setDataNAscimento(LocalDate dataNAscimento) {
		this.dataNAscimento = dataNAscimento;
	}

}
