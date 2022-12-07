package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tbl.PlugInfo;

public class PlugManagerController implements Initializable, DialogInterface {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnPlugAdd;

    @FXML
    private Button btnPlugDelete;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Label lblScreenTitle;

    @FXML
    private TableColumn<PlugInfo, String> colPlugName;

    @FXML
    private TableColumn<PlugInfo, String> colPlugNum;

    @FXML
    private TableView<PlugInfo> tblPlugs;

    @FXML
    private TextField txtPlugName;

    @FXML
    private TextField txtPlugNum;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private String plugSection = "YadPb-Plugs";
    private java.util.List<String> plugNames = new ArrayList<>();
    
    @FXML
    void doSave(ActionEvent event) {
    	savePlugs();
    	
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doPlugAdd(ActionEvent event) {
    	String name = txtPlugName.getText();
    	String num = txtPlugNum.getText();
    	
    	if (name == null || name.isEmpty() == true || num == null || num.isEmpty() == true)
    		return;
    	
    	ObservableList<PlugInfo> lst = tblPlugs.getItems();
    	
    	boolean foundName = false;
    	boolean foundNum = false;
    	
    	for (PlugInfo pi : lst) {
    		if (pi.getName().equals(name) == true) {
    			foundName = true;
    			break;
    		}
    		if (pi.getNum().equals(num) == true) {
    			foundNum = true;
    			break;
    		}
    	}
    	
    	if (foundName == true || foundNum == true) {
    		Alert messageBox = new Alert(Alert.AlertType.ERROR);
 			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();

			messageBox.setContentText("The 'Name' or 'Tab Num' alreay exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnCancel.getScene().getWindow();

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
    	} else {
    		tblPlugs.getItems().add(new PlugInfo(name, num));
    		
    		plugNames.add(name);
    		
    		txtPlugName.setText("");
    		txtPlugNum.setText("");
    	}
    }

    @FXML
    void doPlugDelete(ActionEvent event) {
    	String name = txtPlugName.getText();
    	
    	if (name == null || name.isEmpty() == true)
    		return;
    	
    	int found = -1;
    	int idx = 0;
    	for (String pn : plugNames) {
    		if (pn.equals(name) == true) {
    			found = idx;
    			break;
    		}
    		idx++;
    	}
    	
    	if (found == -1) {
    		
    	} else {
    		tblPlugs.getItems().remove(found);
    		plugNames.remove(found);
    		
    		txtPlugName.setText("");
    		txtPlugNum.setText("");
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	colPlugName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPlugNum.setCellValueFactory(new PropertyValueFactory<>("num"));

		colPlugName.setCellValueFactory(new PropertyValueFactory<PlugInfo, String>("name"));
		colPlugNum.setCellValueFactory(new PropertyValueFactory<PlugInfo, String>("num"));

		colPlugName.setCellFactory(TextFieldTableCell.<PlugInfo>forTableColumn());

		colPlugName.setOnEditCommit((TableColumn.CellEditEvent<PlugInfo, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue());
	        plugNames.remove(t.getOldValue());
	        plugNames.add(t.getNewValue());
	    });

		colPlugNum.setCellFactory(TextFieldTableCell.forTableColumn());

		colPlugNum.setOnEditCommit((TableColumn.CellEditEvent<PlugInfo, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setNum(t.getNewValue());
	    });

		tblPlugs.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtPlugName.setText(newVal.getName());
		        txtPlugNum.setText(newVal.getNum());
		    }
		});
		
		loadPlugs();
	}
    
    private void loadPlugs() {
    	Object[] keys = yg.currIni.getSectionKeys(plugSection);
		
		tblPlugs.getItems().clear();
		
		plugNames.clear();
		
		for (Object key : keys) {
			String k = (String)key;
			
			String num = yg.currIni.getString(plugSection, k);
			
			plugNames.add(k);
			
			tblPlugs.getItems().add(new PlugInfo(k, num));
		}
    }
    
    private void savePlugs() {
    	
    	yg.currIni.removeSection(plugSection);
    	yg.currIni.addSection(plugSection);
    	
    	ObservableList<PlugInfo> lst = tblPlugs.getItems();
    	
    	for (PlugInfo pi : lst) {
    		yg.currIni.addValuePair(plugSection, pi.getName(), pi.getNum());
    	}
    }

	@Override
	public void updateDialog() {
		
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		lblScreenTitle.setText("Plugs for " + data);
	}

}
