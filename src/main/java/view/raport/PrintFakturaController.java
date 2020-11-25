package view.raport;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import model.items.Malli;
import model.storage.Depo;
import model.traffic.Traffic;
import services.items.MalliData;
import services.raport.FaktureData;

import java.util.ArrayList;
import java.util.List;

public class PrintFakturaController {

    private MalliData malliData = new MalliData();
    @FXML
    private AnchorPane anchorPane;
    @FXML
    public Label adresaShitesiEdit;
    @FXML
    private Label telShitesiEdit;
    @FXML
    private Label niptShitesiEdit;
    @FXML
    private Label tvshShitesiEdit;
    @FXML
    private Label biznesiShitesiEdit;
    @FXML
    private Label licensaShitesiEdit;
    @FXML
    private Label adresaBlersiEdit;
    @FXML
    private Label telBlersiEdit;
    @FXML
    private Label niptBlersiEdit;
    @FXML
    private Label tvshBlersiEdit;
    @FXML
    private Label biznesiBlersiEdit;
    @FXML
    private Label licensesBlersiEdit;
    @FXML
    private Label qmimiLabel;
    @FXML
    private Label shitesiEmri;
    @FXML
    private Label bleresiEmri;
    @FXML
    private TableView<Malli> malliTableView;
    @FXML
    private Button print;

    public void fillData(Traffic f, String adresa, String tel, String nipt, String tvsh, String biznesi, String licensa, String emri) {
        shitesiEmri.setText(f.getEmri());
        adresaShitesiEdit.setText(f.getAdresa());
        telShitesiEdit.setText(f.getTel());
        niptShitesiEdit.setText("" + f.getNipt());
        tvshShitesiEdit.setText("" + f.getNrTVSH());
        biznesiShitesiEdit.setText(f.getNrBiznesit());
        licensaShitesiEdit.setText(f.getNrLicenses());
        bleresiEmri.setText(emri);
        adresaBlersiEdit.setText(adresa);
        telBlersiEdit.setText(tel);
        niptBlersiEdit.setText(nipt);
        tvshBlersiEdit.setText(tvsh);
        biznesiBlersiEdit.setText(biznesi);
        licensesBlersiEdit.setText(licensa);
    }

    public void getQmimiBlerje(List<Malli> list, int sasia) {
        List<Malli> updated = bindTableView(list, sasia);

        for (Malli m : updated) {
            m.setVleraMeTVSH(m.getVleraMeTVSH() / sasia);
            m.setCmimiPaTVSH(m.getCmimiPaTVSH() / sasia);
            m.setCmimiMeTVSH(m.getCmimiMeTVSH() / sasia);
            m.setRabati(m.getRabati() / sasia);
            m.setTvsh(m.getTvsh() / sasia);
        }
    }


    public void getQmimiShitje(List<Depo> depo, int sasia) {
        List<Malli> malliList = new ArrayList<>();
        for (Depo d : depo) {
            malliList.add(malliData.getDataBySeria(d.getSeria()));
        }
        bindTableView(malliList, sasia);
    }


    private List<Malli> setDataForMalli(List<Malli> list, int sasia) {
        List<Malli> updatedList = new ArrayList<>();
        for (Malli m : list) {

            double cmimiPaTVSH = m.getCmimiPaTVSH() * sasia;
            double cmimiMeTVSH = m.getCmimiMeTVSH() * sasia;
            double rabati = m.getRabati() * sasia;
            double tvsh = m.getTvsh() * sasia;
            double vleraMeTVSH = m.getVleraMeTVSH() * sasia;

            m.setSasia(sasia);
            m.setCmimiMeTVSH(cmimiMeTVSH);
            m.setCmimiPaTVSH(cmimiPaTVSH);
            m.setRabati(rabati);
            m.setTvsh(tvsh);
            m.setVleraMeTVSH(vleraMeTVSH);
            updatedList.add(m);
        }

        double qmimi = updatedList.stream().mapToDouble(Malli::getVleraMeTVSH).sum();
        qmimiLabel.setText(qmimi + " €");
        return updatedList;
    }

    private List<Malli> bindTableView(List<Malli> data, int sasia) {
        List<Malli> updated = setDataForMalli(data, sasia);

        Task<ObservableList<Malli>> task = new Task<ObservableList<Malli>>() {
            @Override
            protected ObservableList<Malli> call() throws Exception {
                return FXCollections.observableArrayList(updated);
            }
        };
        malliTableView.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
        return updated;
    }

    @FXML
    public void printFaktura() {
        print.setVisible(false);
        WritableImage snapshot = anchorPane.getScene().snapshot(null);
        ImageView imageView = new ImageView(snapshot);
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / imageView.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / imageView.getBoundsInParent().getHeight();
        imageView.getTransforms().add(new Scale(scaleX, scaleY));

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(imageView);
            if (success) {
                job.endJob();
            }
        }

        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}
