
package UCController;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import model.BrojZgrade;
import model.Dobavljac;

import model.Korisnik;
import model.Mesto;
import model.Ulica;



public class DobavljacDeleteCommand implements Command {
    


    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
        
        
        String dobId = request.getParameter("id_delete");
      // String dobavljacIme = request.getParameter("naziv_dobavljaca");
       
       Dobavljac toBeDelated = Dobavljac.findByPrimaryKey(Integer.valueOf(dobId));
       Ulica UltoBeDeleted = Ulica.findByPrimaryKey(Integer.valueOf(dobId));
       Mesto MesToBeDeleted = Mesto.findByPrimaryKey(Integer.valueOf(dobId));
       BrojZgrade brzgToBeDeleted = BrojZgrade.findByPrimaryKey(Integer.valueOf(dobId));
      try{ 
       brzgToBeDeleted.deleteBrojZgrade();
       UltoBeDeleted.deleteUlica();
       toBeDelated.delete();
       MesToBeDeleted.deleteMesto();
      }catch(SQLException ex){ex.printStackTrace();}
      
      
       request.setAttribute("poruka", "USPESNO STE OBRISALI DOBAVLJACA!");
  
        return new DobavljaciListaCommand().execute(request);
    }
    
}
