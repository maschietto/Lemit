package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyleConstants;
import util.DB_broker;

public class StavkaNarudzbenice {

private Integer redniBroj;
private Integer kolicina;
private Integer sifraNarudzbenice;
private Integer sifraArtikla;

private String nazivProizvoda;

private List<Integer> kolicinaZaDBox = new ArrayList<Integer>(500);



    public Integer getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Integer redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Integer getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Integer sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    public Integer getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Integer sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }



     public StavkaNarudzbenice() {
    }

   public StavkaNarudzbenice setFromResultSet(ResultSet rs) {
        try {
            setRedniBroj(rs.getInt(1));
            setKolicina(rs.getInt(2));
            setSifraNarudzbenice(rs.getInt(3));
            setSifraArtikla(rs.getInt(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setInt(1, redniBroj);
            ps.setInt(2, kolicina);
            ps.setInt(3, sifraNarudzbenice);
            ps.setInt(4, sifraArtikla);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
   public void saveStavka() {
        try {
            String SQL = "INSERT INTO STAVKA_NARUDZBENICE (REDNI_BR,KOLICINA,SIFRA_NARUDZBENICE,SIFRA_ARTIKLA) VALUES (?,?,?,?)";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
 
   
       public void delete() throws SQLException {

        String SQL = "DELETE FROM STAVKA_NARUDZBENICE  WHERE REDNI_BR=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, redniBroj);
        stat.executeUpdate();
        stat.close();
  }
    
    
  public static List<StavkaNarudzbenice> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM STAVKA_NARUDZBENICE";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<StavkaNarudzbenice> lista = new ArrayList<StavkaNarudzbenice>();
            StavkaNarudzbenice artikal;
            while (rs.next()) {
                artikal = new StavkaNarudzbenice();
                artikal.setFromResultSet(rs);
                lista.add(artikal);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static StavkaNarudzbenice findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("REDNI_BR", id);
    }

    public static StavkaNarudzbenice findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<StavkaNarudzbenice> stavka = findByParameter(parameterName, parameterValue);
        if (stavka.size() > 0) {
            return stavka.get(0);
        } else {
            return null;
        }
    }

        public static List<StavkaNarudzbenice> findAll() throws SQLException {
        return findByParameter(null, null);
    }
        
        
        public static List<StavkaNarudzbenice> findPoslednjiuStavkuKataloga() throws SQLException {
        try {
            String SQL = "SELECT * FROM STAVKA_NARUDZBENICE WHERE REDNI_BR = (SELECT MAX(REDNI_BR) FROM STAVKA_NARUDZBENICE)";
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();
            
            StavkaNarudzbenice katalog;
            List<StavkaNarudzbenice> lista = new ArrayList<StavkaNarudzbenice>();
            while(rs.next()){
            katalog = new StavkaNarudzbenice();
            katalog.setFromResultSet(rs);
            lista.add(katalog);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }
         
       public static StavkaNarudzbenice findUniqueZaPoslednjiParametar() throws SQLException {
        List<StavkaNarudzbenice> sk = findPoslednjiuStavkuKataloga();
        if (sk.size() > 0) {
            return sk.get(0);
        } else {
            return null;
        }
    }    

       
       public List<Integer> vratiListuBrojeva(){
       
           for(int i=0; i<=500; i++){
           
               kolicinaZaDBox.add(i);
           }
           
         return  kolicinaZaDBox;
       }

}
