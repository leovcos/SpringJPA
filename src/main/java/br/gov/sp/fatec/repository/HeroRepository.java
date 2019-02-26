package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Hero;

public interface HeroRepository extends CrudRepository<Hero, Long> {

	public Hero findByName(String name);
	
	public List<Hero> findByNameContains(String name);
	
	public Hero findTop1ByNameContains(String name);
	
	public List<Hero> findByIdGreaterThan(Long id);
	
	public List<Hero> findByQuirkNameContainsIgnoreCase(String name);
	
	public List<Hero> findByNameContainsIgnoreCaseOrQuirkNameContainsIgnoreCase(String nameHero, String namePower);
	
	@Query("select h from Hero h where h.name like %?1%")
	public List<Hero> findHero(String name);
}
