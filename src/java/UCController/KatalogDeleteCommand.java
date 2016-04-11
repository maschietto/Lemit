
package UCController;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.BrojZgrade;
import model.Dobavljac;
import model.Katalog;

import model.Korisnik;
import model.Mesto;
import model.StavkaKataloga;
import model.Ulica;



public class KatalogDeleteCommand implements Command {
    


    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
        
        
     
        String katId = request.getParameter("id");
      // String dobavljacIme = request.getParameter("naziv_dobavljaca");
       
       Katalog toBeDelated = Katalog.findByPrimaryKey(Integer.valueOf(katId));
       List<StavkaKataloga> stavkaToBeDeleted = StavkaKataloga.findByParameter("SIFRA_KATALOGA",katId);
      try{ 
      for(StavkaKataloga st : stavkaToBeDeleted ){
            
          st.delete();
      }       
            toBeDelated.delete();
      
      }catch(SQLException ex){ex.printStackTrace();}
      
      
      
       request.setAttribute("poruka", "USPESNO STE OBRISALI KATALOG!");
  
       return new KatalogListaCommand().execute(request);
  
    
    
    
}
}