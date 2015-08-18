package py.com.katupyry.bd;

import java.util.TreeMap;

public interface ConectableBD {
	public abstract Object getConexion(String Tipo, TreeMap<String,String> params) throws ParametrosException;
}
