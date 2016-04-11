package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB_broker;
import util.Validate;

//import util.HomeworkException;
public class Narudzbenica {

    private Integer sifraNarudzbenice;
    private Integer sifraKataloga;
    private String datum;
    private String nazivDobavljaca;
    private Integer sifraDobavljaca;

    List<StavkaNarudzbenice> listaStavkiNarudzbenice = new ArrayList<StavkaNarudzbenice>();

    public Integer getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Integer sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    public Integer getSifraKataloga() {
        return sifraKataloga;
    }

    public void setSifraKataloga(Integer sifraKataloga) {
        this.sifraKataloga = sifraKataloga;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
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

    public List<StavkaNarudzbenice> getListaStavkiNarudzbenice() {
        return listaStavkiNarudzbenice;
    }

    public void setListaStavkiNarudzbenice(List<StavkaNarudzbenice> listaStavkiNarudzbenice) {
        this.listaStavkiNarudzbenice = listaStavkiNarudzbenice;
    }

    public Narudzbenica setFromResultSet(ResultSet rs) {
        try {
            setSifraNarudzbenice(rs.getInt(1));
            setSifraKataloga(rs.getInt(2));
            setDatum(rs.getString(3));
            setNazivDobavljaca(rs.getString(4));
            setSifraDobavljaca(rs.getInt(5));

            return this;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setInt(1, sifraNarudzbenice);
            ps.setInt(2, sifraKataloga);
            ps.setString(3, datum);
            ps.setInt(4, sifraDobavljaca);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, datum);
            ps.setInt(2, sifraDobavljaca);
            ps.setString(3, nazivDobavljaca);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void save() {
        try {
            String SQL = "INSERT INTO NARUDZBENICA(SIFRA_NARUDZBENICE, SIFRA_KATALOGA, DATUM, SIFRA_DOBAVLJACA) VALUES(?,?,TO_DATE(?,'dd/mm/yyyy'),?)";
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
            String SQL = "UPDATE NARUDZBENICA SET DATUM=TO_DATE(?, 'DD.MM.YYYY') , SIFRA_DOBAVLJACA=?, NAZIV_DOBAVLJACA = ? WHERE SIFRA_NARUDZBENICE=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setInt(4, sifraNarudzbenice);
            statement.executeUpdate();
            statement.close();
        
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 20000) {

                vrednost = Validate.pokupiPoruku(ex.getMessage());

            } else {

                ex.printStackTrace();
            }
        }
        return vrednost;
    }

    public void delete() throws SQLException {

        String SQL = "DELETE FROM NARUDZBENICA WHERE SIFRA_NARUDZBENICE=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, sifraNarudzbenice);
        stat.executeUpdate();
        stat.close();
    }

    public static List<Narudzbenica> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM NARUDZBENICA";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Narudzbenica> lista = new ArrayList<Narudzbenica>();
            Narudzbenica narudzbenica;
            while (rs.next()) {
                narudzbenica = new Narudzbenica();
                narudzbenica.setFromResultSet(rs);
                lista.add(narudzbenica);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Narudzbenica findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_NARUDZBENICE", id);
    }

    public static Narudzbenica findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Narudzbenica> katalog = findByParameter(parameterName, parameterValue);
        if (katalog.size() > 0) {
            return katalog.get(0);
        } else {
            return null;
        }
    }

    public static List<Narudzbenica> findAll() throws SQLException {
        return findByParameter(null, null);
    }

    public static List<Narudzbenica> findPoslednjaNarudzbenica() throws SQLException {
        try {
            String SQL = "SELECT * FROM NARUDZBENICA WHERE SIFRA_NARUDZBENICE = (SELECT MAX(SIFRA_NARUDZBENICE) FROM NARUDZBENICA)";
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();

            Narudzbenica katalog;
            List<Narudzbenica> lista = new ArrayList<Narudzbenica>();
            while (rs.next()) {
                katalog = new Narudzbenica();
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

    public static Narudzbenica findUniqueZaPoslednjiParametar() throws SQLException {
        List<Narudzbenica> katalog = findPoslednjaNarudzbenica();
        if (katalog.size() > 0) {
            return katalog.get(0);
        } else {
            return null;
        }
    }

}
