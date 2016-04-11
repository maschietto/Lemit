
package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.BrojZgrade;
import model.Dobavljac;
import model.KatalogDetalji;

import model.Korisnik;
import model.Mesto;
import model.StavkaKataloga;
import model.Ulica;



public class StavkaKatalogaDeleteCommand implements Command {
    


    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
//        KatalogDetalji kat = (KatalogDetalji) request.getSession().getAttribute("katalog");
        
       String stkatid = request.getParameter("idbrisi");
              
       
       List<KatalogDetalji> listaStavkeKataloga;
       listaStavkeKataloga = KatalogDetalji.findAll();
       
       
       
       StavkaKataloga toBeDelated = StavkaKataloga.findByPrimaryKey(Integer.valueOf(stkatid));
       
      try{ 
       
          if(listaStavkeKataloga.size() > 1){
            toBeDelated.delete(); request.setAttribute("poruka", "USPESNO STE OBRISALI STAVKU KATALOGA!");}
          else{request.setAttribute("poruka", " NE MOZE OSTATI PRAZAN KATALOG!");}
      }catch(SQLException ex){ex.printStackTrace();}
      
      
     //  request.setAttribute("poruka", "USPESNO STE OBRISALI STAVKA KATALOGA!");
  
        return new KatalogMainCommand().execute(request);
    }
    
}
