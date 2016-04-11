
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
import model.NarudzbenicaDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;
import model.Ulica;



public class StavkaNarudzbeniceDeleteCommand implements Command {
    


    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
       
//        KatalogDetalji kat = (KatalogDetalji) request.getSession().getAttribute("katalog");
        
       String stNariI = request.getParameter("idbrisi");
              
       
       List<NarudzbenicaDetalji> listaStavkeNarudzbenice;
       listaStavkeNarudzbenice = NarudzbenicaDetalji.findAll();
       
       
       
       StavkaNarudzbenice toBeDelated = StavkaNarudzbenice.findByPrimaryKey(Integer.valueOf(stNariI));
       
      try{ 
       
          if(listaStavkeNarudzbenice.size() > 1){
            toBeDelated.delete(); request.setAttribute("poruka", "USPESNO STE OBRISALI STAVKU KATALOGA!");}
          else{request.setAttribute("poruka", " NE MOZE OSTATI PRAZAN KATALOG!");}
      }catch(SQLException ex){ex.printStackTrace();}
      
      
     //  request.setAttribute("poruka", "USPESNO STE OBRISALI STAVKA KATALOGA!");
  
        return new UnesiNarudzbenicuCommand().execute(request);
    }
    
}
