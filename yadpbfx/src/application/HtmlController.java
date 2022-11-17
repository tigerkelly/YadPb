package application;

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

public class HtmlController implements Initializable, DialogInterface {

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
    private ToggleButton btnBrowser;

    @FXML
    private ToggleButton btnPrintUri;

    @FXML
    private Button btnUrl;

    @FXML
    private ComboBox<String> cbEncoding;

    @FXML
    private TextField txtUriHandler;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtUserAgent;

    @FXML
    private TextField txtUserStyle;
    
    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
    }
    
    @FXML
    void doEncoding(ActionEvent event) {
    	yg.iniUpdate("econding", cbEncoding.getSelectionModel().getSelectedItem());
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

    @FXML
    void onUrl(ActionEvent event) {
    	yg.iniUpdate("url", txtUrl.getText());
    }

    @FXML
    void onUriHandler(KeyEvent event) {
    	yg.iniUpdate("urihandler", txtUriHandler.getText());
    }

    @FXML
    void onUserStyle(KeyEvent event) {
    	yg.iniUpdate("useratyle", txtUserStyle.getText());
    }

    @FXML
    void onuserAgent(KeyEvent event) {
    	yg.iniUpdate("useragent", txtUserAgent.getText());
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	cbEncoding.getItems().add("UTF-8");
		cbEncoding.getSelectionModel().select(0);
	}

	@Override
	public void updateDialog() {
		String url = yg.currIni.getString(yg.currDialog, "url");
		String uri = yg.currIni.getString(yg.currDialog, "uri");
		String useragent = yg.currIni.getString(yg.currDialog, "useragent");
		String userstyle = yg.currIni.getString(yg.currDialog, "userstyle");
		String encoding = yg.currIni.getString(yg.currDialog, "encoding");
		
		boolean printuri = yg.currIni.getBoolean(yg.currDialog, "printuri");
		boolean browser = yg.currIni.getBoolean(yg.currDialog, "browser");
		
		if (url != null)
			txtUrl.setText(url);
		if (uri != null)
			txtUriHandler.setText(uri);
		if (useragent != null)
			txtUserAgent.setText(useragent);
		if (userstyle != null)
			txtUserStyle.setText(userstyle);
		if (encoding != null)
			cbEncoding.getSelectionModel().select(encoding);
		
		setToggleButton(btnPrintUri, printuri);
		setToggleButton(btnBrowser, browser);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

