
package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Artikal;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;
import org.jboss.weld.servlet.SessionHolder;


public class DodajStavkuNarudzbeniceCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
     
        List<StavkaNarudzbenice>  listaStavki = (List<StavkaNarudzbenice>) request.getSession().getAttribute("listastavkinarudzbenice");
       
        String skId = request.getParameter("id");
     
        StavkaKataloga sk;
       
        sk = StavkaKataloga.findByPrimaryKey(Integer.valueOf(skId));
        Artikal ar;
        ar = Artikal.findUnique("SIFRA_ARTIKLA", sk.getSifraArtikla());
       
        String nazivProizvoda = ar.getNazivArtikla();
  
        StavkaNarudzbenice sNar = new StavkaNarudzbenice();
               
               
            
               sNar.setRedniBroj(Integer.valueOf(skId));
               sNar.setNazivProizvoda(nazivProizvoda);
               sNar.setKolicina(0);
               sNar.setSifraNarudzbenice(1);
               sNar.setSifraArtikla(sk.getSifraArtikla());
               
           if(listaStavki==null)
               
               listaStavki = new ArrayList<StavkaNarudzbenice>();
               listaStavki.add(sNar);
           
            
              
               
      
       request.getSession().setAttribute("listastavkinarudzbenice", listaStavki);
        
     return new CreateNarudzbenicaCommand().execute(request);
    }
    
}
