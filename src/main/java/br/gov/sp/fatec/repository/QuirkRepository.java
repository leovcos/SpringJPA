package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.model.Quirk;

public interface QuirkRepository extends CrudRepository<Quirk, Long> {
	
	public Quirk findByName(String name);
	
	@Query("select q from Quirk q where q.name like %?1%")
	public List<Quirk> findQuirk(String name);
}
