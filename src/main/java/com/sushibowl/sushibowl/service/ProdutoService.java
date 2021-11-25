package com.sushibowl.sushibowl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sushibowl.sushibowl.model.Produto;
import com.sushibowl.sushibowl.repository.ProdutoRepository;


@Service
public class ProdutoService implements ServiceInterface<Produto> {
	
	
	@Autowired
	private ProdutoRepository repository;

	public ProdutoService() {}
	
	@Override
	public Produto create(Produto obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public List<Produto> findAll(){
		return repository.findAll();
	}
	
	public Page<Produto> findAll(Pageable pageable) {
		return repository.findAllOrderByNome(pageable);
	}
	
	public Page<Produto> findByCategoria(Pageable pageable, Long categoriaId) {
		return repository.findByCategoria(pageable, categoriaId);
	}
	
	public Page<Produto> findByPesquisa(Pageable pageable, String pesquisa) {
		return repository.findByPesquisa(pageable, pesquisa);
	}
	
	public Page<Produto> findByRestaurante(Pageable pageable, Long restauranteId) {
		return repository.findByRestaurante(pageable, restauranteId);
	}
	
	public Page<Produto> findByCalsBetween(Pageable pageable, Float from, Float to) {
		return repository.findByCalsBetween(pageable, from, to);
	}
	
	public Page<Produto> findByGordBetween(Pageable pageable, Float from, Float to) {
		return repository.findByGordBetween(pageable, from, to);
	}
	
	public Page<Produto> findByCarbsBetween(Pageable pageable, Float from, Float to) {
		return repository.findByCarbsBetween(pageable, from, to);
	}
	
	public Page<Produto> findByProtBetween(Pageable pageable, Float from, Float to) {
		return repository.findByProtBetween(pageable, from, to);
	}
	
	@Override
	public Produto findById(Long id) {
		Optional<Produto> _obj = repository.findById(id);
		return _obj.orElse(null);
	}
	
	@Override
	public boolean update(Produto obj) {
		if (repository.existsById(obj.getId())) {
			repository.save(obj);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean delete(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		
		return false;
	}

}
