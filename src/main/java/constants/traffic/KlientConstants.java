package constants.traffic;

import static constants.traffic.TrafficConstants.*;

public class KlientConstants {

    public static final String KLIENT_TABLE = "Klient";

    public static final String QUERY_DATA_BY_ID = "SELECT * FROM " + KLIENT_TABLE + " WHERE " + TRAFFIC_ID + " = ?";

    public static final String QUERY_DATA = "SELECT * FROM " + KLIENT_TABLE;

    public static final String INSERT_KLIENT = "INSERT INTO " + KLIENT_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_FURNIZUES = "UPDATE " + KLIENT_TABLE + " SET ";

    public static final String QUERY_ID_BY_NRBIZNESIT = "SELECT " + TRAFFIC_ID + " FROM " + KLIENT_TABLE + " WHERE " + TRAFFIC_NRBIZNESIT + " = ? ";

    public static final String DELETE = "DELETE FROM " + KLIENT_TABLE + " WHERE " + TRAFFIC_NRBIZNESIT + " = ?";

}
