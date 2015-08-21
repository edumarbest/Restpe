package py.com.katupyry.restpe;

import java.sql.ResultSet;
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
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN));
		}
	}

    @RequestMapping("/consulta")
    public RespuestaCuotas consulta(@RequestParam(value="name", defaultValue="World") String name) {
    	RespuestaCuotas resp = new RespuestaCuotas();
    	
    	String sql = "";
    	try {
			ResultSet rs = conn.getConsulta(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setCodigo(99);
			resp.setDescripcion(KyMensajesError.mensajeFormateado(MSG_ERROR_QUERY, sql));
		}finally {
	        return new RespuestaCuotas(99, KyMensajesError.mensajeFormateado(MSG_ERROR_INESPERADO));
		}
    }
    @RequestMapping("/pago")
    public Respuesta pago(@RequestParam(value="name", defaultValue="World") String name) {
        return new Respuesta(99, KyMensajesError.mensajeFormateado(MSG_ERROR_INESPERADO));
    }
    @RequestMapping("/reversa")
    public Respuesta reversa(@RequestParam(value="name", defaultValue="World") String name) {
        return new Respuesta(99, KyMensajesError.mensajeFormateado(MSG_ERROR_INESPERADO));
    }
}
