package py.com.katupyry.restpe.dao;

import py.com.katupyry.restpe.dao.impl.oracle.PPagoCuotasDAOOracleImpl;

public class DAOFactory {
	
	public static PPagoCuotasDAO getPPagoCuotasDAO() {
		return new PPagoCuotasDAOOracleImpl();
	}

}
