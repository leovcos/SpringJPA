package br.gov.sp.fatec.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.gov.sp.fatec.model.Hero;

public interface HeroRepository extends PagingAndSortingRepository<Hero, Integer> {

	public Hero findByName(String name);
	
	public List<Hero> findByNameContains(String name);
	
	public Hero findTop1ByNameContains(String name);
	
	public List<Hero> findByIdGreaterThan(Long id);
	
	public List<Hero> findByQuirkNameContainsIgnoreCase(String name);
	
	public List<Hero> findByNameContainsIgnoreCaseOrQuirkNameContainsIgnoreCase(String nameHero, String namePower);
	
	@Query("select h from Hero h where h.name like %?1%")
	public List<Hero> findHero(String name);
	
	public Optional<Hero> findById(Integer id);
	
	public Page<Hero> findAll(Pageable pageable);
	
	public Page<Hero> findByNameLike(String name, Pageable pageable);

	public Page<Hero> findByQuirkNameLike(String quirkName, Pageable pageable);

	public Page<Hero> findByNameAndQuirkName(String name, String quirkName, Pageable pageable);
	
}
