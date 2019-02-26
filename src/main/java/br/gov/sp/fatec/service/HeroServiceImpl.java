package br.gov.sp.fatec.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.repository.HeroRepository;


@Service("heroService")
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepo;
	
	@Override
	@Transactional
	public Hero addHeroByName(String name) {
		Hero hero = new Hero();
		hero.setName(name);
		heroRepo.save(hero);
		return hero;
	}

	@Override
	@Transactional
	public Hero addHero(String name, String nameQuirk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addTwoHeros(String nameHero1, String nameHero2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void deleteHero(String name) {
		// TODO Auto-generated method stub
		
	}

}
