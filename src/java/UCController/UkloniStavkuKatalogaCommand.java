
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
import org.jboss.weld.servlet.SessionHolder;


public class UkloniStavkuKatalogaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
       List<StavkaKataloga>  listaStavki = (List<StavkaKataloga>) request.getSession().getAttribute("listastavki");
        
       String artikalId = request.getParameter("id");
       Integer id_artikla = Integer.valueOf(artikalId);
    
       for (StavkaKataloga lis: listaStavki){
       if(lis.getSifraArtikla() == id_artikla){
        listaStavki.remove(lis);
        break;
       }
       }
     
   
       
       request.getSession().setAttribute("listastavki", listaStavki);
        
     return new DodajKatalogDobavljacaCommand().execute(request);
    }
    
}
