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
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import tbl.MarkValue;

public class ScaleController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblDialog;

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnHideValue;

    @FXML
    private ToggleButton btnIncButtons;

    @FXML
    private ToggleButton btnInvert;

    @FXML
    private Button btnMarkAdd;

    @FXML
    private Button btnMarkDelete;

    @FXML
    private ToggleButton btnPrintPartial;

    @FXML
    private ToggleButton btnVertical;

    @FXML
    private TableColumn<MarkValue, String> colMarkText;

    @FXML
    private TableColumn<MarkValue, String> colMarkValue;

    @FXML
    private TableView<MarkValue> tblMarks;

    @FXML
    private TextField txtInitValue;

    @FXML
    private TextField txtMarkText;

    @FXML
    private TextField txtMarkValue;

    @FXML
    private TextField txtMin;

    @FXML
    private TextField txtPage;

    @FXML
    private TextField txtStep;

    @FXML
    void doMarkAdd(ActionEvent event) {
    	String txt = txtMarkText.getText();
		if (txt == null || txt.length() <= 0)
			return;

//		System.out.println(txt);

		if (markNames.contains(txt)) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The mark name '" + txt + "' already exists.");

			messageBox.showAndWait();
			return;
		}

		tblMarks.getItems().add(new MarkValue(txt, txtMarkValue.getText()));

		txtMarkText.setText("");
		txtMarkValue.setText("");
		markNames.add(txt);

		saveMarks();
    }

    @FXML
    void doMarkDelete(ActionEvent event) {
    	String txt = txtMarkText.getText();

		if (txt == null || txt.length() == 0)
			return;

		int n = 0;
		int found = -1;
		for (String f : markNames) {
			if (f.equals(txt)) {
				found = n;
				break;
			}
			n++;
		}

		if (found == -1 || found >= markNames.size())
			return;
		
		tblMarks.getItems().remove(found);
		markNames.remove(found);

		txtMarkText.setText("");
		txtMarkValue.setText("");

		saveMarks();
    }

    @FXML
    void doToggleButton(ActionEvent event) {
    	if (event.getSource() instanceof ToggleButton) {
    		ToggleButton tb = (ToggleButton) event.getSource();
    		String s = tb.getText().replaceAll(" " , "").toLowerCase();
    		yg.iniUpdate(s, tb.isSelected()? "true":"false");
    		if (tb.isSelected()) {
				tb.setGraphic(new ImageView(yg.checkImage));
			} else {
				tb.setGraphic(null);
			}
    	}
    }

    private void setToggleButton(ToggleButton tb, boolean flag) {
		tb.setSelected(flag);
		if (flag) {
			tb.setGraphic(new ImageView(yg.checkImage));
		} else {
			tb.setGraphic(null);
		}
	}

    @FXML
    void onInitValue(KeyEvent event) {
    	yg.iniUpdate("initvalue", txtInitValue.getText());
    }

    @FXML
    void onMin(KeyEvent event) {
    	yg.iniUpdate("min", txtMin.getText());
    }

    @FXML
    void onPage(KeyEvent event) {
    	yg.iniUpdate("page", txtPage.getText());
    }

    @FXML
    void onStep(KeyEvent event) {
    	yg.iniUpdate("step", txtStep.getText());
    }

    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> markNames = new ArrayList<>();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	colMarkText.setCellValueFactory(new PropertyValueFactory<>("text"));
		colMarkValue.setCellValueFactory(new PropertyValueFactory<>("value"));

		colMarkValue.setCellValueFactory(new PropertyValueFactory<MarkValue, String>("text"));
		colMarkValue.setCellValueFactory(new PropertyValueFactory<MarkValue, String>("value"));

		colMarkText.setCellFactory(TextFieldTableCell.<MarkValue>forTableColumn());

		colMarkText.setOnEditCommit((TableColumn.CellEditEvent<MarkValue, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setText(t.getNewValue());
	        markNames.remove(t.getOldValue());
	        markNames.add(t.getNewValue());
	    });

		colMarkValue.setCellFactory(TextFieldTableCell.<MarkValue>forTableColumn());
		colMarkValue.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValue(e.getNewValue()));

		tblMarks.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtMarkText.setText(newVal.getText());
		        txtMarkValue.setText(newVal.getValue());
		    }
		});
	}

    private void saveMarks() {
    	ObservableList<MarkValue> obs = tblMarks.getItems();

    	String marks = null;
    	for (MarkValue mv : obs) {
    		if (marks == null)
    			marks = mv.getText() + ":" + mv.getValue();
    		else
    			marks += "," + mv.getText() + ":" + mv.getValue();
    	}

    	if (marks != null)
    		yg.iniUpdate("marks", marks);
    }

	@Override
	public void updateDialog() {
		String marks = yg.currIni.getString(yg.currDialog, "marks");
		String minvalue = yg.currIni.getString(yg.currDialog, "minvalue");
		String stepsize = yg.currIni.getString(yg.currDialog, "stepsize");
		String initvalue = yg.currIni.getString(yg.currDialog, "initvalue");
		String pagevalue = yg.currIni.getString(yg.currDialog, "pagevalue");

		boolean printpartial = yg.currIni.getBoolean(yg.currDialog, "printpartial");
		boolean incbuttons = yg.currIni.getBoolean(yg.currDialog, "incbuttons");
		boolean hidevalue = yg.currIni.getBoolean(yg.currDialog, "hidevalue");
		boolean vertical = yg.currIni.getBoolean(yg.currDialog, "vertical");
		boolean invert = yg.currIni.getBoolean(yg.currDialog, "invert");

		if (marks != null) {
			tblMarks.getItems().clear();
			markNames.clear();

			String[] cols = marks.split(",");

			for (String c : cols) {
				String[] a = c.split(":");

				tblMarks.getItems().add(new MarkValue(a[0], a[1]));
				markNames.add(a[0]);
			}
		}

		if (minvalue != null)
			txtMin.setText(minvalue);
		if (stepsize != null)
			txtStep.setText(stepsize);
		if (initvalue != null)
			txtInitValue.setText(initvalue);
		if (pagevalue != null)
			txtPage.setText(pagevalue);

		setToggleButton(btnPrintPartial, printpartial);
		setToggleButton(btnIncButtons, incbuttons);
		setToggleButton(btnHideValue, hidevalue);
		setToggleButton(btnVertical, vertical);
		setToggleButton(btnInvert, invert);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		
	}

}

