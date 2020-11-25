package constants.items;

import constants.traffic.FurnizuesConstants;
import constants.traffic.TrafficConstants;

public class MalliConstants {

    public static final String MALLI_TABLE = "Malli";
    public static final String MALLI_KODI = "Kodi";
    public static final String MALLI_PRESHKRIMI = "Pershkrimi";
    public static final String MALLI_SERIA = "Seria";
    public static final String MALLI_DATA_SKADIMIT = "DataSkadimit";
    public static final String MALLI_NJESIA = "Njesia";
    public static final String MALLI_SASIA = "Sasia";
    public static final String MALLI_CMIMI_PA_TVSH = "CmimiPaTVSH";
    public static final String MALLI_CMIMI_ME_TVSH = "CmimiMeTVSH";
    public static final String MALLI_RABATI = "Rabati";
    public static final String MALLI_TVSH = "TVSH";
    public static final String MALLI_VLERA_ME_TVSH = "VleraMeTVSH";
    public static final String MALLI_FURNIZUES_ID = "FurnizuesID";

    public static final String QUERY_DATA_BY_CODE = "SELECT * FROM " + MALLI_TABLE +
            " WHERE " + MALLI_KODI + " = ?";

    public static final String QUERY_NAME_SERIA = " SELECT " + MALLI_PRESHKRIMI + ", " + MALLI_SERIA + ", " + MALLI_SASIA + " FROM " + MALLI_TABLE;

    public static final String QUERY_DATA_FROM_SERIA = "SELECT * FROM " + MALLI_TABLE + " WHERE " + MALLI_SERIA + " = ?";

    public static final String QUERY_DATA = "SELECT * FROM " + MALLI_TABLE;

    public static final String QUERY_DATA_FROM_FURNIZUESI_NAME = "SELECT " + MALLI_KODI + ", " +
            MALLI_PRESHKRIMI + ", " +
            MALLI_SERIA + ", " +
            MALLI_DATA_SKADIMIT + ", " +
            MALLI_NJESIA + ", " +
            MALLI_SASIA + ", " +
            MALLI_CMIMI_PA_TVSH + ", " +
            MALLI_CMIMI_ME_TVSH + ", " +
            MALLI_RABATI + ", " +
            MALLI_TABLE + "." + MALLI_TVSH + ", " +
            MALLI_VLERA_ME_TVSH +
            " FROM " + FurnizuesConstants.FURNIZUES_TABLE +
            " INNER JOIN " +
            MALLI_TABLE + " ON " +
            FurnizuesConstants.FURNIZUES_TABLE + "." + TrafficConstants.TRAFFIC_ID + " = " + MALLI_FURNIZUES_ID +
            " WHERE " + FurnizuesConstants.FURNIZUES_TABLE + "." + TrafficConstants.TRAFFIC_NAME + " = ? COLLATE NOCASE";

    public static final String QUERY_DATA_BY_NAME  = "SELECT * FROM " + MALLI_TABLE + " WHERE " + MALLI_SERIA + " = ?";

    public static final String UPDATE = "UPDATE " + MALLI_TABLE + " SET " + MALLI_SASIA + " = ? WHERE " + MALLI_SERIA + " = ?";

    public static final String DELETE = "DELETE FROM " + MALLI_TABLE + " WHERE " + MALLI_SASIA + " = 0 OR " + MALLI_SASIA + " < 0";
}
