
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;
import org.jboss.weld.servlet.SessionHolder;


public class UkloniStavkuNarudzbeniceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
       List<StavkaNarudzbenice>  listaStavki = (List<StavkaNarudzbenice>) request.getSession().getAttribute("listastavkinarudzbenice");
        
       String artikalId = request.getParameter("id");
       Integer id_artikla = Integer.valueOf(artikalId);
    
       for (StavkaNarudzbenice lis: listaStavki){
       if(lis.getSifraArtikla() == id_artikla){
        listaStavki.remove(lis);
        break;
       }
       }
     
   
       
       request.getSession().setAttribute("listastavkinarudzbenice", listaStavki);
        
     return new CreateNarudzbenicaCommand().execute(request);
    }
    
}
