
package UCController;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import model.Artikal;
import model.CenaArtikla;

import model.JedinicaMere;
import model.Korisnik;



public class ArtikalDeleteCommand implements Command {
    


    @Override
    public String execute(HttpServletRequest request) throws SQLException{
       
       String artikalid = request.getParameter("id");
       Artikal toBeDelated = Artikal.findByPrimaryKey(Integer.valueOf(artikalid));
       CenaArtikla cenDelete = CenaArtikla.findByPrimaryKey(Integer.valueOf(artikalid));
       JedinicaMere JMDelete = JedinicaMere.findByPrimaryKey(Integer.valueOf(artikalid));
       
       cenDelete.delete();
       toBeDelated.delete();
       JMDelete.delete();
      
        return new ArtikalListaCommand().execute(request);
    }
    
    
    
}
