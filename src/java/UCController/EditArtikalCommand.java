
package UCController;
import java.sql.SQLException;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;


public class EditArtikalCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
   
        Integer artikalid = Integer.valueOf(request.getParameter("id"));
        if(artikalid != null) {
            ArtikalDetalji art  = ArtikalDetalji.findByPrimaryKey(Integer.valueOf(artikalid));
            request.setAttribute("art", art);
        }
   
        return "UnosArtikla";
    }
    
}
