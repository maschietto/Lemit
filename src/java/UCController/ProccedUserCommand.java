
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


public class ProccedUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request)  {
       
            //        String tipInstrumenta = request.getParameter("tipinstrumenta");
            Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
            
//            try {  
//             
//               List<DobavljacDetalji> lista = new ArrayList<DobavljacDetalji>();
//               lista = DobavljacDetalji.findDobavljac_ulica_mesto_broj();
//            
//            
//            request.setAttribute("dobavljaci", lista);
//            
//            
//        } catch (SQLException ex) {
//             ex.printStackTrace();
        
     return "Korisnik";
    }
    
}
