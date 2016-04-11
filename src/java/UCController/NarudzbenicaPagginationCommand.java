
package UCController;
//import com.google.common.cache.CacheLoader;
//import com.sun.jmx.snmp.BerDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Artikal;
import model.ArtikalDetalji;
import model.Dobavljac;
import model.Mesto;
import model.Ulica;
import model.BrojZgrade;
import model.DobavljacDetalji;
import model.Katalog;
import model.KatalogDetalji;
import model.NarudzbenicaDetalji;
import model.StavkaKataloga;
import org.jboss.weld.servlet.SessionHolder;


public class NarudzbenicaPagginationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
      
        
      List<NarudzbenicaDetalji> listaStavkiNarudzbenice = new ArrayList<NarudzbenicaDetalji>();
       List<NarudzbenicaDetalji> listaStavkeNarudzbenicaPrenos = new ArrayList<NarudzbenicaDetalji>();
       Integer narudzbenicaId = Integer.valueOf(request.getParameter("id"));
       
        NarudzbenicaDetalji kd;
        
        kd = NarudzbenicaDetalji.findByPrimaryKey(narudzbenicaId);
        
        List<KatalogDetalji> listaStavkiKataloga;
        listaStavkiKataloga =KatalogDetalji.findByParams("S.SIFRA_KATALOGA", kd.getRedniBrojKataloga());
     
        String pocetnaVrednost =  request.getParameter("firstrow");
       String povecavanje = request.getParameter("rowcount");
       String strana = request.getParameter("page");
       Integer pv;
       Integer pov;
       
//       if(pocetnaVrednost == null){pocetnaVrednost = "0";}
//       if(povecavanje== null){povecavanje = "5";}
       
       
       pv=Integer.valueOf(pocetnaVrednost);
       pov = Integer.valueOf(povecavanje);
       Integer novaVrednostIndexa  = 0;
       
       if(pv <0){pv = 0;pov=5;}
       
       if(strana.equals("NEXT")){ pv+=5; novaVrednostIndexa = pv+pov; }
       if(strana.equals("PREVIOUS")){ novaVrednostIndexa = pv-pov; pv-=5;}
//       else{pv -= pov;}
        if(pv <= 0){pv = 0; novaVrednostIndexa =5;}
        if(pv == novaVrednostIndexa){novaVrednostIndexa +=5;}
     
     listaStavkiNarudzbenice = NarudzbenicaDetalji.findByParamsStavke("S.SIFRA_NARUDZBENICE",narudzbenicaId );
       
       if(listaStavkiNarudzbenice.size()< pv){ pv= pv-5;}
      
       if(listaStavkiNarudzbenice.size() < novaVrednostIndexa){listaStavkeNarudzbenicaPrenos=listaStavkiNarudzbenice.subList(pv, listaStavkiNarudzbenice.size());
    }else{
       listaStavkeNarudzbenicaPrenos = listaStavkiNarudzbenice.subList(pv,novaVrednostIndexa );
       }
    
       request.setAttribute("narudzbenica", kd);
       request.setAttribute("stavke_narudzbenice", listaStavkeNarudzbenicaPrenos);
       request.setAttribute("katalog_stavke", listaStavkiKataloga);
       request.setAttribute("prviunos", pv);
       request.setAttribute("povecavanje", pov);
       
       return "NarudzbenicaMain";
    }
    
}
