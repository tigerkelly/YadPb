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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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
    private ListView<String> lvDialogs;

    @FXML
    private TableColumn<TabInfo, String> colIcon;

    @FXML
    private TableColumn<TabInfo, String> colText;

    @FXML
    private TableColumn<TabInfo, String> colTooltip;

    @FXML
    private TableView<TabInfo> tblTabs;

    @FXML
    private TextField txtActiveTab;

    @FXML
    private TextField txtKey;

    @FXML
    private TextField txtTabBorders;

    @FXML
    private TextField txtTabText;

    @FXML
    private TextField txtTabTooltip;
    
    @FXML
    private FlowPane fpLinkedDialogs;

    @FXML
    void doTabAdd(ActionEvent event) {
    	String name = txtTabText.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

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
//		System.out.println(name);

		if (tabNames.contains(name)) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The tab name '" + name + "' is already exists.");
			
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

		tblTabs.getItems().add(new TabInfo(name, icon, tooltip));

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
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

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
		for (String f : tabNames) {
			if (f.equals(name)) {
				break;
			}
			n++;
		}

		if (n >= tabNames.size())
			return;

		TabInfo ti = tblTabs.getItems().get(n);

		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
		stage.hide();
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);

		messageBox.setContentText("Delete '" + name + "'?");
		
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

		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("ti " + ti);
				tblTabs.getItems().remove(ti);
				tabNames.remove(ti.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});

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

		tblTabs.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal);
		        txtTabText.setText(newVal.getText());
		        cbTabIcon.getSelectionModel().select(newVal.getIcon());
		        txtTabTooltip.setText(newVal.getTooltip());
		    }
		});
		
//		lvDialogs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//			@Override
//			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
////				System.out.println("new " + newVal + " old " + oldVal);
//				if (newVal != null && newVal.equals(yg.currProject) == false) {
//					yg.currIni = yg.prjList.get(newVal);
//					yg.currBackupIni = yg.prjBackupList.get(newVal);
//					yg.currProject = newVal;
//				}
//			}
//		});
		
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
		
			int found = -1;
			int idx = 0;
			for (Node n : lst) {
				Label lbl = (Label)n;
				
//				System.out.println(lbl.getText() + ", " + dialog);
				
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

		colText.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("text"));
		colIcon.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("icon"));
		colTooltip.setCellValueFactory(new PropertyValueFactory<TabInfo, String>("tooltip"));

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

    private void saveTabs() {
    	ObservableList<TabInfo> obs = tblTabs.getItems();

    	String tabs = null;
    	for (TabInfo ti : obs) {
    		if (tabs == null) {
    			tabs = ti.getText();
    			if (ti.getIcon().equals("None"))
    				tabs += "~";
    			else
    				tabs += "~" + ti.getIcon();

    			tabs += "~" + ti.getTooltip();
    		} else {
    			tabs += "," + ti.getText();
    			if (ti.getIcon().equals("None"))
    				tabs += "~";
    			else
    				tabs += "~" + ti.getIcon();

    			tabs += "~" + ti.getTooltip();
    		}
    	}

    	if (tabs != null) {
    		System.out.println("tabs " + tabs);
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
				String[] b = s.split("~");

				if (b.length == 3)
					tblTabs.getItems().add(new TabInfo(b[0], b[1], b[2]));
				else
					tblTabs.getItems().add(new TabInfo(b[0], b[1], ""));

				tabNames.add(b[0]);
			}

			txtTabText.setText("");
			cbTabIcon.getSelectionModel().select(0);
			txtTabTooltip.setText("");
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
		
		loadDialogs();
		linkedDialogs();
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		
	}

}

