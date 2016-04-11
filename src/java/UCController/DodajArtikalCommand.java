package UCController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Artikal;
import model.BrojZgrade;
import model.CenaArtikla;
import model.Dobavljac;
import model.JedinicaMere;

import util.Validate;
import model.Korisnik;
import model.Mesto;
import model.Ulica;

public class DodajArtikalCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws SQLException {
  
        String next="UnosArtikla";
        
        // Korisnik logged = (Korisnik) request.getSession().getAttribute("korisnik");

        
        
        
       // String korisnikid = String.valueOf(logged.getId_korisnika());
        String artikalid = request.getParameter("id_artikla");
        
        String nazivArtikla = request.getParameter("naziv_artikla");
        String ime = request.getParameter("ime");
        String oznaka = request.getParameter("oznaka");
        String jedinicaMere = request.getParameter("jednica_mere");
        String cena = request.getParameter("cena");
        String datumUnosa = request.getParameter("datum_unosa");
        String isKilogram = request.getParameter("kilogrami");
        String uKilogramima = request.getParameter("u_kilogramima");
      
        boolean valid = Validate.exists(nazivArtikla)
                && Validate.exists(ime) && Validate.exists(jedinicaMere) && Validate.exists(cena) && Validate.exists(datumUnosa);

    
        if (!valid) {
            request.setAttribute("poruka", "Unesite sve potrebne podatke!");
            return "UnosArtikla";
      
        }else{
         
    boolean update=false; 
    
                Artikal ar;
                CenaArtikla cen;
                JedinicaMere jm;
              
                if(artikalid==null){
                   
                ar = new Artikal();
                cen = new CenaArtikla();
                jm= new JedinicaMere();
           
                } else {
                    
                ar = Artikal.findByPrimaryKey(Integer.valueOf(artikalid));
                //cen = CenaArtikla.findByPrimaryKey(Integer.valueOf(artikalid));
               cen =  new CenaArtikla();
                jm = JedinicaMere.findByPrimaryKey(Integer.valueOf(artikalid));
                update=true;
            }
                
                
     

            ar.setNazivArtikla(nazivArtikla);
            ar.setIme(ime);
            ar.setOznaka(Integer.valueOf(oznaka));
            cen.setSifraArtikla(ar.getSifraArtikla());
            cen.setDatumOd(datumUnosa);
            cen.setCena(Integer.valueOf(cena));
            jm.setNazivJediniceMere(jedinicaMere);
            jm.setKilogram(isKilogram);
            jm.setuKilogramima(Integer.valueOf(uKilogramima));
            

           
           jm.saveOrUpdate();
           ar.saveOrUpdate();
           cen.save();
       
           
           
           
        }
            
      


        request.setAttribute("poruka", "Uspesno ste uredili Artikal");
    
    return next;
      
    }   
    
}
