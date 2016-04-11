
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
import model.Katalog;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;


public class UnesiKatalogCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
       List<StavkaKataloga>  listaStavki = (List<StavkaKataloga>) request.getSession().getAttribute("listastavki");
        
      String nazivKataloga = request.getParameter("naziv_kataloga");
      String sifraDobavljaca = request.getParameter("dobavljac_id");
      
      
        Katalog katalog ;
        katalog = Katalog.findUniqueZaPoslednjiParametar();
        if(katalog == null){
            katalog = new Katalog();
            katalog.setSifraKataloga(1);
            katalog.setSifraDobavljaca(Integer.valueOf(sifraDobavljaca));
            katalog.setNazivKataloga(nazivKataloga);
        }else{
            Integer sifraKatalogaloc = katalog.getSifraKataloga();
            katalog.setSifraKataloga(sifraKatalogaloc + 1);
            katalog.setSifraDobavljaca(Integer.valueOf(sifraDobavljaca));
            katalog.setNazivKataloga(nazivKataloga);
        }
        
        katalog.save();
        
        for(StavkaKataloga sk: listaStavki){
           
            int a = listaStavki.indexOf(sk);
            sk.setSifraKataloga(katalog.getSifraKataloga());
            sk.setStavkaKatalogaid(a + 1);
            sk.setSifraArtikla(sk.getSifraArtikla());
            sk.setSifraDobavljaca(sk.getSifraDobavljaca());
           
            sk.saveStavka();
            
        }
       request.getSession().removeAttribute("listastavki");
        
       List<Katalog> listaKatalogaDobavljaca ;
       listaKatalogaDobavljaca = Katalog.findByParameter("SIFRA_DOBAVLJACA", sifraDobavljaca);
       
       
        
       request.setAttribute("poruka", "Uneli ste katalog!");
       request.setAttribute("listakataloga", listaKatalogaDobavljaca);
     return "DobavljacMain";
    }
    
}
