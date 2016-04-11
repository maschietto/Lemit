
package UCController;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;
import model.KatalogDetalji;
import model.NarudzbenicaDetalji;


public class NarudzbenicaListaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
               //        String tipInstrumenta = request.getParameter("tipinstrumenta");
           
           Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
           List<NarudzbenicaDetalji> listaNarudzbenica = new ArrayList<NarudzbenicaDetalji>();
           List<Dobavljac> listaDobavljaca;
           listaDobavljaca = Dobavljac.findAll();
           listaNarudzbenica.clear();
           listaNarudzbenica = NarudzbenicaDetalji.findByParametersNarudzbenica(null,null);
           
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           
//           for(NarudzbenicaDetalji det : listaNarudzbenica){
//               try {
//                  String datum;
//                  datum = det.getDatum();
//                  datum = datum.substring(0,10);
//                   Date date = df.parse(det.getDatum());
//                   det.setDatum(df.format(date));
//                   
//               } catch (ParseException ex) {
//                   ex.printStackTrace();
//               }
//           
//           }
           
           
           request.setAttribute("listanarudzbenica", listaNarudzbenica);
           request.setAttribute("listadobavljaca",listaDobavljaca);
        
     return "ListaNarudzbenica";
    }
    
}
