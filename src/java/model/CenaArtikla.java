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

public class CenaArtikla {

private String datumOd;
private Integer cena;
private Integer sifraArtikla;
private Integer cenaId;

    public Integer getCenaId() {
        return cenaId;
    }

    public void setCenaId(Integer cenaId) {
        this.cenaId = cenaId;
    }



    public String getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(String datumOd) {
        this.datumOd = datumOd;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(Integer sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

     public CenaArtikla() {
    }

   public CenaArtikla setFromResultSet(ResultSet rs) {
        try {
           
            setDatumOd(rs.getString(1));
            setCena(rs.getInt(2));
            setSifraArtikla(rs.getInt(3));
            setCenaId(rs.getInt(4));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
          ps.setString(1, datumOd);
          ps.setInt(2, cena);
          ps.setInt(3, sifraArtikla);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, datumOd);
            ps.setInt(2, cena);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   public void save() {
        try {
            String SQL = "INSERT INTO CENA_ARTIKLA(DATUM_OD,CENA,SIFRA_ARTIKLA) VALUES (TO_DATE(?,'dd/mm/yyyy'),?,?)";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
   public void update() {
        try {
            String SQL = "UPDATE CENA_ARTIKLA SET DATUM_OD = TO_DATE(?,'dd/mm/yyyy'), CENA=?  WHERE ID_CENA=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setInt(3,cenaId);
           statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
       public void delete() throws SQLException {

        String SQL = "DELETE FROM CENA_ARTIKLA WHERE ID_CENA=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        
        stat.setInt(1,cenaId);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return cenaId == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            save();
        } else {
            update();
        }
    }
    
  public static List<CenaArtikla> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM CENA_ARTIKLA";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<CenaArtikla> lista = new ArrayList<CenaArtikla>();
            CenaArtikla artikalCena;
            while (rs.next()) {
                artikalCena = new CenaArtikla();
                artikalCena.setFromResultSet(rs);
                lista.add(artikalCena);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static CenaArtikla findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("ID_CENA", id);
    }

    public static CenaArtikla findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<CenaArtikla> cenaArtikal = findByParameter(parameterName, parameterValue);
        if (cenaArtikal.size() > 0) {
            return cenaArtikal.get(0);
        } else {
            return null;
        }
    }

        public static List<CenaArtikla> findAll() throws SQLException {
        return findByParameter(null, null);
    }

}
