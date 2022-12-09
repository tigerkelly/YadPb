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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

public class IconsController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private AnchorPane aPane;

    @FXML
    private ToggleButton btnCompact;

    @FXML
    private ToggleButton btnDescend;

    @FXML
    private ToggleButton btnGeneric;

    @FXML
    private ToggleButton btnListen;

    @FXML
    private ToggleButton btnMonitor;

    @FXML
    private Button btnReadDir;

    @FXML
    private ToggleButton btnSortByName;

    @FXML
    private ToggleButton btnSingleClick;

    @FXML
    private TextField txtItemWidth;

    @FXML
    private TextField txtReadDir;

    @FXML
    private TextField txtTerm;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
    }
    
    @FXML
    void doReadDir(ActionEvent event) {
    	DirectoryChooser dc = new DirectoryChooser();
    	File selectedDir = dc.showDialog(aPane.getScene().getWindow());
        if (selectedDir != null) {
        	txtReadDir.setText(selectedDir.getAbsolutePath());
        	yg.iniUpdate("readdir", txtReadDir.getText());
        }
    }

    @FXML
    void doToggleButton(ActionEvent event) {
    	if (event.getSource() instanceof ToggleButton) {
    		ToggleButton tb = (ToggleButton) event.getSource();
    		String s = tb.getText().replaceAll(" " , "").toLowerCase();
    		yg.iniUpdate(s, tb.isSelected()? "true":"false");
    		if (tb.isSelected() == true) {
				tb.setGraphic(new ImageView(yg.checkImage));
			} else {
				tb.setGraphic(null);
			}
    	}
    }
    
    private void setToggleButton(ToggleButton tb, boolean flag) {
		tb.setSelected(flag);
		if (flag == true) {
			tb.setGraphic(new ImageView(yg.checkImage));
		} else {
			tb.setGraphic(null);
		}
	}

    @FXML
    void onItemWidth(KeyEvent event) {
    	yg.iniUpdate("itemwidth", txtItemWidth.getText());
    }

    @FXML
    void onTerm(KeyEvent event) {
    	yg.iniUpdate("term", txtTerm.getText());
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		String readdir = yg.currIni.getString(yg.currDialog, "readdir");
		String term = yg.currIni.getString(yg.currDialog, "term");
		String itemwidth = yg.currIni.getString(yg.currDialog, "itemwidth");

		boolean monitor = yg.currIni.getBoolean(yg.currDialog, "monitor");
		boolean descend = yg.currIni.getBoolean(yg.currDialog, "descend");
		boolean compact = yg.currIni.getBoolean(yg.currDialog, "compact");
		boolean listen = yg.currIni.getBoolean(yg.currDialog, "listen");
		boolean singleclick = yg.currIni.getBoolean(yg.currDialog, "singleclick");
		boolean sortbyname = yg.currIni.getBoolean(yg.currDialog, "sortbyname");
		boolean generic = yg.currIni.getBoolean(yg.currDialog, "generic");
		
		if (readdir != null)
			txtReadDir.setText(readdir);
		if (term != null)
			txtTerm.setText(term);
		if (itemwidth != null)
			txtItemWidth.setText(itemwidth);
		
		setToggleButton(btnMonitor, monitor);
		setToggleButton(btnDescend, descend);
		setToggleButton(btnCompact, compact);
		setToggleButton(btnListen, listen);
		setToggleButton(btnSingleClick, singleclick);
		setToggleButton(btnSortByName, sortbyname);
		setToggleButton(btnGeneric, generic);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}

