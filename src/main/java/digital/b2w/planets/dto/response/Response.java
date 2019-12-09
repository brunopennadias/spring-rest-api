package digital.b2w.planets.dto.response;

public class Response {

	private String mensagem;
	private Boolean sucesso;
	private Object body;
	

	public Response(String mensagem) {
		super();
		this.mensagem = mensagem;
		this.sucesso = true;
		this.body = "";
	}
	
	public Response(Object body) {
		super();
		this.sucesso = true;
		this.body = body;
	}
	
	public Response(String mensagem, Object body) {
		super();
		this.mensagem = mensagem;
		this.sucesso = true;
		this.body = body;
	}
	
	public Response() {
		super();
		this.sucesso = true;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
