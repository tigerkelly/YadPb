package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DetailNameController implements Initializable {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnSelect;
    
    @FXML
    private TextField txtDetailName;
    
    private String detailName = null;

    @FXML
    void doCancel(ActionEvent event) {
    	detailName = null;
    	
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doCreate(ActionEvent event) {
    	String fn = txtDetailName.getText();
    	if (fn == null || fn.isEmpty() == true)
    		return;
    	
    	if (fn.endsWith(".dd") == false)
    		fn += ".dd";
    	
    	File f = new File(fn);
    	if (f.exists() == false) {
    		try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	detailName = fn;
    	
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doSelect(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Date Detail Files", "*.dd"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		File f = fileChooser.showSaveDialog(aPane.getScene().getWindow());
		if (f != null) {
			txtDetailName.setText(f.getAbsolutePath());
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}
	
	public String getDetailName() {
		return detailName;
	}

}
