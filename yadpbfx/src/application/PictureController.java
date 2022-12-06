package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class PictureController implements Initializable, DialogInterface {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnFilename;

    @FXML
    private Button btnGeneral;

    @FXML
    private ComboBox<String> cbSize;

    @FXML
    private TextField txtFilename;

    @FXML
    private TextField txtIncrement;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doFilename(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("PNG", "*.png"),
				new FileChooser.ExtensionFilter("JPEG", "*.jpg,*.JPEG"),
				new FileChooser.ExtensionFilter("BMP", "*.bmp"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtFilename.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("filename", txtFilename.getText());
        }
    }

    @FXML
    void doGeneral(ActionEvent event) {
    	yg.showGeneral(btnGeneral);
    }

    @FXML
    void doIncrement(ActionEvent event) {
    	yg.iniUpdate("increment", txtIncrement.getText());
    }

    @FXML
    void doSize(ActionEvent event) {
    	yg.iniUpdate("size", cbSize.getValue().toString());
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbSize.getItems().addAll("fit", "orig");
	}

	@Override
	public void updateDialog() {
		
		String increment = yg.currIni.getString(yg.currDialog, "increment");
		String size = yg.currIni.getString(yg.currDialog, "size");
		
		if (increment != null)
			txtIncrement.setText(increment);
		if (size != null)
			cbSize.getSelectionModel().select(size);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}
