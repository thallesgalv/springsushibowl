package com.sushibowl.sushibowl.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="tb_restaurante")
@Inheritance(strategy = InheritanceType.JOINED)
public class Restaurante extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="nm_nome", length = 120)
	private String nome;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="fk_endereco_id", unique=true)
	private Endereco endereco;
	
	@Column(name="cd_cnpj", length = 14)
	private String cnpj;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_perfil")
	private Set<Integer> perfis = new HashSet<>();
	
	@Column(name = "nm_login", length = 80, unique = true)
	private String login;

	@Column(name = "nm_senha")
	private String senha;	
	
	public Restaurante() {}
	
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
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public Set<TipoPerfil> getPerfis() {
		 return perfis.stream()
		 .map(x -> TipoPerfil.toEnum(x))
		 .collect(Collectors.toSet());
	}
	
	public void addPerfil(TipoPerfil perfil) {
		 this.perfis.add(perfil.getCod());
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@JsonIgnore
	public String getSenha() {
		return senha;
	}
	
	@JsonProperty
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
