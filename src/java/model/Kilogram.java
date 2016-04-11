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

public class Kilogram {

    private String jeste;


    public Kilogram() {
    }

    public String getJeste() {
        return jeste;
    }

    public void setJeste(String jeste) {
        this.jeste = jeste;
    }


}
