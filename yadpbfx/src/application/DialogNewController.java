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

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tbl.DialogType;

public class DialogNewController implements Initializable, DialogInterface {

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
    
    @FXML
    private Label lblScreenTitle;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private DialogType dt = null;

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void doDialogName(ActionEvent event) {
    	
		if (cbDialogType.getSelectionModel().isEmpty() == false &&
				txtDialogName.getText().isEmpty() == false)
			doSave(null);
    	
    }

    @FXML
    void doSave(ActionEvent event) {
		String dialogName = txtDialogName.getText();
		
		// Remove all white spaces from name;
		String[] t = dialogName.split("\\s+");
		String tt = null;
		if (t.length > 1) {
			for (String s : t) {
				if (tt == null)
					tt = s;
				else
					tt += s;
			}
		}
		if (tt != null)
			dialogName = tt;
		
		if (yg.currIni.sectionExists(dialogName)) {
			Alert messageBox = new Alert(Alert.AlertType.ERROR);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();

			messageBox.setContentText("The field name '" + dialogName + "' is already exists.");
			
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
		
		dt = new DialogType(dialogName, (String)cbDialogType.getSelectionModel().getSelectedItem());
		
		Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbDialogType.getItems().addAll("Calendar", "Color", "DnD", "Entry", "File", "Font", "Form", 
				"HTML", "Icons", "Text-Info", "List", "Notebook", "Notification", "Paned", "Picture", "Print", 
				"Progress", "Progress Multi", "Scale");
		
		
	}
	
	public DialogType getDialog() {
		return dt;
	}

	@Override
	public void updateDialog() {
		
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		lblScreenTitle.setText("Dialog for " + data);
	}

}

