package com.sushibowl.sushibowl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sushibowl.sushibowl.model.Categoria;
import com.sushibowl.sushibowl.repository.CategoriaRepository;


@Service
public class CategoriaService implements ServiceInterface<Categoria> {
	
	
	@Autowired
	private CategoriaRepository repository;

	public CategoriaService() {}
	
	@Override
	public Categoria create(Categoria obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	

	public Page<Categoria> findAll(Pageable pageable){
		return repository.findAllOrderByNome(pageable);
	}
	
	@Override
	public Categoria findById(Long id) {
		Optional<Categoria> _obj = repository.findById(id);
		return _obj.orElse(null);
	}
	
	@Override
	public boolean update(Categoria obj) {
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
