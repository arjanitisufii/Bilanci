package services.utility;

import services.employees.AdministratorData;
import services.employees.PuntoriData;

public class LoginVerification {
    private AdministratorData administratorData;
    private PuntoriData puntoriData;
    private static LoginVerification instance = new LoginVerification();

    private LoginVerification() {
        administratorData = new AdministratorData();
        puntoriData = new PuntoriData();
    }

    public static LoginVerification getInstance() {
        return instance;
    }

    public boolean verifyAdmin(String name, String password) {
        String adminsPassword = administratorData.getAdminPassword(name);
        if(!validateValue(name) || !validateValue(password) || !validateValue(adminsPassword)) {
            return false;
        }
        return adminsPassword.equalsIgnoreCase(password);
    }

    public boolean verifyPuntori(String name, String password) {
        String puntoriPassword = puntoriData.getPasswordByName(name);
        if(!validateValue(name) || !validateValue(password) || !validateValue(puntoriPassword)) {
            return false;
        }
        return puntoriPassword.equalsIgnoreCase(password);
    }

    private boolean validateValue(String value) {
        if(value == null || value.trim().length() == 0) {
            return false;
        }
        return true;
    }
}
