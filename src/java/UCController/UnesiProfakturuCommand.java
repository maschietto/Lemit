
package UCController;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import model.Profaktura;
import model.ProfakturaDetalji;
import model.StavkaKataloga;
import model.StavkaNarudzbenice;
import model.StavkaProfakture;
import org.jboss.weld.servlet.SessionHolder;


public class UnesiProfakturuCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException  {
     
        //Integer brojac = (Integer) request.getSession().getAttribute("brojac");
        
      String datum = request.getParameter("datum");
      String sifranarudzbenice = request.getParameter("sifranarudzbenice");
      String sifradobavljaca = request.getParameter("sifradobavljaca");
      
     
     
        Profaktura profa  ;
        profa = Profaktura.findUniqueZaPoslednjiParametar();
        if(profa == null){
            profa = new Profaktura();
            profa.setSifraProfakture(1);
            profa.setSifraDobavljaca(Integer.valueOf(sifradobavljaca));
            profa.setSifraNarudzbenice(Integer.valueOf(sifranarudzbenice));
            profa.setDatum(datum);
          
        }else{
            Integer sifraProfloc = profa.getSifraProfakture();
           profa.setSifraProfakture(sifraProfloc + 1);
           profa.setSifraDobavljaca(Integer.valueOf(sifradobavljaca));
           profa.setSifraNarudzbenice(Integer.valueOf(sifranarudzbenice));
           profa.setDatum(datum);
        }
        
        profa.save();
        
        
        List<StavkaNarudzbenice> sn = StavkaNarudzbenice.findByParameter("sifra_narudzbenice", sifranarudzbenice);
        
        List<Artikal> art = Artikal.findAll();
        
        
        for(StavkaNarudzbenice stavka : sn){   
            
           
             StavkaProfakture sk =  new StavkaProfakture();
           
             sk.setRedniBroj(sn.indexOf(stavka));
             sk.setSifraProfakture(profa.getSifraProfakture());
             sk.setKolicina(Integer.valueOf(stavka.getKolicina()));
             sk.setSifraArtikla(stavka.getSifraArtikla());
             sk.saveStavka();
        }         
       
        request.getSession().removeAttribute("brojac");
       
        List<ProfakturaDetalji> sk = ProfakturaDetalji.findByParamsStavke("P.SIFRA_PROFAKTURE", profa.getSifraProfakture());
        
        Profaktura profakturica = Profaktura.findByPrimaryKey(profa.getSifraProfakture());
        
        request.getSession().setAttribute("profaktura_new", profakturica);
  
        request.setAttribute("poruka", "Uneli ste profakturu!");
       
       
       request.setAttribute("lista_stavki_profakture", sk);
        
       return "ProfakturaMain";
    }

    private SimpleDateFormat SimpleDateFormat(String ddMMyyyy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
