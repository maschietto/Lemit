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

public class Mesto {

    private Integer mesto_id;
    private String nazivMesta;

     public Mesto() {
    }

    public Integer getMesto_id() {
        return mesto_id;
    }

    public void setMesto_id(Integer mesto_id) {
        this.mesto_id = mesto_id;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }
   public Mesto setFromResultSet(ResultSet rs) {
        try {
            
            setMesto_id(rs.getInt(1));
            setNazivMesta(rs.getString(2));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setString(1, nazivMesta);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   public void saveMesto() {
        try {
            String SQL = "INSERT INTO MESTO(NAZIV_MESTA) VALUES (?)";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
   public void updateMesto() {
        try {
            String SQL = "UPDATE MESTO SET NAZIV_MESTA=? WHERE MESTO_ID=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParams(statement);
            statement.setInt(2,mesto_id);
            statement.executeUpdate();
            statement.close();
           // connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
       public void deleteMesto() throws SQLException {

        String SQL = "DELETE * FROM MESTO WHERE MESTO_ID=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, mesto_id);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return mesto_id == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            saveMesto();
        } else {
            updateMesto();
        }
    }
    
  public static List<Mesto> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM MESTO";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Mesto> lista = new ArrayList<Mesto>();
            Mesto mesto;
            while (rs.next()) {
                mesto = new Mesto();
                mesto.setFromResultSet(rs);
                lista.add(mesto);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static Mesto findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("MESTO_ID", id);
    }

    public static Mesto findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Mesto> mesto = findByParameter(parameterName, parameterValue);
        if (mesto.size() > 0) {
            return mesto.get(0);
        } else {
            return null;
        }
    }

}
