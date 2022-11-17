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

public class EntryController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private AnchorPane aPane;
    
    @FXML
    private ToggleButton btnCompletion;

    @FXML
    private ToggleButton btnEditable;

    @FXML
    private Button btnLeftIcon;

    @FXML
    private ToggleButton btnNumOutput;

    @FXML
    private ToggleButton btnNumeric;

    @FXML
    private Button btnRightIcon;

    @FXML
    private ComboBox<String> cbPrecision;

    @FXML
    private TextField txtEntryLabel;

    @FXML
    private TextField txtEntryText;

    @FXML
    private TextField txtLeftAction;

    @FXML
    private TextField txtLeftIcon;

    @FXML
    private TextField txtRightIcon;

    @FXML
    private TextField txtRightAction;
    
    @FXML
    private Button btnGeneral;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
    }

    @FXML
    void doLeftIcon(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Icon File");
    	File f = fileChooser.showOpenDialog(btnLeftIcon.getScene().getWindow());
    	
    	if (f != null) {
    		txtLeftIcon.setText(f.getAbsolutePath());
    		yg.iniUpdate("lefticon", f.getAbsolutePath());
    	}
    }

    @FXML
    void doPrecision(ActionEvent event) {
    	yg.iniUpdate("precision", cbPrecision.getSelectionModel().getSelectedItem());
    }

    @FXML
    void doRightIcon(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select Icon File");
    	File f = fileChooser.showOpenDialog(btnRightIcon.getScene().getWindow());
    	
    	if (f != null) {
    		txtRightIcon.setText(f.getAbsolutePath());
    		yg.iniUpdate("righticon", f.getAbsolutePath());
    	}
    }

    @FXML
    void onEntryLabel(KeyEvent event) {
    	yg.iniUpdate("entrylabel", txtEntryLabel.getText());
    }

    @FXML
    void onEntryText(KeyEvent event) {
    	yg.iniUpdate("entrytext", txtEntryText.getText());
    }
    
    @FXML
    void onLeftAction(KeyEvent event) {
    	yg.iniUpdate("leftaction", txtLeftAction.getText());
    }

    @FXML
    void onRightAction(KeyEvent event) {
    	yg.iniUpdate("rightaction", txtRightAction.getText());
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
    	cbPrecision.getItems().addAll("1", "2", "3", "4", "5", "6");
		cbPrecision.setValue("3");
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
	}

	@Override
	public void updateDialog() {
		String label = yg.currIni.getString(yg.currDialog, "label");
		String text = yg.currIni.getString(yg.currDialog, "text");
		String lefticon = yg.currIni.getString(yg.currDialog, "lefticon");
		String leftaction = yg.currIni.getString(yg.currDialog, "leftaction");
		String righticon = yg.currIni.getString(yg.currDialog, "righticon");
		String rightaction = yg.currIni.getString(yg.currDialog, "rightaction");
		String precision = yg.currIni.getString(yg.currDialog, "precision");
		
		boolean completion = yg.currIni.getBoolean(yg.currDialog, "completion");
		boolean numeric = yg.currIni.getBoolean(yg.currDialog, "numeric");
		boolean numoutput = yg.currIni.getBoolean(yg.currDialog, "numoutput");
		boolean editable = yg.currIni.getBoolean(yg.currDialog, "editable");
		
		if (label != null)
			txtEntryLabel.setText(label);
		if (text != null)
			txtEntryText.setText(text);
		if (lefticon != null)
			txtLeftIcon.setText(lefticon);
		if (leftaction != null)
			txtLeftAction.setText(leftaction);
		if (righticon != null)
			txtRightIcon.setText(righticon);
		if (rightaction != null)
			txtRightAction.setText(rightaction);
		if (precision != null)
			cbPrecision.setValue(precision);
		else
			cbPrecision.setValue("3");
		
		setToggleButton(btnCompletion, completion);
		setToggleButton(btnNumeric, numeric);
		setToggleButton(btnNumOutput, numoutput);
		setToggleButton(btnEditable, editable);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

