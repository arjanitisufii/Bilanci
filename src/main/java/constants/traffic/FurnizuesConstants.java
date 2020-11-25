package constants.traffic;

import static constants.traffic.TrafficConstants.*;

public class FurnizuesConstants {

    public static final String FURNIZUES_TABLE = "Furnizues";

    public static final String QUERY_DATA_BY_ID = "SELECT * FROM " + FURNIZUES_TABLE + " WHERE " + TRAFFIC_ID + " = ?";

    public static final String QUERY_DATA = "SELECT * FROM " + FURNIZUES_TABLE;

    public static final String INSERT_FURNIZUES = "INSERT INTO " + FURNIZUES_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_FURNIZUES = "UPDATE " + FURNIZUES_TABLE + " SET ";

    public static final String QUERY_ID_BY_NRBIZNESIT = "SELECT " + TRAFFIC_ID + " FROM " + FURNIZUES_TABLE + " WHERE " + TRAFFIC_NRBIZNESIT + " = ? ";

    public static final String DELETE = "DELETE FROM " + FURNIZUES_TABLE + " WHERE " + TRAFFIC_NRBIZNESIT + " = ?";



}
