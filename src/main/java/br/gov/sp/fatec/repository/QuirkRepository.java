package br.gov.sp.fatec.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Quirk;

public interface QuirkRepository extends CrudRepository<Quirk, Long> {
	
	public Quirk findByName(String name);

}
