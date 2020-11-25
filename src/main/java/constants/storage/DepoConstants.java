package constants.storage;

public class DepoConstants {

    public static final String DEPO_TABLE = "Depo";
    public static final String DEPO_ARTIKULL = "Artikull";
    public static final String DEPO_SERIA = "Seria";
    public static final String DEPO_SASIA = "Sasia";

    public static final String QUERY_DATA = "SELECT * FROM " + DEPO_TABLE;

    public static final String QUERY_SASIA = "SELECT " + DEPO_SASIA + " FROM " + DEPO_TABLE +
            " WHERE " + DEPO_SERIA + " = ?";

    public static final String INSERT = "INSERT OR REPLACE INTO " +  DEPO_TABLE + " VALUES (?,?,?)";

    public static final String DELETE = "DELETE FROM " + DEPO_TABLE + " WHERE " + DEPO_SASIA + " = 0 ";

    public static final String UPDATE = "UPDATE " + DEPO_TABLE + " SET " + DEPO_SASIA + " = ? WHERE " + DEPO_SERIA + " = ?";



}
