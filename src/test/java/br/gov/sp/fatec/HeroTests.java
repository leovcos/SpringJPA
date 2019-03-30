package br.gov.sp.fatec;

import java.util.Collections;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.gov.sp.fatec.model.Hero;
import br.gov.sp.fatec.security.Login;
import br.gov.sp.fatec.service.HeroService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataJpaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HeroTests {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	private String heroJson = "{\"name\":\"Izuku Midoriya\",\"quirk\":{\"name\":\"One For All\"},\"classroom\":{\"name\":\"Classe 1-A\"},\"image\":\"https://vignette.wikia.nocookie.net/bokunoheroacademia/images/5/55/Midoriyaheadshot.png/revision/latest?cb=20180915213110&path-prefix=pt-br\",\"birthday\":\"2003-07-15T00:00:00.000Z\"}";
	private String heroMissingClassroom = "{\"name\":\"Izuku Midoriya\",\"quirk\":{\"name\":\"One For All\"},\"image\":\"https://vignette.wikia.nocookie.net/bokunoheroacademia/images/5/55/Midoriyaheadshot.png/revision/latest?cb=20180915213110&path-prefix=pt-br\",\"birthday\":\"2003-07-15T00:00:00.000Z\"}";
	private String heroMissingQuirk = "{\"name\":\"Izuku Midoriya\",\"classroom\":{\"name\":\"Classe 1-A\"},\"image\":\"https://vignette.wikia.nocookie.net/bokunoheroacademia/images/5/55/Midoriyaheadshot.png/revision/latest?cb=20180915213110&path-prefix=pt-br\",\"birthday\":\"2003-07-15T00:00:00.000Z\"}";
	private String heroMissingName = "{\"quirk\":{\"name\":\"One For All\"},\"classroom\":{\"name\":\"Classe 1-A\"},\"image\":\"https://vignette.wikia.nocookie.net/bokunoheroacademia/images/5/55/Midoriyaheadshot.png/revision/latest?cb=20180915213110&path-prefix=pt-br\",\"birthday\":\"2003-07-15T00:00:00.000Z\"}";
	private String savedHeroJson = "{\"id\":1,\"name\":\"Izuku Midoriya\",\"image\":\"https://vignette.wikia.nocookie.net/bokunoheroacademia/images/5/55/Midoriyaheadshot.png/revision/latest?cb=20180915213110&path-prefix=pt-br\",\"birthday\":\"2003-07-15T00:00:00.000+0000\",\"quirk\":{\"id\":1,\"name\":\"One For All\"},\"classroom\":{\"id\":1,\"name\":\"Classe 1-A\"}}";
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	@Test
	public void whenPostHeroCreate_withMissingClassroom_thenStatus400() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(heroMissingClassroom, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/hero/create"),
				HttpMethod.POST,
				entity,
				String.class
		);
		JSONAssert.assertEquals("{\"message\":\"Campo sala ('classroom') vazio\"}", response.getBody(), true);
	}
	
	@Test
	public void whenPostHeroCreate_withMissingQuirk_thenStatus400() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(heroMissingQuirk, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/hero/create"),
				HttpMethod.POST,
				entity,
				String.class
		);
		JSONAssert.assertEquals("{\"message\":\"Campo poder ('quirk') vazio\"}", response.getBody(), true);
	}

	@Test
	public void whenPostHeroCreate_withMissingName_thenStatus400() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(heroMissingName, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/hero/create"),
				HttpMethod.POST,
				entity,
				String.class
		);
		JSONAssert.assertEquals("{\"message\":\"Campo nome ('name') vazio\"}", response.getBody(), true);
	}
	
	@Test
	public void whenPostHeroCreate_withValidHero_thenStatus200() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(heroJson, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/hero/create"),
				HttpMethod.POST,
				entity,
				String.class
		);
		JSONAssert.assertEquals(savedHeroJson, response.getBody(), true);
	}
}
