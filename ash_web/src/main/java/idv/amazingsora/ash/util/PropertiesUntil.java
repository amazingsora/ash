package idv.amazingsora.ash.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesUntil {
	final static String PRORERTIES_NAME = "application";
	public static  ResourceBundle resource;

	static {
	     resource = ResourceBundle.getBundle(PRORERTIES_NAME);
	  }

	public static String getKey(String a) {
		if(null ==resource.getString(a)) {
			return "noValue";
		}
		return resource.getString(a);

	}
	
	public void setLoc(Locale locale) {
//		Locale locale = new Locale("en", "US");
	     resource = ResourceBundle.getBundle(PRORERTIES_NAME,locale);

	}
}
