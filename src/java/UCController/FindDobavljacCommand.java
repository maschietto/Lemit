package UCController;


import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import model.DobavljacDetalji;
import model.Korisnik;
;

/**
 *
 * @author ibranovic
 */
public class FindDobavljacCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException{
        
        String name = request.getParameter("demo_ime");
        
        DobavljacDetalji dobavljac = (DobavljacDetalji) request.getSession().getAttribute("listaDobavljaca");
        request.setAttribute("listaDobavljaca", DobavljacDetalji.findByParams("NAZIV_DOBAVLJACA",name));
        return "ListaDobavljaca";
    }
    
}
