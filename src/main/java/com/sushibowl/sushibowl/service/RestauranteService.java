package com.sushibowl.sushibowl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sushibowl.sushibowl.config.AuthorizationException;
import com.sushibowl.sushibowl.config.JWTUtil;
import com.sushibowl.sushibowl.config.UserDetailsImpl;
import com.sushibowl.sushibowl.model.Estado;
import com.sushibowl.sushibowl.model.Restaurante;
import com.sushibowl.sushibowl.repository.RestauranteRepository;


@Service
public class RestauranteService implements ServiceInterface<Restaurante> {
	
	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	public RestauranteService() {}
	
	@Override
	public Restaurante create(Restaurante obj) {
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
		repository.save(obj);
		return obj;
	}
	
	@Override
	public List<Restaurante> findAll(){
		return repository.findAll();
	}
	
	public Page<Restaurante> findAll(Pageable pageable) {
		return repository.findAllOrderByNome(pageable);
	}
	
	public Page<Restaurante> findByNome(Pageable pageable, String pesquisa) {
		return repository.findByNome(pageable, pesquisa);
	}
	
	public Page<Restaurante> findByLogradouro(Pageable pageable, String pesquisa) {
		return repository.findByLogradouro(pageable, pesquisa);
	}
	
	public Page<Restaurante> findByCnpj(Pageable pageable, String cnpj) {
		return repository.findByCnpj(pageable, cnpj);
	}
	
	public Page<Restaurante> findByCep(Pageable pageable, String cep) {
		return repository.findByCep(pageable, cep);
	}
	
	public Page<Restaurante> findByBairro(Pageable pageable, String bairro) {
		return repository.findByBairro(pageable, bairro);
	}
	
	public Page<Restaurante> findByCidade(Pageable pageable, String cidade) {
		return repository.findByCidade(pageable, cidade);
	}
	
	public Page<Restaurante> findByEstado(Pageable pageable, Estado estado) {
		return repository.findByEstado(pageable, estado);
	}
	
	@Override
	public Restaurante findById(Long id) {
		//throws AuthorizationException
		//if (!jwtUtil.authorized(id)) {
			//throw new AuthorizationException("Acesso negado!");
		//}
		Optional<Restaurante> _obj = repository.findById(id);
		return _obj.orElse(null);
	}
	
	@Override
	public boolean update(Restaurante obj) {
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
	
	public static UserDetailsImpl authenticated() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if (auth != null) {
			 return (UserDetailsImpl) auth.getPrincipal();
		 }
		 return null;
	}


}
