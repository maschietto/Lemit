package UCController;


import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import model.Korisnik;
;

/**
 *
 * @author ibranovic
 */
public class FindUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException{
        
        String name = request.getParameter("demo_ime");
      
        Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
        
        request.setAttribute("lista", Korisnik.findByParameter("NAZIV_KORISNIKA",name));
       
       return "Admin";
    }
    
}
