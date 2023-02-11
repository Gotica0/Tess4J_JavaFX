package iu3.tessapp3.controllers;

import iu3.tessapp3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import static iu3.tessapp3.HelloApplication.Example.f;

public class HelloController implements Initializable {

    @FXML
    private Button btnAccount;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnProcessing;

    @FXML
    private Button btnSettings;

    @FXML
    private Label lblStatus1;

    @FXML
    private Pane pnlStatus11;

    @FXML
    private Button btnChooseFile;

    @FXML
    private FontAwesomeIconView btnClose;

    @FXML
    private Button btnProceed;

    @FXML
    private Pane pnAccount;

    @FXML
    private Pane pnHistory;

    @FXML
    private Pane pnProcessing;

    @FXML
    private Pane pnSettings;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClicks(ActionEvent event){
        if (event.getSource() == btnProcessing){
            lblStatus1.setText("Processing");
            pnlStatus11.setBackground(new Background(new BackgroundFill(Color.rgb(113,86,221), CornerRadii.EMPTY, Insets.EMPTY)));
            pnProcessing.toFront();
        }
        else
        if (event.getSource() == btnHistory){
            lblStatus1.setText("History");
            pnlStatus11.setBackground(new Background(new BackgroundFill(Color.rgb(43,63,99), CornerRadii.EMPTY, Insets.EMPTY)));
            pnHistory.toFront();
        }
        else
        if (event.getSource() == btnAccount){
            lblStatus1.setText("Account");
            pnlStatus11.setBackground(new Background(new BackgroundFill(Color.rgb(43,99,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnAccount.toFront();
        }
        else
        if (event.getSource() == btnSettings){
            lblStatus1.setText("Settings");
            pnlStatus11.setBackground(new Background(new BackgroundFill(Color.rgb(99,43,63), CornerRadii.EMPTY, Insets.EMPTY)));
            pnSettings.toFront();
        }

    }

    @FXML
    public void handleFileChooser(ActionEvent event) throws IOException {
        if (event.getSource() == btnChooseFile) {
            FileChooser fc = new FileChooser();
            fc.setTitle("Open Resource File");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.tiff", "*.jpeg", "*.gif", "*.png", "*.bmp", "*.jpg"),
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            HelloApplication.Example.f = fc.showOpenDialog(null);
            if (HelloApplication.Example.f != null) {
                System.out.println(HelloApplication.Example.f.getAbsolutePath());
            } else {
                System.out.println("Selected file = null");
            }
        }
    }

    @FXML
    public void handleOCR(ActionEvent event) throws Exception {
        final double[] xOffset = {0};
        final double[] yOffset = {0};
        if ((event.getSource() == btnProceed) & (HelloApplication.Example.f != null)){
            System.out.println("Proceeding");

            //tessDoOCR();
            //ProceedController.txtArea.appendText(tessDoOCR());

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("proceed.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            //Parent root = FXMLLoader.load(getClass().getResource("proceed.fxml"));

            stage.setTitle("Digitized text");
            stage.setResizable(false);
            //modstage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            scene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset[0] = event.getSceneX();
                    yOffset[0] = event.getSceneY();
                }
            });
            scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset[0]);
                    stage.setY(event.getScreenY() - yOffset[0]);
                }
            });
            stage.initModality(Modality.WINDOW_MODAL);
            //modstage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        }
    }

    public void handleClose(javafx.scene.input.MouseEvent event){
        if (event.getSource() == btnClose){
            System.exit(0);
        }
    }

    public String tessDoOCR() throws Exception {
        String inputFilePath = HelloApplication.Example.f.getAbsolutePath();

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata");
        String fullText =  tesseract.doOCR(new File(inputFilePath));
        //HelloApplication.Example.result = fullText;
        return fullText;
    }

}