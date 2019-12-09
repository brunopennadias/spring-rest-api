package digital.b2w.planets.dto.response;

import java.util.Collection;

public class ListaResponse {

	private Boolean sucesso;
	private Collection<?> list;
	private Integer resultSize = 0;

	public ListaResponse(Collection<?> list) {
		this.sucesso = true;
		this.list = list;
		if(this.list != null) {
			this.resultSize = list.size();
		}
	}

	public Boolean getSucesso() {
		return sucesso;
	}
	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}
	public Collection<?> getList() {
		return list;
	}
	public void setList(Collection<?> list) {
		this.list = list;
	}
	public Integer getResultSize() {
		return resultSize;
	}

	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}
}