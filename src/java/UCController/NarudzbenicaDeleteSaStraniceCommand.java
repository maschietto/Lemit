
package UCController;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.BrojZgrade;
import model.Dobavljac;
import model.Katalog;

import model.Korisnik;
import model.Mesto;
import model.Narudzbenica;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;
import model.Ulica;



public class NarudzbenicaDeleteSaStraniceCommand implements Command {
    


    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
        
        
     
        String narId = request.getParameter("id");
      // String dobavljacIme = request.getParameter("naziv_dobavljaca");
       
       Narudzbenica toBeDelated = Narudzbenica.findByPrimaryKey(Integer.valueOf(narId));
       List<StavkaNarudzbenice> stavkaToBeDeleted = StavkaNarudzbenice.findByParameter("SIFRA_NARUDZBENICE",narId);
      try{ 
      for(StavkaNarudzbenice st : stavkaToBeDeleted ){
            
          st.delete();
      }       
            toBeDelated.delete();
      
      }catch(SQLException ex){ex.printStackTrace();}
      
      
      
       request.setAttribute("poruka", "Uspesno ste obrisali narudzbenicu!");
  
       return new NarudzbenicaListaCommand().execute(request);
}
}