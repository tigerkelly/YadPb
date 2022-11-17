package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.rkw.IniFile;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tbl.Project;
import tbl.Projectx;

public class ProjectsToSaveController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOpen;

    @FXML
    private TableView<Project> tblProjects;

    @FXML
    private TextField txtPath;
    
    @FXML
    private CheckBox ckbSelectAll;

    ObservableList<Projectx> selectedItems = null;
    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    void doSelectAll(ActionEvent event) {
//    	ObservableList<Project> prjs = tblProjects.getItems();
//    	
//    	for (Project p : prjs) {
//    	}
    }

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doSave(ActionEvent event) {
    	ObservableList<Project> selectedItems = tblProjects.getItems();
    	for (Project p : selectedItems) {
	    	IniFile ini = yg.prjList.get(p.getProject());
			if (ini.getChangedFlag() == true && p.getAction().isSelected() == true) {
				yg.backupProject(p.getProject());
				ini.writeFile(true);
			}
    	}
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	ckbSelectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
    		@Override
    		public void changed(ObservableValue<? extends Boolean> ebservable, Boolean oldValue, Boolean newValue) {
    			ObservableList<Project> items = tblProjects.getItems();
    			
    			for (Project p : items) {
    				if (ckbSelectAll.isSelected() == true)
    					p.getAction().setSelected(true);
    				else
    					p.getAction().setSelected(false);
    			}
    		}
    	});
    	
    	TableColumn<Project, String> action = new TableColumn<>("Select");
    	action.setPrefWidth(50.0);
    	TableColumn<Project, String> colProject = new TableColumn<>("Project");
    	colProject.setPrefWidth(150.0);
    	TableColumn<Project, String> colDesc = new TableColumn<>("Description");
    	colDesc.setPrefWidth(280.0);
    	
    	action.setCellValueFactory(new PropertyValueFactory<>("action"));
    	colProject.setCellValueFactory(new PropertyValueFactory<>("project"));
    	colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
    	
    	tblProjects.getColumns().add(action);
    	tblProjects.getColumns().add(colProject);
    	tblProjects.getColumns().add(colDesc);

    	
    	Object[] keys = yg.prjList.keySet().toArray();
		
		for (Object o : keys) {
			String k = (String)o;
			
			IniFile ini = yg.prjList.get(k);
			if (ini.getChangedFlag() == true)
				tblProjects.getItems().add(new Project(k, yg.sysIni.getString("Projects", k), true));
		}
		
	}

}

