package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import util.DB_broker;

public class Korisnik {

    private Integer id_korisnika;
    private String nazivKorisnika;
    private String jmbg;
    private String kontaktTelefon;
    private String aktivan;
    private String isAdmin;
    private String password;

    private String status;
    private Integer dobavljac_id;

    
    
    public Integer getDobavljac_id() {
        return dobavljac_id;
    }

    public void setDobavljac_id(Integer dobavljac_id) {
        this.dobavljac_id = dobavljac_id;
    }
    
    public Korisnik() {
    }

   public String isAktivan() {
        return aktivan;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId_korisnika() {
        return id_korisnika;
    }

    public void setId_korisnika(Integer id_korisnika) {
        this.id_korisnika = id_korisnika;
    }

    public String getAktivan() {
        return aktivan;
    }

    public void setAktivan(String aktivan) {
        this.aktivan = aktivan;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

  

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setId_korisnika(int id_korisnika) {
        this.id_korisnika = id_korisnika;
    }

    public String getNazivKorisnika() {
        return nazivKorisnika;
    }

    public void setNazivKorisnika(String nazivKorisnika) {
        this.nazivKorisnika = nazivKorisnika;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public Korisnik setFromResultSet(ResultSet rs) {
        try {
            setId_korisnika(rs.getInt(1));
            setNazivKorisnika(rs.getString(2));
            setId_korisnika(rs.getInt(3));
            setJmbg(rs.getString(4));
            setKontaktTelefon(rs.getString(5));
            setAktivan(rs.getString(6));
            setIsAdmin(rs.getString(7));
            setPassword(rs.getString(8));
            setDobavljac_id(9);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public void setStatementParams(PreparedStatement ps) {
        try {
            ps.setString(1, aktivan);
            ps.setString(2, isAdmin);
            ps.setString(3, jmbg);
            ps.setString(4, kontaktTelefon);
            ps.setString(5, nazivKorisnika);
            ps.setString(6, password);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void save() throws SQLException{
      
            String SQL = "INSERT INTO KORISNIK_VIEW(AKTIVAN,ISADMIN,JMBG,KONTAKT_TELEFON,NAZIV_KORISNIKA,SIFRA) VALUES(?,?,?,?,?,?)";
            Connection con = DB_broker.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            setStatementParams(ps);
            ps.executeUpdate();
            ps.close();
       
    }

    public void update() throws SQLException{
        
            String SQL = "UPDATE KORISNIK_VIEW SET AKTIVAN=?, ISADMIN=?, JMBG=?, KONTAKT_TELEFON=?,"
                    + " NAZIV_KORISNIKA=?, SIFRA=? WHERE ID_KORISNIK=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            setStatementParams(statement);
            statement.setInt(7, id_korisnika);
            statement.executeUpdate();
            statement.close();
            //connection.close();
       
    }
    public static List<Korisnik> findByParameter(String parameterName, Object parameterValue){
        try {
            String SQL = "SELECT * FROM NINOSLAV.korisnik k JOIN NINOSLAV.korisnik_detalji kd ON k.id_korisnik = kd.id_korisnik ";
            if (parameterName != null) {
                SQL += " WHERE " + parameterName + " =?";
       }
            else {
                 SQL += " ORDER BY k.id_korisnik";
            } 
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
         if(parameterValue != null){
            stat.setObject(1, parameterValue);
         }
            ResultSet rs = stat.executeQuery();
            List<Korisnik> lista = new ArrayList<Korisnik>();
            Korisnik korisnik;
            while (rs.next()) {
                korisnik = new Korisnik();
                korisnik.setFromResultSet(rs);
                lista.add(korisnik);
            }
            rs.close();
            stat.close();
           con.commit();
          // con.close();
      
            return lista;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
       public static List<Korisnik> findByParameter2(Object parameterValue){
        try {
            String SQL = "SELECT * FROM NINOSLAV.korisnik k JOIN NINOSLAV.korisnik_detalji kd ON k.id_korisnik = kd.id_korisnik WHERE K.ID_KORISNIK =? ";
       
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
         if(parameterValue != null){
            stat.setObject(1, parameterValue);
         }
            ResultSet rs = stat.executeQuery();
            List<Korisnik> lista = new ArrayList<Korisnik>();
            Korisnik korisnik;
            while (rs.next()) {
                korisnik = new Korisnik();
                korisnik.setFromResultSet(rs);
                lista.add(korisnik);
            }
            rs.close();
            stat.close();
           con.commit();
          // con.close();
      
            return lista;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    

    
    
    public void delete() throws SQLException {

        String SQL = "DELETE FROM KORISNIK WHERE id_korisnik=?";
        Connection con = DB_broker.getConnection();
        PreparedStatement stat = con.prepareStatement(SQL);
        stat.setInt(1, id_korisnika);
        stat.executeUpdate();
        stat.close();

    }

    public boolean isNew() {

        return id_korisnika == null;
    }

    public void saveOrUpdate() throws SQLException{
        if (isNew()) {
            save();
        } else {
            update();
        }
    }
    
        public void changeVisibility(String isActive) {
        setAktivan(isActive);
        updateStatus(isActive);
    }
        
            private void updateStatus(String status){
        try {
            String SQL = "UPDATE KORISNIK_VIEW SET aktivan=? WHERE id_korisnik=?";
            Connection connection = DB_broker.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, status);
            statement.setInt(2, id_korisnika);
            statement.executeUpdate();
            statement.close();
            //connection.close();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }

    public static Korisnik findByUsername(String username, String password) throws SQLException {
        Korisnik korisnik = null;
        Korisnik objekat = null;
        String SQL = "SELECT * FROM KORISNIK K JOIN KORISNIK_DETALJI KD ON K.ID_KORISNIK=KD.ID_KORISNIK WHERE NAZIV_KORISNIKA=? ";
        if (password != null) {
            SQL += " AND SIFRA=?";
        }
        Connection con = DB_broker.getConnection();
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setString(1, username);
        if (password != null) {
            ps.setString(2, password);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            //korisnik.setFromResultSet(rs);

            objekat = korisnik.setFromResultSet(rs);
        }
        rs.close();
        ps.close();
        return objekat;
    }

    public static Korisnik insertUser(String naziv_korisnika, String jmbg, String kontakt_telefon, Boolean isAdmin, Boolean aktivan, String sifra)
            throws SQLException {

        String SQL = "INSERT INTO KORISNIK (NAZIV_KORISNIKA,JMBG,KONTAKT_TELEFON,ISADMIN,AKTIVAN,SIFRA) VALUES(?,?,?,?,?,?)";
        Connection con = DB_broker.getConnection();
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setString(1, naziv_korisnika);
        ps.setString(2, jmbg);
        ps.setString(3, kontakt_telefon);
        ps.setBoolean(4, isAdmin);
        ps.setBoolean(5, aktivan);
        ps.setString(6, sifra);

        ps.executeUpdate();
        ps.close();
        return Korisnik.findByUsername(naziv_korisnika, null);
    }

    public static Korisnik findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<Korisnik> korisnici = findByParameter(parameterName, parameterValue);
        if (korisnici.size() > 0) {
            return korisnici.get(0);
        } else {
            return null;
        }
    }
      public static Korisnik findUnique2(String parameterName, Object parameterValue) throws SQLException {
        List<Korisnik> korisnici = findByParameter2(parameterValue);
        if (korisnici.size() > 0) {
            return korisnici.get(0);
        } else {
            return null;
        }
    }


     public static Korisnik findByPrimaryKey(Integer id) throws SQLException {
        return findUnique2("ID_KORISNIK", id);
    }
    
    public static List<Korisnik> findAll() throws SQLException {
        return findByParameter(null, null);
    }
    
     public static Korisnik findLastInBase(){
        try {
            String SQL = "SELECT * FROM KORISNIK where ID_KORISNIK = (select max(ID_KORISNIK) from KORISNIK)";
     
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            ResultSet rs = stat.executeQuery();
            
            Korisnik korisnik = null;
            while (rs.next()) {
                korisnik = new Korisnik();
                korisnik.setFromResultSet(rs);
       
            }
            rs.close();
            stat.close();
         
         return korisnik;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
