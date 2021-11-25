package com.sushibowl.sushibowl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sushibowl.sushibowl.config.UserDetailsImpl;
import com.sushibowl.sushibowl.model.Restaurante;
import com.sushibowl.sushibowl.repository.RestauranteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private RestauranteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Restaurante restaurante = repo.findByLogin(username);
		if (restaurante == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(restaurante.getId(),	restaurante.getLogin(), restaurante.getSenha(),	restaurante.getPerfis());
	}
}
