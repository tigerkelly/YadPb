package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class FontController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private TextField txtFontName;

    @FXML
    private TextField txtSeparator;
    
    @FXML
    private ToggleButton btnPreview;

    @FXML
    private ToggleButton btnQuoted;

    @FXML
    private ToggleButton btnSeparateOutput;

    
    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    void onFontName(KeyEvent event) {
    	yg.iniUpdate("fontname", txtFontName.getText());
    }

    @FXML
    void onSeparator(KeyEvent event) {
    	yg.iniUpdate("sep", txtSeparator.getText());
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
		
    	TxtLimitListener limitCount = new TxtLimitListener(txtSeparator, 1);
    	txtSeparator.textProperty().addListener(limitCount);
	}

	@Override
	public void updateDialog() {
		String fontname = yg.currIni.getString(yg.currDialog, "fontname");
		String sep = yg.currIni.getString(yg.currDialog, "sep");
		
		boolean preview = yg.currIni.getBoolean(yg.currDialog, "preview");
		boolean output = yg.currIni.getBoolean(yg.currDialog, "separateoutput");
		boolean quoted = yg.currIni.getBoolean(yg.currDialog, "quoted");
		
		if (fontname != null)
			txtFontName.setText(fontname);
		if (sep != null)
			txtSeparator.setText(sep);
		
		setToggleButton(btnPreview, preview);
		setToggleButton(btnSeparateOutput, output);
		setToggleButton(btnQuoted, quoted);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

