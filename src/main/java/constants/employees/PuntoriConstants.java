package constants.employees;

import constants.traffic.TrafficConstants;

public class PuntoriConstants {

    public static final String PUNTORI_TABLE = "Puntori";
    public static final String PUNTORI_ID = "Id";
    public static final String PUNTORI_NAME = "Emri";
    public static final String PUNTORI_SURNAME = "Mbiemri";
    public static final String PUNTORI_PASSWORD = "Password";
    public static final String PUNTORI_EMAIL = "Email";
    public static final String PUNTORI_TEL = "Tel";
    public static final String PUNTORI_BILANCI = "Bilanci";

    public static final String QUERY_ID_BY_NAME = "SELECT " + PUNTORI_ID + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_NAME_BY_ID = "SELECT " + PUNTORI_NAME + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_ID + " = ?";

    public static final String QUERY_PASSWORD_BY_NAME = "SELECT " + PUNTORI_PASSWORD + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_SURNAME_BY_NAME = "SELECT " + PUNTORI_SURNAME + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_EMAIL_BY_NAME = "SELECT " + PUNTORI_EMAIL + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_EMAIL + " = ? COLLATE NOCASE";

    public static final String QUERY_TEL_BY_NAME = "SELECT " + PUNTORI_TEL + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_BILANCI_BY_NAME = "SELECT " + PUNTORI_BILANCI + " FROM " + PUNTORI_TABLE +
            " WHERE " + PUNTORI_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_DATA_BY_ID = "SELECT " + PUNTORI_NAME + ", " + PUNTORI_SURNAME + ", " +
            PUNTORI_EMAIL + ", " + PUNTORI_TEL + " FROM " + PUNTORI_TABLE + " WHERE " + PUNTORI_ID + " = ?";

    public static final String QUERY_DATA = "SELECT " + PUNTORI_NAME + ", " + PUNTORI_SURNAME + ", " +
            PUNTORI_EMAIL + ", " + PUNTORI_TEL + ", " + PUNTORI_BILANCI + " FROM " +
            PUNTORI_TABLE;

    public static final String INSERT = " INSERT INTO " + PUNTORI_TABLE + " VALUES (?,?,?,?,?,?,?)";

    public static final String EDIT = "UPDATE " + PUNTORI_TABLE + " SET " + PUNTORI_NAME + " = ?, "
            + PUNTORI_SURNAME + " = ?, " + PUNTORI_EMAIL + " = ?, " + PUNTORI_TEL + " = ?, " +
            PUNTORI_PASSWORD + " = ? WHERE " + PUNTORI_ID + " = ?";

    public static final String EDIT_PASSWORD = "UPDATE " + PUNTORI_TABLE +" SET " + PUNTORI_PASSWORD + " = ? WHERE " + PUNTORI_NAME + " = ?";

    public static final String DELETE = "DELETE FROM " + PUNTORI_TABLE + " WHERE " + PUNTORI_ID + " = ?";

}
