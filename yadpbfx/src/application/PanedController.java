package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PanedController implements Initializable, DialogInterface {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnGeneral;

    @FXML
    private ComboBox<String> cbOrient;
    
    @FXML
    private ComboBox<String> cbPane1;
    
    @FXML
    private ComboBox<String> cbPane2;

    @FXML
    private TextField txtKey;
    
    @FXML
    private TextField txtTabnum;

    @FXML
    private TextField txtSplitter;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> dialogs = new ArrayList<>();

    @FXML
    void doGeneral(ActionEvent event) {
    	yg.showGeneral(btnGeneral);
    }
    
    @FXML
    void onKey(KeyEvent event) {
    	yg.iniUpdate("key", txtKey.getText());
    }
    
    @FXML
    void doOrient(ActionEvent event) {
    	yg.iniUpdate("orient", cbOrient.getValue().toString());
    }

    @FXML
    void doPane1(ActionEvent event) {
//    	String oldVal = yg.currIni.getString(yg.currDialog, "pane1");
    	String keyNum = yg.currIni.getString(yg.currDialog, "key");
//    	String dialog = cbPane1.getValue().toString();
    	
    	if (keyNum == null || keyNum.isEmpty() == true) {
    		Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
            Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
            stage.hide();

            messageBox.setContentText("Be sure and set the 'Key' field.");

            // Code to center dialog within parent.
            Stage ps = (Stage) aPane.getScene().getWindow();

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
    	}
    		
//    	if (oldVal != null && oldVal.equals("None") == false) {
//    		yg.currIni.removeValuePair(oldVal + "-General", "plug");
//    		yg.currIni.removeValuePair(oldVal + "-General", "tabnum");
//    	}
    	yg.iniUpdate("pane1", cbPane1.getValue().toString());
//    	
//    	yg.currIni.addValuePair(dialog + "-General", "plug", keyNum);
//    	yg.currIni.addValuePair(dialog + "-General", "tabnum", "1");
    }
    
    @FXML
    void doPane2(ActionEvent event) {
//    	String oldVal = yg.currIni.getString(yg.currDialog, "pane2");
    	String keyNum = yg.currIni.getString(yg.currDialog, "key");
//    	String dialog = cbPane1.getValue().toString();
    	
    	if (keyNum == null || keyNum.isEmpty() == true) {
    		Alert messageBox = new Alert(Alert.AlertType.ERROR);
            Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
            stage.hide();

            messageBox.setContentText("Be sure and set the 'Key' field.");

            // Code to center dialog within parent.
            Stage ps = (Stage) aPane.getScene().getWindow();

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
    	}
    		
//    	if (oldVal != null && oldVal.equals("None") == false) {
//    		yg.currIni.removeValuePair(oldVal + "-General", "plug");
//    		yg.currIni.removeValuePair(oldVal + "-General", "tabnum");
//    	}
    	yg.iniUpdate("pane2", cbPane1.getValue().toString());
//    	
//    	yg.currIni.addValuePair(dialog + "-General", "plug", keyNum);
//    	yg.currIni.addValuePair(dialog + "-General", "tabnum", "1");
    }

    @FXML
    void doSplitter(ActionEvent event) {
    	yg.iniUpdate("splitter", txtSplitter.getText());
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbOrient.getItems().addAll("Horizontal", "Vertical");
		
    	buildDialogsList();
    	
    	cbPane1.getItems().addAll(dialogs);
		cbPane2.getItems().addAll(dialogs);
	}
    
    private void buildDialogsList() {
    	dialogs.clear();
    	
    	dialogs.add("None");
    	
    	Object[] secs = yg.currIni.getSectionNames();
    	
    	for (Object sec : secs) {
    		String s = (String)sec;
    		if (s.endsWith("-General") == false)
    			continue;
    		if (s.equals(yg.currDialog) == true)
    			continue;
    		
    		dialogs.add(s.split("-")[0]);
    	}
    }

	@Override
	public void updateDialog() {
		
		String key = yg.currIni.getString(yg.currDialog, "key");
		String orient = yg.currIni.getString(yg.currDialog, "orient");
		String splitter = yg.currIni.getString(yg.currDialog, "splitter");
		String pane1 = yg.currIni.getString(yg.currDialog, "pane1");
		String pane2 = yg.currIni.getString(yg.currDialog, "pane2");
		
		if (key != null)
			txtKey.setText(key);
		if (splitter != null)
			txtSplitter.setText(splitter);
		if (orient != null)
			cbOrient.getSelectionModel().select(orient);
		if (pane1 != null)
			cbPane1.getSelectionModel().select(pane1);
		if (pane2 != null)
			cbPane2.getSelectionModel().select(pane2);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}