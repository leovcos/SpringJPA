package br.gov.sp.fatec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public Hero save(Hero hero) throws Exception {
		if (hero.getName() == null || hero.getName().trim() == "") throw new Exception("Campo nome ('name') vazio");
		if (hero.getClassroom() == null) throw new Exception("Campo sala ('classroom') vazio");
		if (hero.getQuirk() == null) throw new Exception("Campo poder ('quirk') vazio");

		hero.setName(hero.getName().trim());
		Classroom classroom = null;
		Quirk quirk = null;
		
		try {
			if (hero.getClassroom().getId() == null) throw new Exception();
			classroom = classroomRepo.findById(hero.getClassroom().getId()).get();
		} catch (Exception e) {
			if (hero.getClassroom().getName() == null) {
				throw new Exception("Sala não encontrada");
			}
			classroom = classroomRepo.findByName(hero.getClassroom().getName());
			if (classroom == null) {
				classroom = new Classroom();
				classroom.setName(hero.getClassroom().getName());
				classroomRepo.save(classroom);	
			}
		}
		
		try {
			if (hero.getQuirk().getId() == null) throw new Exception();
			quirk = quirkRepo.findById(hero.getQuirk().getId()).get();
		} catch (Exception e) {
			if (hero.getQuirk().getName() == null) {
				throw new Exception("Poder não encontrado");
			}
			quirk = quirkRepo.findByName(hero.getQuirk().getName());
			if (quirk == null) {
				quirk = new Quirk();
				quirk.setName(hero.getQuirk().getName());
				quirkRepo.save(quirk);	
			}
		}
		
		hero.setClassroom(classroom);
		hero.setQuirk(quirk);

		return heroRepo.save(hero);
	}

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
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteHero(Integer id) {
		Optional<Hero> heroOptional = heroRepo.findById(id);
		Hero hero = heroOptional.get();
		heroRepo.delete(hero);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Optional<Hero> findHeroById(Integer id) {
		return heroRepo.findById(id);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Page<Hero> getHeros(Pageable pageable) {
        return heroRepo.findAll(pageable);
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Page<Hero> getHerosByName(String name, Pageable pageable) {
        return heroRepo.findByNameLike(name, pageable);
	}

	public Page<Hero> getHerosByQuirkName(String quirkName, Pageable pageable) {
        return heroRepo.findByQuirkNameLike(quirkName, pageable);
	}

	public Page<Hero> getHerosByNameAndQuirkName(String name, String quirkName, Pageable pageable) {
        return heroRepo.findByNameAndQuirkName(name, quirkName, pageable);
	}

	@Override
	public void increasePowerById(Hero hero) {
		hero.setPower(hero.getPower() + 1);
		heroRepo.save(hero);
	}

}
