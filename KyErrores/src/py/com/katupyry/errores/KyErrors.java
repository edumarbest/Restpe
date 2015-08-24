package py.com.katupyry.errores;

import java.util.ListResourceBundle;

public class KyErrors  extends ListResourceBundle implements KyErrorsKeys {
	public Object[][] getContents() {
		return contents;
	}
	  
	static final Object[][] contents = {
			{MSG_OBT_CONN, "Error al obtener conexion"},
			{MSG_CONN_NULL, "No hay conexion"},
			{MSG_PARAM_NULL, "Parametro nulo"},
			{MSG_PARAMS_NULLS, "Parametros nulos"},
			{MSG_NO_DRIVER, "No existe driver para BD"},
			{MSG_CARGAR_CLASE, "Error al cargar la clase"},
			{MSG_ERROR_QUERY, "Error al ejecutar consulta"},
			{MSG_ERROR_INESPERADO, "Error inesperado"},
	};
}
