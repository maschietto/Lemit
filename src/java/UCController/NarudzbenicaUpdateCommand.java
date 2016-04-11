
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Katalog;
import model.KatalogDetalji;
import model.Narudzbenica;
import model.NarudzbenicaDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;


public class NarudzbenicaUpdateCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
   
        Boolean x = false;
       
       Narudzbenica narudzbenica = (Narudzbenica) request.getSession().getAttribute("narudzbenica");

       String nazivDobavljacaStari =  narudzbenica.getNazivDobavljaca();
       String stariDatum =  narudzbenica.getDatum();
       String datum = request.getParameter("datumZaNarudzbenicu"); 
       String imeDobavljaca = request.getParameter("imeKataloga");
       
       
       narudzbenica.setDatum(datum);
       narudzbenica.setNazivDobavljaca(imeDobavljaca);
      
       String poruka = narudzbenica.update();
       
       request.setAttribute("poruka", poruka);
       
       narudzbenica.setNazivDobavljaca(nazivDobavljacaStari);
       narudzbenica.setDatum(stariDatum);
       
       request.getSession().setAttribute("narudzbenica", narudzbenica);
       
       List<NarudzbenicaDetalji> sn =  NarudzbenicaDetalji.findByParamsStavke("K.SIFRA_NARUDZBENICE", narudzbenica.getSifraNarudzbenice());
       List<KatalogDetalji> sk = KatalogDetalji.findByParams(" K.SIFRA_KATALOGA", narudzbenica.getSifraKataloga());
       
       
       
       request.setAttribute("stavke_narudzbenice", sn);
       request.setAttribute("katalog_stavke", sk);
       request.getSession().setAttribute("narudzbenica", narudzbenica);
       
       
      return "NarudzbenicaMain";
    
    }
     
}

