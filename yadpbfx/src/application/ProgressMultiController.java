package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import tbl.ColumnType;

public class ProgressMultiController implements Initializable, DialogInterface {

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
    private ToggleButton btnAutoClose;

    @FXML
    private ToggleButton btnAutoKill;

    @FXML
    private Button btnProgressAdd;

    @FXML
    private Button btnProgressDelete;

    @FXML
    private ToggleButton btnVertical;

    @FXML
    private ComboBox<String> cbAlign;

    @FXML
    private ComboBox<String> cbProgressType;

    @FXML
    private TableColumn<ColumnType, String> colName;

    @FXML
    private TableColumn<ColumnType, String> colType;

    @FXML
    private TableView<ColumnType> tblProgressBars;

    @FXML
    private TextField txtProgressName;

    @FXML
    private TextField txtWatchBar;

    @FXML
    void doAlign(ActionEvent event) {
    	yg.iniUpdate("align", cbAlign.getSelectionModel().getSelectedItem());
    }

    @FXML
    void doProgressAdd(ActionEvent event) {
    	String txt = txtProgressName.getText();
		if (txt == null || txt.length() <= 0)
			return;

//		System.out.println(txt);

		if (barNames.contains(txt)) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The progress bar name '" + txt + "' is already exists.");

			messageBox.showAndWait();
			return;
		}

		tblProgressBars.getItems().add(new ColumnType(txt, cbProgressType.getSelectionModel().getSelectedItem()));

		txtProgressName.setText("");
		cbProgressType.getSelectionModel().select(0);
		barNames.add(txt);

		saveBars();
    }

    @FXML
    void doProgressDelete(ActionEvent event) {
    	String txt = txtProgressName.getText();

		if (txt == null || txt.length() == 0)
			return;

		int n = 0;
		for (String f : barNames) {
			if (f.equals(txt)) {
				break;
			}
			n++;
		}

		if (n >= barNames.size())
			return;

		ColumnType ct = tblProgressBars.getItems().get(n);

		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);

		messageBox.setContentText("Delete '" + txt + "'?");

		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("ct " + ct);
				tblProgressBars.getItems().remove(ct);
				barNames.remove(ct.getText());
			} else if (type == ButtonType.NO) {
				return;
			}
		});

		txtProgressName.setText("");
		cbProgressType.getSelectionModel().select(0);

		saveBars();
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
    void onWatchBar(KeyEvent event) {
    	yg.iniUpdate("watchbar", txtWatchBar.getText());
    }

    private YadGlobal yg = YadGlobal.getInstance();

    private java.util.List<String> barNames = new ArrayList<>();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbProgressType.getItems().addAll("NORM", "RTL", "Pulse");
		cbProgressType.getSelectionModel().select(0);

		cbAlign.getItems().addAll("Left", "Center", "Right");
		cbAlign.getSelectionModel().select(0);

		tblProgressBars.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
//		        System.out.println(newVal);
		        txtProgressName.setText(newVal.getText());
		        cbProgressType.getSelectionModel().select(newVal.getType());
		    }
		});

		colName.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("text"));
		colType.setCellValueFactory(new PropertyValueFactory<ColumnType, String>("type"));

		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setText(t.getNewValue());
	        barNames.remove(t.getOldValue());
	        barNames.add(t.getNewValue());
	    });

		colType.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList("NORM", "RTL", "PULSE")));

		colType.setOnEditCommit((TableColumn.CellEditEvent<ColumnType, String> t) -> {
	        t.getTableView().getItems().get(t.getTablePosition().getRow()).setType(t.getNewValue());
	    });

		tblProgressBars.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtProgressName.setText(newVal.getText());
		        cbProgressType.getSelectionModel().select(newVal.getType());
		    }
		});
	}

    private void saveBars() {
    	ObservableList<ColumnType> obs = tblProgressBars.getItems();

    	String bars = null;
    	for (ColumnType ct : obs) {
    		if (bars == null)
    			bars = ct.getText() + ":" + ct.getType();
    		else
    			bars += "," + ct.getText() + ":" + ct.getType();
    	}

    	if (bars != null)
    		yg.iniUpdate("bars", bars);
    }

	@Override
	public void updateDialog() {
		String bars = yg.currIni.getString(yg.currDialog, "bars");

		if (bars != null) {
			barNames.clear();

			String[] a = bars.split(",");

			for (String s : a) {
				String[] b = s.split(":");

				tblProgressBars.getItems().add(new ColumnType(b[0], b[1]));

				barNames.add(b[0]);
			}

			txtProgressName.setText("");
			cbProgressType.getSelectionModel().select(0);
		}

		String align = yg.currIni.getString(yg.currDialog, "align");
		String watchbar = yg.currIni.getString(yg.currDialog, "watchbar");

		boolean vertical = yg.currIni.getBoolean(yg.currDialog, "vertical");
		boolean autoclose = yg.currIni.getBoolean(yg.currDialog, "autoclose");
		boolean autokill = yg.currIni.getBoolean(yg.currDialog, "autokill");

		if (align != null)
			cbAlign.getSelectionModel().select(align);
		if (watchbar != null)
			txtWatchBar.setText(watchbar);
		setToggleButton(btnVertical, vertical);
		setToggleButton(btnAutoClose, autoclose);
		setToggleButton(btnAutoKill, autokill);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

