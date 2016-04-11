

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


public class NarudzbenicaDetalji {

 
 Narudzbenica kat = new Narudzbenica();
 StavkaNarudzbenice sk = new StavkaNarudzbenice();
Katalog k =  new Katalog();
 Artikal artikal = new Artikal();
 JedinicaMere jm = new JedinicaMere();


    private Integer redniBrojStavkeNarudzbenice = sk.getRedniBroj();
    private Integer redniBrojNarudzbenice = kat.getSifraNarudzbenice();
    private Integer redniBrojKataloga = kat.getSifraKataloga();
    private String nazivKataloga = k.getNazivKataloga();
    private String nazivDobavljaca = kat.getNazivDobavljaca();
    private Integer sifraDobavljaca = kat.getSifraDobavljaca();
    private Integer artikalId = artikal.getSifraArtikla();
    private String nazivArtikla = artikal.getNazivArtikla();
    private String datum = kat.getDatum();
    private String nazivJm = jm.getNazivJediniceMere();
    private Integer kolicina = sk.getKolicina();

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }
    
    

    public String getNazivJm() {
        return nazivJm;
    }

    public void setNazivJm(String nazivJm) {
        this.nazivJm = nazivJm;
    }

    
    public Katalog getK() {
        return k;
    }

    public void setK(Katalog k) {
        this.k = k;
    }

    public String getNazivKataloga() {
        return nazivKataloga;
    }

    public void setNazivKataloga(String nazivKataloga) {
        this.nazivKataloga = nazivKataloga;
    }

    
    
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    
    
    public Narudzbenica getKat() {
        return kat;
    }

    public void setKat(Narudzbenica kat) {
        this.kat = kat;
    }

    public StavkaNarudzbenice getSk() {
        return sk;
    }

    public void setSk(StavkaNarudzbenice sk) {
        this.sk = sk;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Integer getRedniBrojStavkeNarudzbenice() {
        return redniBrojStavkeNarudzbenice;
    }

    public void setRedniBrojStavkeNarudzbenice(Integer redniBrojStavkeNarudzbenice) {
        this.redniBrojStavkeNarudzbenice = redniBrojStavkeNarudzbenice;
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

    public Integer getRedniBrojNarudzbenice() {
        return redniBrojNarudzbenice;
    }

    public void setRedniBrojNarudzbenice(Integer redniBrojNarudzbenice) {
        this.redniBrojNarudzbenice = redniBrojNarudzbenice;
    }
    
    
    
    public NarudzbenicaDetalji() {
    }

    
          public static List<NarudzbenicaDetalji> findByParamsStavke (String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT S.REDNI_BR ,S.KOLICINA, K.SIFRA_NARUDZBENICE, K.NAZIV_DOBAVLJACA, K.SIFRA_DOBAVLJACA ,K.SIFRA_KATALOGA, K.DATUM, A.SIFRA_ARTIKLA, A.NAZIV_ARTIKLA, J.NAZIV_JM from STAVKA_NARUDZBENICE S,NARUDZBENICA K, ARTIKAL A , JEDINICA_MERE J WHERE S.SIFRA_NARUDZBENICE = K.SIFRA_NARUDZBENICE AND S.SIFRA_ARTIKLA = a.sifra_artikla AND A.SIFRA_JM = J.SIFRA_JM";
              if (parameterName != null) {
                SQL += " AND " + parameterName + " =? ORDER BY S.REDNI_BR";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<NarudzbenicaDetalji> lista = new ArrayList<NarudzbenicaDetalji>();
            NarudzbenicaDetalji artikli;
            while (rs.next()) {
                artikli = new NarudzbenicaDetalji();
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
         
        public static List<NarudzbenicaDetalji> findAll() throws SQLException {
        return findByParamsStavke(null, null);
    }          
          

          public NarudzbenicaDetalji setFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            setRedniBrojStavkeNarudzbenice(resultSet.getInt(1));
            setKolicina(resultSet.getInt(2));
            setRedniBrojNarudzbenice(resultSet.getInt(3));
            setNazivDobavljaca(resultSet.getString(4));
            setSifraDobavljaca(resultSet.getInt(5));
            setRedniBrojKataloga(resultSet.getInt(6));
            setDatum(resultSet.getString(7));
            setArtikalId(resultSet.getInt(8));
            setNazivArtikla(resultSet.getString(9));
            setNazivJm(resultSet.getString(10));
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

          public NarudzbenicaDetalji setFromResultSetNarudzbenica(ResultSet resultSet) throws SQLException {
        try {  
            setRedniBrojNarudzbenice(resultSet.getInt(1));
            setDatum(resultSet.getString(2));
            setNazivDobavljaca(resultSet.getString(3));
            setNazivKataloga(resultSet.getString(4));
         
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
          
          
        public static NarudzbenicaDetalji findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("K.SIFRA_NARUDZBENICE", id);
    }

    public static NarudzbenicaDetalji findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<NarudzbenicaDetalji> artikli = findByParamsStavke(parameterName, parameterValue);
        if (artikli.size() > 0) {
            return artikli.get(0);
        } else {
            return null;
        }
    }
          
  
          public static List<NarudzbenicaDetalji> findByParametersNarudzbenica(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = " SELECT K.SIFRA_NARUDZBENICE, K.DATUM,K.NAZIV_DOBAVLJACA,T.NAZIV_KATALOGA from NARUDZBENICA K, KATALOG T  WHERE K.SIFRA_KATALOGA = T.SIFRA_KATALOGA";
              if (parameterName != null) {
                SQL += " and " + parameterName + " =? order by K.SIFRA_NARUDZBENICE";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<NarudzbenicaDetalji> lista = new ArrayList<NarudzbenicaDetalji>();
            NarudzbenicaDetalji artikli;
            while (rs.next()) {
                artikli = new NarudzbenicaDetalji();
                artikli.setFromResultSetNarudzbenica(rs);
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

