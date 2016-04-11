
package UCController;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Korisnik;


public class ActivateUserCommand implements Command {
    
    private String isActive;
    
    public ActivateUserCommand(String active) {
        this.isActive = active;
    }

    @Override
    public String execute(HttpServletRequest request){
        try {    
            String userId = request.getParameter("id");
            Korisnik toBeActivated = Korisnik.findByPrimaryKey(Integer.valueOf(userId));
            toBeActivated.changeVisibility(isActive);
            //nakon aktivacije ili deaktivacije, treba se vratiti na listu korisnika
         
        } catch (SQLException ex) {
         ex.printStackTrace();
        }
        return new UserListCommand().execute(request);
    }
    
}
