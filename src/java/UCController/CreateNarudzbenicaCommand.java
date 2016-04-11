
package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.DobavljacDetalji;
import model.Katalog;
import model.KatalogDetalji;
import model.Korisnik;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;



public class CreateNarudzbenicaCommand implements Command {
    
   

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
      
        
       KatalogDetalji katalog = (KatalogDetalji) request.getSession().getAttribute("katalog_new");
        
       
      
         if(katalog == null){
           String id = request.getParameter("id");
           Integer idNew = Integer.valueOf(id);
           katalog = KatalogDetalji.findByPrimaryKey(idNew);
          
           }
      
         
         
           List<KatalogDetalji> listaStavkiKataloga = new ArrayList<KatalogDetalji>();
           listaStavkiKataloga = KatalogDetalji.findByParams("K.SIFRA_KATALOGA",katalog.getRedniBrojKataloga());
           request.setAttribute("listastavkikataloga", listaStavkiKataloga);
           request.getSession().setAttribute("katalog_new", katalog);
           return "KreiranjeNarudzbenice";
      
    }
    
}
