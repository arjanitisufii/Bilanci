package constants.raport;

public class RaportiConstants {

    public static final String RAPORTI_TABLE = "Raporti";
    public static final String RAPORTI_LLOJI = "Lloji";
    public static final String RAPORTI_NRDOKUMENTIT = "NrDokumentit";
    public static final String RAPORTI_DATA_FILLIMIT = "DataFillimi";
    public static final String RAPORTI_DATA_MBARIMIT = "DataMbarimi";
    public static final String RAPORTI_PERSHKRIMI = "Pershkrimi";
    public static final String RAPORTI_NR_SERIAL = "NrSerial";
    public static final String RAPORTI_NJESI_SHERBIMI = "NjesiSherbimi";
    public static final String RAPORTI_STATUS_PAGESE = "StatusPagese";

    public static final String QUERY_DATA = "SELECT * FROM " + RAPORTI_TABLE;

    public static final String SEARCH_DATA = "SELECT * FROM " + RAPORTI_TABLE + " WHERE " + RAPORTI_LLOJI + " = ? COLLATE NOCASE OR " +
            RAPORTI_NRDOKUMENTIT + " = ? OR " +
            RAPORTI_DATA_FILLIMIT + " = ? OR " +
            RAPORTI_DATA_MBARIMIT + " = ? OR " +
            RAPORTI_PERSHKRIMI + " = ? COLLATE NOCASE OR " +
            RAPORTI_NR_SERIAL + " = ? COLLATE NOCASE OR " +
            RAPORTI_NJESI_SHERBIMI + " = ? COLLATE NOCASE OR " +
            RAPORTI_STATUS_PAGESE + " = ? COLLATE NOCASE";

    public static final String INSERT = "INSERT INTO " + RAPORTI_TABLE + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
}