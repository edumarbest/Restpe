package py.com.katupyry.errores;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class KyMensajesError {
		  private static ResourceBundle misRecursos =
		    ResourceBundle.getBundle("py.com.katupyry.errores.KyErrors");
		  private static String getMensaje(String messageKey) {
		    return misRecursos.getString(messageKey);
		  }
		  public static String mensajeFormateado(String messageKey) {
		    MessageFormat mf = new MessageFormat(getMensaje(messageKey));
		    return mf.format(new Object[0]);
		  }
		  public static String mensajeFormateado(String messageKey, 
		                                     Object arg0) {
		    MessageFormat mf = new MessageFormat(getMensaje(messageKey));
		    Object[] args = new Object[1];
		    args[0] = arg0;
		    return mf.format(args);
		  }
		  public static String mensajeFormateado(String messageKey, 
		                                     Object arg0,
		                                     Object arg1) {
		    MessageFormat mf = new MessageFormat(getMensaje(messageKey));
		    Object[] args = new Object[2];
		    args[0] = arg0;
		    args[1] = arg1;
		    return mf.format(args);
		  }
		  // Include implementations of formatMessage() for as many arguments
		  // as you need
}
