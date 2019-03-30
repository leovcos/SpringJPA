package br.gov.sp.fatec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Quirk;

public interface QuirkRepository extends CrudRepository<Quirk, Integer> {
	
	public Quirk findByName(String name);
	public Optional<Quirk> findById(Integer id);
	
	@Query("select q from Quirk q where q.name like %?1%")
	public List<Quirk> findQuirk(String name);
}
