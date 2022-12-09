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

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tbl.TabInfo;

public class NotebookController implements Initializable, DialogInterface {

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
    private Button btnTabAdd;

    @FXML
    private Button btnTabDelete;

    @FXML
    private ComboBox<String> cbTabIcon;

    @FXML
    private ComboBox<String> cbTabPosition;
    
    @FXML
    private ComboBox<String> cbTabLinked;
    
    @FXML
    private ListView<String> lvDialogs;

    @FXML
    private TableColumn<TabInfo, String> colIcon;

    @FXML
    private TableColumn<TabInfo, String> colText;

    @FXML
    private TableColumn<TabInfo, String> colTooltip;
    
    @FXML
    private TableColumn<TabInfo, String> colLinked;

    @FXML
    private TableView<TabInfo> tblTabs;

    @FXML
    private TextField txtActiveTab;
    
    @FXML
    private TextField txtTabnum;

    @FXML
    private TextField txtKey;

    @FXML
    private TextField txtTabBorders;

    @FXML
    private TextField txtTabText;

    @FXML
    private TextField txtTabTooltip;

    @FXML
    void doTabAdd(ActionEvent event) {
    	String name = txtTabText.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.ERROR);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();

			messageBox.setContentText("No name given.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnTabAdd.getScene().getWindow();

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

		String icon = cbTabIcon.getSelectionModel().getSelectedItem();
		String tooltip = txtTabTooltip.getText();
		String linked = cbTabLinked.getSelectionModel().getSelectedItem();
//		System.out.println(name);

		if (tabNames.contains(name)) {
			Alert messageBox = new Alert(Alert.AlertType.ERROR);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();

			messageBox.setContentText("The tab name '" + name + "' already exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnTabAdd.getScene().getWindow();

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

		tblTabs.getItems().add(new TabInfo(name, icon, tooltip, linked));

		txtTabText.setText("");
		cbTabIcon.getSelectionModel().select(0);
		txtTabTooltip.setText("");
		tabNames.add(name);

		saveTabs();
    }

    @FXML
    void doTabDelete(ActionEvent event) {
    	String name = txtTabText.getText();

		if (name == null || name.length() == 0) {
			Alert messageBox = new Alert(Alert.AlertType.ERROR);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();

			messageBox.setContentText("No name given.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnTabAdd.getScene().getWindow();

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

		int n = 0;
		int found = -1;
		for (String f : tabNames) {
			if (f.equals(name)) {
				found = n;
				break;
			}
			n++;
		}

		if (found == -1 || found >= tabNames.size())
			return;
		
		tblTabs.getItems().remove(found);
		tabNames.remove(found);

		txtTabText.setText("");
		cbTabIcon.getSelectionModel().select(0);
		txtTabTooltip.setText("");

		saveTabs();
    }

    @FXML
    void doTabPosition(ActionEvent event) {
    	yg.iniUpdate("tabposition", cbTabPosition.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActiveTab(KeyEvent event) {
    	yg.iniUpdate("activetab", txtActiveTab.getText());
    }

    @FXML
    void onKey(KeyEvent event) {
    	yg.iniUpdate("key", txtKey.getText());
    }

    @FXML
    void onTabBorders(KeyEvent event) {
    	yg.iniUpdate("tabborders", txtTabBorders.getText());
    }

    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> tabNames = new ArrayList<>();
    private java.util.List<String> dialogs = new ArrayList<>();
	private String[] iconTypes = {"None", "help-about", "list-add", "gtk-apply", "gtk-cancel", "gtk-close", "document-clear", "window-close", "gtk-edit", "system-run", "gtk-no", "gtk-ok", "document-open", "document-print", "application-exit", "view-refresh", "list-remove", "document-save", "system-search", "gtk-preferences", "gtk-yes"};


    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbTabPosition.getItems().addAll("Top", "Bottom", "Left", "Right");
		cbTabPosition.getSelectionModel().select(0);

    	cbTabIcon.getItems().addAll(iconTypes);
		cbTabIcon.getSelectionModel().select(0);
		
		buildDialogsList();
		
		cbTabLinked.getItems().addAll(dialogs);
		cbTabLinked.getSelectionModel().select(0);

		tblTabs.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal);
		        txtTabText.setText(newVal.getText());
		        cbTabIcon.getSelectionModel().select(newVal.getIcon());
		        txtTabTooltip.setText(newVal.getTooltip());
		        cbTabLinked.getSelectionModel().select(newVal.getLinked());
		    }
		});

		colText.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("text"));
		colIcon.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("icon"));
		colTooltip.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("tooltip"));
		colLinked.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("linked"));

		colText.setCellFactory(TextFieldTableCell.forTableColumn());
		colText.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setText(t.getNewValue());
	        tabNames.remove(t.getOldValue());
	        tabNames.add(t.getNewValue());
	        saveTabs();
	    });

		colIcon.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(iconTypes)));
		colIcon.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setIcon(t.getNewValue());
	        saveTabs();
	    });

		colTooltip.setCellFactory(TextFieldTableCell.forTableColumn());
		colTooltip.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setTooltip(t.getNewValue());
	        saveTabs();
	    });
		
		colLinked.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(dialogs)));
		colLinked.setOnEditCommit((TableColumn.CellEditEvent<TabInfo, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setLinked(t.getNewValue());
//	        String oldVal = t.getOldValue();
//	        yg.currIni.removeValuePair(oldVal + "-General", "plug");
//	        yg.currIni.removeValuePair(oldVal + "-General", "tabnum");
//	        int row = t.getTablePosition().getRow();
	        saveTabs();
	    });
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

    private void saveTabs() {
    	ObservableList<TabInfo> obs = tblTabs.getItems();

    	String tabs = null;
    	for (TabInfo ti : obs) {
    		String txt = ti.getText();
			String icn = ti.getIcon();
			String tip = ti.getTooltip();
			String lnk = ti.getLinked();
			
    		if (tabs == null) {
    			tabs = txt;
    			if (icn.equals("None"))
    				tabs += "~";
    			else
    				tabs += "~" + icn;

    			tabs += "~" + tip + "~" + lnk;
    		} else {
    			tabs += "," + txt;
    			if (icn.equals("None"))
    				tabs += "~";
    			else
    				tabs += "~" + icn;

    			tabs += "~" + tip + "~" + lnk;
    		}
    	}

    	if (tabs != null) {
    		yg.iniUpdate("tabs", tabs);
    	}
    }

	@Override
	public void updateDialog() {
		String tabs = yg.currIni.getString(yg.currDialog, "tabs");

		if (tabs != null) {
			tabNames.clear();
			tblTabs.getItems().clear();

			String[] a = tabs.split(",");

			for (String s : a) {
				String[] b = s.split("~", -1);

				if (b.length == 4)
					tblTabs.getItems().add(new TabInfo(b[0], b[1], b[2], b[3]));
				else
					tblTabs.getItems().add(new TabInfo(b[0], b[1], "", null));

				tabNames.add(b[0]);
			}

			txtTabText.setText("");
			cbTabIcon.getSelectionModel().select(0);
			txtTabTooltip.setText("");
			cbTabLinked.getSelectionModel().select(0);
		}

		String key = yg.currIni.getString(yg.currDialog, "key");
		String tabposition = yg.currIni.getString(yg.currDialog, "tabposition");
		String tabborders = yg.currIni.getString(yg.currDialog, "tabborders");
		String activetab = yg.currIni.getString(yg.currDialog, "activetab");

		if (key != null)
			txtKey.setText(key);
		if (tabposition != null)
			cbTabPosition.getSelectionModel().select(tabposition);
		if (tabborders != null)
			txtTabBorders.setText(tabborders);
		if (activetab != null)
			txtActiveTab.setText(activetab);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		
	}

}

