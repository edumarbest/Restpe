package py.com.katupyry.restpe;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.com.katupyry.bd.BDException;
import py.com.katupyry.bd.ConexionBD;
import py.com.katupyry.bd.ParametrosException;
import py.com.katupyry.errores.KyErrorsKeys;
import py.com.katupyry.errores.KyMensajesError;
import py.com.katupyry.restpe.dao.DAOFactory;
import py.com.katupyry.restpe.dao.PPagoCuotasDAO;
import py.com.katupyry.restpe.dto.PPagoCuotasDTO;
import scala.annotation.meta.getter;
import scala.collection.mutable.HashTable;

@RestController
public class CuotaController implements KyErrorsKeys{
	
	private final int CR_ERROR_INESPERADO = 99;
	
	private ConexionBD conn;
	
/*	CuotaController() throws BDException{
		conn = new ConexionBD();
		try {
			conn.conectar();
		} catch (ParametrosException | BDException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN));
		}
	}*/

    @RequestMapping("/consulta")
    public RespuestaCuotas consulta(@RequestParam(value="parametros") Hashtable<String, String> parametros) {
    	RespuestaCuotas resp = null;
    	
    	PPagoCuotasDAO pcMan = DAOFactory.getPPagoCuotasDAO();

    	Integer codigoTrans = null;
    	try{
    		codigoTrans = Integer.valueOf(parametros.get("codigoTrans"));
    	}catch(Exception e){
	        resp = new RespuestaCuotas(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": codigoTrans"));
    	}
    	
    	
    	try {
			pcMan.obtenerCuotas(parametros.get("documento"), codigoTrans);
		} catch (BDException | ParametrosException e) {
			e.printStackTrace();
			resp = new RespuestaCuotas();
			resp.setCodigo(CR_ERROR_INESPERADO);
			resp.setDescripcion(KyMensajesError.mensajeFormateado(MSG_OBTENER_CUOTAS + " 0004"));
		}finally {
			
			if(resp == null)
		        resp = new RespuestaCuotas(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_ERROR_INESPERADO));
		}
		return resp;
    	
    }
    @RequestMapping("/pago")
    public Respuesta pago(@RequestParam(value="parametros") Hashtable<String, String> parametros) {
    	Respuesta resp = null;
    	
    	try {
	    	PPagoCuotasDAO pcMan = DAOFactory.getPPagoCuotasDAO();
	    	
	    	PPagoCuotasDTO pc = new PPagoCuotasDTO();
	    	
	    	/* p_fraccion IN NUMBER
	                   ,p_manzana IN NUMBER
	                   ,p_lote    IN NUMBER
	                   ,p_cobrado_cuota IN NUMBER
	                   ,p_cobrado_mora  in number
	                   ,p_codigo_trans 
	                   */
	    	try{
	    		pc.setIdFraccion(Integer.valueOf(parametros.get("idFraccion")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": idFraccion"));
		        throw e;
	    	}
	    	try{
	    		pc.setIdManzana(Integer.valueOf(parametros.get("idManzana")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": idManzana"));
		        throw e;
	    	}
	    	try{
	    		pc.setIdLote(Integer.valueOf(parametros.get("idLote")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": idLote"));
		        throw e;
	    	}
	    	try{
	    		pc.setMontoCuota(new BigDecimal(parametros.get("montoCuota")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": montoCuota"));
		        throw e;
	    	}
	    	try{
	    		pc.setMontoMora(new BigDecimal(parametros.get("montoMora")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": montoMora"));
		        throw e;
	    	}
	    	try{
	    		pc.setCodigoTrans(Integer.valueOf(parametros.get("codigoTrans")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": codigoTrans"));
		        throw e;
	    	}
	    	
	    	try{
				pcMan.pagarCuota(pc);

				resp = new Respuesta();
				resp.setCodigo(pc.getCodigoRetorno());
				resp.setDescripcion(pc.getDescRetorno());
			} catch (BDException | ParametrosException e) {
				e.printStackTrace();
				resp = new Respuesta();
				resp.setCodigo(CR_ERROR_INESPERADO);
				resp.setDescripcion(KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + " 0004"));
			}	
		}finally {
			
			if(resp == null)
		        resp = new Respuesta(99, KyMensajesError.mensajeFormateado(MSG_ERROR_INESPERADO));
		}
		return resp;
    	
    }
    @RequestMapping("/reversa")
    public Respuesta reversa(@RequestParam(value="parametros") Hashtable<String, String> parametros) {
    	Respuesta resp = null;
    	
    	try {
	    	PPagoCuotasDAO pcMan = DAOFactory.getPPagoCuotasDAO();
	    	
	    	PPagoCuotasDTO pc = new PPagoCuotasDTO();
	    	
	    	/* p_fraccion IN NUMBER
	                   ,p_manzana IN NUMBER
	                   ,p_lote    IN NUMBER
	                   ,p_cobrado_cuota IN NUMBER
	                   ,p_cobrado_mora  in number
	                   ,p_codigo_trans 
	                   */
	    	try{
	    		pc.setCodigoTrans(Integer.valueOf(parametros.get("codigoTrans")));
	    	}catch(Exception e){
		        resp = new Respuesta(CR_ERROR_INESPERADO, KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + ": codigoTrans"));
		        throw e;
	    	}
	    	
	    	try{
				pcMan.anularCuota(pc);
				
				resp = new Respuesta();
				resp.setCodigo(pc.getCodigoRetorno());
				resp.setDescripcion(pc.getDescRetorno());
			} catch (ParametrosException e) {
				e.printStackTrace();
				resp = new Respuesta();
				resp.setCodigo(CR_ERROR_INESPERADO);
				resp.setDescripcion(KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + " 0004"));
			} catch (BDException e) {
				e.printStackTrace();
				resp = new Respuesta();
				resp.setCodigo(CR_ERROR_INESPERADO);
				resp.setDescripcion(KyMensajesError.mensajeFormateado(MSG_PARAM_INVALIDO + " 0004"));
			}	
		}finally {
			
			if(resp == null){
		        resp = new Respuesta(99, KyMensajesError.mensajeFormateado(MSG_ERROR_INESPERADO));
			}
			
		}
		return resp;
    	
    }
}
