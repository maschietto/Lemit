
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;
import model.KatalogDetalji;


public class KatalogListaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
               //        String tipInstrumenta = request.getParameter("tipinstrumenta");
           
           Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
           List<KatalogDetalji> listaKataloga = new ArrayList<KatalogDetalji>();
           List<Dobavljac> listaDobavljaca;
           listaDobavljaca = Dobavljac.findAll();
           listaKataloga.clear();
           listaKataloga = KatalogDetalji.findByParametersKatalog(null,null);
           request.setAttribute("listakataloga", listaKataloga);
           request.setAttribute("listadobavljaca",listaDobavljaca);
        
     return "ListaKataloga";
    }
    
}
