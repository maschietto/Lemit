package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Validate {

    public static boolean exists(String param) {
        return (param != null && !param.isEmpty());
    }

    public static boolean isInteger(String param) {
        try {
            if (!exists(param)) {
                throw new Exception();
            } else {
                Integer.valueOf(param);
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isDouble(String param) {
        try {
            if (!exists(param)) {
                throw new Exception();
            } else {
                Double.valueOf(param);
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
           public static String pokupiPoruku (String message) {
            
  
            String error = message.substring(11);
            error = error.substring(0, error.indexOf("\nORA-06512"));
            return error;
}
           
           
             
    public static String konvertujDatum(String ulaz){
           
            String povratak ="";
        
            try {
       
            SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
            
            Date date;
            
            date = sdfSource.parse(ulaz);
            
            SimpleDateFormat sdfDestination = new SimpleDateFormat("dd.MM.yyyy");
            
            povratak = (sdfDestination.format(date));
            
          
        } catch (ParseException ex) {
           ex.printStackTrace();
        }
          return povratak;
    }
    
}
