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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.ColumnType;

public class ListController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblDialog;

    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> columnNames = new ArrayList<>();
  	private String[] types = {
  			"Checkbox-CHK",
  			"Float-FLT",
  			"Hidden-HD",
  			"Image-IMG",
  			"Numeric-NUM",
  			"Progress Bar-BAR",
  			"Radio Button-RD",
  			"Size Column-SZ",
  			"Text-TXT",
  			"Tooltip-TIP"
  			};

  	 @FXML
     private AnchorPane aPane;

     @FXML
     private ToggleButton btnAddOnTop;

     @FXML
     private ToggleButton btnCheckList;

     @FXML
     private Button btnColumnHelp;

     @FXML
     private ToggleButton btnEditable;

     @FXML
     private Button btnGeneral;

     @FXML
     private ToggleButton btnListen;

     @FXML
     private ToggleButton btnIecFormat;

     @FXML
     private ToggleButton btnMultiple;

     @FXML
     private ToggleButton btnNoClick;

     @FXML
     private ToggleButton btnNoHeaders;

     @FXML
     private ToggleButton btnNoRulesHint;

     @FXML
     private ToggleButton btnNoSelection;

     @FXML
     private ToggleButton btnPrintAll;

     @FXML
     private ToggleButton btnQuotedOutput;

     @FXML
     private ToggleButton btnRadioList;

     @FXML
     private ToggleButton btnRegexSearch;

     @FXML
     private ToggleButton btnTail;

     @FXML
     private ComboBox<String> cbColumnType;

     @FXML
     private ComboBox<String> cbGridLines;

     @FXML
     private ComboBox<String> cbEllipsize;

     @FXML
     private TableColumn<ColumnType, String> colName;

     @FXML
     private TableColumn<ColumnType, String> colType;

     @FXML
     private TableView<ColumnType> tblColumns;

     @FXML
     private TextField txtAddAction;

     @FXML
     private TextField txtColumnName;

     @FXML
     private TextField txtDClickAction;

     @FXML
     private TextField txtEditableCols;

     @FXML
     private TextField txtEllipsizeCols;

     @FXML
     private TextField txtExpand;

     @FXML
     private TextField txtHide;

     @FXML
     private TextField txtLimit;

     @FXML
     private TextField txtPrecision;

     @FXML
     private TextField txtPrintCol;

     @FXML
     private TextField txtSearchCol;

     @FXML
     private TextField txtSelectAction;

     @FXML
     private TextField txtSepCol;

     @FXML
     private TextField txtSepValue;

     @FXML
     private TextField txtSeparator;

     @FXML
     private TextField txtTooltip;

     @FXML
     private TextField txtWrapCols;

     @FXML
     private TextField txtWrapWidth;

     @FXML
     void doColumnAdd(ActionEvent event) {
    	String txt = txtColumnName.getText();
 		if (txt == null || txt.length() <= 0) {
 			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
 			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnAddOnTop.getScene().getWindow();

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

// 		System.out.println(txt);

 		if (columnNames.contains(txt)) {
 			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
 			messageBox.setTitle("Warning");
 			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
 			messageBox.getButtonTypes().setAll(yesButton);

 			messageBox.setContentText("The column name '" + txt + "' is already exists.");

 			messageBox.showAndWait();
 			return;
 		}

 		tblColumns.getItems().add(new ColumnType(txt, cbColumnType.getSelectionModel().getSelectedItem()));

 		txtColumnName.setText("");
 		cbColumnType.getSelectionModel().select(8);
 		columnNames.add(txt);

 		saveColumns();
     }

     @FXML
     void doColumnDelete(ActionEvent event) {
    	 String txt = txtColumnName.getText();

 		if (txt == null || txt.length() == 0) {
 			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
 			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnAddOnTop.getScene().getWindow();

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
 		for (String f : columnNames) {
 			if (f.equals(txt)) {
 				found = n;
 				break;
 			}
 			n++;
 		}

 		if (found == -1 || found >= columnNames.size())
 			return;
 		
 		tblColumns.getItems().remove(found);
		columnNames.remove(found);
 		

 		txtColumnName.setText("");
 		cbColumnType.getSelectionModel().select(8);

 		saveColumns();
     }

     @FXML
     void doColumnType(ActionEvent event) {
//    	 System.out.println(lblDialog.getText());
    	 
    	try {
		    Stage stage = new Stage();
		    stage.hide();
		    stage.setTitle("Column Types Help");

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/list_type.txt", 900.0, 600.0, false);
		    
		 // Code to center dialog within parent.
		    Stage ps = (Stage) btnAddOnTop.getScene().getWindow();

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

		    if (yg.currColumnType != null) {
		    	cbColumnType.getSelectionModel().select(yg.currColumnType);
		    }

		} catch (IOException e1) {
			e1.printStackTrace();
		}
     }

     @FXML
     void doGridLines(ActionEvent event) {
    	 yg.iniUpdate("gridlines", cbGridLines.getSelectionModel().getSelectedItem());
     }

     @FXML
     void doEllipsize(ActionEvent event) {
    	 yg.iniUpdate("ellipsize", cbEllipsize.getSelectionModel().getSelectedItem());
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
     void onAddAction(KeyEvent event) {
    	 yg.iniUpdate("addaction", txtAddAction.getText());
     }

     @FXML
     void onDClickAction(KeyEvent event) {
    	 yg.iniUpdate("dclickaction", txtDClickAction.getText());
     }

     @FXML
     void onEditableCols(KeyEvent event) {
    	 yg.iniUpdate("edtiablecols", txtEditableCols.getText());
     }

     @FXML
     void onSelectAction(KeyEvent event) {
    	 yg.iniUpdate("selectaction", txtSelectAction.getText());
     }

     @FXML
     void onSeparator(KeyEvent event) {
    	 yg.iniUpdate("sep", txtSeparator.getText());
     }

     @FXML
     void onWrapCols(KeyEvent event) {
    	 yg.iniUpdate("wrapcols", txtWrapCols.getText());
     }

     @FXML
     void onEllipsizeCols(KeyEvent event) {
    	 yg.iniUpdate("ellipsizecols", txtEllipsizeCols.getText());
     }

     @FXML
     void onExpand(KeyEvent event) {
    	 yg.iniUpdate("expand", txtExpand.getText());
     }

     @FXML
     void onHide(KeyEvent event) {
    	 yg.iniUpdate("hide", txtHide.getText());
     }

     @FXML
     void onLimit(KeyEvent event) {
    	 yg.iniUpdate("limit", txtLimit.getText());
     }

     @FXML
     void onPrecision(KeyEvent event) {
    	 yg.iniUpdate("precision", txtPrecision.getText());
     }

     @FXML
     void onPrintCol(KeyEvent event) {
    	 yg.iniUpdate("printcol", txtPrintCol.getText());
     }

     @FXML
     void onSepCol(KeyEvent event) {
    	 yg.iniUpdate("sepcol", txtSepCol.getText());
     }

     @FXML
     void onSepValue(KeyEvent event) {
    	 yg.iniUpdate("sepvalue", txtSepValue.getText());
     }

     @FXML
     void onTooltip(KeyEvent event) {
    	 yg.iniUpdate("tooltip", txtTooltip.getText());
     }

     @FXML
     void onWrapWidth(KeyEvent event) {
    	 yg.iniUpdate("wrapwidth", txtWrapWidth.getText());
     }

     @FXML
     void onSearchCol(KeyEvent event) {
    	 yg.iniUpdate("searchcol", txtSearchCol.getText());
     }

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    private void saveColumns() {
    	ObservableList<ColumnType> obs = tblColumns.getItems();

    	String columns = null;
    	for (ColumnType ct : obs) {
    		if (columns == null)
    			columns = ct.getText() + ":" + ct.getType();
    		else
    			columns += "," + ct.getText() + ":" + ct.getType();
    	}

    	if (columns != null)
    		yg.iniUpdate("columns", columns);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbColumnType.getItems().addAll(types);
		
		cbEllipsize.getItems().addAll("None", "Start", "Middle", "End");
		
		cbGridLines.getItems().addAll("Horizontal", "Vertical", "Both");

		colName.setCellValueFactory(new PropertyValueFactory<>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<>("type"));

		colName.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("type"));

		colName.setCellFactory(TextFieldTableCell.<ColumnType>forTableColumn());

		colName.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setText(t.getNewValue());
	        columnNames.remove(t.getOldValue());
	        columnNames.add(t.getNewValue());
	        saveColumns();
	    });

		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(types)));

		colType.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setType(t.getNewValue());
	        saveColumns();
	    });

		tblColumns.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtColumnName.setText(newVal.getText());
		        cbColumnType.getSelectionModel().select(newVal.getType());
		    }
		});
		
		TxtLimitListener limitCount = new TxtLimitListener(txtSeparator, 1);
    	txtSeparator.textProperty().addListener(limitCount);
	}

	@Override
	public void updateDialog() {
		String columns = yg.currIni.getString(yg.currDialog, "columns");
		String editablecolumns = yg.currIni.getString(yg.currDialog, "editablecolumns");
		String sep = yg.currIni.getString(yg.currDialog, "sep");
		String sepvalue = yg.currIni.getString(yg.currDialog, "sepvalue");
		String wrapcolumns = yg.currIni.getString(yg.currDialog, "wrapcolumns");
		String ellipsizecolumns = yg.currIni.getString(yg.currDialog, "ellipsizecolumns");
		String dclickaction = yg.currIni.getString(yg.currDialog, "dclickaction");
		String selectaction = yg.currIni.getString(yg.currDialog, "selectaction");
		String addaction = yg.currIni.getString(yg.currDialog, "addaction");
//		String type = yg.currIni.getString(yg.currDialog, "type");
		String gridlines = yg.currIni.getString(yg.currDialog, "gridlines");
		String ellipsize = yg.currIni.getString(yg.currDialog, "ellipsize");
		String printcolumn = yg.currIni.getString(yg.currDialog, "printcolumn");
		String hidecolumn = yg.currIni.getString(yg.currDialog, "hidecolumn");
		String expandcolumn = yg.currIni.getString(yg.currDialog, "expandcolumn");
		String searchcolumn = yg.currIni.getString(yg.currDialog, "searchcolumn");
		String tooltipcolumn = yg.currIni.getString(yg.currDialog, "tooltipcolumn");
		String sepcolumn = yg.currIni.getString(yg.currDialog, "sepcolumn");
		String percision = yg.currIni.getString(yg.currDialog, "percision");
		String limit = yg.currIni.getString(yg.currDialog, "limit");
		String wrapwidth = yg.currIni.getString(yg.currDialog, "wrapwidth");

		boolean noruleshint = yg.currIni.getBoolean(yg.currDialog, "noruleshint");
		boolean noclick = yg.currIni.getBoolean(yg.currDialog, "noclick");
		boolean noheaders = yg.currIni.getBoolean(yg.currDialog, "noheaders");
		boolean radiolist = yg.currIni.getBoolean(yg.currDialog, "radiolist");
		boolean editable = yg.currIni.getBoolean(yg.currDialog, "editable");
		boolean regexsearch = yg.currIni.getBoolean(yg.currDialog, "regexsearch");
		boolean quotedoutput = yg.currIni.getBoolean(yg.currDialog, "quotedoutput");
		boolean addontop = yg.currIni.getBoolean(yg.currDialog, "addontop");
		boolean tail = yg.currIni.getBoolean(yg.currDialog, "tail");
		boolean iecformat = yg.currIni.getBoolean(yg.currDialog, "iecformat");
		boolean printall = yg.currIni.getBoolean(yg.currDialog, "printall");
		boolean listen = yg.currIni.getBoolean(yg.currDialog, "listen");
		boolean checklist = yg.currIni.getBoolean(yg.currDialog, "checklist");
		boolean noselection = yg.currIni.getBoolean(yg.currDialog, "noselection");
		boolean multiple = yg.currIni.getBoolean(yg.currDialog, "multiple");

		if (columns != null) {
			tblColumns.getItems().clear();
			columnNames.clear();

			String[] cols = columns.split(",");

			for (String c : cols) {
				String[] a = c.split(":");
				tblColumns.getItems().add(new ColumnType(a[0], a[1]));
				
				columnNames.add(a[0]);
			}
		}

		if (editablecolumns != null)
			txtEditableCols.setText(editablecolumns);
		if (sep != null)
			txtSeparator.setText(sep);
		if (sepvalue != null)
			txtSepValue.setText(sepvalue);
		if (wrapcolumns != null)
			txtWrapCols.setText(wrapcolumns);
		if (ellipsizecolumns != null)
			txtEllipsizeCols.setText(ellipsizecolumns);
		if (dclickaction != null)
			txtDClickAction.setText(dclickaction);
		if (selectaction != null)
			txtSelectAction.setText(selectaction);
		if (addaction != null)
			txtAddAction.setText(addaction);
//		if (type != null)
//			cbColumnType.getSelectionModel().select(type);
		if (gridlines != null)
			cbGridLines.getSelectionModel().select(gridlines);
		if (ellipsize != null)
			cbEllipsize.getSelectionModel().select(ellipsize);
		if (printcolumn != null)
			txtPrintCol.setText(printcolumn);
		if (hidecolumn != null)
			txtHide.setText(hidecolumn);
		if (expandcolumn != null)
			txtExpand.setText(expandcolumn);
		if (searchcolumn != null)
			txtSearchCol.setText(searchcolumn);
		if (tooltipcolumn != null)
			txtTooltip.setText(tooltipcolumn);
		if (sepcolumn != null)
			txtSepCol.setText(sepcolumn);
		if (percision != null)
			txtPrecision.setText(percision);
		if (limit != null)
			txtLimit.setText(limit);
		if (wrapwidth != null)
			txtWrapWidth.setText(wrapwidth);

		setToggleButton(btnNoRulesHint, noruleshint);
		setToggleButton(btnNoClick, noclick);
		setToggleButton(btnNoHeaders, noheaders);
		setToggleButton(btnRadioList, radiolist);
		setToggleButton(btnEditable, editable);
		setToggleButton(btnRegexSearch, regexsearch);
		setToggleButton(btnQuotedOutput, quotedoutput);
		setToggleButton(btnAddOnTop, addontop);
		setToggleButton(btnTail, tail);
		setToggleButton(btnIecFormat, iecformat);
		setToggleButton(btnPrintAll, printall);
		setToggleButton(btnListen, listen);
		setToggleButton(btnMultiple, multiple);
		setToggleButton(btnCheckList, checklist);
		setToggleButton(btnNoSelection, noselection);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

