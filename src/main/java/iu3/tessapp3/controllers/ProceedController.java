package iu3.tessapp3.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import iu3.tessapp3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class ProceedController {

    @FXML
    private FontAwesomeIconView btnClose;

    @FXML
    public TextArea txtArea;

    @FXML
    private Button btnStart;

    public void handleClose(javafx.scene.input.MouseEvent event){
        if (event.getSource() == btnClose){
            //System.exit(0);
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void handleOCR(ActionEvent event) throws Exception {
        if (event.getSource() == btnStart){
            txtArea.clear();
            txtArea.appendText(tessDoOCR());
        }
    }

    public String tessDoOCR() throws Exception {
        String inputFilePath = HelloApplication.Example.f.getAbsolutePath();

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata");
        tesseract.setLanguage("eng");
        String fullText =  tesseract.doOCR(new File(inputFilePath));
        //HelloApplication.Example.result = fullText;
        return fullText;
    }
}
