package UCController;

import java.sql.SQLException;
import model.Korisnik;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class UserListCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            request.setAttribute("lista", Korisnik.findAll());
            Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Admin";
    }
}



