package br.gov.sp.fatec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.sp.fatec.model.Hero;

public interface HeroService {
	
	public Hero addHero(String name, String nameClassroom, String nameQuirk);
	
	public void deleteHero(String name);
	
	public List<Hero> findHero(String name);

	public Optional<Hero> findHeroById(Integer id);
	
	public Page<Hero> getHeros(Pageable page);
}
