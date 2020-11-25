package services.employees;

import datasource.Base;
import constants.employees.PuntoriConstants;
import model.employees.Puntori;
import services.utility.ServiceUtility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PuntoriData {
    private PreparedStatement queryIdByName;
    private PreparedStatement queryNameById;
    private PreparedStatement queryPasswordByName;
    private PreparedStatement querySurnameByName;
    private PreparedStatement queryEmailByName;
    private PreparedStatement queryTelByName;
    private PreparedStatement queryBilanciByName;
    private PreparedStatement queryDataById;
    private PreparedStatement updatePassword;

    public PuntoriData() {
        try {
            this.queryIdByName = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_ID_BY_NAME);
            this.queryNameById = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_NAME_BY_ID);
            this.queryPasswordByName = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_PASSWORD_BY_NAME);
            this.querySurnameByName = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_SURNAME_BY_NAME);
            this.queryEmailByName = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_EMAIL_BY_NAME);
            this.queryTelByName = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_TEL_BY_NAME);
            this.queryBilanciByName = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_BILANCI_BY_NAME);
            this.queryDataById = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.QUERY_DATA_BY_ID);
            this.updatePassword = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.EDIT_PASSWORD);
        } catch (SQLException sqlException) {
            System.out.println("PuntoriData() : " + sqlException.getMessage());
        }
    }

    public PreparedStatement getQueryIdByName() {
        return queryIdByName;
    }

    public PreparedStatement getQueryNameById() {
        return queryNameById;
    }

    public PreparedStatement getQueryPasswordByName() {
        return queryPasswordByName;
    }

    public PreparedStatement getQuerySurnameByName() {
        return querySurnameByName;
    }

    public PreparedStatement getQueryEmailByName() {
        return queryEmailByName;
    }

    public PreparedStatement getQueryTelByName() {
        return queryTelByName;
    }

    public PreparedStatement getQueryBilanciByName() {
        return queryBilanciByName;
    }

    public PreparedStatement getQueryDataById() {
        return queryDataById;
    }

    public PreparedStatement getUpdatePassword() {
        return this.updatePassword;
    }

    public int getIdByName(String name) {
        return (int) ServiceUtility.getInstance().proceedData(queryIdByName,name);
    }

    public String getNameById(int id) {
       return (String) ServiceUtility.getInstance().proceedData(queryNameById,id);
    }

    public String getPasswordByName(String name) {
        return (String) ServiceUtility.getInstance().proceedData(queryPasswordByName,name);
    }

    public String getSurnameByName(String name) {
        return (String) ServiceUtility.getInstance().proceedData(querySurnameByName, name);
    }

    public List<Puntori> getQueryData() {
        try {
            ResultSet resultSet = Base.getInstance().getStatement().executeQuery(PuntoriConstants.QUERY_DATA);
            List<Puntori> puntoret= new ArrayList<>();
            while (resultSet.next()) {
                Puntori puntori = new Puntori();
                puntori.setEmri(resultSet.getString(1));
                puntori.setMbiemri(resultSet.getString(2));
                puntori.setEmail(resultSet.getString(3));
                puntori.setTel(resultSet.getLong(4));
                puntori.setBilanci(resultSet.getDouble(5));
                puntoret.add(puntori);
            }
            return puntoret;

        } catch (SQLException sqlException) {
            System.out.println("SQLException on queryData(): " + sqlException.getMessage());
            return null;
        }
    }

    public void updatePassword(String password, String name) {
        try {
            updatePassword.setString(1,password);
             updatePassword.setString(2,name);
             updatePassword.execute();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }
}
