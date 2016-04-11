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

public class StavkaKataloga {

private Integer stavkaKatalogaid;
private Integer sifraKataloga;
private Integer sifraDobavljaca;
private Integer sifraArtikla;

private String nazivProizvoda;

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }




    public Integer getStavkaKatalogaid() {
        return stavkaKatalogaid;
    }

    public void setStavkaKatalogaid(Integer stavkaKatalogaid) {
        this.stavkaKatalogaid = stavkaKatalogaid;
    }

    public Integer getSifraKataloga() {
        return sifraKataloga;
    }

    public void setSifraKataloga(Integer sifraKataloga) {
        this.sifraKataloga = sifraKataloga;
    }

    public Integer getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(Integer sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }

    public Integer getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Integer sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }



    
    

     public StavkaKataloga() {
    }

   public StavkaKataloga setFromResultSet(ResultSet rs) {
        try {
            setStavkaKatalogaid(rs.getInt(1));
            setSifraKataloga(rs.getInt(2));
            setSifraDobavljaca(rs.getInt(3));
            setSifraArtikla(rs.getInt(4));
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setInt(1, stavkaKatalogaid);
            ps.setInt(2, sifraKataloga);
            ps.setInt(3, sifraDobavljaca);
            ps.setInt(4, sifraArtikla);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
   public void saveStavka() {
        try {
            String SQL = "INSERT INTO STAVKA_KATALOGA (REDNI_BR,SIFRA_KATALOGA,SIFRA_DOBAVLJACA,SIFRA_ARTIKLA) VALUES (?,?,?,?)";
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

        String SQL = "DELETE FROM STAVKA_KATALOGA  WHERE REDNI_BR=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, stavkaKatalogaid);
        stat.executeUpdate();
        stat.close();
  }
    
    
  public static List<StavkaKataloga> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM STAVKA_KATALOGA";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<StavkaKataloga> lista = new ArrayList<StavkaKataloga>();
            StavkaKataloga artikal;
            while (rs.next()) {
                artikal = new StavkaKataloga();
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

    public static StavkaKataloga findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("REDNI_BR", id);
    }

    public static StavkaKataloga findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<StavkaKataloga> stavka = findByParameter(parameterName, parameterValue);
        if (stavka.size() > 0) {
            return stavka.get(0);
        } else {
            return null;
        }
    }

        public static List<StavkaKataloga> findAll() throws SQLException {
        return findByParameter(null, null);
    }
        
        
        public static List<StavkaKataloga> findPoslednjiuStavkuKataloga() throws SQLException {
        try {
            String SQL = "SELECT * FROM STAVKA_KATALOGA WHERE REDNI_BR = (SELECT MAX(REDNI_BR) FROM STAVKA_KATALOGA)";
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();
            
            StavkaKataloga katalog;
            List<StavkaKataloga> lista = new ArrayList<StavkaKataloga>();
            while(rs.next()){
            katalog = new StavkaKataloga();
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
         
       public static StavkaKataloga findUniqueZaPoslednjiParametar() throws SQLException {
        List<StavkaKataloga> sk = findPoslednjiuStavkuKataloga();
        if (sk.size() > 0) {
            return sk.get(0);
        } else {
            return null;
        }
    }    


}
