package services.traffic;

import constants.traffic.KlientConstants;
import constants.traffic.TrafficConstants;
import datasource.Base;
import model.traffic.Klient;
import model.traffic.Traffic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class KlientiData {
    private PreparedStatement insertKlient;
    private PreparedStatement queryDataById;
    private PreparedStatement queryIdByNrBiz;
    private PreparedStatement delete;

    public KlientiData() {
        try {
            insertKlient = Base.getInstance().getConnection().prepareStatement(KlientConstants.INSERT_KLIENT);
            queryDataById = Base.getInstance().getConnection().prepareStatement(KlientConstants.QUERY_DATA_BY_ID);
            queryIdByNrBiz = Base.getInstance().getConnection().prepareStatement(KlientConstants.QUERY_ID_BY_NRBIZNESIT);
            delete = Base.getInstance().getConnection().prepareStatement(KlientConstants.DELETE);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on KlientiData() : " + sqlException.getMessage());
        }
    }

    public PreparedStatement getQueryDataById() {
        return queryDataById;
    }

    public PreparedStatement getQueryIdByNrBiz() {
        return queryIdByNrBiz;
    }

    public PreparedStatement getInsertKlient() {
        return this.insertKlient;
    }

    public PreparedStatement getDelete() {
        return this.delete;
    }

    public void addKlient(Klient k) {
        TrafficService.getInstance().addTraffic(insertKlient, k);
    }

    public void editKlient(Klient k, int id) {
        TrafficService.getInstance().editTraffic(KlientConstants.UPDATE_FURNIZUES,k, id);
    }

    public void deleteKlient(String name) {
        TrafficService.getInstance().deleteTraffic(delete, name);
    }



    public Klient getDataById(int id) {
        try {
            queryDataById.setInt(1, id);
            ResultSet resultSet = getQueryDataById().executeQuery();
            Klient klient = new Klient();
            return (Klient) TrafficUtility.getKlientInfo(new Klient(), resultSet);
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getDataById() : " + sqlException.getMessage());
            return null;
        }
    }

    public List<Klient> getData() {
        try {
            ResultSet resultSet = Base.getInstance().getStatement().executeQuery(KlientConstants.QUERY_DATA);
            ArrayList<Klient> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add((Klient) TrafficUtility.getKlientInfo(new Klient(), resultSet));
            }
            return clients;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getData() : " + sqlException.getMessage());
            return null;
        }
    }

    public int getIdByNrBiznesit(String nrBiznesit) {
       return TrafficService.getInstance().getIdByNrBiznesitTraffic(queryIdByNrBiz, nrBiznesit);
    }

}
