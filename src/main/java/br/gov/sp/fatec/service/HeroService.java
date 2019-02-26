package br.gov.sp.fatec.service;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Hero;

public interface HeroService {
	
	public Hero addHeroByName(String name);
	
	public Hero addHero(String name, String nameQuirk);
	
	public void addTwoHeros(String nameHero1, String nameHero2);
	
	public void deleteHero(String name);

}
