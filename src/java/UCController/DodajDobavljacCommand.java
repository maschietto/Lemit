package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.BrojZgrade;
import model.Dobavljac;

import util.Validate;
import model.Korisnik;
import model.Mesto;
import model.Ulica;

public class DodajDobavljacCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
  
        String next="UnosDobavljaca";
        
        //Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
        // String korisnikid = String.valueOf(logged.getId_korisnika());
        
        String dobavljacId = request.getParameter("id");
        String nazivDob = request.getParameter("nazivDob");
        String mesto_id = request.getParameter("mesto_id");
        String nazivMesta = request.getParameter("naziv_mesta");
        String ulicaId = request.getParameter("ulica_id");
        String nazivUlice = request.getParameter("naziv_ulice");
        String brojZgrade = request.getParameter("broj_zgrade");
        String borojZgradeId = request.getParameter("broj_zgrade_id");
      
        boolean valid = Validate.exists(nazivDob)
                && Validate.exists(nazivMesta) && Validate.exists(nazivUlice) && Validate.exists(brojZgrade);

    
        if (!valid) {
            request.setAttribute("poruka", "Unesite sve potrebne podatke!");
            return "UnosDobavljaca";
      
        }else{
            
            boolean update = false;
            Dobavljac  dob;
                Mesto mes;
                Ulica ul;
                BrojZgrade brzg;
                
            
            if (dobavljacId == null && mesto_id == null && ulicaId == null && borojZgradeId == null) {
           
                dob = new Dobavljac();
                mes = new Mesto();
                ul = new Ulica();
                brzg = new BrojZgrade();

            } else {
                dob = Dobavljac.findByPrimaryKey(Integer.valueOf(dobavljacId));
                mes = Mesto.findByPrimaryKey(Integer.valueOf(mesto_id));
                ul = Ulica.findByPrimaryKey(Integer.valueOf(ulicaId));
                brzg = BrojZgrade.findByPrimaryKey(Integer.valueOf(borojZgradeId));
                update=true;
            }

            dob.setNazivDobavljaca(nazivDob);
            dob.setDobavljacOpis("NEMA OPISA");
            mes.setNazivMesta(nazivMesta);
            ul.setNazivUlice(nazivUlice);
            brzg.setBrojZgrade(brojZgrade);

            mes.saveOrUpdate();
            ul.saveOrUpdate();
            brzg.saveOrUpdate();
            dob.saveOrUpdate();
           
            
            
             request.getSession().setAttribute("dobavljac", dob);
           
        }
            
       
//            
//           Dobavljac novi = null;
//           List<Dobavljac> lista = Dobavljac.findAll();
//           for(Dobavljac i : lista){
//               if(i.getNazivDobavljaca().equals(nazivDob)  
//                       && i.getMestoID().equals(mesto_id)
//                        ){novi = i;}
//           }
//            request.getSession().setAttribute("slikaid", novi.getSifraDobavljaca());
//            request.setAttribute("poruka", "Uspesno ste upisali podatke");
//        
//            next = "UploadujSliku";
//        } else {
//            request.setAttribute("poruka", "Unesite sve potrebne podatke!");
//            next = new InstrumentCommand().execute(request);
//        }
      
       
        
        request.setAttribute("poruka", "Uspesno ste uneli sve potrebne podatke za identifikaciju Dobavljaca! HVALA!");
    
    return next;
      
    }   
    
}
