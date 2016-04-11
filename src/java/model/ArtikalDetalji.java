

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


public class ArtikalDetalji {
    
 Artikal artikal =new Artikal();
 CenaArtikla cena =  new CenaArtikla();
 JedinicaMere jm = new JedinicaMere();
 Ime_objekta i =  new Ime_objekta();
 
 
    private Integer aktuelnaCena = artikal.getAktuelnaCena();
    private Integer artikalId = artikal.getSifraArtikla();
    private String nazivArtikla = artikal.getNazivArtikla();
    private String imeArtikla = artikal.getIme();
    private Integer oznakaArtikla = artikal.getOznaka();
    private String izrazenoUKilogramima = jm.getKilogram();
    private Integer uKilogramima = jm.getuKilogramima();
    private String nazivJediniceMere = jm.getNazivJediniceMere() ;

    public Integer getuKilogramima() {
        return uKilogramima;
    }

    public void setuKilogramima(Integer uKilogramima) {
        this.uKilogramima = uKilogramima;
    }

    public String getIzrazenoUKilogramima() {
        return izrazenoUKilogramima;
    }

    public void setIzrazenoUKilogramima(String izrazenoUKilogramima) {
        this.izrazenoUKilogramima = izrazenoUKilogramima;
    }

    
    

    public Ime_objekta getI() {
        return i;
    }

    public void setI(Ime_objekta i) {
        this.i = i;
    }
    public Integer getAktuelnaCena() {
        return aktuelnaCena;
    }

    public void setAktuelnaCena(Integer aktuelnaCena) {
        this.aktuelnaCena = aktuelnaCena;
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

    public String getImeArtikla() {
        return imeArtikla;
    }

    public void setImeArtikla(String imeArtikla) {
        this.imeArtikla = imeArtikla;
    }

    public Integer getOznakaArtikla() {
        return oznakaArtikla;
    }

    public void setOznakaArtikla(Integer oznakaArtikla) {
        this.oznakaArtikla = oznakaArtikla;
    }

    public String getNazivJediniceMere() {
        return nazivJediniceMere;
    }

    public void setNazivJediniceMere(String nazivJediniceMere) {
        this.nazivJediniceMere = nazivJediniceMere;
    }
    
    
   
   
    public ArtikalDetalji() {
    }

    
          public static List<ArtikalDetalji> findByParams (String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT D.SIFRA_ARTIKLA, D.NAZIV_ARTIKLA,D.AKTUELNA_CENA, M.NAZIV_JM, D.IME_OBJEKTA.GET_IME(), D.IME_OBJEKTA.GET_OZNAKA(),M.KG.GET_VREDNOST(), M.U_KILOGRAMIMA from ARTIKAL D , JEDINICA_MERE M  WHERE D.SIFRA_ARTIKLA = M.SIFRA_JM ";
              if (parameterName != null) {
                SQL += " and " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<ArtikalDetalji> lista = new ArrayList<ArtikalDetalji>();
            ArtikalDetalji artikli;
            while (rs.next()) {
                artikli = new ArtikalDetalji();
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
         
        public static List<ArtikalDetalji> findAll() throws SQLException {
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
          
          public ArtikalDetalji setFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            setArtikalId(resultSet.getInt(1));
            setNazivArtikla(resultSet.getString(2));
            setAktuelnaCena(resultSet.getInt(3));
            setNazivJediniceMere(resultSet.getString(4));
            setImeArtikla(resultSet.getString(5));
            setOznakaArtikla(resultSet.getInt(6));
            setIzrazenoUKilogramima(resultSet.getString(7));
            setuKilogramima(resultSet.getInt(8));
            
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
          
        public static ArtikalDetalji findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_ARTIKLA", id);
    }

    public static ArtikalDetalji findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<ArtikalDetalji> artikli = findByParams(parameterName, parameterValue);
        if (artikli.size() > 0) {
            return artikli.get(0);
        } else {
            return null;
        }
    }
          
          
 
          
}

