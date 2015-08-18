package py.com.katupyry.restpe;

import java.util.List;

public class Respuesta implements Respondible{
	private int codigo;
	private String descripcion;
	
	Respuesta(){
	}

	Respuesta(int pCodigo, String pDescripcion){
		setCodigo(pCodigo);
		setDescripcion(pDescripcion);
	}
	
	@Override
	public int getCodigo() {
		return codigo;
	}
	
	@Override
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String getDescripcion() {
		return descripcion;
	}
	
	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
