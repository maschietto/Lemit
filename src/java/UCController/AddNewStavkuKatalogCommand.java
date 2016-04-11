
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
import model.Katalog;
import model.KatalogDetalji;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;


public class AddNewStavkuKatalogCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       

       

       String nazivArtikla = request.getParameter("artikli_select");
       String KatalogId = request.getParameter("katalog_id");
       String sifraDob = request.getParameter("sifra_dob");
        
       
       
       Artikal artikal;
       artikal=Artikal.findUnique("NAZIV_ARTIKLA", nazivArtikla);
       
       StavkaKataloga novaSk;
       novaSk = StavkaKataloga.findUniqueZaPoslednjiParametar();
       
       
       StavkaKataloga sk = new StavkaKataloga();
       
       
       
       sk.setSifraArtikla(artikal.getSifraArtikla());
       sk.setStavkaKatalogaid(novaSk.getStavkaKatalogaid() + 1);
       sk.setNazivProizvoda(nazivArtikla);
       sk.setSifraDobavljaca(Integer.valueOf(sifraDob));
       sk.setSifraKataloga(Integer.valueOf(KatalogId));
       sk.saveStavka();
       
       KatalogDetalji katalog;
       katalog=KatalogDetalji.findByPrimaryKey(Integer.valueOf(KatalogId));
       
       List<KatalogDetalji>  listaStavki;
           List<KatalogDetalji>  listaStavkeKatalogaprenos;
       listaStavki = KatalogDetalji.findByParams("K.SIFRA_KATALOGA", KatalogId);
        Integer poc= 0 ;
        Integer uvecanje = 5;
      
        if(listaStavki.size() < uvecanje){listaStavkeKatalogaprenos = listaStavki.subList(poc, listaStavki.size());}else{
       listaStavkeKatalogaprenos = listaStavki.subList(poc, uvecanje);}
       
       List<Artikal>  listaArtikala;
       listaArtikala = Artikal.findAll();
       
       
       request.setAttribute("poruka", "STAVKA USPESNO DODATA!");
       request.setAttribute("katalog", katalog);
       request.setAttribute("listastavki", listaStavkeKatalogaprenos);
       request.setAttribute("listaartikalakatalog", listaArtikala);
       
       return "KatalogMain";
    }
    
}
