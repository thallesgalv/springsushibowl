package com.sushibowl.sushibowl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushibowl.sushibowl.model.TabelaNutricional;
import com.sushibowl.sushibowl.repository.TabelaNutricionalRepository;


@Service
public class TabelaNutricionalService implements ServiceInterface<TabelaNutricional> {
	
	
	@Autowired
	private TabelaNutricionalRepository repository;

	public TabelaNutricionalService() {}
	
	@Override
	public TabelaNutricional create(TabelaNutricional obj) {
		repository.save(obj);
		return obj;
	}
	
	@Override
	public List<TabelaNutricional> findAll(){
		return repository.findAll();
	}
	
	@Override
	public TabelaNutricional findById(Long id) {
		Optional<TabelaNutricional> _obj = repository.findById(id);
		return _obj.orElse(null);
	}
	
	public Float findByMaxCals() {
		return repository.findByMaxCals();
	}
	public Float findByMinCals() {
		return repository.findByMinCals();
	}
	
	public Float findByMaxGord() {
		return repository.findByMaxGord();
	}
	public Float findByMinGord() {
		return repository.findByMinGord();
	}
	
	public Float findByMaxCarbs() {
		return repository.findByMaxCarbs();
	}
	public Float findByMinCarbs() {
		return repository.findByMinCarbs();
	}
	
	public Float findByMaxProt() {
		return repository.findByMaxProt();
	}
	public Float findByMinProt() {
		return repository.findByMinProt();
	}
	
	@Override
	public boolean update(TabelaNutricional obj) {
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
