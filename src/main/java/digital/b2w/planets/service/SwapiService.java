package digital.b2w.planets.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import digital.b2w.planets.exception.ValidacaoException;

@Service
public class SwapiService {

	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	public Integer buscarNumeroAparicoesEmFilmes(Long planetaId) {
		HashMap<String, ?> map = restTemplate.getForObject("https://swapi.co/api/planets/" + planetaId, HashMap.class);
		if(map.get("films") == null) return 0;
		
		List<String> films = (List<String>) map.get("films");
		return films.size();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer buscarQuantidadeAparicoesEmFilmes(String nomePlaneta) {
		 HttpHeaders headers = new HttpHeaders();
         headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
         headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
         HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
         
		ResponseEntity<HashMap> response = restTemplate.exchange("https://swapi.co/api/planets?format=json&search=" + nomePlaneta, HttpMethod.GET, entity, HashMap.class);
		if(response.getStatusCode() != HttpStatus.OK) {
			throw new ValidacaoException("Houve um erro para acessar a API STAR WARS");
		}
		
		Integer count = (Integer) response.getBody().get("count");
		
		if(count == 0) {
			throw new ValidacaoException(String.format("Planeta %s nao encontrado na API STAR WARS", nomePlaneta));
		}
		
		if(count > 1) {
			throw new ValidacaoException(String.format("Mais de um planeta encontrado com o nome %s na API STAR WARS", nomePlaneta));
		}
		
		HashMap<String, ?> planetaMap = ((ArrayList<HashMap<String, ?>>) response.getBody().get("results")).get(0);

		if (planetaMap.get("films") == null)
			return 0;

		List<String> films = (List<String>) planetaMap.get("films");
		return films.size();
	}
}
