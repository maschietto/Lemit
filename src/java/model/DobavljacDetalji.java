

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.Dobavljac.findByParameter;
import util.DB_broker;


public class DobavljacDetalji {
    
 Dobavljac dobavljac =new Dobavljac();
 Mesto mesto =  new Mesto();
 Ulica ulica = new Ulica();
 BrojZgrade brZgrade =  new BrojZgrade();
 
    
    private Integer dobavljacId = dobavljac.getSifraDobavljaca();
    private String dobavljacNaziv = dobavljac.getNazivDobavljaca();
    private String nazivMesta = mesto.getNazivMesta();
    private String ulicaNova = ulica.getNazivUlice();
    private String brojZgrade = brZgrade.getBrojZgrade();
    private String dobavljacOpis= dobavljac.getDobavljacOpis();

    public String getDobavljacOpis() {
        return dobavljacOpis;
    }

    public void setDobavljacOpis(String dobavljacOpis) {
        this.dobavljacOpis = dobavljacOpis;
    }
   
    public Integer getDobavljacId() {
        return dobavljacId;
    }

    public void setDobavljacId(Integer dobavljacId) {
        this.dobavljacId = dobavljacId;
    }

    public String getDobavljacNaziv() {
        return dobavljacNaziv;
    }

    public void setDobavljacNaziv(String dobavljacNaziv) {
        this.dobavljacNaziv = dobavljacNaziv;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public String getUlicaNova() {
        return ulicaNova;
    }

    public void setUlicaNova(String ulicaNova) {
        this.ulicaNova = ulicaNova;
    }

    public String getBrojZgrade() {
        return brojZgrade;
    }

    public void setBrojZgrade(String brojZgrade) {
        this.brojZgrade = brojZgrade;
    }
   
    public DobavljacDetalji() {
    }

    
          public static List<DobavljacDetalji> findByParams (String parameterName, Object parameterValue) throws SQLException {
        try {
            String SQL = "SELECT D.SIFRA_DOBAVLJACA, D.NAZIV_DOBAVLJACA,D.DOBAVLJAC_OPIS, M.NAZIV_MESTA,U.NAZIV_ULICE,Z.BROJ_ZGRADE_b from DOBAVLJAC D , MESTO M  ,ULICA U, BR_ZGRADE Z WHERE D.MESTO_ID = M.MESTO_ID and M.MESTO_ID = U.MESTO_ID AND U.ULICA_ID = Z.ULICA_ID";
              if (parameterName != null) {
                SQL += " and " + parameterName + " =?";
            }
            Connection con = DB_broker.getConnection();
            PreparedStatement stat = con.prepareStatement(SQL);
            if (parameterName != null) {
                stat.setObject(1, parameterValue);
            }
            ResultSet rs = stat.executeQuery();
            List<DobavljacDetalji> lista = new ArrayList<DobavljacDetalji>();
            DobavljacDetalji dobavljac;
            while (rs.next()) {
                dobavljac = new DobavljacDetalji();
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
         
        public static List<DobavljacDetalji> findAll() throws SQLException {
        return findByParams(null, null);
    }          
          
//          private static List<DobavljacDetalji> mapToListFromResultSet(ResultSet resultSet) throws SQLException {
//        List<DobavljacDetalji> dob = new ArrayList<DobavljacDetalji>();
//        try {
//            while (resultSet.next()) {
//                DobavljacDetalji dobi = new DobavljacDetalji();
//                dobi.setFromResultSet(resultSet);
//                dob.add(dobi);
//            }
//            resultSet.close();
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return dob;
//    }
          
          public DobavljacDetalji setFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            setDobavljacId(resultSet.getInt(1));
            setDobavljacNaziv(resultSet.getString(2));
            setDobavljacOpis(resultSet.getString(3));
            setNazivMesta(resultSet.getString(4));
            setUlicaNova(resultSet.getString(5));
            setBrojZgrade(resultSet.getString(6));
            
            return this;
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
          
        public static DobavljacDetalji findByPrimaryKey(Integer id) throws SQLException {
        return findUnique("SIFRA_DOBAVLJACA", id);
    }

    public static DobavljacDetalji findUnique(String parameterName, Object parameterValue) throws SQLException {
        List<DobavljacDetalji> dobavljac = findByParams(parameterName, parameterValue);
        if (dobavljac.size() > 0) {
            return dobavljac.get(0);
        } else {
            return null;
        }
    }
          
          
 
          
}

