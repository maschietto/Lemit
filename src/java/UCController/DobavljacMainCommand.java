
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;
import model.DobavljacDetalji;
import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;
import model.Katalog;


public class DobavljacMainCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
        
        List<Katalog> listaKatalogaDobavljaca = new ArrayList<Katalog>();
        
        DobavljacDetalji dob = (DobavljacDetalji) request.getSession().getAttribute("dobavljacShow");
        
        
       Integer dobId = Integer.valueOf(request.getParameter("id"));
       
   
       
       dob = DobavljacDetalji.findUnique("SIFRA_DOBAVLJACA", dobId);
       
       
       listaKatalogaDobavljaca = Katalog.findByParameter("SIFRA_DOBAVLJACA", dob.getDobavljacId());
       request.getSession().setAttribute("dobavljacShow", dob);
       request.setAttribute("listakataloga", listaKatalogaDobavljaca);
       
    
        
        return "DobavljacMain";
    
    }
    
}
