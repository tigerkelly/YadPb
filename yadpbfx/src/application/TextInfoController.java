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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TextInfoController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    private AnchorPane aPane;

    @FXML
    private ToggleButton btnEditable;

    @FXML
    private Button btnFileName;

    @FXML
    private ToggleButton btnListen;

    @FXML
    private ToggleButton btnShowUri;

    @FXML
    private ToggleButton btnTail;

    @FXML
    private ToggleButton btnWrapText;

    @FXML
    private ComboBox<String> cbTheme;
    
    @FXML
    private TextField txtMargins;
    
    @FXML
    private ComboBox<String> cbJustify;

    @FXML
    private ColorPicker cpBgColor;

    @FXML
    private ColorPicker cpFgColor;

    @FXML
    private ColorPicker cpUriColor;

    @FXML
    private TextField txtFontName;
    
    @FXML
    private TextField txtFileName;

    @FXML
    void doBgColor(ActionEvent event) {
    	yg.iniUpdate("bgcolor", cpBgColor.getValue().toString());
    }

    @FXML
    void doFgColor(ActionEvent event) {
    	yg.iniUpdate("fgcolor", cpFgColor.getValue().toString());
    }

    @FXML
    void doFileName(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog((Stage)aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtFileName.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("filename", txtFileName.getText());
        }
    }

    @FXML
    void doTheme(ActionEvent event) {
    	yg.iniUpdate("theme", cbTheme.getValue().toString());
    }

    @FXML
    void doUriColor(ActionEvent event) {
    	yg.iniUpdate("uricolor", cpUriColor.getValue().toString());
    }

    @FXML
    void onFontName(KeyEvent event) {
    	yg.iniUpdate("fontname", txtFontName.getText());
    }
    
    @FXML
    void onMargins(KeyEvent event) {
    	yg.iniUpdate("margins", txtMargins.getText());
    }

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
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

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbJustify.getItems().addAll("Left", "Right", "Center", "Fill");
		cbJustify.getSelectionModel().select(0);
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		String filename = yg.currIni.getString(yg.currDialog, "filename");
		String fontname = yg.currIni.getString(yg.currDialog, "fontname");
		String justify = yg.currIni.getString(yg.currDialog, "justify");
		String margin = yg.currIni.getString(yg.currDialog, "margin");
		String theme = yg.currIni.getString(yg.currDialog, "theme");

		boolean editable = yg.currIni.getBoolean(yg.currDialog, "editable");
		boolean wraptext = yg.currIni.getBoolean(yg.currDialog, "wraptext");
		boolean tail = yg.currIni.getBoolean(yg.currDialog, "tail");
		boolean listen = yg.currIni.getBoolean(yg.currDialog, "listen");
		boolean showuri = yg.currIni.getBoolean(yg.currDialog, "showuri");
		
		if (filename != null)
			txtFileName.setText(filename);
		if (fontname != null)
			txtFontName.setText(fontname);
		if (theme != null)
			cbTheme.getSelectionModel().select(theme);
		if (justify != null)
			cbJustify.getSelectionModel().select(justify);
		if (margin != null)
			txtMargins.setText(margin);
		
		setToggleButton(btnEditable, editable);
		setToggleButton(btnWrapText, wraptext);
		setToggleButton(btnTail, tail);
		setToggleButton(btnListen, listen);
		setToggleButton(btnShowUri, showuri);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

