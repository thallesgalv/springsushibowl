package com.sushibowl.sushibowl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name="tb_endereco")
@Entity
public class Endereco  extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="nm_logradouro", length = 120)
	private String logradouro;
	@Column(name="ds_complemento", length = 60)
	private String complemento;
	@Column(name="nm_bairro", length = 60)
	private String bairro;
	@Column(name="nm_cidade", length = 60)
	private String cidade;
	@Column(name="sg_estado", length = 2)
	private Estado estado;
	@Column(name="cd_cep", length = 11)
	private String cep;
	
	public Endereco() {}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
