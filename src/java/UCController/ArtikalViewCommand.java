
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.Artikal;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;


public class ArtikalViewCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
   String next = "PrikazArtikla";
        
        String idArtikla = request.getParameter("id");
        
        ArtikalDetalji a = ArtikalDetalji.findByPrimaryKey(Integer.valueOf(idArtikla));
        
        request.setAttribute("art", a);
        
 return next;       
}
}