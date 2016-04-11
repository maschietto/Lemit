

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


public class ProfakturaDetalji {

 
 Profaktura pro = new Profaktura();
 StavkaProfakture sp = new StavkaProfakture();
 Katalog k =  new Katalog();
 Artikal artikal = new Artikal();
 JedinicaMere jm = new JedinicaMere();
 
 Integer cena;
 Integer cenaStavke;

    public Integer getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(Integer cenaStavke) {
        this.cenaStavke = cenaStavke;
    }
 
 

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

 

    private Integer redniBrojStavkeProfakture = sp.getRedniBroj();
    private Integer sifraProfakture = pro.getSifraProfakture();
    private Integer redniBrojNarudzbenice = pro.getSifraNarudzbenice();
    private Integer sifraNarudzbenice = pro.getSifraNarudzbenice();
    private String nazivKataloga = k.getNazivKataloga();
    private String nazivDobavljaca = k.getNazivDobavljaca();
    private Integer sifraDobavljaca = k.getSifraDobavljaca();
    private Integer artikalId = artikal.getSifraArtikla();
    private String nazivArtikla = artikal.getNazivArtikla();
    private String datum = pro.getDatum();
    private String nazivJm = jm.getNazivJediniceMere();
    private Integer kolicina = sp.getKolicina();

    public Profaktura getPro() {
        return pro;
    }

    public void setPro(Profaktura pro) {
        this.pro = pro;
    }

    public StavkaProfakture getSp() {
        return sp;
    }

    public void setSp(StavkaProfakture sp) {
        this.sp = sp;
    }

    public Katalog getK() {
        return k;
    }

    public void setK(Katalog k) {
        this.k = k;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public JedinicaMere getJm() {
        return jm;
    }

    public void setJm(JedinicaMere jm) {
        this.jm = jm;
    }

    public Integer getRedniBrojStavkeProfakture() {
        return redniBrojStavkeProfakture;
    }

    public void setRedniBrojStavkeProfakture(Integer redniBrojStavkeProfakture) {
        this.redniBrojStavkeProfakture = redniBrojStavkeProfakture;
    }

    public Integer getSifraProfakture() {
        return sifraProfakture;
    }

    public void setSifraProfakture(Integer sifraProfakture) {
        this.sifraProfakture = sifraProfakture;
    }

    public Integer getRedniBrojNarudzbenice() {
        return redniBrojNarudzbenice;
    }

    public void setRedniBrojNarudzbenice(Integer redniBrojNarudzbenice) {
        this.redniBrojNarudzbenice = redniBrojNarudzbenice;
    }

    public Integer getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Integer sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    public String getNazivKataloga() {
        return nazivKataloga;
    }

    public void setNazivKataloga(String nazivKataloga) {
        this.nazivKataloga = nazivKataloga;
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

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getNazivJm() {
        return nazivJm;
    }

    public void setNazivJm(String nazivJm) {
        this.nazivJm = nazivJm;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }
    
   

 

    
          public static List<ProfakturaDetalji> findByParamsStavke (String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT SP.REDNI_BR ,sp.cena_stavke, SP.KOLICINA, P.SIFRA_PROFAKTURE, P.SIFRA_NARUDZBENICE, K.NAZIV_KATALOGA, K.NAZIV_DOBAVLJACA ,K.SIFRA_DOBAVLJACA, A.SIFRA_ARTIKLA, A.NAZIV_ARTIKLA, P.DATUM, J.NAZIV_JM, SP.KOLICINA from STAVKA_PROFAKTURE SP,PROFAKTURA P, NARUDZBENICA N, ARTIKAL A , JEDINICA_MERE J, KATALOG K WHERE P.SIFRA_PROFAKTURE = SP.SIFRA_PROFAKTURE AND SP.SIFRA_ARTIKLA = a.sifra_artikla AND A.SIFRA_JM = J.SIFRA_JM and n.sifra_kataloga = k.sifra_kataloga and p.sifra_narudzbenice = n.sifra_narudzbenice";
              if (parameterName != null) {
                SQL += " AND " + parameterName + " =? ORDER BY SP.REDNI_BR";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<ProfakturaDetalji> lista = new ArrayList<ProfakturaDetalji>();
            ProfakturaDetalji artikli;
            while (rs.next()) {
                artikli = new ProfakturaDetalji();
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
         
        public static List<ProfakturaDetalji> findAll() throws SQLException {
        return findByParamsStavke(null, null);
    }          
          

          public ProfakturaDetalji setFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            setRedniBrojStavkeProfakture(resultSet.getInt(1));
            setCenaStavke(resultSet.getInt(2));
            setKolicina(resultSet.getInt(3));
            setSifraProfakture(resultSet.getInt(4));
            setSifraNarudzbenice(resultSet.getInt(5));
            setNazivKataloga(resultSet.getString(6));
            setNazivDobavljaca(resultSet.getString(7));
            setSifraDobavljaca(resultSet.getInt(8));
            setArtikalId(resultSet.getInt(9));
            setNazivArtikla(resultSet.getString(10));
            setDatum(resultSet.getString(11));
            setNazivJm(resultSet.getString(12));
            setKolicina(resultSet.getInt(13));
            
         
            
            
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }

 
          
        public static ProfakturaDetalji findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("K.SIFRA_PROFAKTURE", id);
    }

    public static ProfakturaDetalji findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<ProfakturaDetalji> artikli = findByParamsStavke(parameterName, parameterValue);
        if (artikli.size() > 0) {
            return artikli.get(0);
        } else {
            return null;
        }
    }
          
  
          public static List<ProfakturaDetalji> findByParametersProfaktura(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = " SELECT K.SIFRA_NARUDZBENICE, P.SIFRA_PROFAKTURE, P.DATUM,K.SIFRA_DOBAVLJACA, K.NAZIV_DOBAVLJACA, T.NAZIV_KATALOGA from NARUDZBENICA K, PROFAKTURA P, KATALOG T  WHERE K.SIFRA_NARUDZBENICE = P.SIFRA_NARUDZBENICE";
              if (parameterName != null) {
                SQL += " and " + parameterName + " =? order by K.SIFRA_NARUDZBENICE";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<ProfakturaDetalji> lista = new ArrayList<ProfakturaDetalji>();
            ProfakturaDetalji artikli;
            while (rs.next()) {
                artikli = new ProfakturaDetalji();
                artikli.setFromResultSetProfaktura(rs);
                lista.add(artikli);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

           public ProfakturaDetalji setFromResultSetProfaktura(ResultSet resultSet) throws SQLException {
        try {  
            setSifraNarudzbenice(resultSet.getInt(1));
            setSifraProfakture(resultSet.getInt(2));
            setDatum(resultSet.getString(3));
            setSifraDobavljaca(resultSet.getInt(4));
            setNazivDobavljaca(resultSet.getString(5));
            setNazivKataloga(resultSet.getString(6));
            
         
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
          
          
}

