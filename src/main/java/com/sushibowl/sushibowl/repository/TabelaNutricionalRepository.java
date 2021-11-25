package com.sushibowl.sushibowl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sushibowl.sushibowl.model.TabelaNutricional;

@Repository
public interface TabelaNutricionalRepository extends JpaRepository<TabelaNutricional, Long> {
	
	@Query("select max(tn.cals) from TabelaNutricional tn")
	Float findByMaxCals();
	
	@Query("select min(tn.cals) from TabelaNutricional tn")
	Float findByMinCals();
	
	@Query("select max(tn.gord) from TabelaNutricional tn")
	Float findByMaxGord();
	
	@Query("select min(tn.gord) from TabelaNutricional tn")
	Float findByMinGord();
	
	@Query("select max(tn.carbs) from TabelaNutricional tn")
	Float findByMaxCarbs();
	
	@Query("select min(tn.carbs) from TabelaNutricional tn")
	Float findByMinCarbs();
	
	@Query("select max(tn.prot) from TabelaNutricional tn")
	Float findByMaxProt();
	
	@Query("select min(tn.prot) from TabelaNutricional tn")
	Float findByMinProt();
}
