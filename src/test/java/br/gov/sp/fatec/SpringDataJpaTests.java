package br.gov.sp.fatec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Classroom;
import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.model.Quirk;
import br.gov.sp.fatec.repository.ClassroomRepository;
import br.gov.sp.fatec.repository.HeroRepository;
import br.gov.sp.fatec.repository.QuirkRepository;
import br.gov.sp.fatec.service.HeroServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SpringDataJpaTests {

	private static final String NAME = "Usuário Test";

	@Autowired
	private HeroRepository heroRepo;

	@Autowired
	private ClassroomRepository classroomRepository;

	@Autowired
	private QuirkRepository quirkRepository;

	@Autowired
	private HeroServiceImpl heroService;

	public void setUsuarioRepo(HeroRepository usuarioRepo) {
		this.heroRepo = usuarioRepo;
	}

	@Test
	public void testeInsercaoOk() {
		Hero hero = getHero();
		hero.setClassroom(getClassroom());
		hero.setQuirk(getQuirk());
		classroomRepository.save(hero.getClassroom());
		quirkRepository.save(hero.getQuirk());
		heroRepo.save(hero);
		assertTrue(hero.getId() != null);
	}

	@Test
	public void testeFindOk() {
		Hero hero = getHero();
		hero.setClassroom(getClassroom());
		hero.setQuirk(getQuirk());
		classroomRepository.save(hero.getClassroom());
		quirkRepository.save(hero.getQuirk());
		heroRepo.save(hero);
		Hero h = heroRepo.findByName(NAME);
		assertEquals(h.getName(), NAME);
	}

	@Test
	public void testeHeroSaveOk() {
		classroomRepository.save(getClassroom());
		quirkRepository.save(getQuirk());
		Hero h = heroService.addHero(NAME, NAME, NAME);
		assertEquals(h.getName(), NAME);
	}

	@Test
	public void testeFindHeroServiceOk() {
		classroomRepository.save(getClassroom());
		quirkRepository.save(getQuirk());
		Hero h = heroService.addHero(NAME, NAME, NAME);
		Hero foundHero = heroService.findHero(NAME).get(0);
		assertEquals(foundHero.getName(), NAME);

	}

	private Hero getHero() {
		Hero h = new Hero();
		h.setName(NAME);
		h.setQuirk(getQuirk());
		h.setClassroom(getClassroom());
		return h;
	}

	private Quirk getQuirk() {
		Quirk q = new Quirk();
		q.setName(NAME);
		return q;
	}

	private Classroom getClassroom() {
		Classroom c = new Classroom();
		c.setName(NAME);
		return c;
	}
}
