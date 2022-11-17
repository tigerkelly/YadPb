package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class ColorController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane aPane;

    @FXML
    private Label lblDialog;

    @FXML
    private ToggleButton btnAlpha;

    @FXML
    private ToggleButton btnExpand;

    @FXML
    private ToggleButton btnExtra;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnGtkPalette;

    @FXML
    private Button btnPalette;

    @FXML
    private ComboBox<String> cbMode;

    @FXML
    private ColorPicker cpInitColor;

    @FXML
    private TextField txtPalette;

    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doInitColor(ActionEvent event) {
    	String s = cpInitColor.getValue().toString().substring(2);
    	yg.iniUpdate("initcolor", "#" + s);
    }

    @FXML
    void doMode(ActionEvent event) {

    	yg.iniUpdate("mode", cbMode.getSelectionModel().getSelectedItem());
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
    void doPalette(ActionEvent event) {

    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtPalette.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("palette", txtPalette.getText());
        }
    }



    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbMode.getItems().addAll("Hex", "RGB");
		cbMode.getSelectionModel().select(0);

		javafx.scene.paint.Color c = javafx.scene.paint.Color.web("#00000000");
		cpInitColor.setValue(c);

		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		String palette = yg.currIni.getString(yg.currDialog, "palette");
		String mode = yg.currIni.getString(yg.currDialog, "mode");
		String initcolor = yg.currIni.getString(yg.currDialog, "initcolor");

		boolean gtkpalette = yg.currIni.getBoolean(yg.currDialog, "gtkpalette");
		boolean extra = yg.currIni.getBoolean(yg.currDialog, "extra");
		boolean alpha = yg.currIni.getBoolean(yg.currDialog, "alpha");
		boolean expand = yg.currIni.getBoolean(yg.currDialog, "expand");

		if (palette != null)
			txtPalette.setText(palette);
		if (mode != null)
			cbMode.setValue(mode);
		if (initcolor != null) {
			javafx.scene.paint.Color c = javafx.scene.paint.Color.web(initcolor);
			cpInitColor.setValue(c);
		}

		setToggleButton(btnGtkPalette, gtkpalette);
		setToggleButton(btnExtra, extra);
		setToggleButton(btnAlpha, alpha);
		setToggleButton(btnExpand, expand);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

