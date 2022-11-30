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

public class RunOutputController implements Initializable {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnClose;

    @FXML
    private TextArea taOutput;

    @FXML
    void doClose(ActionEvent event) {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setOutput(String output) {
		taOutput.setText(output);
	}

}