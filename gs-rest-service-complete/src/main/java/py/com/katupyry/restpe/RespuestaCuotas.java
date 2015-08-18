package py.com.katupyry.restpe;

import java.util.List;

public class RespuestaCuotas extends Respuesta{
	private List<Cuota> lista;
	
	RespuestaCuotas(){
		super();
	}

	RespuestaCuotas(int pCodigo, String pDescripcion){
		super(pCodigo, pDescripcion);
	}
	
	public List<Cuota> getLista() {
		return lista;
	}
	
	public void setLista(List<Cuota> lista) {
		this.lista = lista;
	}
	

}
