package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.DialogButton;

public class GeneralSettingsController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private ToggleButton btnAlwaysPrint;

    @FXML
    private Button btnCancel;

    @FXML
    private ToggleButton btnCenterWindow;

    @FXML
    private ToggleButton btnCloseOnFocus;

    @FXML
    private Button btnDelete;

    @FXML
    private ToggleButton btnEnableSpell;

    @FXML
    private ToggleButton btnEscapeOk;

    @FXML
    private ToggleButton btnExpander;

    @FXML
    private ToggleButton btnFixed;

    @FXML
    private ToggleButton btnFullScreen;

    @FXML
    private Button btnGtkrc;

    @FXML
    private Button btnIamgePath;

    @FXML
    private Button btnIcon;

    @FXML
    private Button btnImage;

    @FXML
    private ToggleButton btnKillParent;

    @FXML
    private ToggleButton btnMaximized;

    @FXML
    private ToggleButton btnMouse;

    @FXML
    private ToggleButton btnNoEscape;

    @FXML
    private ToggleButton btnNoFocus;

    @FXML
    private ToggleButton btnNoMarkup;

    @FXML
    private ToggleButton btnNoButtons;

    @FXML
    private ToggleButton btnOnTop;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnRest;
    
    @FXML
    private Button btnIconHelp;

    @FXML
    private Button btnSave;

    @FXML
    private ToggleButton btnSelectable;

    @FXML
    private ToggleButton btnSkipTaskbar;

    @FXML
    private ToggleButton btnSplash;

    @FXML
    private ToggleButton btnSticky;

    @FXML
    private ToggleButton btnTimeout;

    @FXML
    private ToggleButton btnUndecorated;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cbButtonIcon;

    @FXML
    private ComboBox<String> cbButtonLayout;

    @FXML
    private ComboBox<String> cbHScrollPolicy;

    @FXML
    private ComboBox<String> cbKillSignal;

    @FXML
    private ComboBox<String> cbTextAlign;

    @FXML
    private ComboBox<String> cbTimeoutPosition;

    @FXML
    private ComboBox<String> cbVScrollPolicy;

    @FXML
    private Label lblDialog;

    @FXML
    private TableView<DialogButton> tblButtons;
    
    @FXML
    private TextField txtPosX;

    @FXML
    private TextField txtPosY;

    @FXML
    private TextField txtBorders;

    @FXML
    private TextField txtButtonId;

    @FXML
    private TextField txtButtonText;

    @FXML
    private TextField txtButtonTooltip;

    @FXML
    private TextField txtDialogText;

    @FXML
    private TextField txtExpanderText;

    @FXML
    private TextField txtGtkrc;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtIcon;

    @FXML
    private TextField txtImage;

    @FXML
    private TextField txtImagePath;

    @FXML
    private TextField txtPlug;

    @FXML
    private TextField txtResponse;

    @FXML
    private TextField txtRest;

    @FXML
    private TextField txtSpellLang;

    @FXML
    private TextField txtTabNum;

    @FXML
    private TextField txtTimeoutSecs;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtWidth;
    
    @FXML
    private TableColumn<DialogButton, String> colButtonTooltip;

    @FXML
    private TableColumn<DialogButton, String> colButtonIcon;

    @FXML
    private TableColumn<DialogButton, String> colButtonId;

    @FXML
    private TableColumn<DialogButton, String> colButtonText;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.Map<String, Boolean> toggleButtons = new HashMap<String, Boolean>();
    private java.util.List<String> btnNames = new ArrayList<String>();
    private String[] icons = {"help-about", "list-add", "gtk-apply", "gtk-cancel", "gtk-close", "document-clear", "window-close", "gtk-edit", "system-run", "gtk-no", "gtk-ok", "document-open", "document-print", "application-exit", "view-refresh", "list-remove", "document-save", "system-search", "gtk-preferences", "gtk-yes"};

    @FXML
    void doIconHelp(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.hide();
		    stage.setTitle("Common Mime Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/icon_info.txt", 900.0, 600.0, false);
		    
		 // Code to center dialog within parent.
		    Stage ps = (Stage) btnOnTop.getScene().getWindow();

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
			
		    stage.showAndWait();
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doAdd(ActionEvent event) {
    	String txt = txtButtonText.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
		if (btnNames.contains(txt) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("A button with the name '" + txt + "' already exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnOnTop.getScene().getWindow();

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
			String id = txtButtonId.getText();
			String icon = cbButtonIcon.getSelectionModel().getSelectedItem();
			String tooltip = txtButtonTooltip.getText();
			
			tblButtons.getItems().add(new DialogButton(id, txt, icon, tooltip));
			
			txtButtonText.setText("");
			txtButtonTooltip.setText("");
			cbButtonIcon.getSelectionModel().select(0);
			txtButtonId.setText("0");
			btnNames.add(txt);
			
			saveButtons();
		}
    }

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doDelete(ActionEvent event) {
    	String txt = txtButtonText.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
		int n = 0;
		for (String f : btnNames) {
			if (f.equals(txt) == true) {
				break;
			}
			n++;
		}
		
		if (n >= btnNames.size())
			return;
		
		DialogButton db = tblButtons.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
		stage.hide();
		
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + txt + "'?");
		
		// Code to center dialog within parent.
		Stage ps = (Stage) btnOnTop.getScene().getWindow();

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
				System.out.println("db " + db);
				tblButtons.getItems().remove(db);
				btnNames.remove(db.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
//		ObservableList<DialogButton> btns = tblButtons.getItems();
//		btns.remove(n);
		
		txtButtonText.setText("");
		txtButtonTooltip.setText("");
		cbButtonIcon.getSelectionModel().select(0);
		txtButtonId.setText("0");
		
		saveButtons();
    }

    @FXML
    void doGtkrc(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Resource File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtGtkrc.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doIcon(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Icon File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtIcon.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doImage(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Image File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtImage.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doImagePath(ActionEvent event) {
    	DirectoryChooser dirChooser = new DirectoryChooser();
    	dirChooser.setTitle("Select Image Path");
    	
    	File f = dirChooser.showDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtImagePath.setText(f.getAbsolutePath());
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    }

    @FXML
    void doRest(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Rest File");
    	File f = fileChooser.showOpenDialog(btnGtkrc.getScene().getWindow());
    	
    	if (f != null) {
    		txtRest.setText(f.getAbsolutePath());
    	}
    }
    
    @FXML
    void onMouseClicked(MouseEvent event) {
    	DialogButton db = tblButtons.getSelectionModel().getSelectedItem();
    	
    	if (db != null) {
	    	txtButtonId.setText(db.getId());
	    	txtButtonText.setText(db.getText());
	    	cbButtonIcon.getSelectionModel().select(db.getIcon());
	    	txtButtonTooltip.setText(db.getTooltip());
    	}
    }

    @FXML
    void doSave(ActionEvent event) {
    	String buttons = null;

		ObservableList<DialogButton> selectedItems = tblButtons.getItems();
		for (DialogButton dp : selectedItems) {
			
			if (buttons == null) {
				if (dp.getText() != null)
					buttons = dp.getText() + "!" + dp.getIcon() + "!" + dp.getTooltip() + ":" + dp.getId();
				else
					buttons = dp.getIcon() + ":" + dp.getId();
			} else {
				if (dp.getText() != null)
					buttons += "," + dp.getText() + "!" + dp.getIcon() + "!" + dp.getTooltip() + ":" + dp.getId();
				else
					buttons += "," + dp.getIcon() + ":" + dp.getId();
			}
		}
		
//		System.out.println(yg.currDialog + "-General");
		yg.currIni.addValuePair(yg.currDialog + "-General", "title", txtTitle.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "icon", txtIcon.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "timeoutsecs", txtTimeoutSecs.getText());
		if (buttons != null)
			yg.currIni.addValuePair(yg.currDialog + "-General", "buttons", buttons);
		yg.currIni.addValuePair(yg.currDialog + "-General", "text", txtDialogText.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "image", txtImage.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "width", txtWidth.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "height", txtHeight.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "posx", txtPosX.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "posy", txtPosY.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "plug", txtPlug.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "tabnum", txtTabNum.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "expandertext", txtExpanderText.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "borders", txtBorders.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "imagepath", txtImagePath.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "rest", txtRest.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "response", txtResponse.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "gtkc", txtGtkrc.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "spelllang", txtSpellLang.getText());
		yg.currIni.addValuePair(yg.currDialog + "-General", "signal", txtSpellLang.getText());
		
		yg.currIni.addValuePair(yg.currDialog + "-General", "timeoutposition", cbTimeoutPosition.getValue());
		yg.currIni.addValuePair(yg.currDialog + "-General", "btnlayout", cbButtonLayout.getValue());
		yg.currIni.addValuePair(yg.currDialog + "-General", "textalign", cbTextAlign.getValue());
		yg.currIni.addValuePair(yg.currDialog + "-General", "hscroll", cbHScrollPolicy.getValue());
		yg.currIni.addValuePair(yg.currDialog + "-General", "vscroll", cbVScrollPolicy.getValue());
		
		for (String name : toggleButtons.keySet()) {
			yg.currIni.addValuePair(yg.currDialog + "-General", name, toggleButtons.get(name)? "true":"false");
		}
		  
//		yg.currIni.addValuePair(yg.currDialog + "-General", "killparent", btnKillParent.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "undecorated", btnUndecorated.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "fullscreen", btnFullScreen.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "noescape", btnNoEscape.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "nobuttons", btnNoButtons.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "center", btnCenterWindow.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "maximized", btnMaximized.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "expander", btnExpander.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "nomarkup", btnNoMarkup.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "escapeok", btnEscapeOk.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "alwaysprint", btnAlwaysPrint.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "sticky", btnSticky.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "fixed", btnFixed.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "mouse", btnMouse.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "ontop", btnOnTop.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "skiptaskbar", btnSkipTaskbar.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "splash", btnSplash.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "nofocus", btnNoFocus.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "closeonfocus", btnCloseOnFocus.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "selectable", btnSelectable.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "enablespell", btnEnableSpell.isSelected()? "true":"false");
//		yg.currIni.addValuePair(yg.currDialog + "-General", "timeout", btnTimeout.isSelected()? "true":"false");
		
		Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doToggleButton(ActionEvent event) {
    	if (event.getSource() instanceof ToggleButton) {
    		ToggleButton tb = (ToggleButton) event.getSource();
    		String s = tb.getText().replaceAll(" " , "").toLowerCase();
    		toggleButtons.put(s, tb.isSelected());
    		if (tb.isSelected() == true) {
				tb.setGraphic(new ImageView(yg.checkImage));
			} else {
				tb.setGraphic(null);
			}
    	}
    }
    
    private void setToggleButton(ToggleButton tb, boolean flag) {
		tb.setSelected(flag);
		String s = tb.getText().replaceAll(" " , "").toLowerCase();
		toggleButtons.put(s, flag);
		if (flag == true) {
			tb.setGraphic(new ImageView(yg.checkImage));
		} else {
			tb.setGraphic(null);
		}
	}

//    @FXML
//    void doUpdate(ActionEvent event) {
//    	String txt = txtButtonText.getText();
//		if (txt == null || txt.length() <= 0)
//			return;
//		
//		String id = txtButtonId.getText();
//		String icon = cbButtonIcon.getSelectionModel().getSelectedItem();
//		String tooltip = txtButtonTooltip.getText();
//		
//		int n = 0;
//		for (String f : btnNames) {
//			if (f.equals(txt) == true) {
//				break;
//			}
//			n++;
//		}
//		
//		ObservableList<DialogButton> btns = tblButtons.getItems();
//		
//		DialogButton db = btns.get(n);
//		db.setId(id);
//		db.setText(txt);
//		db.setIcon(icon);
//		db.setTooltip(tooltip);
//		
//		
//		txtButtonText.setText("");
//		txtButtonTooltip.setText("");
//		cbButtonIcon.getSelectionModel().select(0);
//		txtButtonId.setText("0");
//		
//		saveButtons();
//    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		colButtonId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colButtonText.setCellValueFactory(new PropertyValueFactory<>("text"));
		colButtonIcon.setCellValueFactory(new PropertyValueFactory<>("icon"));
		colButtonTooltip.setCellValueFactory(new PropertyValueFactory<>("tooltip"));
		
		colButtonText.setCellValueFactory(new PropertyValueFactory<DialogButton, String>("text"));
		colButtonIcon.setCellValueFactory(new PropertyValueFactory<DialogButton, String>("icon"));
		colButtonTooltip.setCellValueFactory(new PropertyValueFactory<DialogButton, String>("tooltip"));
		
		colButtonText.setCellFactory(TextFieldTableCell.forTableColumn());
		colButtonText.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		colButtonIcon.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(icons)));
		colButtonIcon.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setIcon(e.getNewValue()));
		
		colButtonTooltip.setCellFactory(TextFieldTableCell.forTableColumn());
		colButtonTooltip.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		cbTimeoutPosition.getItems().addAll("Top", "Bottom", "Left", "Right");
		cbTimeoutPosition.setValue("Top");
		cbTextAlign.getItems().addAll("Left", "Right", "Center", "Fill");
		cbTextAlign.setValue("Left");
		cbKillSignal.getItems().addAll("SIGTERM", "SIGINT", "SIGUSR1", "SIGUSR2", "SIGKILL", "SIGCHLD");
		cbKillSignal.setValue("SIGTERM");
		cbVScrollPolicy.getItems().addAll("Auto", "Always", "Never");
		cbVScrollPolicy.setValue("Auto");
		cbHScrollPolicy.getItems().addAll("Auto", "Always", "Never");
		cbHScrollPolicy.setValue("Auto");
		cbButtonLayout.getItems().addAll("Spread", "Edge", "Start", "End", "Center");
		cbButtonLayout.setValue("Start");
		cbButtonIcon.getItems().addAll(icons);
		cbButtonIcon.setValue("help-about");
		
		loadSettings();
	}
	
	private void saveButtons() {
		String buttons = null;

		ObservableList<DialogButton> selectedItems = tblButtons.getItems();
		for (DialogButton dp : selectedItems) {
			
			if (buttons == null) {
				if (dp.getText() != null)
					buttons = dp.getText() + "!" + dp.getIcon() + "!" + dp.getTooltip() + ":" + dp.getId();
				else
					buttons = dp.getIcon() + ":" + dp.getId();
			} else {
				if (dp.getText() != null)
					buttons += "," + dp.getText() + "!" + dp.getIcon() + "!" + dp.getTooltip() + ":" + dp.getId();
				else
					buttons += "," + dp.getIcon() + ":" + dp.getId();
			}
		}
		
		if (buttons != null)
			yg.currIni.addValuePair(yg.currDialog + "-General", "buttons", buttons);
	}
	
	private void loadSettings() {
		String title = yg.currIni.getString(yg.currDialog + "-General", "title");
		String icon = yg.currIni.getString(yg.currDialog + "-General", "icon");
		String timeoutsecs = yg.currIni.getString(yg.currDialog + "-General", "timeoutsecs");
		String buttons = yg.currIni.getString(yg.currDialog + "-General", "buttons");
		String text = yg.currIni.getString(yg.currDialog + "-General", "text");
		String image = yg.currIni.getString(yg.currDialog + "-General", "image");
		String width = yg.currIni.getString(yg.currDialog + "-General", "width");
		String height = yg.currIni.getString(yg.currDialog + "-General", "height");
		String posx = yg.currIni.getString(yg.currDialog + "-General", "posx");
		String posy = yg.currIni.getString(yg.currDialog + "-General", "posy");
		String plug = yg.currIni.getString(yg.currDialog + "-General", "plug");
		String tabnum = yg.currIni.getString(yg.currDialog + "-General", "tabnum");
		String expandertext = yg.currIni.getString(yg.currDialog + "-General", "expandertext");
		String borders = yg.currIni.getString(yg.currDialog + "-General", "borders");
		String imagepath = yg.currIni.getString(yg.currDialog + "-General", "imagepath");
		String rest = yg.currIni.getString(yg.currDialog + "-General", "rest");
		String response = yg.currIni.getString(yg.currDialog + "-General", "response");
		String gtkc = yg.currIni.getString(yg.currDialog + "-General", "gtkc");
		String spelllang = yg.currIni.getString(yg.currDialog + "-General", "spelllang");
		String signal = yg.currIni.getString(yg.currDialog + "-General", "signal");
		
		String timeoutposition = yg.currIni.getString(yg.currDialog + "-General", "timeoutposition");
		String btnlayout = yg.currIni.getString(yg.currDialog + "-General", "btnlayout");
		String textalign = yg.currIni.getString(yg.currDialog + "-General", "textalign");
		String hscroll = yg.currIni.getString(yg.currDialog + "-General", "hscroll");
		String vscroll = yg.currIni.getString(yg.currDialog + "-General", "vscroll");
		
		Boolean killparent = yg.currIni.getBoolean(yg.currDialog + "-General", "killparent");
//		Boolean timeout = yg.currIni.getBoolean(yg.currDialog + "-General", "timeout");
		Boolean undecorated = yg.currIni.getBoolean(yg.currDialog + "-General", "undecorated");
		Boolean fullscreen = yg.currIni.getBoolean(yg.currDialog + "-General", "fullscreen");
		Boolean noescape = yg.currIni.getBoolean(yg.currDialog + "-General", "noescape");
		Boolean nobuttons = yg.currIni.getBoolean(yg.currDialog + "-General", "nobuttons");
		Boolean center = yg.currIni.getBoolean(yg.currDialog + "-General", "center");
		Boolean maximized = yg.currIni.getBoolean(yg.currDialog + "-General", "maximized");
		Boolean expander = yg.currIni.getBoolean(yg.currDialog + "-General", "expander");
		Boolean nomarkup = yg.currIni.getBoolean(yg.currDialog + "-General", "nomarkup");
		Boolean escapeok = yg.currIni.getBoolean(yg.currDialog + "-General", "escapeok");
		Boolean alwaysprint = yg.currIni.getBoolean(yg.currDialog + "-General", "alwaysprint");
		Boolean sticky = yg.currIni.getBoolean(yg.currDialog + "-General", "sticky");
		Boolean fixed = yg.currIni.getBoolean(yg.currDialog + "-General", "fixed");
		Boolean mouse = yg.currIni.getBoolean(yg.currDialog + "-General", "mouse");
		Boolean ontop = yg.currIni.getBoolean(yg.currDialog + "-General", "ontop");
		Boolean skiptaskbar = yg.currIni.getBoolean(yg.currDialog + "-General", "skiptaskbar");
		Boolean splash = yg.currIni.getBoolean(yg.currDialog + "-General", "splash");
		Boolean nofocus = yg.currIni.getBoolean(yg.currDialog + "-General", "nofocus");
		Boolean closeonfocus = yg.currIni.getBoolean(yg.currDialog + "-General", "closeonfocus");
		Boolean selectable = yg.currIni.getBoolean(yg.currDialog + "-General", "selectable");
		Boolean enablespell = yg.currIni.getBoolean(yg.currDialog + "-General", "enablespell");
		
		if (buttons != null) {
			btnNames.clear();
			
			String[] a = buttons.split(",");

			for (String s : a) {
				String[] b = s.split(":");
				String[] c = b[0].split("!");
				
				tblButtons.getItems().add(new DialogButton(b[1], c[0], c[1], c[2]));
				
				btnNames.add(c[0]);
			}
			
			txtButtonText.setText("");
			cbButtonIcon.getSelectionModel().select(0);
			txtButtonTooltip.setText("");
		}
		
		if (title != null)
			txtTitle.setText(title);
		if (icon != null)
			txtIcon.setText(icon);
		if (timeoutsecs != null)
			txtTimeoutSecs.setText(timeoutsecs);
		if (buttons != null) {
			
		}
		if (text != null)
			txtDialogText.setText(text);
		if (image != null)
			txtImage.setText(image);
		if (width != null)
			txtWidth.setText(width);
		if (height != null)
			txtHeight.setText(height);
		if (posx != null)
			txtPosX.setText(posx);
		if (posy != null)
			txtPosY.setText(posy);
		if (plug != null)
			txtPlug.setText(plug);
		if (tabnum != null)
			txtTabNum.setText(tabnum);
		if (expandertext != null)
			txtExpanderText.setText(expandertext);
		if (borders != null)
			txtBorders.setText(borders);
		if (imagepath != null)
			txtImagePath.setText(imagepath);
		if (rest != null)
			txtRest.setText(rest);
		if (response != null)
			txtResponse.setText(response);
		if (gtkc != null)
			txtGtkrc.setText(gtkc);
		if (spelllang != null)
			txtSpellLang.setText(spelllang);
		
		if (timeoutposition != null)
			cbTimeoutPosition.setValue(timeoutposition);
		if (btnlayout != null)
			cbButtonLayout.setValue(btnlayout);
		if (textalign != null)
			cbTextAlign.setValue(textalign);
		if (hscroll != null)
			cbHScrollPolicy.setValue(hscroll);
		if (vscroll != null)
			cbVScrollPolicy.setValue(vscroll);
		if (signal != null)
			cbKillSignal.setValue(signal);
		
		setToggleButton(btnKillParent, killparent);
//		setToggleButton(btnTimeout, timeout);
		setToggleButton(btnUndecorated, undecorated);
		setToggleButton(btnFullScreen, fullscreen);
		setToggleButton(btnNoEscape, noescape);
		setToggleButton(btnNoButtons, nobuttons);
		setToggleButton(btnCenterWindow, center);
		setToggleButton(btnMaximized, maximized);
		setToggleButton(btnExpander, expander);
		setToggleButton(btnNoMarkup, nomarkup);
		setToggleButton(btnEscapeOk, escapeok);
		setToggleButton(btnAlwaysPrint, alwaysprint);
		setToggleButton(btnSticky, sticky);
		setToggleButton(btnFixed, fixed);
		setToggleButton(btnMouse, mouse);
		setToggleButton(btnOnTop, ontop);
		setToggleButton(btnSkipTaskbar, skiptaskbar);
		setToggleButton(btnSplash, splash);
		setToggleButton(btnNoFocus, nofocus);
		setToggleButton(btnCloseOnFocus, closeonfocus);
		setToggleButton(btnSelectable, selectable);
		setToggleButton(btnEnableSpell, enablespell);
	}
}

