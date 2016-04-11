package UCController;


import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Dobavljac;
import model.DobavljacDetalji;
import model.KatalogDetalji;
import model.Korisnik;
;

/**
 *
 * @author ibranovic
 */
public class FindKatalog2Command implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException{
        
        String name = request.getParameter("demo_ime");
        List<Dobavljac> listaDobavljaca;
        listaDobavljaca = Dobavljac.findAll();
        request.setAttribute("listakataloga", KatalogDetalji.findByParametersKatalog("D.NAZIV_DOBAVLJACA",name));
        request.setAttribute("listadobavljaca",listaDobavljaca);
        return "ListaKatalogaNarudzbenica";
    }
    
}
