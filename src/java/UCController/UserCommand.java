package UCController;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;

import util.Validate;
import javax.servlet.http.HttpServletRequest;
import util.DB_broker;

public class UserCommand implements Command  {

    
   @Override
   public String execute(HttpServletRequest request) throws SQLException{

       boolean x=false;
       String next;

        String id = request.getParameter("id");
        String naziv = request.getParameter("naziv");
        String jmbg = request.getParameter("jmbg");
      
        String telefon = request.getParameter("tel");
        String sifra = request.getParameter("pwd");
        String sifraPOnovi = request.getParameter("con_pwd");

     
        boolean valid = Validate.exists(naziv) && Validate.exists(jmbg)
             //   && Validate.exists(passwordPonovo)
                && Validate.exists(telefon) && Validate.exists(sifra)
                && Validate.exists(sifraPOnovi) && sifra.equals(sifraPOnovi) ;
        
        Korisnik k =  Korisnik.findLastInBase();
                //&& password.equals(passwordPonovo);

        if (!valid) {
            request.setAttribute("poruka", "Unesite sve potrebne podatke!");
            return new EditUserCommand().execute(request);
         
        } else {
            Korisnik editable;
            boolean update = false;
            if (Validate.exists(id)) {
                //update postojeceg korisnika
                editable = Korisnik.findByPrimaryKey(Integer.valueOf(id));
                update = true;
            } else {
    
                editable = Korisnik.findUnique("NAZIV_KORISNIKA", naziv);
    
                if (editable != null) {
                    request.setAttribute("poruka", "Izabrano korisničko ime već postoji!");
                    return new EditUserCommand().execute(request);
                }
                editable = new Korisnik();
            }
            
            if(editable == null){
            editable.setId_korisnika(k.getId_korisnika() + 1);
            }else{
            editable.setId_korisnika(editable.getId_korisnika());
            }
            editable.setNazivKorisnika(naziv);
            editable.setJmbg(jmbg);    
            editable.setKontaktTelefon(telefon);
            editable.setIsAdmin("FALSE");
            editable.setPassword(sifra);
            editable.setAktivan("FALSE");

            
            Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");
            if (logged != null && logged.getIsAdmin().equals("FALSE")) {
                editable.setAktivan("TRUE");
            } else {
                editable.setAktivan("FALSE");
            }
            
             try{     
            editable.saveOrUpdate();
               x=true;
             }catch(SQLException ex){
             ex.printStackTrace();
             x=false;
             }
             
             if(x == true){
             DB_broker.commitTransaction();
             }else {
             DB_broker.rollbackTransaction();
             }
            
            if (logged != null && logged.getIsAdmin().equals("TRUE")) {
                return new UserListCommand().execute(request);
            } else {
                request.setAttribute("poruka", "Uspešno ste se registrovali. "
                        + "Morate sačekati da administrator odobri vašu prijavu.");
                next = "index";               
            }
        }
        return next;
    }
}
