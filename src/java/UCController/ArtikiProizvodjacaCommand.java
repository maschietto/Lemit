
package UCController;
import java.sql.SQLException;
import java.util.ArrayList;
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
import servisi.ArtikliWebService;
import servisi.ArtikliWebService_Service;


public class ArtikiProizvodjacaCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
       
        ArtikliWebService_Service art = new ArtikliWebService_Service();
        ArtikliWebService ws = art.getArtikliWebServicePort();
      
        request.setAttribute("listaProizvodaProizvodjaca",  ws.vratiSamoNAziveArtikala());
        
        return "ListaArtikalaProizvodjaca";
    }
    
}
