package py.com.katupyry.restpe;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.com.katupyry.bd.BDException;
import py.com.katupyry.bd.ConexionBD;
import py.com.katupyry.bd.ParametrosException;
import scala.annotation.meta.getter;

@RestController
public class CuotaController {
	
	private ConexionBD conn;
	
	CuotaController(){
		conn = new ConexionBD();
		try {
			conn.conectar();
		} catch (ParametrosException | BDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @RequestMapping("/consulta")
    public RespuestaCuotas consulta(@RequestParam(value="name", defaultValue="World") String name) {
        return new RespuestaCuotas(99, "Error inesperado");
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
