package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox chkBackupProject;
    
    @FXML
    private CheckBox chkAutoSelect;

    @FXML
    private TextField txtWorkDir;
    
    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    void doWorkDir(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtWorkDir.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) aPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doSave(ActionEvent event) {
    	yg.sysIni.addValuePair("System", "workdir", txtWorkDir.getText());
		yg.sysIni.addValuePair("System", "backup", chkBackupProject.isSelected() ? "true" : "false");
		yg.sysIni.addValuePair("System", "backup", chkAutoSelect.isSelected() ? "true" : "false");
		
		yg.sysIni.writeFile(true);
		
		Stage stage = (Stage) aPane.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtWorkDir.setText(yg.workDir.getAbsolutePath());
		
		boolean backup = yg.sysIni.getBoolean("System", "backup");
		boolean autoselect = yg.sysIni.getBoolean("System", "autoselect");
		
		chkBackupProject.setSelected(backup);
		chkAutoSelect.setSelected(autoselect);
		
	}

}
