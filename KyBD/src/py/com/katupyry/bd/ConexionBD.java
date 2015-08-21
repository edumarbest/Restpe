package py.com.katupyry.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import py.com.katupyry.errores.KyErrorsKeys;
import py.com.katupyry.errores.KyMensajesError;
public class ConexionBD implements ConectableBD, KyErrorsKeys{
	
	private Connection conn;
	private Hashtable<String, String> params;
	private Hashtable<String, String> drivers;
	private final String PARAM_URL = "URL";
	private final String PARAM_BD = "BD";
	private final String PARAM_USUARIO = "usuario";
	private final String PARAM_PASSWORD = "password";
	
	public ConexionBD(){
		drivers.put("oracle", "oracle.jdbc.driver.OracleDriver");
	}
	
	public Connection getConexion(Hashtable<String, String> params) throws ParametrosException, BDException{
		if(conn == null){
			conectar(params);
			this.params = params;
			return conn;
		}else{
			return conn;
		}
		
	}

	public Connection getConexion() throws BDException{
		if(conn == null){
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_CONN_NULL));
		}else{
			return conn;
		}
		
	}

	public void conectar(Hashtable<String, String> params) throws ParametrosException, BDException {
		this.params = params;
		this.conectar(params);
	}
	
	@Override
	public void conectar() throws ParametrosException, BDException {
		if(params == null){
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAMS_NULLS));
		}
		if(params.get(PARAM_URL) == null){
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL,PARAM_URL));
		}
		if(params.get(PARAM_BD) == null){
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL,PARAM_BD));
		}
		
		if(params.get(PARAM_USUARIO) == null){
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL,PARAM_USUARIO));
		}

		if(params.get(PARAM_PASSWORD) == null){
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL,PARAM_PASSWORD));
		}

		if(drivers.get(params.get("BD")) == null){
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_NO_DRIVER,params.get("BD")));
		}
		
		try {
			Class.forName(drivers.get(params.get("BD")));
		} catch (ClassNotFoundException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_CARGAR_CLASE,e.getMessage()));
		}

		try {

			conn = DriverManager.getConnection(params.get("URL"),params.get("usuario"),params.get("password"));
				//	"jdbc:oracle:thin:@localhost:1521:mkyong", "username",
				//	"password");

		} catch (SQLException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN,e.getMessage()));
		}

		try {

			conn = DriverManager.getConnection(params.get("URL"),params.get("usuario"),params.get("password"));
				//	"jdbc:oracle:thin:@localhost:1521:mkyong", "username",
				//	"password");

		} catch (SQLException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN, e.getMessage()));
		}

		if (conn == null) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_CONN_NULL));
		}		
		
	}
	
	public ResultSet getConsulta(String sql) throws SQLException{
		Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        return stmt.executeQuery(sql);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	    }
	}

}
