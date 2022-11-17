package application;

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
    private TitledPane titledPane;
    
    @FXML
    private AnchorPane aPane;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> fieldNames = new ArrayList<String>();
	private String[] types = {
			"Button-BTN",
			"Button Full-FBTN",
			"Checkbox-CHK",
			"Color-CLR",
			"Combobox-CB",
			"Combobox Editable-CBE",
			"Completion-CE",
			"Date-DT",
			"Directory Create-CDIR",
			"Directory-DIR",
			"Directory Multiple-MDIR",
			"File Create-SFL",
			"File-FL",
			"File Multiple-MFL",
			"Font Selector-FN",
			"Hidden-H",
			"Label-LBL",
			"Numeric-NUM",
			"Read-Only-RO",
			"Scale-SCL",
			"Text-TXT"
			};
	private final int defaultType = 20;
    
    @FXML
    private Button btnAdd;

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
    private Button btnUpdate;
    
    @FXML
    private Button btnTypeHelp;

    @FXML
    private ComboBox<String> cbAlign;

    @FXML
    private ComboBox<String> cbComplete;

    @FXML
    private ComboBox<String> cbPrecision;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private TableColumn<TextType, String> colText;

    @FXML
    private TableColumn<TextType, String> colType;
    
    @FXML
    private TableColumn<TextType, String> colValues;

    @FXML
    private TableView<TextType> tblButtons;

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
    private TextField txtText;
    
    @FXML
    private TextField txtValues;

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
    void doType(ActionEvent event) {
    	String txt = cbType.getSelectionModel().getSelectedItem();
    	
    	int idx = txt.lastIndexOf('-');
    	String s = txt.substring(idx);
    	
    	switch(s) {
    	case "-NUM":
    	case "-CHK":
    	case "-CB":
    	case "-MFL":
    	case "-MDIR":
    	case "-BTN":
    	case "-FBTN":
    		txtValues.setDisable(false);
    		break;
    	default:
    		txtValues.setDisable(true);
    		break;
    	}
    }

    @FXML
    void onFormat(KeyEvent event) {
    	yg.iniUpdate("format", txtFormat.getText());
    }

    @FXML
    void onItemSeparator(KeyEvent event) {
    	yg.iniUpdate("itemsep", txtSeparator.getText());
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
    	String txt = txtText.getText();
		if (txt == null || txt.length() <= 0)
			return;
		
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
		
		tblButtons.getItems().add(new TextType(txt, cbType.getSelectionModel().getSelectedItem(), txtValues.getText()));
		
		txtText.setText("");
		cbType.getSelectionModel().select(defaultType);
		fieldNames.add(txt);
		
		saveButtons();
    }

    @FXML
    void doButtonDelete(ActionEvent event) {
    	String txt = txtText.getText();
		
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
		
		txtText.setText("");
		cbType.getSelectionModel().select(defaultType);
		
		saveButtons();
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbType.getItems().addAll(types);
		cbType.getSelectionModel().select(defaultType);
		
		cbAlign.getItems().addAll("Left", "Center", "Right");
		cbAlign.getSelectionModel().select(0);
		
		cbPrecision.getItems().addAll("1","2","3","4","5","6");
		cbPrecision.getSelectionModel().select(2);
		
		cbComplete.getItems().addAll("Any", "All", "Regex");
		cbComplete.getSelectionModel().select(0);
		
		tblButtons.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal);
		        txtText.setText(newVal.getText());
		        cbType.getSelectionModel().select(newVal.getType());
		        txtValues.setText(newVal.getValues());
		    }
		});
		
		colText.setCellValueFactory(new PropertyValueFactory<TextType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<TextType, String>("type"));
		colValues.setCellValueFactory(new PropertyValueFactory<TextType, String>("values"));
		
		colText.setCellFactory(TextFieldTableCell.forTableColumn());
		colText.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setText(e.getNewValue()));
		
		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(types)));
		colType.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue()));
		
		colValues.setCellFactory(TextFieldTableCell.forTableColumn());
		colValues.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setValues(e.getNewValue()));
		
		TxtLimitListener limitCount = new TxtLimitListener(txtSeparator, 1);
    	txtSeparator.textProperty().addListener(limitCount);
    	
    	TxtLimitListener limitCount2 = new TxtLimitListener(txtItemSeparator, 1);
    	txtItemSeparator.textProperty().addListener(limitCount2);
	}
    
    private void saveButtons() {
    	ObservableList<TextType> obs = tblButtons.getItems();
    
    	String fields = null;
    	for (TextType tt : obs) {
    		if (fields == null)
    			fields = tt.getText() + "|" + tt.getType() + "|" + tt.getValues();
    		else
    			fields += "," + tt.getText() + "|" + tt.getType() + "|" + tt.getValues();
    	}
    	
    	if (fields != null)
    		yg.iniUpdate("fields", fields);
    }

	@Override
	public void updateDialog() {
		String fields = yg.currIni.getString(yg.currDialog, "fields");
		String columns = yg.currIni.getString(yg.currDialog, "columns");
		String sep = yg.currIni.getString(yg.currDialog, "sep");
		String items = yg.currIni.getString(yg.currDialog, "item");
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
		if (items != null)
			txtItemSeparator.setText(items);
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
		
		tblButtons.getItems().clear();
		
		if (fields != null) {
			fieldNames.clear();
			
			String[] a = fields.split(",");

			for (String s : a) {
				String[] b = s.split(":");
				String[] c = b[1].split("|");
				
				tblButtons.getItems().add(new TextType(b[0], c[0], c[1]));
				
				fieldNames.add(b[0]);
			}
			
			txtText.setText("");
			cbType.getSelectionModel().select(defaultType);
		}

		setToggleButton(btnScroll, scroll);
		setToggleButton(btnOutputByRow, outputrow);
		setToggleButton(btnQuotedOutput, quoted);
		setToggleButton(btnCycle, cycle);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

