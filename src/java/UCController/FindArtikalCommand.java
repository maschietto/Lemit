package UCController;


import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;
import model.DobavljacDetalji;
import model.Korisnik;
;

/**
 *
 * @author ibranovic
 */
public class FindArtikalCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException{
        
        String name = request.getParameter("demo_ime");
        
        ArtikalDetalji artikal = (ArtikalDetalji) request.getSession().getAttribute("listaartikala");
        request.setAttribute("listaartikala", ArtikalDetalji.findByParams("NAZIV_ARTIKLA",name));
        return "ListaArtikala";
    }
    
}
