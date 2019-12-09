package digital.b2w.planets.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import digital.b2w.planets.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String>{

	Planeta findByNomeIgnoreCase(String nome);
	
	boolean existsByNomeIgnoreCase(String nome);

}