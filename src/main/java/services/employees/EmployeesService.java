package services.employees;

import datasource.Base;
import constants.employees.PuntoriConstants;
import model.employees.Puntori;
import services.utility.ServiceUtility;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EmployeesService {
    private static EmployeesService instance = new EmployeesService();
    private PreparedStatement insert;
    private PreparedStatement edit;
    private PreparedStatement delete;

    private EmployeesService() {
        try {
            insert = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.INSERT);
            edit = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.EDIT);
            delete = Base.getInstance().getConnection().prepareStatement(PuntoriConstants.DELETE);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on Service() : " + sqlException.getMessage());
        }
    }

    public static EmployeesService getInstance() {
        return instance;
    }


    public void addPuntori(Puntori puntori) {
        try {
            if (!ServiceUtility.getInstance().validate(puntori)) {
                System.out.println("Error!");
                return;
            }
            insert.setString(1, puntori.getEmri());
            insert.setString(2, puntori.getMbiemri());
            insert.setString(3, puntori.getEmail());
            insert.setLong(4, puntori.getTel());
            insert.setDouble(5, puntori.getBilanci());
            insert.setString(6, puntori.getPassword());
            insert.setObject(7, null);
            insert.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on addEmployer() : " + sqlException.getMessage());
        }
    }

    public void addPuntori(String name, String surname, String email, long tel, double bilanci, String password) {
        addPuntori(new Puntori(name, surname, email, tel, bilanci, password));
    }

    public void editPuntori(int id, Puntori puntori) {
        try {
            edit.setString(1,puntori.getEmri());
            edit.setString(2,puntori.getMbiemri());
            edit.setString(3,puntori.getEmail());
            edit.setLong(4,puntori.getTel());
            edit.setString(5,puntori.getPassword());
            edit.setInt(6,id);
            edit.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on editPuntori() : " + sqlException.getMessage());
        }
    }

    public void editPuntori(int id, String name, String surname, String email, long tel, String password) {
        editPuntori(id, new Puntori(name,surname,email,tel,0,password));
    }

    public void deletePuntori(int id) {
        try {
            delete.setInt(1,id);
            delete.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on deletePuntori() : " + sqlException.getMessage());
        }
    }
}
