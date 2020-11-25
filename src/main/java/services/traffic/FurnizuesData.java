package services.traffic;

import constants.traffic.FurnizuesConstants;
import datasource.Base;
import model.traffic.Furnizues;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FurnizuesData {
    private PreparedStatement insertFurnizues;
    private PreparedStatement queryDataById;
    private PreparedStatement queryIdByBiz;
    private PreparedStatement delete;

    public FurnizuesData() {
        try {
            insertFurnizues = Base.getInstance().getConnection().prepareStatement(FurnizuesConstants.INSERT_FURNIZUES);
            queryDataById = Base.getInstance().getConnection().prepareStatement(FurnizuesConstants.QUERY_DATA_BY_ID);
            queryIdByBiz = Base.getInstance().getConnection().prepareStatement(FurnizuesConstants.QUERY_ID_BY_NRBIZNESIT);
            delete = Base.getInstance().getConnection().prepareStatement(FurnizuesConstants.DELETE);
        } catch (SQLException sqlException) {
            System.out.println("Error on FurnizuesData() : " + sqlException.getMessage());
        }
    }

    public PreparedStatement getQueryDataById() {
        return queryDataById;
    }

    public PreparedStatement getQueryIdByBiz() {
        return queryIdByBiz;
    }

    public PreparedStatement getInsertFurnizues() {
        return this.insertFurnizues;
    }

    public PreparedStatement getDelete() {
        return this.delete;
    }

    public void addFurnizues(Furnizues f) {
        TrafficService.getInstance().addTraffic(insertFurnizues, f);
    }

    public void editFurnizues(Furnizues f, int id) {
        TrafficService.getInstance().editTraffic(FurnizuesConstants.UPDATE_FURNIZUES,f, id);
    }

    public void deleteFurnizues(String name) {
        TrafficService.getInstance().deleteTraffic(delete,name);
    }

    public Furnizues getDataById(int id) {
        try {
            queryDataById.setInt(1,id);
            ResultSet resultSet = getQueryDataById().executeQuery();
            while(resultSet.next()) {
                return (Furnizues)TrafficUtility.getKlientInfo(new Furnizues(), resultSet);
            }
            return null;
        } catch (SQLException sqlException) {
            System.out.println("Erron on getDataById() : " + sqlException.getMessage());
            return null;
        }
    }

    public List<Furnizues> getData() {
        try {
            ResultSet resultSet = Base.getInstance().getStatement().executeQuery(FurnizuesConstants.QUERY_DATA);
            List<Furnizues> furnizues = new ArrayList<>();
            while(resultSet.next()) {
                furnizues.add((Furnizues)TrafficUtility.getKlientInfo(new Furnizues(),resultSet));
            }
            return furnizues;
        } catch (SQLException sqlException) {
            System.out.println("SQLException on getData() : " + sqlException.getMessage());
            return null;
        }
    }

    public int getIdByBiz(String biznes) {
        return TrafficService.getInstance().getIdByNrBiznesitTraffic(queryIdByBiz,biznes);
    }
}
