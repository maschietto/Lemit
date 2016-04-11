

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Dobavljac.findByParameter;
import util.DB_broker;


public class KatalogDetalji {

 
 Katalog kat = new Katalog();
 StavkaKataloga sk = new StavkaKataloga();
 Dobavljac dob = new Dobavljac();
 Artikal artikal = new Artikal();


    private Integer redniBrojStavkeKataloga = sk.getStavkaKatalogaid();
    private Integer redniBrojKataloga = kat.getSifraKataloga();
    private String nazivKataloga = kat.getNazivKataloga();
    private String nazivDobavljaca = dob.getNazivDobavljaca();
    private Integer sifraDobavljaca = dob.getSifraDobavljaca();
    private Integer artikalId = artikal.getSifraArtikla();
    private String nazivArtikla = artikal.getNazivArtikla();
    
    public String getNazivKataloga() {
        return nazivKataloga;
    }

    public void setNazivKataloga(String nazivKataloga) {
        this.nazivKataloga = nazivKataloga;
    }
  

    public Katalog getKat() {
        return kat;
    }

    public void setKat(Katalog kat) {
        this.kat = kat;
    }

    public StavkaKataloga getSk() {
        return sk;
    }

    public void setSk(StavkaKataloga sk) {
        this.sk = sk;
    }

    public Dobavljac getDob() {
        return dob;
    }

    public void setDob(Dobavljac dob) {
        this.dob = dob;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Integer getRedniBrojStavkeKataloga() {
        return redniBrojStavkeKataloga;
    }

    public void setRedniBrojStavkeKataloga(Integer redniBrojStavkeKataloga) {
        this.redniBrojStavkeKataloga = redniBrojStavkeKataloga;
    }

    public Integer getRedniBrojKataloga() {
        return redniBrojKataloga;
    }

    public void setRedniBrojKataloga(Integer redniBrojKataloga) {
        this.redniBrojKataloga = redniBrojKataloga;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public Integer getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(Integer sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }

    public Integer getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(Integer artikalId) {
        this.artikalId = artikalId;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }
  


   
   
   
    public KatalogDetalji() {
    }

    
          public static List<KatalogDetalji> findByParams (String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT S.REDNI_BR,K.NAZIV_KATALOGA, K.SIFRA_KATALOGA,D.NAZIV_DOBAVLJACA, D.SIFRA_DOBAVLJACA,A.SIFRA_ARTIKLA,A.NAZIV_ARTIKLA from STAVKA_KATALOGA S,KATALOG K, DOBAVLJAC D , ARTIKAL A WHERE S.SIFRA_KATALOGA = K.SIFRA_KATALOGA AND k.SIFRA_DOBAVLJACA = D.SIFRA_DOBAVLJACA AND S.SIFRA_ARTIKLA = a.sifra_artikla";
              if (parameterName != null) {
                SQL += " AND " + parameterName + " =? ORDER BY S.REDNI_BR";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<KatalogDetalji> lista = new ArrayList<KatalogDetalji>();
            KatalogDetalji artikli;
            while (rs.next()) {
                artikli = new KatalogDetalji();
                artikli.setFromResultSet(rs);
                lista.add(artikli);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }
         
        public static List<KatalogDetalji> findAll() throws SQLException {
        return findByParams(null, null);
    }          
          
//          private static List<DobavljacDetalji> mapToListFromResultSet(ResultSet resultSet) throws SQLException {
//        List<DobavljacDetalji> dob = new ArrayList<DobavljacDetalji>();
//        try {
//            while (resultSet.next()) {
//                DobavljacDetalji dobi = new DobavljacDetalji();
//                dobi.setFromResultSet(resultSet);
//                dob.add(dobi);
//            }
//            resultSet.close();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return dob;
//    }
          
          public KatalogDetalji setFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            setRedniBrojStavkeKataloga(resultSet.getInt(1));
            setNazivKataloga(resultSet.getString(2));
            setRedniBrojKataloga(resultSet.getInt(3));
            setNazivDobavljaca(resultSet.getString(4));
            setSifraDobavljaca(resultSet.getInt(5));
            setArtikalId(resultSet.getInt(6));
            setNazivArtikla(resultSet.getString(7));
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
              public KatalogDetalji setFromResultSetKatalog(ResultSet resultSet) throws SQLException {
        try {  
            setNazivKataloga(resultSet.getString(1));
            setRedniBrojKataloga(resultSet.getInt(2));
            setNazivDobavljaca(resultSet.getString(3));
            setSifraDobavljaca(resultSet.getInt(4));
            

            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
          
          
        public static KatalogDetalji findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("K.SIFRA_KATALOGA", id);
    }

    public static KatalogDetalji findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<KatalogDetalji> artikli = findByParams(parameterName, parameterValue);
        if (artikli.size() > 0) {
            return artikli.get(0);
        } else {
            return null;
        }
    }
          
  
          public static List<KatalogDetalji> findByParametersKatalog (String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = " SELECT K.NAZIV_KATALOGA, K.SIFRA_KATALOGA,D.NAZIV_DOBAVLJACA, D.SIFRA_DOBAVLJACA from KATALOG K, DOBAVLJAC D where k.naziv_dobavljaca = d.naziv_dobavljaca";
              if (parameterName != null) {
                SQL += " and " + parameterName + " =? order by k.sifra_kataloga";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<KatalogDetalji> lista = new ArrayList<KatalogDetalji>();
            KatalogDetalji artikli;
            while (rs.next()) {
                artikli = new KatalogDetalji();
                artikli.setFromResultSetKatalog(rs);
                lista.add(artikli);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }
         
    
    
 
          
}

