package application;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tbl.Projectx;

public class ProjectNewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreate;

    @FXML
    private TextArea taDiscription;

    @FXML
    private TextField txtProjectName;
    
    private Projectx project = null;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doCancel(ActionEvent event) {
    	project = null;
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doCreate(ActionEvent event) {
    	
    	if (txtProjectName.getText().isEmpty() == true)
    		return;
    	
    	File[] files = yg.workDir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".yadpb");
		    }
		});
		
		boolean found = false;
		for (File f : files) {
//			System.out.println(f.getAbsolutePath());
			if (f.getName().equals(txtProjectName.getText() + ".yadpb") == true) {
				found = true;
				break;
			}
		}
		
		if (found == true) {
			Alert messageBox = new Alert(Alert.AlertType.ERROR);
			messageBox.setTitle("Dup project name.");
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();

			messageBox.setContentText("The project '" + txtProjectName.getText() + "' already exists.");
			
			Stage ps = (Stage) btnCreate.getScene().getWindow();

			ChangeListener<Number> widthListener = (observable, oldValue, newValue) -> {
				double stageWidth = newValue.doubleValue();
				stage.setX(ps.getX() + ps.getWidth() / 2 - stageWidth / 2);
			};
			ChangeListener<Number> heightListener = (observable, oldValue, newValue) -> {
				double stageHeight = newValue.doubleValue();
				stage.setY(ps.getY() + ps.getHeight() / 2 - stageHeight / 2);
			};

			stage.widthProperty().addListener(widthListener);
			stage.heightProperty().addListener(heightListener);

			// Once the window is visible, remove the listeners
			stage.setOnShown(e2 -> {
				stage.widthProperty().removeListener(widthListener);
				stage.heightProperty().removeListener(heightListener);
			});
			messageBox.showAndWait();
			return;
		}
		
		project = new Projectx(txtProjectName.getText(), taDiscription.getText());
		
    	Stage stage = (Stage) btnCreate.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onProjectName(KeyEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public Projectx getProject() {
		return project;
	}

}

