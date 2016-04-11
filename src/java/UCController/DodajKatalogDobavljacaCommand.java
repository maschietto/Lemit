
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
import model.Korisnik;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;



public class DodajKatalogDobavljacaCommand implements Command {
    
   

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
      
        

           List<ArtikalDetalji> listaArtikala = new ArrayList<ArtikalDetalji>();
           listaArtikala = ArtikalDetalji.findAll();
           request.setAttribute("listaartikala", listaArtikala);

           return "DodajKatalogDobavljaca";
      
    }
    
}
