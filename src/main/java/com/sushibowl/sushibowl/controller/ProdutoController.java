package com.sushibowl.sushibowl.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sushibowl.sushibowl.model.Produto;
import com.sushibowl.sushibowl.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/produtos")
public class ProdutoController implements ControllerInterface<Produto> {
	
	@Autowired
	public ProdutoService service;
	
	@Operation(summary = "Retorna todos os produtos cadastrados")
	@Override
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@Operation(summary = "Retorna o produto que possui o id informado")
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Produto _obj = service.findById(id);
		if (_obj != null)
			return ResponseEntity.ok(_obj);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Operation(summary = "Retorna uma lista de produtos por paginação")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Produto>> get(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@Operation(summary = "Retorna o produto que possui a categoria informada")
	@GetMapping(value = "/categoria/{id}")
	public ResponseEntity<Page<Produto>> getByCategoria(Pageable pageable, @PathVariable("id") Long categoriaId) {
		return ResponseEntity.ok(service.findByCategoria(pageable, categoriaId));
	}
	
	@Operation(summary = "Retorna o produto que possui a palavra informada")
	@GetMapping(value = "/pesquisa/{query}")
	public ResponseEntity<Page<Produto>> getByPesquisa(Pageable pageable, @PathVariable("query") String pesquisa) {
		return ResponseEntity.ok(service.findByPesquisa(pageable, pesquisa));
	}
	
	@Operation(summary = "Retorna os produtos que estão disponíveis no restaurante que possui o id informado")
	@GetMapping(value = "/restaurante/{id}")
	public ResponseEntity<Page<Produto>> getByRestaurante(Pageable pageable, @PathVariable("id") Long restauranteId) {
		return ResponseEntity.ok(service.findByRestaurante(pageable, restauranteId));
	}
	
	@Operation(summary = "Retorna os produtos que possuem a quantidade de calorias entre os valores informados")
	@GetMapping(value = "/calsbetween/{from}/{to}")
	public ResponseEntity<Page<Produto>> getByCalsBetween(
			Pageable pageable, 
			@PathVariable("from") Float from, 
			@PathVariable("to") Float to
			) {
		return ResponseEntity.ok(service.findByCalsBetween(pageable, from, to));
	}
	
	@Operation(summary = "Retorna os produtos que possuem a quantidade de gorduras entre os valores informados")
	@GetMapping(value = "/gordbetween/{from}/{to}")
	public ResponseEntity<Page<Produto>> getByGordBetween(
			Pageable pageable, 
			@PathVariable("from") Float from, 
			@PathVariable("to") Float to
			) {
		return ResponseEntity.ok(service.findByGordBetween(pageable, from, to));
	}
	
	@Operation(summary = "Retorna os produtos que possuem a quantidade de carboidratos entre os valores informados")
	@GetMapping(value = "/carbsbetween/{from}/{to}")
	public ResponseEntity<Page<Produto>> getByCarbsBetween(
			Pageable pageable, 
			@PathVariable("from") Float from, 
			@PathVariable("to") Float to
			) {
		return ResponseEntity.ok(service.findByCarbsBetween(pageable, from, to));
	}
	
	@Operation(summary = "Retorna os produtos que possuem a quantidade de proteínas entre os valores informados")
	@GetMapping(value = "/protbetween/{from}/{to}")
	public ResponseEntity<Page<Produto>> getByProtBetween(
			Pageable pageable, 
			@PathVariable("from") Float from, 
			@PathVariable("to") Float to
			) {
		return ResponseEntity.ok(service.findByProtBetween(pageable, from, to));
	}

	@Operation(summary = "Cadastra um novo produto")
	@Override
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Produto> post(@RequestBody Produto obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@Operation(summary = "Atualiza os dados de um produto existente")
	@Override
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> put(@RequestBody Produto obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Operation(summary = "Deleta o registro do produto que possui o id informado")
	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
