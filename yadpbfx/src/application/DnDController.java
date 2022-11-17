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

public class DnDController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private Label lblDialog;
    
    @FXML
    private Button btnGeneral;
    
    @FXML
    private ToggleButton btnTooltip;

    @FXML
    private TextField txtCommand;

    @FXML
    private TextField txtExitOnDrop;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
    }
    @FXML
    void doCommand(KeyEvent event) {
    	yg.iniUpdate("command", txtCommand.getText());
    }

    @FXML
    void doExitOnDrop(KeyEvent event) {
    	yg.iniUpdate("exitondrop", txtExitOnDrop.getText());
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
		
	}

	@Override
	public void updateDialog() {
		String command = yg.currIni.getString(yg.currDialog, "command");
		String exitondrop = yg.currIni.getString(yg.currDialog, "exitondrop");
		
		boolean tooltip = yg.currIni.getBoolean(yg.currDialog, "tooltip");
		
		setToggleButton(btnTooltip, tooltip);
		
		if (command != null)
			txtCommand.setText(command);
		if (exitondrop != null)
			txtExitOnDrop.setText(exitondrop);
	}

	@Override
	public void saveDialog() {
		
	}
	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

