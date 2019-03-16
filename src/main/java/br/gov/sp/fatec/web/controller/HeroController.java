package br.gov.sp.fatec.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.service.HeroServiceImpl;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/hero")
public class HeroController {
	
	@Autowired
	private HeroServiceImpl heroService;

	public void setUsuarioService(HeroServiceImpl heroService) {
		this.heroService = heroService;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<Hero>> findPaginated(Pageable pageable) {
		Page<Hero> resultPage = heroService.getHeros(pageable);
	    return new ResponseEntity<Page<Hero>>(resultPage, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@JsonView(View.HeroComplete.class)
	public ResponseEntity<Hero> searchById(@PathVariable("id") Integer id) {
		Optional<Hero> optionalHero = heroService.findHeroById(id);
		Hero hero = optionalHero.get();
		if(hero == null) {
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Hero>(hero, HttpStatus.OK);
	}	
}
