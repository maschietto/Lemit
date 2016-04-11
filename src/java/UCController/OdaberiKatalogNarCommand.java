
package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.DobavljacDetalji;
import model.Katalog;
import model.KatalogDetalji;
import model.Korisnik;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;



public class OdaberiKatalogNarCommand implements Command {
    
   

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
      
        
                 
          // Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
           List<KatalogDetalji> listaKataloga = new ArrayList<KatalogDetalji>();
           List<Dobavljac> listaDobavljaca;
           listaDobavljaca = Dobavljac.findAll();
           listaKataloga.clear();
           listaKataloga = KatalogDetalji.findByParametersKatalog(null,null);
           request.setAttribute("listakataloga", listaKataloga);
           request.setAttribute("listadobavljaca",listaDobavljaca);
           
           
        return "ListaKatalogaNarudzbenica";
      
    }
    
}
