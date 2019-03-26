package br.gov.sp.fatec.repository;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Authority;
import br.gov.sp.fatec.model.Classroom;

public interface AuthorityRepository extends CrudRepository<Authority, Integer>  {
	
	public Authority findByName(String name);
	
}
