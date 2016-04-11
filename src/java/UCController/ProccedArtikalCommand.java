
package UCController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;
import model.Dobavljac;

import javax.servlet.http.HttpServletRequest;


public class ProccedArtikalCommand implements Command {

   
    @Override
    public String execute(HttpServletRequest request) {
   
   
     return "UnosArtikla";
    
    }
    
    
}
