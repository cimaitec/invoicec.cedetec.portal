package com.cimait.invoicec.portal.core.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatting {
	
		public static String formatDate(Date date){
				SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
				String formatted = DATE_FORMAT.format(date);
				return formatted;			
		}
		
		public static String formatNumeroDocumento(String nroDoc){
		    String patternStr = "(-)";
		    Pattern pattern = Pattern.compile(patternStr);
		    Matcher matcher = pattern.matcher(nroDoc);
		    return matcher.replaceAll("");
	    }

		

}
