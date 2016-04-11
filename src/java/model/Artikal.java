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
import util.Validate;

public class Artikal {

private Integer sifraArtikla;
private String nazivArtikla;
private Integer aktuelnaCena;
private Integer sifraJediniceMere;

    
    
 Ime_objekta i = new Ime_objekta();

    private String ime =  i.getIme();
    private Integer oznaka = i.getBroj();

    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getOznaka() {
        return oznaka;
    }

    public void setOznaka(Integer oznaka) {
        this.oznaka = oznaka;
    }

    
    
    
    public Integer getSifraJediniceMere() {
        return sifraJediniceMere;
    }

    public void setSifraJediniceMere(Integer sifraJediniceMere) {
        this.sifraJediniceMere = sifraJediniceMere;
    }

    

    public Integer getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Integer sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public Integer getAktuelnaCena() {
        return aktuelnaCena;
    }

    public void setAktuelnaCena(Integer aktuelnaCena) {
        this.aktuelnaCena = aktuelnaCena;
    }

  
  
     public Artikal() {
    }

   public Artikal setFromResultSet(ResultSet rs) {
        try {
            setSifraArtikla(rs.getInt(1));
            setNazivArtikla(rs.getString(2));
            setAktuelnaCena(rs.getInt(3));
            setSifraJediniceMere(rs.getInt(4));
            setIme(rs.getString(5));
            setOznaka(rs.getInt(6));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setString(1, nazivArtikla);
            ps.setString(2, ime);
            ps.setInt(3, oznaka);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, nazivArtikla);
            ps.setInt(2, aktuelnaCena);
            ps.setString(3, ime);
            ps.setInt(4, oznaka);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   public void saveArtikal() {
        try {
            String SQL = "INSERT INTO ARTIKAL (NAZIV_ARTIKLA,IME_OBJEKTA) VALUES (?,IME_OBJEKTA_OZNAKA(?,?))";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
   public String update() {
       String vrednost = "";
        try {
            String SQL = "UPDATE ARTIKAL SET NAZIV_ARTIKLA=?,AKTUELNA_CENA=?, IME_OBJEKTA=(IME_OBJEKTA_OZNAKA(?,?)) WHERE SIFRA_ARTIKLA=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setInt(5,sifraArtikla);
            statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 20000) {

                vrednost = Validate.pokupiPoruku(ex.getMessage());

            } else {

                ex.printStackTrace();
            }
        }return vrednost;
   }
   
       public void delete() throws SQLException {

        String SQL = "DELETE FROM ARTIKAL WHERE SIFRA_ARTIKLA=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, sifraArtikla);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return sifraArtikla == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            saveArtikal();
        } else {
            update();
        }
    }
    
  public static List<Artikal> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT A.SIFRA_ARTIKLA, A.NAZIV_ARTIKLA, A.AKTUELNA_CENA, A.SIFRA_JM, A.IME_OBJEKTA.GET_IME(), A.IME_OBJEKTA.GET_OZNAKA() FROM ARTIKAL A ";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Artikal> lista = new ArrayList<Artikal>();
            Artikal artikal;
            while (rs.next()) {
                artikal = new Artikal();
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

    public static Artikal findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_ARTIKLA", id);
    }

    public static Artikal findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Artikal> artikli = findByParameter(parameterName, parameterValue);
        if (artikli.size() > 0) {
            return artikli.get(0);
        } else {
            return null;
        }
    }

        public static List<Artikal> findAll() throws SQLException {
        return findByParameter(null, null);
    }

}
