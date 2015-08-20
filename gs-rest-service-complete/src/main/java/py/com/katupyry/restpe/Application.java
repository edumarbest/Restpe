package py.com.katupyry.restpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import py.com.katupyry.errores.KyErrorsKeys;
import py.com.katupyry.errores.KyMensajesError;

@SpringBootApplication
public class Application  implements KyErrorsKeys{

    public static void main(String[] args) {
    	
    	System.out.println(KyMensajesError.mensajeFormateado(MSG_FILE_NOT_FOUND, "error"));
    	
        SpringApplication.run(Application.class, args);
    }
}
