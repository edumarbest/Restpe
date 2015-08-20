package py.com.katupyry.errores;

import java.util.ListResourceBundle;

public class KyErrors  extends ListResourceBundle implements KyErrorsKeys {
	public Object[][] getContents() {
		return contents;
	}
	  
	static final Object[][] contents = {
			{MSG_FILE_NOT_FOUND, "Cannot find file {1}"},
			{MSG_CANT_OPEN_FILE, "Cannot open file {1}"},
	};
}
