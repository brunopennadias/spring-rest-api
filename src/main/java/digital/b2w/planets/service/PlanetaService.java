package digital.b2w.planets.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import digital.b2w.planets.dto.request.PlanetaDTO;
import digital.b2w.planets.exception.PlanetaNaoEncontradoException;
import digital.b2w.planets.exception.ValidacaoException;
import digital.b2w.planets.model.Planeta;
import digital.b2w.planets.repository.PlanetaRepository;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private SwapiService swapiService;

	public Planeta adicionar(PlanetaDTO dto) {
		validar(dto);
		
		Planeta planeta = new Planeta();
		planeta.setNome(dto.getNome());
		planeta.setClima(dto.getClima());
		planeta.setTerreno(dto.getTerreno());
		
		Integer quantidadeAparicoesEmFilmes = swapiService.buscarQuantidadeAparicoesEmFilmes(dto.getNome());
		planeta.setQuantidadeAparicoesEmFilmes(quantidadeAparicoesEmFilmes);
		
		planetaRepository.save(planeta);
		
		return planeta;
	}

	public List<Planeta> listar() {
		return planetaRepository.findAll();
	}

	public Planeta buscarPorNome(String nome) {
		Planeta planeta = planetaRepository.findByNomeIgnoreCase(nome);
		
		if(planeta == null) {
			throw new PlanetaNaoEncontradoException(nome);
		}
		
		return planeta;
	}

	public Planeta buscarPorId(String planetaId) {
		Optional<Planeta> optional = planetaRepository.findById(planetaId);
		
		if(!optional.isPresent()) {
			throw new PlanetaNaoEncontradoException(planetaId);
		}
		
		return optional.get();
	}

	public void remover(String planetaId) {
		Planeta planeta = buscarPorId(planetaId);
		planetaRepository.delete(planeta);
	}
	
	/*
	private PlanetaResponse entityToDTO(Planeta planeta) {
		PlanetaResponse res = new PlanetaResponse();
		res.setId(planeta.getId());
		res.setNome(planeta.getNome());
		res.setClima(planeta.getClima());
		res.setTerreno(planeta.getTerreno());
		res.setQuantidadeAparicoesEmFilmes(planeta.getQuantidadeAparicoesEmFilmes());
		return res;
	}*/
	
	private void validar(PlanetaDTO dto) {
		if(StringUtils.isBlank(dto.getNome())) {
			throw new ValidacaoException("Nome é obrigatório");
		}
		
		if(StringUtils.isBlank(dto.getClima())) {
			throw new ValidacaoException("Clima é obrigatório");
		}
		
		if(StringUtils.isBlank(dto.getTerreno())) {
			throw new ValidacaoException("Terreno é obrigatório");
		}
		
		if(planetaRepository.existsByNomeIgnoreCase(dto.getNome())) {
			throw new ValidacaoException(String.format("Já existe um planeta cadastrado com o nome %s", dto.getNome()));
		}
	}	

}
