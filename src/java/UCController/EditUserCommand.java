
package UCController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;


public class EditUserCommand implements Command {

   
    @Override
    public String execute(HttpServletRequest request) {
   
        String userId = request.getParameter("id");
        if(userId != null) {
            try {
                request.setAttribute("editable", Korisnik.findByPrimaryKey(Integer.valueOf(userId)));
            } catch (SQLException ex) {
             ex.printStackTrace();
            }
        }
     return "register";
    }
    
}
