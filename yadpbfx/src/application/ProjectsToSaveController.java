/*
 * This file is part of YadPb.
 *
 * YadPb is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * YadPb is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with YadPb. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2022-2023, Kelly Wiles <rkwiles@twc.com>
 */

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonBar.ButtonData;
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
    private CheckBox ckbSelectAll;

    ObservableList<Projectx> selectedItems = null;
    private YadGlobal yg = YadGlobal.getInstance();
    private boolean leaveFlag = false;
    
    @FXML
    void doCancel(ActionEvent event) {
    	Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
		
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);

		messageBox.setContentText("Canceling out of this screen will abandon all changes.\nProcess with Cancel?");
		
		// Code to center dialog within parent.
		Stage ps = (Stage) ckbSelectAll.getScene().getWindow();

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

		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
				leaveFlag = true;
			} else if (type.getButtonData() == ButtonData.NO) {
//				System.out.println("NO");
				return;
			}
		});
		
		if (leaveFlag == true) {
	    	Stage stage2 = (Stage) btnCancel.getScene().getWindow();
	        stage2.close();
		}
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

    	boolean autoselect = yg.sysIni.getBoolean("System", "autoselect");
		ckbSelectAll.setSelected(autoselect);
    	
    	Object[] keys = yg.prjList.keySet().toArray();
		
		for (Object o : keys) {
			String k = (String)o;
			
			IniFile ini = yg.prjList.get(k);
			if (ini.getChangedFlag() == true) {
				Project p = new Project(k, yg.sysIni.getString("Projects", k), true);
				tblProjects.getItems().add(p);
				
				p.getAction().setSelected(autoselect);
					
			}
		}
	}

}

