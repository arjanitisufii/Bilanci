package services.traffic;

import constants.traffic.FurnizuesConstants;
import constants.traffic.TrafficConstants;
import datasource.Base;
import model.traffic.Traffic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrafficService {

    private static TrafficService instance = new TrafficService();

    public static TrafficService getInstance() {
        return instance;
    }

    public void addTraffic(PreparedStatement preparedStatement, Traffic traffic) {
        try {
            preparedStatement.setString(1, traffic.getEmri());
            preparedStatement.setString(2, traffic.getAdresa());
            preparedStatement.setString(3, traffic.getTel());
            preparedStatement.setString(4, traffic.getEmail());
            preparedStatement.setLong(5, traffic.getNipt());
            preparedStatement.setLong(6, traffic.getNrTVSH());
            preparedStatement.setString(7, traffic.getNrBiznesit());
            preparedStatement.setString(8, traffic.getNrLicenses());
            preparedStatement.setString(9, null);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on addTraffic(): " + sqlException.getMessage());
        }
    }

    public void editTraffic(String constant, Traffic traffic, int id) {
        try {
            StringBuilder query = new StringBuilder(constant);
            query.append(TrafficConstants.TRAFFIC_NAME + " = '" + traffic.getEmri() + "', ");
            query.append(TrafficConstants.TRAFFIC_ADRESA + " = '" + traffic.getAdresa() + "', ");
            query.append(TrafficConstants.TRAFFIC_EMAIL + " = '" + traffic.getEmail() + "', ");
            query.append(TrafficConstants.TRAFFIC_TEL + " = '" + traffic.getTel() + "', ");
            query.append(TrafficConstants.TRAFFIC_NIPT + " = '" + traffic.getNipt() + "', ");
            query.append(TrafficConstants.TRAFFIC_NRTVSH + " = '" + traffic.getNrTVSH() + "', ");
            query.append(TrafficConstants.TRAFFIC_NRBIZNESIT + " = '" + traffic.getNrBiznesit() + "', ");
            query.append(TrafficConstants.TRAFFIC_NRLICENSES + " = '" + traffic.getNrLicenses() + "' ");
            query.append("WHERE " + TrafficConstants.TRAFFIC_ID + " = '" + id + "'");
            Base.getInstance().getStatement().execute(query.toString());

        } catch (SQLException sqlException) {
            System.out.println("editTraffic() : " + sqlException.getMessage());
        }
    }

    public void deleteTraffic(PreparedStatement delete, String nrBiznesit) {
        try {
            delete.setString(1,nrBiznesit);
            delete.execute();
        } catch (SQLException sqlException) {
            System.out.println("deleteTraffic(): " + sqlException.getMessage() );
        }
    }

    public boolean validateData(String emri, String adresa, String telefoni, String email, long nipt, long nrTvsh,
                                String nrBiznesit, String nrLicenses) {
        String[] array = new String[]{emri, adresa, telefoni, email, nrBiznesit, nrLicenses};
        for (String s : array) {
            if (s == null || s.trim().length() == 0) {
                return false;
            }
        }

        return nipt >= 0 && nrTvsh >= 0;
    }

    public long stringToLong(String s) {
        if (s == null || s.trim().length() == 0) {
            return -1;
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                count++;
            }
        }

        return count == s.length() ? Long.parseLong(s) : -1;
    }

    public int getIdByNrBiznesitTraffic(PreparedStatement preparedStatement, String biznesi) {
        try {
            preparedStatement.setString(1, biznesi);
            ResultSet resultSet = preparedStatement.executeQuery();
            int id = -1;
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException sqlException) {
            System.out.println("getIDByNrBiznesit(): " + sqlException.getMessage());
            return -1;
        }
    }
}