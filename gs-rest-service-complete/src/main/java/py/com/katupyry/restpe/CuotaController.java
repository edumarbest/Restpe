package py.com.katupyry.restpe;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.com.katupyry.bd.BDException;
import py.com.katupyry.bd.ConexionBD;
import py.com.katupyry.bd.ParametrosException;
import py.com.katupyry.errores.KyErrorsKeys;
import py.com.katupyry.errores.KyMensajesError;
import scala.annotation.meta.getter;

@RestController
public class CuotaController implements KyErrorsKeys{
	
	private ConexionBD conn;
	
	CuotaController() throws BDException{
		conn = new ConexionBD();
		try {
			conn.conectar();
		} catch (ParametrosException | BDException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_FILE_NOT_FOUND, "hola"));
		}
	}

    @RequestMapping("/consulta")
    public RespuestaCuotas consulta(@RequestParam(value="name", defaultValue="World") String name) {
    	RespuestaCuotas resp = new RespuestaCuotas();
    	try {
			Statement stmt = conn.getConexion().createStatement();
		} catch (SQLException | BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.setCodigo(99);
			resp.setDescripcion(KyMensajesError.mensajeFormateado(MSG_FILE_NOT_FOUND, "hola"));
		}finally {
	        return new RespuestaCuotas(99, "Error inesperado");
		}
    }
    @RequestMapping("/pago")
    public Respuesta pago(@RequestParam(value="name", defaultValue="World") String name) {
        return new Respuesta(99, "Error inesperado");
    }
    @RequestMapping("/reversa")
    public Respuesta reversa(@RequestParam(value="name", defaultValue="World") String name) {
        return new Respuesta(99, "Error inesperado");
    }
}
