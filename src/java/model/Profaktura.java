
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

//import util.HomeworkException;

public class Profaktura {
    
    private Integer sifraNarudzbenice;
    private Integer sifraProfakture;
    private String datum;
    private Integer ukupno;
    private Integer sifraDobavljaca;

    public Integer getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Integer sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    public Integer getSifraProfakture() {
        return sifraProfakture;
    }

    public void setSifraProfakture(Integer sifraProfakture) {
        this.sifraProfakture = sifraProfakture;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Integer getUkupno() {
        return ukupno;
    }

    public void setUkupno(Integer ukupno) {
        this.ukupno = ukupno;
    }

    public Integer getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(Integer sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }
    
  
    

   
    
     public Profaktura setFromResultSet(ResultSet rs){
        try {    
            setSifraProfakture(rs.getInt(1));   
            setSifraNarudzbenice(rs.getInt(2));
            setSifraDobavljaca(rs.getInt(3));
            setDatum(rs.getString(4));
            setUkupno(rs.getInt(5));
            return this;
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      return this;
    }
    
    public void setStatementParams(PreparedStatement ps) {
        try { 
            ps.setInt(1, sifraProfakture);
            ps.setInt(2, sifraNarudzbenice);
            ps.setInt(3, sifraDobavljaca);
            ps.setString(4, datum);
           
       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
 
    public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, datum);
     } catch (SQLException ex) {
            ex.printStackTrace();
        }
 }
    
    
     
    
      public void save() {
        try {
            String SQL = "INSERT INTO PROFAKTURA(SIFRA_PROFAKTURE, SIFRA_NARUDZBENICE, SIFRA_DOBAVLJACA, DATUM) VALUES(?,?,?,TO_DATE(?,'dd/mm/yyyy'))";
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
            String SQL = "UPDATE PROFAKTURA SET DATUM=TO_DATE(?, 'yyyy/mm/dd hh24:mi:ss') , SIFRA_DOBAVLJACA=? WHERE SIFRA_PROFAKTURE=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setString(1,datum);
            statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
      
       public void delete() throws SQLException {

        String SQL = "DELETE FROM PROFAKTURA WHERE SIFRA_PROFAKTURE=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, sifraProfakture);
        stat.executeUpdate();
        stat.close();
  }
    
    
        public static List<Profaktura> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM PROFAKTURA";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Profaktura> lista = new ArrayList<Profaktura>();
            Profaktura prof;
            while (rs.next()) {
                prof = new Profaktura();
                prof.setFromResultSet(rs);
                lista.add(prof);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }

    public static Profaktura findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_PROFAKTURE", id);
    }

    public static Profaktura findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Profaktura> prof = findByParameter(parameterName, parameterValue);
        if (prof.size() > 0) {
            return prof.get(0);
        } else {
            return null;
        }
    }

        public static List<Profaktura> findAll() throws SQLException {
        return findByParameter(null, null);
    }
    
         public static List<Profaktura> findPoslednjaProfaktura() throws SQLException {
        try {
            String SQL = "SELECT * FROM NINOSLAV.PROFAKTURA WHERE SIFRA_PROFAKTURE = (SELECT MAX(SIFRA_PROFAKTURE) FROM NINOSLAV.PROFAKTURA)";
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();
            
            Profaktura prof;
            List<Profaktura> lista = new ArrayList<Profaktura>();
            while(rs.next()){
            prof = new Profaktura();
            prof.setFromResultSet(rs);
            lista.add(prof);
            }
            rs.close();
            stat.close();
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } 
    }
         
       public static Profaktura findUniqueZaPoslednjiParametar() throws SQLException {
        List<Profaktura> prof = findPoslednjaProfaktura();
        if (prof.size() > 0) {
            return prof.get(0);
        } else {
            return null;
        }
    }    

        
        
    
    }
    
 
