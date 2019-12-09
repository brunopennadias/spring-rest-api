package digital.b2w.planets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.b2w.planets.dto.request.PlanetaDTO;
import digital.b2w.planets.dto.response.ListaResponse;
import digital.b2w.planets.dto.response.Response;
import digital.b2w.planets.model.Planeta;
import digital.b2w.planets.service.PlanetaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping(produces = "application/json")
@Api(authorizations = { @Authorization(value = "Authorization") })
public class PlanetaController {
	
	@Autowired
	private PlanetaService planetaService;

	@PostMapping("/planetas")
	public ResponseEntity<Planeta> adicionar(@RequestBody PlanetaDTO dto) {
		Planeta planeta = planetaService.adicionar(dto);
		
		return new ResponseEntity<Planeta>(planeta, HttpStatus.CREATED);
	}
	
	@GetMapping("/planetas")
	public ResponseEntity<ListaResponse> listar() {
		List<Planeta> planetas = planetaService.listar();
		
		return new ResponseEntity<ListaResponse>(new ListaResponse(planetas), HttpStatus.OK);
	}
	
	@GetMapping("/planetas/nome/{nome}")
	public ResponseEntity<Planeta> buscarPorNome(@PathVariable("nome") String nome) {
		Planeta planeta = planetaService.buscarPorNome(nome);
		
		return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
	}
	
	@GetMapping("/planetas/{planetaId}")
	public ResponseEntity<Planeta> buscarPorId(@PathVariable("planetaId") String planetaId) {
		Planeta planeta = planetaService.buscarPorId(planetaId);
		
		return new ResponseEntity<Planeta>(planeta, HttpStatus.OK);
	}
	
	@DeleteMapping("/planetas/{planetaId}")
	public ResponseEntity<Response> remover(@PathVariable("planetaId") String planetaId) {
		planetaService.remover(planetaId);
		
		return new ResponseEntity<Response>(new Response(String.format("Planeta %s removido com sucesso", planetaId)),
				HttpStatus.OK);
	}
}
