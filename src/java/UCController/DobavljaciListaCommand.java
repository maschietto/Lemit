
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;


public class DobavljaciListaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
            //        String tipInstrumenta = request.getParameter("tipinstrumenta");
            Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
           
           List<DobavljacDetalji> listaDobavljaca = new ArrayList<DobavljacDetalji>();
           listaDobavljaca.clear();
           listaDobavljaca = DobavljacDetalji.findAll();
           request.setAttribute("listaDobavljaca", listaDobavljaca);
        
     return "ListaDobavljaca";
    }
    
}
