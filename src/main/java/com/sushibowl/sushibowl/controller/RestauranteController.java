package com.sushibowl.sushibowl.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sushibowl.sushibowl.model.Estado;
import com.sushibowl.sushibowl.model.Restaurante;
import com.sushibowl.sushibowl.service.RestauranteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController implements ControllerInterface<Restaurante> {
	
	@Autowired
	public RestauranteService service;
	
	@Operation(summary = "Retorna todos os restaurantes cadastrados")
	@Override
	@GetMapping
	public ResponseEntity<List<Restaurante>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@Operation(summary = "Retorna o restaurante que possui o id informado")
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Restaurante _obj = service.findById(id);
		if(_obj != null) {
			return ResponseEntity.ok(_obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Operation(summary = "Retorna uma lista de restaurantes por paginação")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Restaurante>> get(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@Operation(summary = "Retorna o restaurante que possui o nome informado")
	@GetMapping(value = "/nome/{query}")
	public ResponseEntity<Page<Restaurante>> getByNome(Pageable pageable, @PathVariable("query") String pesquisa) {
		return ResponseEntity.ok(service.findByNome(pageable, pesquisa));
	}
	
	@Operation(summary = "Retorna o restaurante que possui o logradouro informado")
	@GetMapping(value = "/logradouro/{query}")
	public ResponseEntity<Page<Restaurante>> getByLogradouro(Pageable pageable, @PathVariable("query") String pesquisa) {
		return ResponseEntity.ok(service.findByLogradouro(pageable, pesquisa));
	}
	
	@Operation(summary = "Retorna o restaurante que possui o CNPJ informado")
	@GetMapping(value = "/cnpj/{query}")
	public ResponseEntity<Page<Restaurante>> getByCnpj(Pageable pageable, @PathVariable("query") String cnpj) {
		return ResponseEntity.ok(service.findByCnpj(pageable, cnpj));
	}
	
	@Operation(summary = "Retorna o restaurante que possui o CEP informado")
	@GetMapping(value = "/cep/{query}")
	public ResponseEntity<Page<Restaurante>> getByCep(Pageable pageable, @PathVariable("query") String cep) {
		return ResponseEntity.ok(service.findByCep(pageable, cep));
	}
	
	@Operation(summary = "Retorna o restaurante que está localizado no bairro informado")
	@GetMapping(value = "/bairro/{query}")
	public ResponseEntity<Page<Restaurante>> getByBairro(Pageable pageable, @PathVariable("query") String bairro) {
		return ResponseEntity.ok(service.findByBairro(pageable, bairro));
	}
	
	@Operation(summary = "Retorna o restaurante que está localizado na cidadade informada")
	@GetMapping(value = "/cidade/{query}")
	public ResponseEntity<Page<Restaurante>> getByCidade(Pageable pageable, @PathVariable("query") String cidade) {
		return ResponseEntity.ok(service.findByCidade(pageable, cidade));
	}
	
	@Operation(summary = "Retorna o restaurante que está localizado no Estado informado")
	@GetMapping(value = "/estado/{query}")
	public ResponseEntity<Page<Restaurante>> getByEstado(Pageable pageable, @PathVariable("query") Estado estado) {
		return ResponseEntity.ok(service.findByEstado(pageable, estado));
	}
	
	@Operation(summary = "Cadastro de um novo restaurante")
	@Override
	@PostMapping
	public ResponseEntity<Restaurante> post(@RequestBody Restaurante obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@Operation(summary = "Atualiza os dados de um restaurante existente")
	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Restaurante obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Operation(summary = "Deleta o registro do restaurante que possui o id informado")
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
