
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

public class Katalog {
    
    private int sifraKataloga;
    private String nazivKataloga;
    private int sifraDobavljaca;
    private String nazivDobavljaca;
    private String poruka;

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    
    
    List<StavkaKataloga> listaStavki = new ArrayList<StavkaKataloga>();

    
    
    public List<StavkaKataloga> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaKataloga> listaStavki) {
        this.listaStavki = listaStavki;
    }
   
    public Katalog() {
    }

    public int getSifraKataloga() {
        return sifraKataloga;
    }

    public void setSifraKataloga(int sifraKataloga) {
        this.sifraKataloga = sifraKataloga;
    }

    public String getNazivKataloga() {
        return nazivKataloga;
    }

    public void setNazivKataloga(String nazivKataloga) {
        this.nazivKataloga = nazivKataloga;
    }

    public int getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(int sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }

    public String getNazivDobavljaca() {
        return nazivDobavljaca;
    }

    public void setNazivDobavljaca(String nazivDobavljaca) {
        this.nazivDobavljaca = nazivDobavljaca;
    }
    
     public Katalog setFromResultSet(ResultSet rs){
        try {      
            setSifraKataloga(rs.getInt(1));
            setNazivKataloga(rs.getString(2));
            setSifraDobavljaca(rs.getInt(3));
            setNazivDobavljaca(rs.getString(4));
           
           
            return this;
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      return this;
    }
    
    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setInt(1, sifraKataloga);
            ps.setString(2, nazivKataloga);
            ps.setInt(3, sifraDobavljaca);
       
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
 
    public void setStatementParamsforUpdate(PreparedStatement ps) {
        try {
            ps.setString(1, nazivKataloga);
            ps.setString(2, nazivDobavljaca);
     } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    
    
     
    
      public void save() {
        try {
            String SQL = "INSERT INTO KATALOG(SIFRA_KATALOGA, NAZIV_KATALOGA, SIFRA_DOBAVLJACA) VALUES(?,?,?)";
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
        String rezultat="";
            try {
            String SQL = "UPDATE KATALOG SET NAZIV_KATALOGA=? , NAZIV_DOBAVLJACA = ? WHERE SIFRA_KATALOGA=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParamsforUpdate(statement);
            statement.setInt(3,sifraKataloga);
            statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
           if(ex.getErrorCode()== 20000){
               
           rezultat = Validate.pokupiPoruku(ex.getMessage());
           }else{
            
               ex.printStackTrace();
        }
    } return rezultat; 
        }
        
 
        
       public void delete() throws SQLException {

        String SQL = "DELETE FROM KATALOG WHERE SIFRA_KATALOGA=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, sifraKataloga);
        stat.executeUpdate();
        stat.close();
  }
    
    
        public static List<Katalog> findByParameter(String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT * FROM KATALOG";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<Katalog> lista = new ArrayList<Katalog>();
            Katalog katalog;
            while (rs.next()) {
                katalog = new Katalog();
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

    public static Katalog findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_KATALOGA", id);
    }

    public static Katalog findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Katalog> katalog = findByParameter(parameterName, parameterValue);
        if (katalog.size() > 0) {
            return katalog.get(0);
        } else {
            return null;
        }
    }

        public static List<Katalog> findAll() throws SQLException {
        return findByParameter(null, null);
    }
    
         public static List<Katalog> findPoslednjiKatalog() throws SQLException {
        try {
            String SQL = "SELECT * FROM KATALOG WHERE SIFRA_KATALOGA = (SELECT MAX(SIFRA_KATALOGA) FROM KATALOG)";
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();
            
            Katalog katalog;
            List<Katalog> lista = new ArrayList<Katalog>();
            while(rs.next()){
            katalog = new Katalog();
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
         
       public static Katalog findUniqueZaPoslednjiParametar() throws SQLException {
        List<Katalog> katalog = findPoslednjiKatalog();
        if (katalog.size() > 0) {
            return katalog.get(0);
        } else {
            return null;
        }
    }    

        
        
    
    }
    
 
