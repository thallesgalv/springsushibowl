package com.sushibowl.sushibowl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sushibowl.sushibowl.model.Estado;
import com.sushibowl.sushibowl.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	Restaurante findByLogin(String login);
	
	@Query("select r from Restaurante r order by r.nome")
	Page<Restaurante> findAllOrderByNome(Pageable pageable);
	
	@Query("select r from Restaurante r where r.nome like %?1%")
	Page<Restaurante> findByNome(Pageable pageable, String pesquisa);
	
	@Query("select r from Restaurante r where r.endereco.logradouro like %?1%")
	Page<Restaurante> findByLogradouro(Pageable pageable, String pesquisa);
	
	@Query("select r from Restaurante r where r.cnpj=?1")
	Page<Restaurante> findByCnpj(Pageable pageable, String cnpj);
	
	@Query("select r from Restaurante r where r.endereco.cep=?1")
	Page<Restaurante> findByCep(Pageable pageable, String cep);
	
	@Query("select r from Restaurante r where r.endereco.bairro=?1")
	Page<Restaurante> findByBairro(Pageable pageable, String bairro);
	
	@Query("select r from Restaurante r where r.endereco.cidade=?1")
	Page<Restaurante> findByCidade(Pageable pageable, String cidade);
	
	@Query("select r from Restaurante r where r.endereco.estado=?1")
	Page<Restaurante> findByEstado(Pageable pageable, Estado estado);
}
