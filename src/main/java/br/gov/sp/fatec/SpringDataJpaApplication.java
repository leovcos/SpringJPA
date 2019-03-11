package br.gov.sp.fatec;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.gov.sp.fatec.model.Classroom;
import br.gov.sp.fatec.repository.ClassroomRepository;
import br.gov.sp.fatec.service.ClassroomService;
import br.gov.sp.fatec.service.HeroService;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringDataJpaApplication implements CommandLineRunner {
	
	@Autowired
	private HeroService heroService;
	
	@Autowired
	private ClassroomRepository classroomRepo;
	
	@Autowired
	private ClassroomService classroomService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
	
	public void setUsuarioService(HeroService heroService) {
		this.heroService = heroService;
	}

	@Override
	public void run(String... args) throws Exception {
		//heroService.addHero("Alexandre", "3-C", "Rider");
		
//		ArrayList<Hero> murias = (ArrayList<Hero>) heroService.findHero("Muria");
//		for (Hero hero : murias) {
//			System.out.println(hero.getName());
//		}
		
//		heroService.deleteHero("Teste JPA");
	}
}
