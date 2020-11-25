package constants.employees;

public class AdministratorConstants {

    public static final String ADMINISTRATOR_TABLE = "Administrator";
    public static final String ADMINISTRATOR_ID = "Id";
    public static final String ADMINISTRATOR_NAME = "Emri";
    public static final String ADMINISTRATOR_PASSWORD = "Password";

    public static final String QUERY_ID_BY_NAME = "SELECT " + ADMINISTRATOR_ID + " FROM " + ADMINISTRATOR_TABLE +
            " WHERE " + ADMINISTRATOR_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_DATA = "SELECT * FROM " + ADMINISTRATOR_TABLE;

    public static final String QUERY_NAMES = "SELECT " + ADMINISTRATOR_NAME + " FROM " + ADMINISTRATOR_TABLE;

    public static final String QUERY_NAMES_BY_ID = "SELECT " + ADMINISTRATOR_NAME + " FROM " + ADMINISTRATOR_TABLE +
            " WHERE " + ADMINISTRATOR_ID + " = ?";

    public static final String QUERY_PASSWORD_BY_NAME = "SELECT " + ADMINISTRATOR_PASSWORD + " FROM " +
            ADMINISTRATOR_TABLE + " WHERE " + ADMINISTRATOR_NAME  + " = ? COLLATE NOCASE";

}
