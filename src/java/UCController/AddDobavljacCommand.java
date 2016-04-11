
package UCController;

import javax.servlet.http.HttpServletRequest;
import model.Korisnik;



public class AddDobavljacCommand implements Command {
    
   

    @Override
    public String execute(HttpServletRequest request)  {
      return "UnosDobavljaca";
    }
    
}
