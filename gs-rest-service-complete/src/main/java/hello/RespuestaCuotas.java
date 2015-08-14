package hello;

import java.util.List;

public class RespuestaCuotas {
	private int codigo;
	private String descripcion;
	private List<Cuota> lista;
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Cuota> getLista() {
		return lista;
	}
	public void setLista(List<Cuota> lista) {
		this.lista = lista;
	}
	

}
