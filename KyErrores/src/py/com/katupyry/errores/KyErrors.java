package py.com.katupyry.errores;

import java.util.ListResourceBundle;

public class KyErrors  extends ListResourceBundle implements KyErrorsKeys {
	public Object[][] getContents() {
		return contents;
	}
	  
	static final Object[][] contents = {
			{MSG_OBT_CONN, "Error al obtener conexion: {1}"},
			{MSG_CONN_NULL, "No hay conexion"},
			{MSG_PARAM_NULL, "Parametro {1} nulo"},
			{MSG_PARAMS_NULLS, "Parametros nulos"},
			{MSG_NO_DRIVER, "No existe driver para BD {1}"},
			{MSG_CARGAR_CLASE, "Error al cargar la clase {1} "},
			{MSG_ERROR_QUERY, "Error al ejecutar consulta: {1}"},
			{MSG_ERROR_INESPERADO, "Error inesperado"},
	};
}
