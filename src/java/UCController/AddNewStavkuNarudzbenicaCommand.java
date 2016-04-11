
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
import model.NarudzbenicaDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;
import org.jboss.weld.servlet.SessionHolder;


public class AddNewStavkuNarudzbenicaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       

       

       String nazivArtikla = request.getParameter("katalog_select");
       String narudzbenicaId = request.getParameter("narudzbenica_id");
       String sifraDob = request.getParameter("sifra_dob");
       String kolicina =  request.getParameter("kolicina_id");
       
       
       
       
       Artikal artikal;
       artikal=Artikal.findUnique("NAZIV_ARTIKLA", nazivArtikla);
       
       StavkaNarudzbenice novaSk;
       novaSk = StavkaNarudzbenice.findUniqueZaPoslednjiParametar();
       
       
       StavkaNarudzbenice sk = new StavkaNarudzbenice();
       
       
       
       sk.setSifraArtikla(artikal.getSifraArtikla());
       sk.setRedniBroj(novaSk.getRedniBroj()+ 1);
       sk.setSifraNarudzbenice(Integer.valueOf(narudzbenicaId));
       sk.setNazivProizvoda(nazivArtikla);
       sk.setKolicina(Integer.valueOf(kolicina));
       sk.setSifraArtikla(Integer.valueOf(artikal.getSifraArtikla()));
       
       sk.saveStavka();
       
       NarudzbenicaDetalji nar;
       nar=NarudzbenicaDetalji.findByPrimaryKey(Integer.valueOf(narudzbenicaId));
       
       List<NarudzbenicaDetalji>  listaStavki;
           List<NarudzbenicaDetalji>  listaStavkeNarudzbenicaprenos;
       listaStavki = NarudzbenicaDetalji.findByParamsStavke("K.SIFRA_NARUDZBENICE", narudzbenicaId);
        Integer poc= 0 ;
        Integer uvecanje = 5;
      
        if(listaStavki.size() < uvecanje){listaStavkeNarudzbenicaprenos = listaStavki.subList(poc, listaStavki.size());}else{
       listaStavkeNarudzbenicaprenos = listaStavki.subList(poc, uvecanje);}
       
       List<KatalogDetalji>  listaArtikalauKatalogu;
       listaArtikalauKatalogu = KatalogDetalji.findByParams("K.SIFRA_KATALOGA", nar.getRedniBrojKataloga());
       
       
       request.setAttribute("poruka", "STAVKA USPESNO DODATA!");
      // request.setAttribute("narudzbenica", katalog);
       request.setAttribute("stavke_narudzbenice", listaStavkeNarudzbenicaprenos);
       request.setAttribute("katalog_stavke", listaArtikalauKatalogu);
       
       return "NarudzbenicaMain";
    }
    
}
