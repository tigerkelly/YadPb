package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.TextType;

public class FormController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private Button btnFormValueHelp;
    
    @FXML
    private TitledPane titledPane;
    
    @FXML
    private AnchorPane aPane;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> fieldNames = new ArrayList<String>();
    private java.util.List<String> iconNames = new ArrayList<String>();
	private String[] types = {
			"Button-BTN",
			"Button Full-FBTN",
			"Checkbox-CHK",
			"Color-CLR",
			"Combobox-CB",
			"Combobox Editable-CBE",
			"Completion-CE",
			"Date-DT",
			"Dir Create-CDIR",
			"Dir-DIR",
			"Dir Multiple-MDIR",
			"File Create-SFL",
			"File-FL",
			"File Multiple-MFL",
			"Font Selector-FN",
			"Hidden-H",
			"Horizontal-HZ",
			"Label-LBL",
			"Numeric-NUM",
			"Read-Only-RO",
			"Scale-SCL",
			"TextArea-TXT",
			"TextField-TF"
			};
	private final int defaultType = 21;
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnButtonIcon;
    
    @FXML
    private TextField txtButtonTooltip;

    @FXML
    private ToggleButton btnCycle;

    @FXML
    private Button btnDelete;

    @FXML
    private ToggleButton btnOutputByRow;

    @FXML
    private ToggleButton btnQuotedOutput;

    @FXML
    private ToggleButton btnScroll;
    
    @FXML
    private Button btnTypeHelp;

    @FXML
    private ComboBox<String> cbAlign;

    @FXML
    private ComboBox<String> cbComplete;

    @FXML
    private ComboBox<String> cbPrecision;

    @FXML
    private ComboBox<String> cbButtonType;

    @FXML
    private TableColumn<TextType, String> colText;

    @FXML
    private TableColumn<TextType, String> colType;
    
    @FXML
    private TableColumn<TextType, String> colIcon;
    
    @FXML
    private TableColumn<TextType, String> colTooltip;
    
    @FXML
    private TableColumn<TextType, String> colValues;

    @FXML
    private TableView<TextType> tblButtons;
    
    @FXML
    private TextField txtButtonIcon;

    @FXML
    private TextField txtColumns;

    @FXML
    private TextField txtFocus;

    @FXML
    private TextField txtFormat;

    @FXML
    private TextField txtItemSeparator;

    @FXML
    private TextField txtNum;

    @FXML
    private TextField txtSeparator;

    @FXML
    private TextField txtButtonText;
    
    @FXML
    private TextField txtButtonValues;

    @FXML
    void doAlign(ActionEvent event) {
    	yg.iniUpdate("align", cbAlign.getSelectionModel().getSelectedItem());
    }

    @FXML
    void doColumns(ActionEvent event) {
    	yg.iniUpdate("columns", txtColumns.getText());
    }

    @FXML
    void doComplete(ActionEvent event) {
    	yg.iniUpdate("complete", cbComplete.getSelectionModel().getSelectedItem());
    }

    @FXML
    void doFocus(ActionEvent event) {
    	yg.iniUpdate("focus", txtFocus.getText());
    }

    @FXML
    void doPrecision(ActionEvent event) {
    	yg.iniUpdate("precision", cbPrecision.getSelectionModel().getSelectedItem());
    }
    
    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
    }
    
    @FXML
    void doButtonIcon(ActionEvent event) {
    	try {
		    Stage stage = new Stage();
		    stage.hide();
		    stage.setTitle("Icon Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("icons.txt", 900.0, 600.0, true);
		    
		 // Code to center dialog within parent.
		    Stage ps = (Stage) btnAdd.getScene().getWindow();

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
		    
		    if (yg.currIcon != null)
		    	txtButtonIcon.setText(yg.currIcon);
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doTypeHelp(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.hide();
		    stage.setTitle("Field Types Help");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/type_help.txt", 900.0, 600.0, false);
		    
		 // Code to center dialog within parent.
		    Stage ps = (Stage) aPane.getScene().getWindow();

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
    void doValuesHelp(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.hide();
		    stage.setTitle("Field Syntax Help");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/form_field_syntax.txt", 500.0, 300.0, false);
		    
		 // Code to center dialog within parent.
		    Stage ps = (Stage) aPane.getScene().getWindow();

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
    void doFormValueHelp(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.hide();
		    stage.setTitle("Field Types Help");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/Form_value_help.txt", 1100.0, 700.0, false);
		    
		 // Code to center dialog within parent.
		    Stage ps = (Stage) aPane.getScene().getWindow();

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
    void doButtonType(ActionEvent event) {
    	String txt = cbButtonType.getSelectionModel().getSelectedItem();
    	
    	setFields(txt);
    }

    @FXML
    void onFormat(KeyEvent event) {
    	yg.iniUpdate("format", txtFormat.getText());
    }

    @FXML
    void onItemSeparator(KeyEvent event) {
    	yg.iniUpdate("isep", txtSeparator.getText());
    }

    @FXML
    void onNum(KeyEvent event) {
    	yg.iniUpdate("num", txtNum.getText());
    }

    @FXML
    void onSeparator(KeyEvent event) {
    	yg.iniUpdate("sep", txtFormat.getText());
    }
    
    @FXML
    void doButtonAdd(ActionEvent event) {
    	String txt = txtButtonText.getText();
    	String typ = cbButtonType.getSelectionModel().getSelectedItem();
    	
    	if (typ.endsWith("HZ") == true)
			txt = "HORZ";
    	
		if (txt == null || txt.isEmpty() == true) {
			return;
		}
		
//		System.out.println(txt);
		
		if (fieldNames.contains(txt) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + txt + "' is already exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) aPane.getScene().getWindow();

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
		
		tblButtons.getItems().add(new TextType(txt, typ,
				txtButtonIcon.getText(), txtButtonTooltip.getText(), txtButtonValues.getText()));
		
		txtButtonText.setText("");
		cbButtonType.getSelectionModel().select(defaultType);
		fieldNames.add(txt);
		
		saveButtons();
    }

    @FXML
    void doButtonDelete(ActionEvent event) {
    	String txt = txtButtonText.getText();
		
		if (txt == null || txt.length() == 0)
			return;
		
		int n = 0;
		for (String f : fieldNames) {
			if (f.equals(txt) == true) {
				break;
			}
			n++;
		}
		
		if (n >= fieldNames.size())
			return;
		
		TextType tt = tblButtons.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
		stage.hide();
		
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + txt + "'?");
		
		// Code to center dialog within parent.
		Stage ps = (Stage) aPane.getScene().getWindow();

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
//				System.out.println("tt " + tt);
				tblButtons.getItems().remove(tt);
				fieldNames.remove(tt.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtButtonText.setText("");
		cbButtonType.getSelectionModel().select(defaultType);
		
		saveButtons();
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	buildIconList();
    	
		cbButtonType.getItems().addAll(types);
		cbButtonType.getSelectionModel().select(defaultType);
		
		cbAlign.getItems().addAll("Left", "Center", "Right");
		
		cbPrecision.getItems().addAll("1","2","3","4","5","6");
		
		cbComplete.getItems().addAll("Any", "All", "Regex");
		
		tblButtons.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtButtonText.setText(newVal.getText());
		        cbButtonType.getSelectionModel().select(newVal.getType());
		        txtButtonIcon.setText(newVal.getIcon());
		        txtButtonTooltip.setText(newVal.getTooltip());
		        txtButtonValues.setText(newVal.getValues());
		        
		        setFields(newVal.getType());
		    }
		});
		
		colText.setCellValueFactory(new PropertyValueFactory<TextType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<TextType, String>("type"));
		colIcon.setCellValueFactory(new PropertyValueFactory<TextType, String>("icon"));
		colTooltip.setCellValueFactory(new PropertyValueFactory<TextType, String>("tooltip"));
		colValues.setCellValueFactory(new PropertyValueFactory<TextType, String>("values"));
		
		colText.setCellFactory(TextFieldTableCell.forTableColumn());
		colText.setOnEditCommit((TableColumn.CellEditEvent<TextType, String> t) -> {
			int row = t.getTablePosition().getRow();
			
			TextType tt = ((TextType) t.getTableView().getItems().get(row));
			if (tt.getType().endsWith("HZ") == false)
				((TextType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setText(t.getNewValue());
			else
				((TextType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setText("HORZ");
	        saveButtons();
	        reloadFields();
	    });
		
		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(types)));
		colType.setOnEditCommit((TableColumn.CellEditEvent<TextType, String> t) -> {
	        ((TextType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setType(t.getNewValue());
	        saveButtons();
	    });
		
		colIcon.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(iconNames)));

		colIcon.setOnEditCommit((TableColumn.CellEditEvent<TextType, String> t) -> {
			int row = t.getTablePosition().getRow();
			
			TextType tt = ((TextType) t.getTableView().getItems().get(row));
			if (tt.getType().endsWith("BTN") == true)
				t.getTableView().getItems().get(t.getTablePosition().getRow()).setIcon(t.getNewValue());
			saveButtons();
			reloadFields();
	    });
		
		colTooltip.setCellFactory(TextFieldTableCell.forTableColumn());
		colTooltip.setOnEditCommit((TableColumn.CellEditEvent<TextType, String> t) -> {
			int row = t.getTablePosition().getRow();
			
			TextType tt = ((TextType) t.getTableView().getItems().get(row));
			if (tt.getType().endsWith("BTN") == true)
				((TextType) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTooltip(t.getNewValue());
	        saveButtons();
	        reloadFields();
	    });
		
		colValues.setCellFactory(TextFieldTableCell.forTableColumn());
		colValues.setOnEditCommit((TableColumn.CellEditEvent<TextType, String> t) -> {
			int row = t.getTablePosition().getRow();
			
			TextType tt = ((TextType) t.getTableView().getItems().get(row));
			String typ = tt.getType();
			int idx = typ.lastIndexOf('-');
	    	String s = typ.substring(idx+1);
	    	
	    	switch(s) {
	    	case "TXT":
	    	case "TF":
	    	case "NUM":
	    	case "CHK":
	    	case "CB":
	    	case "CBE":
	    	case "CE":
	    	case "MFL":
	    	case "MDIR":
	    		tt.setValues(t.getNewValue());
	    		break;
	    	default:
	    		
	    		break;
	    	}
			
	        saveButtons();
	        reloadFields();
	    });
		
		TxtLimitListener limitCount = new TxtLimitListener(txtSeparator, 1);
    	txtSeparator.textProperty().addListener(limitCount);
    	
    	TxtLimitListener limitCount2 = new TxtLimitListener(txtItemSeparator, 1);
    	txtItemSeparator.textProperty().addListener(limitCount2);
    	
	}
    
    private void buildIconList() {
    	File f = new File(System.getProperty("user.home") + File.separator + "YadPb" + File.separator + "icons.txt");
    	iconNames.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));

			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.length() > 0 && line.charAt(0) == '#')		// Skip comments.
					continue;
				
				File fn = new File(line);
				String name = fn.getName();
				int idx = name.lastIndexOf('.');
				iconNames.add(name.substring(0, idx));
			}
			br.close();
//			for( String s : iconNames)
//				System.out.println(s);
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}
    }
    
    private void saveButtons() {
    	ObservableList<TextType> obs = tblButtons.getItems();
    
    	String fields = null;
    	for (TextType tt : obs) {
    		String txt = tt.getText();
    		String typ = tt.getType();
    		String icn = tt.getIcon();
    		String tip = tt.getTooltip();
    		String val = tt.getValues();
    		
    		if (typ.endsWith("HZ") == true)
    			txt = "HORZ";
    		
    		if (fields == null)
    			fields = txt;
    		else
    			fields += "," + txt;
    		
    		if (typ != null && typ.isEmpty() == false)
    			fields += "~" + typ;
    		else
    			fields += "~";
    		
    		if (icn != null)
    			fields += "~" + icn;
    		else
    			fields += "~";
    		
    		if (tip != null)
    			fields += "~" + tip;
    		else
    			fields += "~";
    		
    		if (val != null)
    			fields += "~" + val;
    		else
    			fields += "~";
    	}
    	
    	if (fields != null)
    		yg.iniUpdate("fields", fields);
    }
    
    private void reloadFields() {
    	tblButtons.getItems().clear();
    	String fields = yg.currIni.getString(yg.currDialog, "fields");
    	if (fields != null) {
			fieldNames.clear();
			
			String[] a = fields.split(",");
			
			for (String s : a) {
				String lbl = null;
				String typ = null;
				String icn = null;
				String tip = null;
				String val = null;
				
				String[] b = s.split("~", -1);
				if (b.length == 1) {
					lbl = b[0];
					typ = null;
				} else if (b.length == 2) {
					lbl = b[0];
					typ = b[1];
				} else if (b.length == 3) {
					lbl = b[0];
					typ = b[1];
					val = b[2];
				} else if (b.length == 4) {
					lbl = b[0];
					typ = b[1];
					icn = b[2];
					val = b[3];
				} else if (b.length == 5) {
					lbl = b[0];
					typ = b[1];
					icn = b[2];
					tip = b[3];
					val = b[4];
				}
				
				if (typ.endsWith("HZ") == true)
					lbl = "HORZ";
				
				tblButtons.getItems().add(new TextType(lbl, typ, icn, tip, val));
			}
		}
    }
    
    private void setFields(String txt) {
    	int idx = txt.lastIndexOf('-');
    	String s = txt.substring(idx+1);
    	
    	switch(s) {
    	case "BTN":
    	case "FBTN":
    		txtButtonValues.setDisable(false);
    		txtButtonIcon.setDisable(false);
    		txtButtonTooltip.setDisable(false);
    		break;
    	case "NUM":
    	case "CHK":
    	case "CB":
    	case "CBE":
    	case "SCL":
    	case "CE":
    	case "CLR":
    	case "FL":
    	case "FN":
    	case "SFL":
    	case "DIR":
    	case "CDIR":
    	case "MDIR":
    	case "MFL":
    	case "DT":
    	case "H":
    		txtButtonValues.setDisable(false);
    		txtButtonIcon.setDisable(true);
    		txtButtonTooltip.setDisable(true);
    		break;
    	case "TXT":
    		txtButtonValues.setDisable(false);
    		txtButtonIcon.setDisable(true);
    		txtButtonTooltip.setDisable(true);
    		break;
    	default:
    		txtButtonValues.setDisable(true);
    		txtButtonIcon.setDisable(true);
    		txtButtonTooltip.setDisable(true);
    		break;
    	}
    }

	@Override
	public void updateDialog() {
		String columns = yg.currIni.getString(yg.currDialog, "columns");
		String sep = yg.currIni.getString(yg.currDialog, "sep");
		String isep = yg.currIni.getString(yg.currDialog, "isep");
		String format = yg.currIni.getString(yg.currDialog, "format");
		String outputnum = yg.currIni.getString(yg.currDialog, "outputnum");
		String precision = yg.currIni.getString(yg.currDialog, "precision");
		String align = yg.currIni.getString(yg.currDialog, "align");
		String complete = yg.currIni.getString(yg.currDialog, "complete");
		String focus = yg.currIni.getString(yg.currDialog, "focus");

		boolean scroll = yg.currIni.getBoolean(yg.currDialog, "scroll");
		boolean outputrow = yg.currIni.getBoolean(yg.currDialog, "outputrow");
		boolean quoted = yg.currIni.getBoolean(yg.currDialog, "quoted");
		boolean cycle = yg.currIni.getBoolean(yg.currDialog, "cycle");

		if (columns != null)
			txtColumns.setText(columns);
		if (sep != null)
			txtSeparator.setText(sep);
		if (isep != null)
			txtItemSeparator.setText(isep);
		if (format != null)
			txtFormat.setText(format);
		if (outputnum != null)
			txtNum.setText(outputnum);
		if (precision != null)
			cbPrecision.getSelectionModel().select(precision);
		if (align != null)
			cbAlign.getSelectionModel().select(align);
		if (complete != null)
			cbComplete.getSelectionModel().select(complete);
		if (focus != null)
			txtFocus.setText(focus);
		
		reloadFields();
		
//		tblButtons.getItems().clear();
//		
//		if (fields != null) {
//			fieldNames.clear();
//			
//			String[] a = fields.split(",");
//			
//			for (String s : a) {
//				String lbl = null;
//				String typ = null;
//				String icn = null;
//				String tip = null;
//				String val = null;
//				
//				String[] b = s.split("~", -1);
////				System.out.println(b.length);
//				if (b.length == 1) {
//					lbl = b[0];
//					typ = "TXT";
//				} else if (b.length == 2) {
//					lbl = b[0];
//					typ = b[1];
//				} else if (b.length == 3) {
//					lbl = b[0];
//					typ = b[1];
//					val = b[2];
//				} else if (b.length == 4) {
//					lbl = b[0];
//					typ = b[1];
//					icn = b[2];
//					val = b[3];
//				} else if (b.length == 5) {
//					lbl = b[0];
//					typ = b[1];
//					icn = b[2];
//					tip = b[3];
//					val = b[4];
//				}
//				
//				tblButtons.getItems().add(new TextType(lbl, typ, icn, tip, val));
//			}
//			
//			txtButtonText.setText("");
//			cbButtonType.getSelectionModel().select(defaultType);
//		}
		
		txtButtonText.setText("");
		cbButtonType.getSelectionModel().select(defaultType);

		setToggleButton(btnScroll, scroll);
		setToggleButton(btnOutputByRow, outputrow);
		setToggleButton(btnQuotedOutput, quoted);
		setToggleButton(btnCycle, cycle);
		
		setFields("Text-TXT");
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}

