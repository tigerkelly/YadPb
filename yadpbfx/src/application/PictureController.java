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

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class PictureController implements Initializable, DialogInterface {

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnFilename;

    @FXML
    private Button btnGeneral;

    @FXML
    private ComboBox<String> cbSize;

    @FXML
    private TextField txtFilename;

    @FXML
    private TextField txtIncrement;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doFilename(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("PNG", "*.png"),
				new FileChooser.ExtensionFilter("JPEG", "*.jpg,*.JPEG"),
				new FileChooser.ExtensionFilter("BMP", "*.bmp"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtFilename.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("filename", txtFilename.getText());
        }
    }

    @FXML
    void doGeneral(ActionEvent event) {
    	yg.showGeneral(btnGeneral);
    }

    @FXML
    void doIncrement(ActionEvent event) {
    	yg.iniUpdate("increment", txtIncrement.getText());
    }

    @FXML
    void doSize(ActionEvent event) {
    	yg.iniUpdate("size", cbSize.getValue().toString());
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbSize.getItems().addAll("fit", "orig");
	}

	@Override
	public void updateDialog() {
		
		String increment = yg.currIni.getString(yg.currDialog, "increment");
		String size = yg.currIni.getString(yg.currDialog, "size");
		
		if (increment != null)
			txtIncrement.setText(increment);
		if (size != null)
			cbSize.getSelectionModel().select(size);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}
