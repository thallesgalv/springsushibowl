package com.sushibowl.sushibowl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sushibowl.sushibowl.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("select p from Produto p order by p.name")
	Page<Produto> findAllOrderByNome(Pageable pageable);
	
	@Query("select p from Produto p where p.categoria.id=?1")
	Page<Produto> findByCategoria(Pageable pageable, Long categoriaId);
	
	@Query("select p from Produto p where p.name like %?1% or p.ingredientes like %?1%")
	Page<Produto> findByPesquisa(Pageable pageable, String pesquisa);
	
	@Query("select p from Produto p inner join p.restaurantes r where r.id=?1")
	Page<Produto> findByRestaurante(Pageable pageable, Long restauranteId);
	
	@Query("select p from Produto p where p.tabelaNutricional.cals between ?1 and ?2")
	Page<Produto> findByCalsBetween(Pageable pageable, Float from, Float to);
	
	@Query("select p from Produto p where p.tabelaNutricional.gord between ?1 and ?2")
	Page<Produto> findByGordBetween(Pageable pageable, Float from, Float to);
	
	@Query("select p from Produto p where p.tabelaNutricional.carbs between ?1 and ?2")
	Page<Produto> findByCarbsBetween(Pageable pageable, Float from, Float to);
	
	@Query("select p from Produto p where p.tabelaNutricional.prot between ?1 and ?2")
	Page<Produto> findByProtBetween(Pageable pageable, Float from, Float to);
	
}
