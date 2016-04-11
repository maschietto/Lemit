
package UCController;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;
import model.Dobavljac;

import javax.servlet.http.HttpServletRequest;
import model.Katalog;
import model.Narudzbenica;
import model.NarudzbenicaDetalji;


public class ProccedNarudzbenicuCommand implements Command {

   
    @Override
    public String execute(HttpServletRequest request) throws SQLException {
   
   
    Narudzbenica narudzbenica = (Narudzbenica) request.getSession().getAttribute("narudzbenica");
       
        
    String ktalaogId = request.getParameter("id");
    
       List<Dobavljac>  dob;
       dob = Dobavljac.findAll();
       
    
       NarudzbenicaDetalji nd;
       nd = NarudzbenicaDetalji.findByPrimaryKey(Integer.valueOf(ktalaogId));

       request.setAttribute("katalog", nd);
       request.setAttribute("dobavljac", dob);
       
     return "NarudzbenicaUpdate";
    
    }
    
    
}
