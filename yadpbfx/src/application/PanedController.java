package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PanedController implements Initializable, DialogInterface {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnGeneral;

    @FXML
    private ComboBox<String> cbOrient;

    @FXML
    private FlowPane fpLinkedDialogs;

    @FXML
    private ListView<String> lvDialogs;

    @FXML
    private TextField txtKey;

    @FXML
    private TextField txtSplitter;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
    	yg.showGeneral(btnGeneral);
    }

    @FXML
    void doOrient(ActionEvent event) {
    	yg.iniUpdate("orient", cbOrient.getValue().toString());
    }

    @FXML
    void doSplitter(ActionEvent event) {
    	yg.iniUpdate("splitter", txtSplitter.getText());
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	ContextMenu cm = new ContextMenu();
		
		MenuItem mMenuTitle = new MenuItem("Notebook Menu");
		mMenuTitle.setDisable(true);
		
		MenuItem mLinkDialog = new MenuItem("Link Dialog");
		mLinkDialog.setOnAction((ActionEvent e) -> {
			String d = lvDialogs.getSelectionModel().getSelectedItem();
			String dialog = d.split("-")[0];
			ObservableList<Node> lst = fpLinkedDialogs.getChildren();
			
			String keyNum = yg.currIni.getString(yg.currDialog, "key");
			if (keyNum == null || keyNum.isEmpty() == true) {
				Alert messageBox = new Alert(Alert.AlertType.ERROR);
				Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
				stage.hide();

				messageBox.setContentText("The 'Key' field if blank.");
				
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
				return;
			}
			
			boolean found = false;
			for (Node n : lst) {
				Label lbl = (Label)n;
				
				if (lbl.getText().equals(dialog) == true) {
					found = true;
					break;
				}
			}
			
			if (found == false) {
				// May want to sort the list.
				Label lbl = new Label(dialog);
				lbl.setStyle("-fx-border-color: black;");
				lbl.setAlignment(Pos.CENTER);
				lbl.setPrefWidth(135.0);
				fpLinkedDialogs.getChildren().add(lbl);
				
				yg.currIni.addValuePair(dialog + "-General", "plug", keyNum);
			}
		});
		
		MenuItem mUnlinkDialog = new MenuItem("Unlink Dialog");
		mUnlinkDialog.setOnAction((ActionEvent e) -> {
			String d = lvDialogs.getSelectionModel().getSelectedItem();
			String dialog = d.split("-")[0];
			ObservableList<Node> lst = fpLinkedDialogs.getChildren();
			
			String keyNum = yg.currIni.getString(yg.currDialog, "key");
			if (keyNum == null || keyNum.isEmpty() == true) {
				Alert messageBox = new Alert(Alert.AlertType.ERROR);
				Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
				stage.hide();

				messageBox.setContentText("The 'Key' field if blank.");
				
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
				return;
			}
		
			int found = -1;
			int idx = 0;
			for (Node n : lst) {
				Label lbl = (Label)n;
				
				if (lbl.getText().equals(dialog) == true) {
					found = idx;
					break;
				}
				
				idx++;
			}
			
			if (found != -1) {
				lst.remove(found);
				
				yg.currIni.removeValuePair(dialog + "-General", "plug");
			}
		});
		
		SeparatorMenuItem sep = new SeparatorMenuItem();
		SeparatorMenuItem sep2 = new SeparatorMenuItem();
		
		cm.getItems().addAll(mMenuTitle, sep, mLinkDialog, sep2, mUnlinkDialog);
		
		lvDialogs.setContextMenu(cm);
	}

	@Override
	public void updateDialog() {
		
		String key = yg.currIni.getString(yg.currDialog, "key");
		String orient = yg.currIni.getString(yg.currDialog, "orient");
		String splitter = yg.currIni.getString(yg.currDialog, "splitter");
		
		if (key != null)
			txtKey.setText(key);
		if (splitter != null)
			txtSplitter.setText(splitter);
		if (orient != null)
			cbOrient.getSelectionModel().select(orient);
		
		loadDialogs();
		linkedDialogs();
	}
	
	private void loadDialogs() {
    	if (lvDialogs == null)
    		return;
    	
    	lvDialogs.getItems().clear();
    	
    	Object[] secs = yg.currIni.getSectionNames();
    	
    	for (Object sec : secs) {
    		String s = (String)sec;
    		if (s.endsWith("-General") == false)
    			continue;
    		if (s.equals(yg.currDialog) == true)
    			continue;
    		
    		lvDialogs.getItems().add(s.split("-")[0]);
    	}
    }
    
    private void linkedDialogs() {
    	if (fpLinkedDialogs.getChildren() != null)
    		fpLinkedDialogs.getChildren().clear();
    	
    	String keyNum = yg.currIni.getString(yg.currDialog, "key");
    	
    	if (keyNum == null || keyNum.isEmpty() == true)
    		return;
    	
    	Object[] secs = yg.currIni.getSectionNames();
    	
    	for (Object sec : secs) {
    		String s = (String)sec;
    		
    		if (s.equals(yg.currDialog) == true)
    			continue;
    		
    		String plug = yg.currIni.getString(s + "-General", "plug");
    		if (plug == null || plug.isEmpty() == true)
    			continue;
    		
    		if (plug.equals(keyNum) == true) {
    			Label lbl = new Label(s.split("-")[0]);
				lbl.setStyle("-fx-border-color: black;");
				lbl.setAlignment(Pos.CENTER);
				lbl.setPrefWidth(135.0);
				fpLinkedDialogs.getChildren().add(lbl);
    		}
    	}
    }

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}