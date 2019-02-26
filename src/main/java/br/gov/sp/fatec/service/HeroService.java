package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Hero;

public interface HeroService {
	
	public Hero addHero(String name, String nameClassroom, String nameQuirk);
	
	public void deleteHero(String name);
	
	public List<Hero> findHero(String name);

}
