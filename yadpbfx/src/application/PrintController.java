package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class PrintController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblDialog;

    @FXML
    private AnchorPane aPane;

    @FXML
    private ComboBox<String> cbPrintType;

    @FXML
    private ToggleButton btnAddPreview;

    @FXML
    private Button btnFileName;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnHeaders;

    @FXML
    private TextField txtFileName;

    @FXML
    private TextField txtFontName;

    @FXML
    void doFileName(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtFileName.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("filename", txtFileName.getText());
        }
    }

    @FXML
    void doPrintType(ActionEvent event) {
    	yg.iniUpdate("printtype", cbPrintType.getSelectionModel().getSelectedItem());
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
    void onFontName(KeyEvent event) {
    	yg.iniUpdate("fontname", txtFontName.getText());
    }


    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbPrintType.getItems().addAll("Text", "Image", "Raw");
		cbPrintType.getSelectionModel().select("Text");

		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		String filename = yg.currIni.getString(yg.currDialog, "filename");
		String fontname = yg.currIni.getString(yg.currDialog, "fontname");
		String printtype = yg.currIni.getString(yg.currDialog, "printtype");

		boolean headers = yg.currIni.getBoolean(yg.currDialog, "headers");
		boolean addpreview = yg.currIni.getBoolean(yg.currDialog, "addpreview");

		if (filename != null)
			txtFileName.setText(filename);
		if (fontname != null)
			txtFontName.setText(fontname);
		if (printtype != null)
			cbPrintType.getSelectionModel().select(printtype);

		setToggleButton(btnHeaders, headers);
		setToggleButton(btnAddPreview, addpreview);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

