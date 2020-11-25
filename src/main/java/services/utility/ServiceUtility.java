package services.utility;

import model.employees.Admin;
import model.employees.Puntori;
import services.traffic.TrafficService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUtility {

    private static ServiceUtility serviceUtility = new ServiceUtility();

    public static ServiceUtility getInstance() {
        return serviceUtility;
    }

    public Object proceedData(PreparedStatement preparedStatement, Object o) {
        try {
            preparedStatement.setObject(1, o);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getObject(1);
            }
            return null;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on proceedData : " + sqlException.getMessage());
            return null;
        }
    }

    private boolean checkPuntoriInfo(String name, String surname, String email, long tel, double bilanci, String password) {
        List<String> strings = new ArrayList<>();
        strings.add(name);
        strings.add(surname);
        strings.add(email);
        strings.add(password);

        for (String s : strings) {
            if (s == null || s.trim().length() == 0) {
                return false;
            }
        }

        if (tel <= 0) {
            return false;
        }

        return bilanci >= 0.0;
    }

    public boolean validate(String name, String surname, String email, long tel, double bilanci, String password) {
        if (!checkPuntoriInfo(name, surname, email, tel, bilanci, password)) {
            System.out.println("Values are wrong");
            return false;
        }
        return true;
    }

    public boolean validate(Puntori puntori) {
        return validate(puntori.getEmri(), puntori.getMbiemri(), puntori.getEmail(),
                puntori.getTel(), puntori.getBilanci(), puntori.getPassword());
    }
}
