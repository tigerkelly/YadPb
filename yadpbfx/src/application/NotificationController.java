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

public class NotificationController implements Initializable, DialogInterface{

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
    private ToggleButton btnHidden;

    @FXML
    private ToggleButton btnListen;

    @FXML
    private ToggleButton btnNoMiddle;

    @FXML
    private TextField txtCommand;

    @FXML
    private TextField txtItemSep;

    @FXML
    private TextField txtMenu;

    @FXML
    private TextField txtSep;

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
    void onCommand(KeyEvent event) {
    	yg.iniUpdate("command", txtCommand.getText());
    }

    @FXML
    void onItemSep(KeyEvent event) {
    	yg.iniUpdate("isep", txtItemSep.getText());
    }

    @FXML
    void onMenu(KeyEvent event) {
    	yg.iniUpdate("menu", txtMenu.getText());
    }

    @FXML
    void onSep(KeyEvent event) {
    	yg.iniUpdate("sep", txtSep.getText());
    }

    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());

    	yg.showGeneral(btnGeneral);
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	TxtLimitListener limitCount = new TxtLimitListener(txtSep, 1);
    	txtSep.textProperty().addListener(limitCount);

    	TxtLimitListener limitCount2 = new TxtLimitListener(txtItemSep, 1);
    	txtItemSep.textProperty().addListener(limitCount2);
	}

	@Override
	public void updateDialog() {
		String sep = yg.currIni.getString(yg.currDialog, "sep");
		String isep = yg.currIni.getString(yg.currDialog, "isep");
		String menu = yg.currIni.getString(yg.currDialog, "menu");

		boolean listen = yg.currIni.getBoolean(yg.currDialog, "listen");
		boolean nomiddle = yg.currIni.getBoolean(yg.currDialog, "nomiddle");
		boolean hidden = yg.currIni.getBoolean(yg.currDialog, "hidden");

		if (sep != null)
			txtSep.setText(sep);
		if (isep != null)
			txtItemSep.setText(isep);
		if (menu != null)
			txtMenu.setText(menu);

		setToggleButton(btnListen, listen);
		setToggleButton(btnNoMiddle, nomiddle);
		setToggleButton(btnHidden, hidden);
	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		
	}

}

