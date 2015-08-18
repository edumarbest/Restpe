package py.com.katupyry.bd;

import java.util.TreeMap;

public class ConexionBD implements ConectableBD{

	@Override
	public Object getConexion(String Tipo, TreeMap<String, String> params) throws ParametrosException {
		if(params.get("URL") == null){
			throw new ParametrosException("Parametro URL nulo");
		}
		return null;
	}

}
