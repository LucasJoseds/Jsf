package br.unitins.crud.model;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario implements Cloneable {

	private Integer id;
	private String nome;
	private Sexo sexo;
	private Endereco endereco;
	private String login;
	private String cpf;
	private String telefone;
	private String senha;
	private Perfil perfil;
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


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public LocalDate getDataNAscimento() {
		return dataNAscimento;
	}

	public void setDataNAscimento(LocalDate dataNAscimento) {
		this.dataNAscimento = dataNAscimento;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
}
