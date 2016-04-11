
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


public class DodajStavkuKatalogaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
       List<StavkaKataloga>  listaStavki = (List<StavkaKataloga>) request.getSession().getAttribute("listastavki");
        
       String artikalId = request.getParameter("id");
       
       String dobavljac = request.getParameter("dobavljac_id");
       
       ArtikalDetalji ar;
       ar = ArtikalDetalji.findByPrimaryKey(Integer.valueOf(artikalId));
       
       String nazivProizvoda = ar.getNazivArtikla();
  
       StavkaKataloga sk = new StavkaKataloga();
               
               sk.setSifraArtikla(Integer.valueOf(artikalId));
               sk.setNazivProizvoda(nazivProizvoda);
               sk.setSifraDobavljaca(1);
               sk.setStavkaKatalogaid(1);
               sk.setSifraKataloga(1);
               
           if(listaStavki==null)
               
               listaStavki= new ArrayList<StavkaKataloga>();
               listaStavki.add(sk);
           
       
       request.getSession().setAttribute("listastavki", listaStavki);
        
     return new DodajKatalogDobavljacaCommand().execute(request);
    }
    
}
