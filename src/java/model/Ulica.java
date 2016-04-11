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

public class Ulica {

    private Integer ulicaId;
    private String nazivUlice;
   // private Integer brZgradeId;
    private Integer mestoId;

    public Integer getUlicaId() {
        return ulicaId;
    }

    public void setUlicaId(Integer ulicaId) {
        this.ulicaId = ulicaId;
    }

    public String getNazivUlice() {
        return nazivUlice;
    }

    public void setNazivUlice(String nazivUlice) {
        this.nazivUlice = nazivUlice;
    }



    public Integer getMestoId() {
        return mestoId;
    }

    public void setMestoId(Integer mestoId) {
        this.mestoId = mestoId;
    }

  
 
   public Ulica setFromResultSet(ResultSet rs) {
        try {
            
            setUlicaId(rs.getInt(1));
            setMestoId(rs.getInt(2));
            setNazivUlice(rs.getString(3));
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setString(1, nazivUlice);
           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
       public void setStatementParamsForUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, nazivUlice);
            ps.setInt(2, mestoId);
           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

   public void saveUlica() {
        try {
            String SQL = "INSERT INTO ULICA(NAZIV_ULICE) VALUES (?)";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
   public void updateUlica() {
        try {
            String SQL = "UPDATE ULICA SET NAZIV_ULICE=?,MESTO_ID=? WHERE ULICA_ID=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsForUpdate(statement);
            statement.setInt(3,ulicaId);
            statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
       public void deleteUlica() throws SQLException {

        String SQL = "DELETE * FROM ULICA WHERE ULICA_ID=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, ulicaId);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return ulicaId == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            saveUlica();
        } else {
            updateUlica();
        }
    }
    
  public static List<Ulica> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM ULICA";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Ulica> lista = new ArrayList<Ulica>();
            Ulica ulica;
            while (rs.next()) {
                ulica = new Ulica();
                ulica.setFromResultSet(rs);
                lista.add(ulica);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static Ulica findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("ULICA_ID", id);
    }

    public static Ulica findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Ulica> ulica = findByParameter(parameterName, parameterValue);
        if (ulica.size() > 0) {
            return ulica.get(0);
        } else {
            return null;
        }
    }

}
