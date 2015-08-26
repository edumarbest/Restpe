package py.com.katupyry.restpe.dao.impl.oracle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.mvc.multiaction.ParameterMethodNameResolver;

import py.com.katupyry.bd.BDException;
import py.com.katupyry.bd.ConexionBD;
import py.com.katupyry.bd.ParametrosException;
import py.com.katupyry.errores.KyErrorsKeys;
import py.com.katupyry.errores.KyMensajesError;
import py.com.katupyry.restpe.dao.PPagoCuotasDAO;
import py.com.katupyry.restpe.dto.PPagoCuotasDTO;
import scala.annotation.meta.param;

public class PPagoCuotasDAOOracleImpl implements PPagoCuotasDAO, KyErrorsKeys{
	
	private final int CR_APROBADO = 0;
	private final int CR_NO_TIENE_DEUDAS = 2;
	private final String MSG_NO_TIENE_DEUDAS = "Cliente no tiene deudas";

	private void verifParametrosConsulta(String documento, Integer codigoTrans) throws ParametrosException{
		if( "".equals(documento) || (documento == null))
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL + ": documento. (0006)"));

		if( (new Integer(0)).equals(codigoTrans) || (codigoTrans == null))
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL + ": codigoTrans (0007)"));
	}
	private void verifParametros(PPagoCuotasDTO pc) throws ParametrosException{
		if(pc == null)
			throw new ParametrosException(KyMensajesError.mensajeFormateado(MSG_PARAM_NULL + ": objeto PPagoCuotas. (0008)"));
	}

	@Override
    public List<PPagoCuotasDTO> obtenerCuotas(String documento, Integer codigoTrans) throws BDException, ParametrosException {
    	
		verifParametrosConsulta(documento,codigoTrans);
		
		ConexionBD connMan = new ConexionBD();	
		try {
			connMan.conectar();
		} catch (ParametrosException | BDException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN + " (0001)"));
		}
		List<PPagoCuotasDTO> listaPC = new ArrayList<PPagoCuotasDTO>(); 
		
		Connection conn = connMan.getConexion();
		String command = "{call pf_consulta(?,?)}";
        CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(command);
	        cstmt.setString(1, documento);
	        cstmt.setString(2, String.valueOf(codigoTrans));
	        cstmt.execute();
		} catch (SQLException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_ERROR_QUERY + " (0002)"));
		}finally{
	        try {
	        	if(cstmt != null)
	        		cstmt.close();
			} catch (SQLException e) {
			}
		}
		
		command = "select nombre_cliente, "
		                 + "id_fraccion, " 
				         + "id_manzana, "
				         + "id_lote, "
				         + "numero_cuota, "
				         + "monto_cuota, "
				         + "meses_mora, "
				         + "monto_mora, "
				         + "codigo_retorno, "
				         + "desc_retorno "
				         + "from ppago_cuotas "
				         + "where documento = '" + documento + "' "
				         + "and codigo_trans = '" + codigoTrans + "'";
				         
		ResultSet rs = null;
		try {
			rs = connMan.getConsulta(command);
			PPagoCuotasDTO pc = null;
			while(rs.next()){
				
				pc = new PPagoCuotasDTO();
				
				pc.setCodigoRetorno(rs.getInt("codigo_retorno"));
				pc.setDescRetorno(rs.getString("desc_retorno"));
				
				if( !(new Integer(CR_APROBADO)).equals(pc.getCodigoRetorno()))
					break;
				
				pc.setCodigoTrans(codigoTrans);
				pc.setDocumento(documento);
				pc.setIdFraccion(rs.getInt("id_fraccion"));
				pc.setIdManzana(rs.getInt("id_manzana"));
				pc.setIdLote(rs.getInt("id_lote"));
				pc.setNumeroCuota(rs.getInt("numero_cuota"));
				pc.setMontoCuota(rs.getBigDecimal("monto_cuota"));
				pc.setMesesMora(rs.getInt("meses_mora"));
				pc.setMontoMora(rs.getBigDecimal("monto_mora"));
				
				listaPC.add(pc);
			}
			
			if( pc == null ){
				pc = new PPagoCuotasDTO();
				pc.setCodigoRetorno(CR_NO_TIENE_DEUDAS);
				pc.setDescRetorno(MSG_NO_TIENE_DEUDAS);
				listaPC.add(pc);
			}
			
			
		} catch (SQLException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_ERROR_QUERY + " (0003)"));
		}
        
        return listaPC;
    }


	@Override
	public void pagarCuota(PPagoCuotasDTO pc) throws BDException, ParametrosException {

		verifParametros(pc);
		
		ConexionBD connMan = new ConexionBD();
		try {
			connMan.conectar();
		} catch (ParametrosException | BDException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN + " (0005)"));
		}
		
		Connection conn = connMan.getConexion();
		String command = "{call pf_pago(?,?,?,?,?,?,?)}";
        CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(command);
	        cstmt.setInt(1, pc.getIdFraccion());
	        cstmt.setInt(2, pc.getIdManzana());
	        cstmt.setInt(3, pc.getIdLote());
	        cstmt.setBigDecimal(4, pc.getMontoCuota());
	        cstmt.setBigDecimal(5, pc.getMontoMora());
	        cstmt.setInt(6, pc.getCodigoTrans());
	        cstmt.registerOutParameter(7, Types.VARCHAR);
	        cstmt.execute();
	        
	        String resp = cstmt.getString(7);
	        
	        String cod = resp.substring(0, resp.indexOf("|"));
	        String desc = resp.substring(resp.indexOf("|"));
	        
	        
	        pc.setCodigoRetorno(Integer.valueOf(cod));
	        pc.setDescRetorno(desc);
	        
		} catch (SQLException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_ERROR_QUERY + " (0002)"));
		}finally{
	        try {
	        	if(cstmt != null)
	        		cstmt.close();
			} catch (SQLException e) {
			}
		}
	}
    public void anularCuota(PPagoCuotasDTO pc) throws BDException, ParametrosException{

		verifParametros(pc);
		
    	ConexionBD connMan = new ConexionBD();
		try {
			connMan.conectar();
		} catch (ParametrosException | BDException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_OBT_CONN + " (0005)"));
		}
		
		Connection conn = connMan.getConexion();
		String command = "{call pf_proceso(?,?)}";
        CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(command);
	        cstmt.setInt(1, pc.getCodigoTrans());
	        cstmt.registerOutParameter(2, Types.VARCHAR);
	        cstmt.execute();
	        
	        String resp = cstmt.getString(7);
	        
	        String cod = resp.substring(0, resp.indexOf("|"));
	        String desc = resp.substring(resp.indexOf("|"));
	        
	        
	        pc.setCodigoRetorno(Integer.valueOf(cod));
	        pc.setDescRetorno(desc);
	        
		} catch (SQLException e) {
			throw new BDException(KyMensajesError.mensajeFormateado(MSG_ERROR_QUERY + " (0002)"));
		}finally{
	        try {
	        	if(cstmt != null)
	        		cstmt.close();
			} catch (SQLException e) {
			}
		}
    }
}
