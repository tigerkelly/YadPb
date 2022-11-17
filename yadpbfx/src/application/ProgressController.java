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
import javafx.scene.layout.AnchorPane;

public class ProgressController implements Initializable, DialogInterface {

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
    private TextField txtLogHeight;

    @FXML
    private TextField txtLogText;

    @FXML
    private TextField txtPercentage;

    @FXML
    private TextField txtProgressText;

    @FXML
    private ToggleButton btnAutoClose;

    @FXML
    private ToggleButton btnAutoKill;

    @FXML
    private ToggleButton btnLogExpand;

    @FXML
    private ToggleButton btnLogOnTop;

    @FXML
    private ToggleButton btnPulsate;

    @FXML
    private ToggleButton btnRightToLeft;

    @FXML
    private ToggleButton btnEnableLog;


    @FXML
    void onLogHeight(KeyEvent event) {
    	yg.iniUpdate("logheight", txtLogHeight.getText());
    }

    @FXML
    void onLogText(KeyEvent event) {
    	yg.iniUpdate("logtext", txtLogText.getText());
    }

    @FXML
    void onPercentage(KeyEvent event) {
    	yg.iniUpdate("percentage", txtPercentage.getText());
    }

    @FXML
    void onProgressText(KeyEvent event) {
    	yg.iniUpdate("progresstext", txtProgressText.getText());
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
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@Override
	public void updateDialog() {
		String progresstext = yg.currIni.getString(yg.currDialog, "progresstext");
		String enablelogtext = yg.currIni.getString(yg.currDialog, "enablelogtext");

		String percentage = yg.currIni.getString(yg.currDialog, "percentage");
		String logheight = yg.currIni.getString(yg.currDialog, "logheight");

		boolean righttoleft = yg.currIni.getBoolean(yg.currDialog, "righttoleft");
		boolean autoclose = yg.currIni.getBoolean(yg.currDialog, "autoclose");
		boolean autokill = yg.currIni.getBoolean(yg.currDialog, "autokill");
		boolean pulsate = yg.currIni.getBoolean(yg.currDialog, "pulsate");
		boolean logontop = yg.currIni.getBoolean(yg.currDialog, "logontop");
		boolean expandlog = yg.currIni.getBoolean(yg.currDialog, "expandlog");
		boolean enablelog = yg.currIni.getBoolean(yg.currDialog, "enablelog");

		if (progresstext != null)
			txtProgressText.setText(progresstext);
		if (enablelogtext != null)
			txtLogText.setText(enablelogtext);
		if (percentage != null)
			txtPercentage.setText(percentage);
		if (logheight != null)
			txtLogHeight.setText(logheight);

		setToggleButton(btnRightToLeft, righttoleft);
		setToggleButton(btnAutoClose, autoclose);
		setToggleButton(btnAutoKill, autokill);
		setToggleButton(btnPulsate, pulsate);
		setToggleButton(btnLogOnTop, logontop);
		setToggleButton(btnLogExpand, expandlog);
		setToggleButton(btnEnableLog, enablelog);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

