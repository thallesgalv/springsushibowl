package com.sushibowl.sushibowl.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

import com.sushibowl.sushibowl.model.TabelaNutricional;
import com.sushibowl.sushibowl.service.TabelaNutricionalService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tabelasNutricionais")
public class TabelaNutricionalController implements ControllerInterface<TabelaNutricional> {
	
	@Autowired
	public TabelaNutricionalService service;
	
	@Operation(summary = "Retorna todas as tabelas nutricionais cadastradas")
	@Override
	@GetMapping
	public ResponseEntity<List<TabelaNutricional>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@Operation(summary = "Retorna a tabela nutricional que possui o id informado")
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		TabelaNutricional _obj = service.findById(id);
		if (_obj != null)
			return ResponseEntity.ok(_obj);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@Operation(summary = "Retorna o maior valor de calorias entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/maxcals")
	public ResponseEntity<?> getMaxCals() {
		return ResponseEntity.ok(service.findByMaxCals());
	}

	@Operation(summary = "Retorna o menor valor de calorias entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/mincals")
	public ResponseEntity<?> getMinCals() {
		return ResponseEntity.ok(service.findByMinCals());
	}
	
	@Operation(summary = "Retorna o maior valor de gorduras entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/maxgord")
	public ResponseEntity<?> getMaxGord() {
		return ResponseEntity.ok(service.findByMaxGord());
	}

	@Operation(summary = "Retorna o menor valor de gorduras entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/mingord")
	public ResponseEntity<?> getMinGord() {
		return ResponseEntity.ok(service.findByMinGord());
	}
	
	@Operation(summary = "Retorna o maior valor de carboidratos entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/maxcarbs")
	public ResponseEntity<?> getMaxCarbs() {
		return ResponseEntity.ok(service.findByMaxCarbs());
	}

	@Operation(summary = "Retorna o menor valor de carboidratos entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/mincarbs")
	public ResponseEntity<?> getMinCarbs() {
		return ResponseEntity.ok(service.findByMinCarbs());
	}
	
	@Operation(summary = "Retorna o maior valor de proteínas entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/maxprot")
	public ResponseEntity<?> getMaxProt() {
		return ResponseEntity.ok(service.findByMaxProt());
	}

	@Operation(summary = "Retorna o menor valor de proteínas entre as tabelas nutricionais cadastradas")
	@GetMapping(value = "/minprot")
	public ResponseEntity<?> getMinProt() {
		return ResponseEntity.ok(service.findByMinProt());
	}

	@Operation(summary = "Cadastra uma nova tabela nutricional")
	@Override
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<TabelaNutricional> post(@RequestBody TabelaNutricional obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@Operation(summary = "Atualiza os dados de uma tabela nutricional existente")
	@Override
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> put(@RequestBody TabelaNutricional obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Operation(summary = "Deleta o registro da tabela nutricional que possui o id informado")
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
