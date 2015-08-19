package py.com.katupyry.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
public class ConexionBD implements ConectableBD{
	
	private Connection conn;
	private Hashtable<String, String> params;
	private Hashtable<String, String> drivers;
	
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
			throw new BDException("No hay conexion");
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
			throw new ParametrosException("Parametros nulos");
		}
		if(params.get("URL") == null){
			throw new ParametrosException("Parametro URL nulo");
		}
		if(params.get("BD") == null){
			throw new ParametrosException("Parametro BD nulo");
		}
		
		if(params.get("usuario") == null){
			throw new ParametrosException("Parametro usuario nulo");
		}

		if(params.get("password") == null){
			throw new ParametrosException("Parametro password nulo");
		}

		if(drivers.get(params.get("BD")) == null){
			throw new ParametrosException("No existe driver para BD [" + params.get("BD") + "]");
		}
		
		try {
			Class.forName(drivers.get(params.get("BD")));
		} catch (ClassNotFoundException e) {
			throw new BDException("Error al cargar la clase: " + e.getMessage());
		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(params.get("URL"),params.get("usuario"),params.get("password"));
				//	"jdbc:oracle:thin:@localhost:1521:mkyong", "username",
				//	"password");

		} catch (SQLException e) {
			throw new BDException("Error al obtener conexion: " + e.getMessage());
		}

		if (connection == null) {
			throw new ParametrosException("Conexion nula");
		}		
		
	}

}
