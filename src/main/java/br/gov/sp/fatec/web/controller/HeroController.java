package br.gov.sp.fatec.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	@JsonView(View.HeroComplete.class)
	public ResponseEntity<?>  createHero(@RequestBody Hero hero) {
		try {
			hero = heroService.save(hero);
		} catch (Exception e) {
			Map<String,String> errorResponse = new HashMap<String, String>();
			errorResponse.put("message", e.getMessage());
			return new ResponseEntity<Map<String,String>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	    return new ResponseEntity<Hero>(hero, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(View.HeroShort.class)
	public ResponseEntity<Page<Hero>> findPaginated(@RequestParam(value="quirkName", required=false) String quirkName, @RequestParam(value="name", required=false) String name, Pageable pageable) {
		Page<Hero> resultPage = null;
		if (name == null && quirkName == null) {
			resultPage = heroService.getHeros(pageable);
		} else if (name != null && quirkName != null) {
			resultPage = heroService.getHerosByNameAndQuirkName(name, quirkName, pageable);
		} else if (name != null && quirkName == null) {
			resultPage = heroService.getHerosByName(name, pageable);
		} else {
			resultPage = heroService.getHerosByQuirkName(quirkName, pageable);
		}
	    return new ResponseEntity<Page<Hero>>(resultPage, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@JsonView(View.HeroCompleteExceptId.class)
	public ResponseEntity<Hero> searchById(@PathVariable("id") Integer id) {
		Hero hero = null;
		try {
			hero = heroService.findHeroById(id).get();
			hero.getClassroom();
			hero.getQuirk();
		} catch (Exception e) {
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Hero>(hero, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@JsonView(View.HeroCompleteExceptId.class)
	public ResponseEntity<Hero> deleteById(@PathVariable("id") Integer id) {
		try {
			heroService.deleteHero(id);
		} catch (Exception e) {
			return new ResponseEntity<Hero>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Hero>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/increase-power/{id}", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	@JsonView(View.HeroComplete.class)
	public ResponseEntity<?>  increaseHeroPower(@PathVariable("id") Integer id) {
		try {
			Hero hero = heroService.findHeroById(id).get();
			heroService.increasePowerById(hero);
		} catch (Exception e) {
			Map<String,String> errorResponse = new HashMap<String, String>();
			errorResponse.put("message", e.getMessage());
			return new ResponseEntity<Map<String,String>>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	    return new ResponseEntity<Hero>(HttpStatus.OK);
	}
}
