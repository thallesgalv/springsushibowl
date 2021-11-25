package com.sushibowl.sushibowl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushibowl.sushibowl.model.Endereco;
import com.sushibowl.sushibowl.repository.EnderecoRepository;


@Service
public class EnderecoService implements ServiceInterface<Endereco> {
	
	
	@Autowired
	private EnderecoRepository repository;

	public EnderecoService() {}
	
	@Override
	public Endereco create(Endereco obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public List<Endereco> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Endereco findById(Long id) {
		Optional<Endereco> _obj = repository.findById(id);
		return _obj.orElse(null);
	}
	
	@Override
	public boolean update(Endereco obj) {
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
