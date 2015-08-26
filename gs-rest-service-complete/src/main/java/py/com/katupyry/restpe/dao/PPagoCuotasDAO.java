package py.com.katupyry.restpe.dao;

import java.util.List;

import py.com.katupyry.bd.BDException;
import py.com.katupyry.bd.ParametrosException;
import py.com.katupyry.restpe.dto.PPagoCuotasDTO;

public interface PPagoCuotasDAO {
    public List<PPagoCuotasDTO> obtenerCuotas(String documento, Integer codigoTrans) throws BDException, ParametrosException;
    public void pagarCuota(PPagoCuotasDTO pc) throws BDException, ParametrosException;
    public void anularCuota(PPagoCuotasDTO pc) throws BDException, ParametrosException;
}
