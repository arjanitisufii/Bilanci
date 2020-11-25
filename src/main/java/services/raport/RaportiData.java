package services.raport;

import constants.raport.RaportiConstants;
import datasource.Base;
import model.raport.Raporti;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaportiData {

    private PreparedStatement searchData;
    private PreparedStatement insert;

    public RaportiData() {
        try {
            this.searchData = Base.getInstance().getConnection().prepareStatement(RaportiConstants.SEARCH_DATA);
            this.insert = Base.getInstance().getConnection().prepareStatement(RaportiConstants.INSERT);
        } catch (SQLException sqlException) {
            System.out.println("RaportiData() : " + sqlException.getMessage());
        }
    }

    public PreparedStatement getSearchData() {
        return this.searchData;
    }

    public PreparedStatement getInsert() {
        return this.insert;
    }

    public List<Raporti> getData() {
        try {
            ResultSet resultSet = Base.getInstance().getStatement().executeQuery(RaportiConstants.QUERY_DATA);
            List<Raporti> raports = new ArrayList<>();
            while (resultSet.next()) {
                Raporti raporti = getRaportiList(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8));
                raports.add(raporti);
            }
            return raports;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getData() : " + sqlException.getMessage());
            return null;
        }
    }

    public List<Raporti> searchData(int nrDokumentit, String dataFillimi, String dataMbarimi, String pershkrimi, String nrSerial, String njesiSherbimi, String lloji, String statusPagese) {
        try {
            setPreparedStatementValues(searchData, lloji, nrDokumentit, dataFillimi, dataMbarimi, pershkrimi, nrSerial, njesiSherbimi, statusPagese);
            List<Raporti> list = new ArrayList<>();
            ResultSet resultSet = searchData.executeQuery();
            while (resultSet.next()) {
                Raporti raport = getRaportiList(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8));
                list.add(raport);
            }

            return list;
        } catch (SQLException sqlException) {
            System.out.println("SQLException : " + sqlException.getMessage());
            return null;
        }
    }

    public void insertData(String lloji, int nrDokumentit, String dataFillimi, String dataMbarimi, String pershkrimi, String nrSerial, String njesiSherbimi, String statusPagese) {
        try {
            setPreparedStatementValues(insert, lloji, nrDokumentit, dataFillimi, dataMbarimi, pershkrimi, nrSerial, njesiSherbimi, statusPagese);
            insert.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getRaportiList(): " + sqlException.getMessage());
        }
    }

    private void setPreparedStatementValues(PreparedStatement preparedStatmentValues, String lloji, int nrDokumentit, String dataFillimi, String dataMbarimi, String pershkrimi, String nrSerial, String njesiSherbimi, String statusPagese) throws SQLException {
        preparedStatmentValues.setString(1,lloji);
        preparedStatmentValues.setInt(2, nrDokumentit);
        preparedStatmentValues.setString(3,dataFillimi);
        preparedStatmentValues.setString(4, dataMbarimi);
        preparedStatmentValues.setString(5, pershkrimi);
        preparedStatmentValues.setString(6, nrSerial);
        preparedStatmentValues.setString(7, njesiSherbimi);
        preparedStatmentValues.setString(8, statusPagese);
    }

    private Raporti getRaportiList(String lloji, int nrDokumentit, String dataFillimi, String dataMbarimi, String pershkrimi, String nrSerial, String njesiSherbimi, String statusPagese) {
        Raporti raporti = new Raporti();
        raporti.setLlojiRaportit(lloji);
        raporti.setNrDokumentit(nrDokumentit);
        raporti.setDataFillimi(dataFillimi);
        raporti.setDataMbarimi(dataMbarimi);
        raporti.setPershkrimiRaporti(pershkrimi);
        raporti.setNrSerial(nrSerial);
        raporti.setNjesiSherbimi(njesiSherbimi);
        raporti.setStatusPagese(statusPagese);
        return raporti;
    }


}
