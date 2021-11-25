package com.sushibowl.sushibowl.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="tb_categoria")
@Inheritance(strategy = InheritanceType.JOINED)
public class Categoria extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="nm_nome", length = 120)
	private String nome;	
	
	
	public Categoria() {}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
