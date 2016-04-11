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

public class JedinicaMere {

Kilogram i = new Kilogram();
    
    
private Integer sifraJediniceMere;
private String nazivJediniceMere;
private String kilogram;
private Integer uKilogramima;

    public Integer getuKilogramima() {
        return uKilogramima;
    }

    public void setuKilogramima(Integer uKilogramima) {
        this.uKilogramima = uKilogramima;
    }

  public Integer getSifraJediniceMere() {
        return sifraJediniceMere;
    }

    public void setSifraJediniceMere(Integer sifraJediniceMere) {
        this.sifraJediniceMere = sifraJediniceMere;
    }

    public String getNazivJediniceMere() {
        return nazivJediniceMere;
    }

    public void setNazivJediniceMere(String nazivJediniceMere) {
        this.nazivJediniceMere = nazivJediniceMere;
    }

   
    public String getKilogram() {
        return kilogram;
    }

    public void setKilogram(String kilogram) {
        this.kilogram = kilogram;
    }
    
     public JedinicaMere() {
    }

   public JedinicaMere setFromResultSet(ResultSet rs) {
        try {
            
            setSifraJediniceMere(rs.getInt(1));
            setNazivJediniceMere(rs.getString(2));
            setKilogram(rs.getString(3));
            setuKilogramima(rs.getInt(4));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
           
            ps.setString(1, nazivJediniceMere);
            ps.setString(2, kilogram);
            ps.setInt(3, uKilogramima);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

        public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1,nazivJediniceMere);
            ps.setString(2, kilogram);
            ps.setInt(3, uKilogramima);
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   public void saveJedinicaMere() {
        try {
            String SQL = "INSERT INTO JEDINICA_MERE (NAZIV_JM,KG,U_KILOGRAMIMA) VALUES (?,KILOGRAMI(?),?)";
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
            String SQL = "UPDATE JEDINICA_MERE SET NAZIV_JM=?, KG=(KILOGRAMI(?)), U_KILOGRAMIMA=? WHERE SIFRA_JM=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setInt(4,sifraJediniceMere);
            statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
       public void delete() throws SQLException {

        String SQL = "DELETE FROM JEDINICA_MERE WHERE SIFRA_JM=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, sifraJediniceMere);
        stat.executeUpdate();
        stat.close();
  }
    
        public boolean isNew() {

        return sifraJediniceMere == null;
    }

    public void saveOrUpdate() {
        if (isNew()) {
            saveJedinicaMere();
        } else {
            update();
        }
    }
    
  public static List<JedinicaMere> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM JEDINICA_MERE";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<JedinicaMere> lista = new ArrayList<JedinicaMere>();
            JedinicaMere artikal;
            while (rs.next()) {
                artikal = new JedinicaMere();
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

    public static JedinicaMere findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_JM", id);
    }

    public static JedinicaMere findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<JedinicaMere> jediniceMere = findByParameter(parameterName, parameterValue);
        if (jediniceMere.size() > 0) {
            return jediniceMere.get(0);
        } else {
            return null;
        }
    }

        public static List<JedinicaMere> findAll() throws SQLException {
        return findByParameter(null, null);
    }

}
