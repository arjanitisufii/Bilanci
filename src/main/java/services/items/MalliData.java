package services.items;

import constants.items.MalliConstants;
import datasource.Base;
import model.items.Malli;
import services.storage.DepoData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MalliData {
    private DepoData depoData = new DepoData();
    private PreparedStatement queryDataByCode;
    private PreparedStatement queryDataByFurnizuesiName;
    private PreparedStatement queryDataByName;
    private PreparedStatement update;

    public MalliData() {
        try {
            queryDataByCode = Base.getInstance().getConnection().prepareStatement(MalliConstants.QUERY_DATA_BY_CODE);
            queryDataByFurnizuesiName = Base.getInstance().getConnection().prepareStatement(MalliConstants.QUERY_DATA_FROM_FURNIZUESI_NAME);
            queryDataByName = Base.getInstance().getConnection().prepareStatement(MalliConstants.QUERY_DATA_BY_NAME);
            update = Base.getInstance().getConnection().prepareStatement(MalliConstants.UPDATE);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on ItemsData() : " + sqlException.getMessage());
        }
    }

    public PreparedStatement getQueryDataByName() {
        return queryDataByName;
    }

    public PreparedStatement getQueryDataByCode() {
        return queryDataByCode;
    }

    public PreparedStatement getQueryDataByFurnizuesiName() {
        return queryDataByFurnizuesiName;
    }

    public PreparedStatement getUpdate() {
        return update;
    }

    public Malli getMalliByCode(long code) {
        try {
            queryDataByCode.setLong(1, code);
            ResultSet resultSet = getQueryDataByCode().executeQuery();
            if (resultSet.next()) {
                Malli malli = setMalliData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getDouble(8),
                        resultSet.getDouble(9), resultSet.getDouble(10), resultSet.getDouble(11));
                return malli;
            }
            return null;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getMalliByCode() : " + sqlException.getMessage());
            return null;
        }
    }

    public void queryMalli() {
        try {
            ResultSet resultSet = Base.getInstance().getStatement().executeQuery(MalliConstants.QUERY_DATA);
            while (resultSet.next()) {
                depoData.insertData(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(6));
            }
        } catch (SQLException sqlException) {
            System.out.println("SQLException on queryMalli() : " + sqlException.getMessage());
        }
    }

    public List<Malli> queryMalliDataByFurnizuesiName(String furnizuesiName) {
        try {
            getQueryDataByFurnizuesiName().setString(1, furnizuesiName);
            ResultSet resultSet = getQueryDataByFurnizuesiName().executeQuery();
            ArrayList<Malli> list = new ArrayList<>();
            while (resultSet.next()) {
                Malli malli = setMalliData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getDouble(8),
                        resultSet.getDouble(9), resultSet.getDouble(10), resultSet.getDouble(11));
                list.add(malli);
            }
            return list;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            return null;
        }
    }

    private Malli setMalliData(int code, String pershkrimi, String seria, String dataSkadimit, String njesia, int sasia, double cmimiPaTvsh,
                               double cmimiMeTvsh, double rabati, double tvsh, double vleraMeTvsh) {
        Malli malli = new Malli();
        malli.setKodi(code);
        malli.setPershkrimi(pershkrimi);
        malli.setSeria(seria);
        malli.setDataSkadimit(dataSkadimit);
        malli.setNjesia(njesia);
        malli.setSasia(sasia);
        malli.setCmimiPaTVSH(cmimiPaTvsh);
        malli.setCmimiMeTVSH(cmimiMeTvsh);
        malli.setRabati(rabati);
        malli.setTvsh(tvsh);
        malli.setVleraMeTVSH(vleraMeTvsh);
        return malli;
    }

    public Malli getDataBySeria(String seria) {
        try {
            queryDataByName.setString(1, seria);
            ResultSet resultSet = queryDataByName.executeQuery();
            while (resultSet.next()) {
                return setMalliData(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6), resultSet.getDouble(7), resultSet.getDouble(8),
                        resultSet.getDouble(9), resultSet.getDouble(10), resultSet.getDouble(11));
            }
            return null;
        } catch (SQLException sqlException) {
            System.out.println("getDataByPershkrimi() : " + sqlException.getMessage());
            return null;
        }
    }

    public void updateSasia(int sasia, String seria) {
        try {
            update.setInt(1, sasia);
            update.setString(2, seria);
            update.execute();
        } catch (SQLException sqlException) {
            System.out.println("SQLException on updatesasia() : " + sqlException.getMessage());
        }
    }

    public void deleteMalli() {
        try {
            Base.getInstance().getStatement().execute(MalliConstants.DELETE);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on deleteMalli() : " + sqlException.getMessage());
        }
    }


}
