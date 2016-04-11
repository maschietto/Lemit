package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyleConstants;

import util.DB_broker;

public class Ime_objekta implements SQLData {

    private String ime;
    private Integer broj;

    public String getSQLTypeName() throws SQLException {
 
    return "IME_OBJEKTA";
 
}
 
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
 
    setIme(stream.readString());
 
    setBroj(stream.readInt());
}
    public void writeSQL(SQLOutput stream) throws SQLException {
 
    stream.writeString(getIme());

    stream.writeInt(getBroj());
    }
    
    public Ime_objekta() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

    
   
}
