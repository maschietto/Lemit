package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.BrojZgrade;
import model.Dobavljac;
import model.DobavljacDetalji;

import util.Validate;
import model.Korisnik;
import model.Mesto;
import model.Ulica;

public class UpdateDobavljacCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
  
    
        String dobavljacId = request.getParameter("id");
        String nazivDob = request.getParameter("naziv_dobavljaca");
        String nazivMesta = request.getParameter("naziv_mesta");
        String nazivUlice = request.getParameter("naziv_ulice");
        String brojZgrade = request.getParameter("broj");
        String dobavljacOpis = request.getParameter("dobavljac_opis");
       
                Dobavljac  dob;
                Mesto mes;
                Ulica ul;
                BrojZgrade brzg;
             
                dob = Dobavljac.findByPrimaryKey(Integer.valueOf(dobavljacId));
                mes = Mesto.findByPrimaryKey(Integer.valueOf(dobavljacId));
                ul = Ulica.findByPrimaryKey(Integer.valueOf(dobavljacId));
                brzg = BrojZgrade.findByPrimaryKey(Integer.valueOf(dobavljacId));
               
            
                
            dob.setSifraDobavljaca(Integer.valueOf(dobavljacId));
            dob.setNazivDobavljaca(nazivDob);
            dob.setMestoID(Integer.valueOf(dobavljacId));
            dob.setDobavljacOpis(dobavljacOpis);
            mes.setMesto_id(Integer.valueOf(dobavljacId));
            mes.setNazivMesta(nazivMesta);
            ul.setNazivUlice(nazivUlice);
            ul.setUlicaId(Integer.valueOf(dobavljacId));
            ul.setMestoId(Integer.valueOf(dobavljacId));
            brzg.setBrojZgrade(brojZgrade);
            brzg.setBrZgradeId(Integer.valueOf(dobavljacId));
            brzg.setUlicaId(Integer.valueOf(dobavljacId));
        
            boolean valid = true;
            
         if(valid){
          
           mes.saveOrUpdate();
           ul.saveOrUpdate();
           brzg.saveOrUpdate();
           dob.saveOrUpdate();
           
      
           request.setAttribute("poruka", "Uspesno ste updatovali Dobavljaca!");
           List<DobavljacDetalji> listaDobavljaca = new ArrayList<DobavljacDetalji>();
            listaDobavljaca = DobavljacDetalji.findAll();
           request.setAttribute("listaDobavljaca", listaDobavljaca);
         
            return "ListaDobavljaca";
          
         }else{
             
           request.setAttribute("poruka", "Ponovite azuriranje!");
           
         }
         request.getSession().removeAttribute("dobavljacShow");
          List<DobavljacDetalji> listaDobavljaca = new ArrayList<DobavljacDetalji>();
            listaDobavljaca = DobavljacDetalji.findAll();
           request.setAttribute("listaDobavljaca", listaDobavljaca);
       return "ListaDobavljaca";
    }
}
