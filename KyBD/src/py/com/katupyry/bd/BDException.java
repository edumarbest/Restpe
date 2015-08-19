package py.com.katupyry.bd;

public class BDException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4797192718075684986L;

	BDException(String mensaje){
		super(mensaje);
	}
}
