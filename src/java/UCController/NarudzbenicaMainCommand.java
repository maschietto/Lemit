
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;
import model.DobavljacDetalji;
import javax.servlet.http.HttpServletRequest;
import model.Artikal;
import model.ArtikalDetalji;
import model.Katalog;
import model.KatalogDetalji;
import model.Narudzbenica;
import model.NarudzbenicaDetalji;
import util.Validate;


public class NarudzbenicaMainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
    
      
       Narudzbenica narudzbenica = (Narudzbenica) request.getSession().getAttribute("narudzbenica");
        
       List<NarudzbenicaDetalji> listaStavkeNarudzbenica = new ArrayList<NarudzbenicaDetalji>();
       List<NarudzbenicaDetalji> listaStavkeNarudzbenicaaprenos = new ArrayList<NarudzbenicaDetalji>();
       
       String nazivKataloga = "";
       
     
       
       String narudzbenicaId = request.getParameter("id");
       
  
       
       narudzbenica = Narudzbenica.findByPrimaryKey(Integer.valueOf(narudzbenicaId));
       
      narudzbenica.setDatum(Validate.konvertujDatum(narudzbenica.getDatum()));
    
            Katalog k = Katalog.findByPrimaryKey(narudzbenica.getSifraKataloga());
       nazivKataloga = k.getNazivKataloga();
       
       request.getSession().setAttribute("narudzbenica",narudzbenica);
       
       
       
       Integer narudzbenica_id = narudzbenica.getSifraNarudzbenice();
        

        
        List<KatalogDetalji> listaArtikala;
        listaArtikala = KatalogDetalji.findByParams("S.SIFRA_KATALOGA",narudzbenica.getSifraKataloga());
        Integer poc = 0 ;
        Integer uvecanje = 5;
       
        
    
        listaStavkeNarudzbenica = NarudzbenicaDetalji.findByParamsStavke("S.SIFRA_NARUDZBENICE", narudzbenica_id);
     
        if(listaStavkeNarudzbenica == null){request.setAttribute("poruka", "PRAZNA NARUDZBENICA!");}
        
        if (listaStavkeNarudzbenicaaprenos.size() < 5) {
            listaStavkeNarudzbenicaaprenos= listaStavkeNarudzbenica.subList(poc, listaStavkeNarudzbenica.size());
        } else {
            listaStavkeNarudzbenicaaprenos = listaStavkeNarudzbenica.subList(poc, uvecanje);
        }
  

        request.getSession().setAttribute("nazivKatalogaZaMain", nazivKataloga);
        request.setAttribute("stavke_narudzbenice", listaStavkeNarudzbenicaaprenos);
        request.setAttribute("katalog_stavke", listaArtikala);
        request.setAttribute("prviunos", poc);
        request.setAttribute("povecavanje", uvecanje);
   
        
        return "NarudzbenicaMain";
    
    }
    
}
