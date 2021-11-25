package com.sushibowl.sushibowl.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="nm_nome", length = 120)
	private String name;
	@Column(name="ds_ingredientes", length = 120)
	private String ingredientes;

	@Column(name="ds_imagem", length = 120)
	private String imagem;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="fk_tabela_nutricional_id", unique=true)
	private TabelaNutricional tabelaNutricional;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Restaurante> restaurantes;
	
	@ManyToOne
	private Categoria categoria;


	public Produto() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public TabelaNutricional getTabelaNutricional() {
		return tabelaNutricional;
	}

	public void setTabelaNutricional(TabelaNutricional tabelaNutricional) {
		this.tabelaNutricional = tabelaNutricional;
	}

	public List<Restaurante> getRestaurante() {
		return restaurantes;
	}

	public void setRestaurante(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
