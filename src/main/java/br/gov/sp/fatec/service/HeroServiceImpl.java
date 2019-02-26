package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Classroom;
import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.model.Quirk;
import br.gov.sp.fatec.repository.ClassroomRepository;
import br.gov.sp.fatec.repository.HeroRepository;
import br.gov.sp.fatec.repository.QuirkRepository;


@Service("heroService")
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepo;

	@Autowired
	private ClassroomRepository classroomRepo;

	@Autowired
	private QuirkRepository quirkRepo;

	@Override
	@Transactional
	public Hero addHero(String name, String nameClassroom, String nameQuirk) {
		Classroom classroom = classroomRepo.findByName(nameClassroom);
		Quirk quirk = quirkRepo.findByName(nameQuirk);
		
		if(classroom == null) {
			classroom = new Classroom();
			classroom.setName(nameClassroom);
			classroomRepo.save(classroom);
		}
		if(quirk == null) {
			quirk = new Quirk();
			quirk.setName(nameQuirk);
			quirkRepo.save(quirk);
		}
		
		Hero hero = new Hero();
		hero.setName(name);
		hero.setQuirk(quirk);
		hero.setClassroom(classroom);
		heroRepo.save(hero);
		
		return hero;
	}

	@Override
	public List<Hero> findHero(String name) {
		return heroRepo.findHero(name);
	}

	@Override
	@Transactional
	public void deleteHero(String name) {
		// TODO Auto-generated method stub
		
	}

}
