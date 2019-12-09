package digital.b2w.planets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planets")
public class Planeta {

	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private Integer quantidadeAparicoesEmFilmes;
	
	public Planeta() {}
	
	public Planeta(String id, String nome, String clima, String terreno, Integer quantidadeAparicoesEmFilmes) {
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.quantidadeAparicoesEmFilmes = quantidadeAparicoesEmFilmes;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public Integer getQuantidadeAparicoesEmFilmes() {
		return quantidadeAparicoesEmFilmes;
	}
	public void setQuantidadeAparicoesEmFilmes(Integer quantidadeAparicoesEmFilmes) {
		this.quantidadeAparicoesEmFilmes = quantidadeAparicoesEmFilmes;
	}
}