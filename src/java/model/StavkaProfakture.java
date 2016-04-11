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

public class StavkaProfakture {

private Integer redniBroj;
private Integer sifraProfakture;
private Integer sifraArtikla;
private Integer kolicina;
private Integer cenaStavke;






 

  

    public Integer getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Integer redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Integer getSifraProfakture() {
        return sifraProfakture;
    }

    public void setSifraProfakture(Integer sifraProfakture) {
        this.sifraProfakture = sifraProfakture;
    }

    public Integer getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Integer sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public Integer getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(Integer cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

     public StavkaProfakture setFromResultSet(ResultSet rs) {
        try {
            setRedniBroj(rs.getInt(1));
            setSifraProfakture(rs.getInt(2));
            setSifraArtikla(rs.getInt(3));
            setKolicina(rs.getInt(4));
            setCenaStavke(rs.getInt(5));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }
    
    
    
    
    
    
    
    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setInt(1, redniBroj);
            ps.setInt(2, sifraProfakture);
            ps.setInt(3, sifraArtikla);
            ps.setInt(4, kolicina);
     
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
   public void saveStavka() {
        try {
            String SQL = "INSERT INTO STAVKA_PROFAKTURE (REDNI_BR, SIFRA_PROFAKTURE, SIFRA_ARTIKLA, KOLICINA) VALUES (?,?,?,?)";
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

        String SQL = "DELETE FROM STAVKA_PROFAKTURE  WHERE REDNI_BR=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, redniBroj);
        stat.executeUpdate();
        stat.close();
  }
    
    
  public static List<StavkaProfakture> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM STAVKA_PROFAKTURE";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<StavkaProfakture> lista = new ArrayList<StavkaProfakture>();
            StavkaProfakture artikal;
            while (rs.next()) {
                artikal = new StavkaProfakture();
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

    public static StavkaProfakture findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("REDNI_BR", id);
    }

    public static StavkaProfakture findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<StavkaProfakture> stavka = findByParameter(parameterName, parameterValue);
        if (stavka.size() > 0) {
            return stavka.get(0);
        } else {
            return null;
        }
    }

        public static List<StavkaProfakture> findAll() throws SQLException {
        return findByParameter(null, null);
    }
        
        
        public static List<StavkaProfakture> findPoslednjuStavkuProfakture() throws SQLException {
        try {
            String SQL = "SELECT * FROM STAVKA_PROFAKTURE WHERE REDNI_BR = (SELECT MAX(REDNI_BR) FROM STAVKA_PROFAKTURE)";
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();
            
            StavkaProfakture katalog;
            List<StavkaProfakture> lista = new ArrayList<StavkaProfakture>();
            while(rs.next()){
            katalog = new StavkaProfakture();
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
         
       public static StavkaProfakture findUniqueZaPoslednjiParametar() throws SQLException {
        List<StavkaProfakture> sk = findPoslednjuStavkuProfakture();
        if (sk.size() > 0) {
            return sk.get(0);
        } else {
            return null;
        }
    }    

}
