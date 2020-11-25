package services.traffic;

import model.traffic.Klient;
import model.traffic.Traffic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficUtility {

    public static Traffic getKlientInfo(Traffic klient, ResultSet resultSet) {
        try {
            klient.setEmri(resultSet.getString(1));
            klient.setAdresa(resultSet.getString(2));
            klient.setTel(resultSet.getString(3));
            klient.setEmail(resultSet.getString(4));
            klient.setNipt(resultSet.getLong(5));
            klient.setNrTVSH(resultSet.getLong(6));
            klient.setNrBiznesit(resultSet.getString(7));
            klient.setNrLicenses(resultSet.getString(8));
            return klient;
        } catch (SQLException sqlException) {
            System.out.println("Error on getKlientInfo() : " + sqlException.getMessage());
            return null;
        }
    }
}
