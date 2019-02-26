package br.gov.sp.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.repository.HeroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SpringDataJpaTests {

	private static final String NAME = "Usuário Test";
	private static final String PASSWORD = "Test";
	
	@Autowired
	private HeroRepository heroRepo;
	
	public void setUsuarioRepo(HeroRepository usuarioRepo) {
		this.heroRepo = usuarioRepo;
	}

	@Test
	public void testeInsercaoOk() {
		Hero hero = new Hero();
		hero.setName(NAME);
		heroRepo.save(hero);
		assertTrue(hero.getId() != null);
	}

}
