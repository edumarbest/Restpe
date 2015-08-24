package py.com.katupyry.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public interface ConectableBD {
	public abstract Connection getConexion(Hashtable<String,String> params) throws ParametrosException, BDException;

	public abstract Connection getConexion() throws BDException;

	public abstract void conectar(Hashtable<String, String> params) throws ParametrosException, BDException;
	
	public abstract void conectar() throws ParametrosException, BDException;
	
	public abstract ResultSet getConsulta(String sql) throws SQLException;

}
