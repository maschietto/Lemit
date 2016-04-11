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

public class BrojZgrade {

    private Integer brZgradeId;
    private Integer mestoId;
    private Integer ulicaId;
    private String brojZgrade;

    public String getBrojZgrade() {
        return brojZgrade;
    }

    public void setBrojZgrade(String brojZgrade) {
        this.brojZgrade = brojZgrade;
    }
    
    public BrojZgrade() {
    }

    
    public Integer getBrZgradeId() {
        return brZgradeId;
    }

    public void setBrZgradeId(Integer brZgradeId) {
        this.brZgradeId = brZgradeId;
    }

    public Integer getMestoId() {
        return mestoId;
    }

    public void setMestoId(Integer mestoId) {
        this.mestoId = mestoId;
    }

    public Integer getUlicaId() {
        return ulicaId;
    }

    public void setUlicaId(Integer ulicaId) {
        this.ulicaId = ulicaId;
    }

 
   public BrojZgrade setFromResultSet(ResultSet rs) {
        try {
            setBrZgradeId(rs.getInt(1));
            setUlicaId(rs.getInt(2));
            setBrojZgrade(rs.getString(3));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    } 

    public void setStatementParams(PreparedStatement ps) {
        try {
          
            
         //   ps.setInt(2,ulicaId);
            ps.setString(1, brojZgrade);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void setStatementParamsForUpdate(PreparedStatement ps) {
        try {
          
            
            ps.setInt(2,ulicaId);
            ps.setString(1, brojZgrade);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
   public void saveBrojZgrade() {
        try {
            String SQL = "INSERT INTO BR_ZGRADE(BROJ_ZGRADE_B) VALUES (?)";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
   public void updateBrojZgrade() {
        try {
            String SQL = "UPDATE BR_ZGRADE SET BROJ_ZGRADE_B=? ,ULICA_ID=? WHERE BR_ZGRADE_ID=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsForUpdate(statement);
            statement.setInt(2,brZgradeId);
            statement.executeUpdate();
            statement.close();
           // connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
       public void deleteBrojZgrade() throws SQLException {

        String SQL = "DELETE FROM BR_ZGRADE WHERE BR_ZGRADE_ID=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1,brZgradeId);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return brZgradeId == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            saveBrojZgrade();
        } else {
            updateBrojZgrade();
        }
    }
    
  public static List<BrojZgrade> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM BR_ZGRADE";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<BrojZgrade> lista = new ArrayList<BrojZgrade>();
            BrojZgrade br;
            while (rs.next()) {
                br = new BrojZgrade();
                br.setFromResultSet(rs);
                lista.add(br);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static BrojZgrade findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("BR_ZGRADE_ID", id);
    }

    public static BrojZgrade findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<BrojZgrade> br = findByParameter(parameterName, parameterValue);
        if (br.size() > 0) {
            return br.get(0);
        } else {
            return null;
        }
    }

}
