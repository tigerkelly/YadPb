package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class OpenDataController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private TextArea taScript;
    
    @FXML
    private AnchorPane aPane;

    @FXML
    void doClose(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    public void setData(String data) {
    	taScript.setText(data);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		
	}

	@Override
	public void saveDialog() {
		
	}
}