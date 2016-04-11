
package UCController;
import java.sql.SQLException;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;
import model.Katalog;
import model.KatalogDetalji;


public class KatalogUpdateCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
   
       
        KatalogDetalji katalog = (KatalogDetalji) request.getSession().getAttribute("katalog");
       
       
       String nazivKataloga = request.getParameter("naziv_kataloga"); 
       String nazivDobavljaca = request.getParameter("naziv_dobavljaca"); 
      
       Katalog k ;
       k = Katalog.findByPrimaryKey(katalog.getRedniBrojKataloga());
       
       k.setNazivDobavljaca(nazivDobavljaca);
       k.setNazivKataloga(nazivKataloga);
       String poruka = k.update();
       
      
       
       if(poruka != null && !poruka.isEmpty()){
       request.setAttribute("poruka", poruka);
       }
       
       katalog.setNazivKataloga(nazivKataloga);
       request.getSession().setAttribute("katalog", katalog);

       

       return new KatalogMainCommand().execute(request);
    }
    
}
