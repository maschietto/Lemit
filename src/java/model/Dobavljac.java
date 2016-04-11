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

public class Dobavljac {

    private Integer sifraDobavljaca;
    private String nazivDobavljaca;
    private Integer mestoID;
    private String dobavljacOpis;

    public String getDobavljacOpis() {
        return dobavljacOpis;
    }

    public void setDobavljacOpis(String dobavljacOpis) {
        this.dobavljacOpis = dobavljacOpis;
    }
    
  //  private String status;

     public Dobavljac() {
    }

    public Integer getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(Integer sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }

    public Integer getMestoID() {
        return mestoID;
    }

    public void setMestoID(Integer mestoID) {
        this.mestoID = mestoID;
    }


    
    public Dobavljac setFromResultSet(ResultSet rs) {
        try {
            setSifraDobavljaca(rs.getInt(1));
            setNazivDobavljaca(rs.getString(2));
            setMestoID(rs.getInt(3));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setString(1, nazivDobavljaca);
            //ps.setInt(2, mestoID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, nazivDobavljaca);
            ps.setInt(2, mestoID);
            ps.setString(3, dobavljacOpis);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   public void saveDobavljac() {
        try {
            String SQL = "INSERT INTO DOBAVLJAC(NAZIV_DOBAVLJACA) VALUES (?)";
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
            String SQL = "UPDATE DOBAVLJAC SET NAZIV_DOBAVLJACA=?, MESTO_ID=? ,DOBAVLJAC_OPIS=? WHERE SIFRA_DOBAVLJACA=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setInt(4,sifraDobavljaca);
            statement.executeUpdate();
            statement.close();
            DB_broker.commitTransaction();
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            DB_broker.rollbackTransaction();
        }
    }
   
       public void delete() throws SQLException {

        String SQL = "DELETE FROM DOBAVLJAC WHERE SIFRA_DOBAVLJACA=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, sifraDobavljaca);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return sifraDobavljaca == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            saveDobavljac();
        } else {
            update();
        }
    }
    
  public static List<Dobavljac> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM DOBAVLJAC";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Dobavljac> lista = new ArrayList<Dobavljac>();
            Dobavljac dobavljac;
            while (rs.next()) {
                dobavljac = new Dobavljac();
                dobavljac.setFromResultSet(rs);
                lista.add(dobavljac);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static Dobavljac findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_DOBAVLJACA", id);
    }

    public static Dobavljac findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Dobavljac> dobavljac = findByParameter(parameterName, parameterValue);
        if (dobavljac.size() > 0) {
            return dobavljac.get(0);
        } else {
            return null;
        }
    }

        public static List<Dobavljac> findAll() throws SQLException {
        return findByParameter(null, null);
    }

}
