package services.raport;

import constants.raport.FakturaConstants;
import datasource.Base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FaktureData {

    private PreparedStatement insert;

    public FaktureData() {
        try {
            insert = Base.getInstance().getConnection().prepareStatement(FakturaConstants.INSERT);
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    public PreparedStatement getInsert() {
        return insert;
    }

    public void insertFakture(String klient, String furnizues, String data, String dataSkadimit, String puntori, String malli) {
        try {
            insert.setString(1,klient);
            insert.setString(2,furnizues);
            insert.setString(3,data);
            insert.setString(4,dataSkadimit);
            insert.setString(5,puntori);
            insert.setString(6,malli);
            insert.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLExcpetion on insertFakutre() : " + sqlException.getMessage());
        }
    }
}
