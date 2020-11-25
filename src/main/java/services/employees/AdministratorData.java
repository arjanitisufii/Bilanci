package services.employees;

import constants.employees.AdministratorConstants;
import datasource.Base;
import model.employees.Admin;
import services.utility.ServiceUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorData {
    private PreparedStatement queryNamesByID;
    private PreparedStatement queryPasswordByName;
    private PreparedStatement queryIDByName;

    public AdministratorData() {
         try {
            queryNamesByID = Base.getInstance().getConnection().prepareStatement(AdministratorConstants.QUERY_NAMES_BY_ID);
            queryPasswordByName = Base.getInstance().getConnection().prepareStatement(AdministratorConstants.QUERY_PASSWORD_BY_NAME);
            queryIDByName = Base.getInstance().getConnection().prepareStatement(AdministratorConstants.QUERY_ID_BY_NAME);
        } catch (SQLException s) {
            System.out.println("Couldnt prepare statements");
        }
    }

    public PreparedStatement getQueryNamesByID() {
        return queryNamesByID;
    }

    public PreparedStatement getQueryPasswordByName() {
        return queryPasswordByName;
    }

    public PreparedStatement getQueryIDByName() {
        return queryIDByName;
    }

    public List<Admin> getAdminData() {
        try {
            ResultSet rs = Base.getInstance().getStatement().executeQuery(AdministratorConstants.QUERY_DATA);
            ArrayList<Admin> admins = new ArrayList<>();
            while(rs.next()) {
                Admin admin = new Admin();
                admin.setEmri(rs.getString(2));
                admin.setMbiemri(rs.getString(4));
                admins.add(admin);
            }
            return admins;
        } catch (SQLException sqlException) {
            System.out.println("Error getting admin data");
            return null;
        }
    }

    public List<String> getAdminsNames() {
        try {
            ResultSet rs = Base.getInstance().getStatement().executeQuery(AdministratorConstants.QUERY_NAMES);
            ArrayList<String> names = new ArrayList<>();
            while(rs.next()) {
                names.add(rs.getString(1));
            }
            return names;
        } catch (SQLException sqlException) {
            System.out.println("Error getting data");
            System.out.println(sqlException.getMessage());
            return null;
        }
    }

    public int getAdminIDByName(String name) {
       return (int) ServiceUtility.getInstance().proceedData(getQueryIDByName(),name);
    }
    public String getAdminNamesByID(int id) {
        return (String) ServiceUtility.getInstance().proceedData(getQueryNamesByID(),id);
    }

    public String getAdminPassword(String name) {
        return (String) ServiceUtility.getInstance().proceedData(getQueryPasswordByName(),name);
    }

}
