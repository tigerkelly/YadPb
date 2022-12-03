package application;

import java.io.File;
import java.io.IOException;
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
    private Button btnRebuildIconList;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox chkBackupProject;
    
    @FXML
    private CheckBox chkAutoSelect;
    
    @FXML
    private CheckBox chkShowCommand;

    @FXML
    private TextField txtWorkDir;
    
    @FXML
    private TextField txtYad;
    
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
    void doYad(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtYad.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) aPane.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void doRebuildIconList(ActionEvent event) {
    	// Build icon list.
		File f = new File(System.getProperty("user.home") + File.separator + "YadPb" + File.separator + "mk_icons_list.sh");
		
		if (f.exists() == true) {
			try {
				ProcessBuilder pb = new ProcessBuilder(f.getAbsolutePath());
				Process process = pb.start();
				process.waitFor();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }

    @FXML
    void doSave(ActionEvent event) {
    	yg.sysIni.addValuePair("System", "workdir", txtWorkDir.getText());
    	yg.sysIni.addValuePair("System", "yad", txtYad.getText());
		yg.sysIni.addValuePair("System", "backup", chkBackupProject.isSelected() ? "true" : "false");
		yg.sysIni.addValuePair("System", "autoselect", chkAutoSelect.isSelected() ? "true" : "false");
		yg.sysIni.addValuePair("System", "showcommand", chkShowCommand.isSelected() ? "true" : "false");
		
		yg.sysIni.writeFile(true);
		
		Stage stage = (Stage) aPane.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtWorkDir.setText(yg.workDir.getAbsolutePath());
		txtYad.setText(yg.yad.getAbsolutePath());
		
		boolean backup = yg.sysIni.getBoolean("System", "backup");
		boolean autoselect = yg.sysIni.getBoolean("System", "autoselect");
		boolean showcommand = yg.sysIni.getBoolean("System", "showcommand");
		
		chkBackupProject.setSelected(backup);
		chkAutoSelect.setSelected(autoselect);
		chkShowCommand.setSelected(showcommand);
		
	}

}
