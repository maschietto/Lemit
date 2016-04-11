
package UCController;
import com.sun.org.apache.xerces.internal.util.URI;
import java.io.IOException;
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
import model.KatalogDetalji;
import model.Narudzbenica;
import model.NarudzbenicaDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;




public class UnesiNarudzbenicuCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
      List<StavkaNarudzbenice>  listaStavki = (List<StavkaNarudzbenice>) request.getSession().getAttribute("listastavkinarudzbenice");
        KatalogDetalji  katalog = (KatalogDetalji) request.getSession().getAttribute("katalog_new"); 
      
     String sifraDobavljaca = request.getParameter("dobavljac_id");
      String datum = request.getParameter("datum");
      
        Narudzbenica narudzbenica ;
        narudzbenica = Narudzbenica.findUniqueZaPoslednjiParametar();
        if(narudzbenica == null){
            narudzbenica= new Narudzbenica();
            narudzbenica.setSifraNarudzbenice(1);
            narudzbenica.setSifraKataloga(katalog.getRedniBrojKataloga());
            narudzbenica.setSifraDobavljaca(Integer.valueOf(katalog.getSifraDobavljaca()));
            narudzbenica.setDatum(datum);
        }else{
            Integer sifraNarudzbenice = narudzbenica.getSifraNarudzbenice();
            narudzbenica.setSifraNarudzbenice(sifraNarudzbenice + 1);
            narudzbenica.setSifraDobavljaca(Integer.valueOf(katalog.getSifraDobavljaca()));
            narudzbenica.setSifraKataloga(katalog.getRedniBrojKataloga());
            narudzbenica.setDatum(datum);
        }
        
        
        narudzbenica.save();
        
        for(StavkaNarudzbenice sk: listaStavki){
           
          
            
            String kolicina = request.getParameter("broj" + String.valueOf(listaStavki.indexOf(sk)));
            if(kolicina == null){
                
            Integer kol = 0;
            }else{Integer kol = Integer.valueOf(kolicina);
            
            int a = listaStavki.indexOf(sk);
            sk.setSifraNarudzbenice(narudzbenica.getSifraNarudzbenice());
            sk.setRedniBroj(a + 1);
            sk.setKolicina(kol);
            sk.setSifraArtikla(sk.getSifraArtikla());
            sk.saveStavka();
            }
        }
            
        
      
        request.getSession().removeAttribute("listastavkinarudzbenice");
        
       
        Narudzbenica nar = Narudzbenica.findByPrimaryKey(narudzbenica.getSifraNarudzbenice());
     
        //Narudzbenica detaji!!!
        List<NarudzbenicaDetalji> stavke = new ArrayList<NarudzbenicaDetalji>();
        stavke = NarudzbenicaDetalji.findByParamsStavke("s.SIFRA_NARUDZBENICE", narudzbenica.getSifraNarudzbenice());
        List<KatalogDetalji> katalogStavke = KatalogDetalji.findByParams("k.SIFRA_KATALOGA", narudzbenica.getSifraKataloga());
        
     
       request.getSession().setAttribute("narudzbenica", nar);
       request.setAttribute("poruka", "Uneli ste narudzbenicu!");
       request.setAttribute("stavke_narudzbenice", stavke);
       request.setAttribute("katalog_stavke", katalogStavke);
       return "NarudzbenicaMain";
    }
    
}
