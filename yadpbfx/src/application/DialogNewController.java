package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import tbl.DialogType;

public class DialogNewController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cbDialogType;

    @FXML
    private TextField txtDialogName;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private DialogType dt = null;

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doSave(ActionEvent event) {
		
		if (yg.currIni.sectionExists(txtDialogName.getText())) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + txtDialogName.getText() + "' is already exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnSave.getScene().getWindow();

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
		
		dt = new DialogType(txtDialogName.getText(), (String)cbDialogType.getSelectionModel().getSelectedItem());
		
		Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbDialogType.getItems().addAll("Calendar", "Color", "DnD", "Entry", "File", "Font", "Form", "HTML", "Icons", "Info", "List", "Notebook", "Notification", "Print", "Progress", "Progress Multi", "Scale");
	}
	
	public DialogType getDialog() {
		return dt;
	}

}

