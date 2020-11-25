package services.storage;

import constants.items.MalliConstants;
import constants.storage.DepoConstants;
import datasource.Base;
import model.storage.Depo;
import services.items.MalliData;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepoData {
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement query_sasia;

    public DepoData() {
        try {
            insert = Base.getInstance().getConnection().prepareStatement(DepoConstants.INSERT);
            update = Base.getInstance().getConnection().prepareStatement(DepoConstants.UPDATE);
            query_sasia = Base.getInstance().getConnection().prepareStatement(DepoConstants.QUERY_SASIA);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on DepoData() : " + sqlException.getMessage());
        }
    }

    public PreparedStatement getInsert() {
        return insert;
    }

    public PreparedStatement getUpdate() {
        return update;
    }

    public PreparedStatement getQuerySasia() {
        return query_sasia;
    }

    public List<Depo> getDepoData() {
        try {
            ResultSet resultSet = Base.getInstance().getStatement().executeQuery(DepoConstants.QUERY_DATA);
            List<Depo> depoList = new ArrayList<>();
            while (resultSet.next()) {
                Depo depo = new Depo();
                depo.setArtikull(resultSet.getString(1));
                depo.setSeria(resultSet.getString(2));
                depo.setSasia(resultSet.getInt(3));
                depoList.add(depo);
            }
            return depoList;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getDepoData(): " + sqlException.getMessage());
            return null;
        }
    }

    public void insertData(String artikulli, String seria, int sasia) {
        try {
            insert.setString(1,artikulli);
            insert.setString(2,seria);
            insert.setInt(3,sasia);
            insert.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on insertData() : " + sqlException.getMessage());
        }
    }

    public void updateSasia(int sasia, String seria) {
        try {
            update.setInt(1, sasia);
            update.setString(2, seria);
            update.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on updatesasia() : " + sqlException.getMessage());
        }
    }

    public void deleteData() {
        try {
            Base.getInstance().getStatement().execute(DepoConstants.DELETE);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on deleteData() : " + sqlException.getMessage());
        }
    }

    public int getSasia(String seria) {
        try {
            query_sasia.setString(1,seria);
            ResultSet resultSet = query_sasia.executeQuery();
            int sasia = 0;
            while ((resultSet.next())) {
                sasia = resultSet.getInt(1);
            }
            return sasia;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getSasia() : " + sqlException.getMessage());
            return 0;
        }
    }
}
