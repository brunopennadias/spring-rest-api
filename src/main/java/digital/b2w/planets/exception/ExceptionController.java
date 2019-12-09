package digital.b2w.planets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import digital.b2w.planets.dto.response.Response;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler({ PlanetaNaoEncontradoException.class })
	public ResponseEntity<Response> handlePlanetaNaoEncontradoException(PlanetaNaoEncontradoException e) {
		Response res = new Response();
		res.setSucesso(Boolean.FALSE);
		res.setMensagem(e.getMessage());
		return new ResponseEntity<Response>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ ValidacaoException.class })
	public ResponseEntity<Response> handleValidacaoException(ValidacaoException e) {
		Response res = new Response();
		res.setSucesso(Boolean.FALSE);
		res.setMensagem(e.getMessage());
		return new ResponseEntity<Response>(res, HttpStatus.PRECONDITION_FAILED);
	}

}