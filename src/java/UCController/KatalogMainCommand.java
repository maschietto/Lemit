
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


public class KatalogMainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
    
      
       KatalogDetalji katalog = (KatalogDetalji) request.getSession().getAttribute("katalog");
        
       List<KatalogDetalji> listaStavkeKataloga = new ArrayList<KatalogDetalji>();
       List<KatalogDetalji> listaStavkeKatalogaprenos = new ArrayList<KatalogDetalji>();
       
       if(katalog == null ){
       
       String katalogId = request.getParameter("id");
       
       
       katalog = KatalogDetalji.findByPrimaryKey(Integer.valueOf(katalogId));
       
       request.getSession().setAttribute("katalog",katalog);
       }
       
       
       Integer katalog_ID = katalog.getRedniBrojKataloga();
        

        
        List<Artikal> listaArtikala;
        listaArtikala = Artikal.findAll();
        Integer poc = 0 ;
        Integer uvecanje = 5;
       
        
    
        listaStavkeKataloga = KatalogDetalji.findByParams("S.SIFRA_KATALOGA", katalog_ID);
     
        if(listaStavkeKataloga == null){request.setAttribute("poruka", "PRAZAN KATALOG!");}
        
        if (listaStavkeKatalogaprenos.size() < 5) {
            listaStavkeKatalogaprenos = listaStavkeKataloga.subList(poc, listaStavkeKataloga.size());
        } else {
            listaStavkeKatalogaprenos = listaStavkeKataloga.subList(poc, uvecanje);
        }


        request.setAttribute("listastavki", listaStavkeKatalogaprenos);
        request.setAttribute("listaartikalakatalog", listaArtikala);
        request.setAttribute("prviunos", poc);
        request.setAttribute("povecavanje", uvecanje);
   
        
        return "KatalogMain";
    
    }
    
}
