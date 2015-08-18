package py.com.katupyry.restpe;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuotaController {

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
