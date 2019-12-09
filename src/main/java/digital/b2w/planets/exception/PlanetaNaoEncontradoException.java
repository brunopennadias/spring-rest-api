package digital.b2w.planets.exception;

public class PlanetaNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanetaNaoEncontradoException(String planeta) {
		super(String.format("Planeta %s não encontrado", planeta));
	}
}
