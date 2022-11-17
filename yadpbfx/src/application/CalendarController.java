package application;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class CalendarController implements Initializable, DialogInterface {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnArrow;

    @FXML
    private Button btnGeneral;

    @FXML
    private ToggleButton btnShowWeeks;

    @FXML
    private DatePicker dpDefaultDate;

    @FXML
    private Label lblDialog;

    @FXML
    private TextField txtDateFormat;

    @FXML
    private TextField txtDetailFile;

    @FXML
    private AnchorPane aPane;

    private YadGlobal yg = YadGlobal.getInstance();

    @FXML
    void doDateFormat(ActionEvent event) {
    	yg.iniUpdate("dateformat", txtDateFormat.getText());
    }

//    @FXML
//    void doDefaultDate(MouseEvent   event) {
//    	yg.iniUpdate("defaultdate", dpDefaultDate.getValue().toString().replaceAll("-", "/"));
//    }

    @FXML
    void doFileDialog(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtDetailFile.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("detailfile", txtDetailFile.getText());
        }
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

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	dpDefaultDate.setValue(LocalDate.now());
    	dpDefaultDate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
		     public void handle(ActionEvent event) {
		         yg.iniUpdate("defaultdate", dpDefaultDate.getValue().toString());
		     }
		});

    	aPane.getStylesheets().add(getClass().getResource("application.css").toString());

	}

	@Override
	public void updateDialog() {
		String detailfile = yg.currIni.getString(yg.currDialog, "detailfile");
		String dateformat = yg.currIni.getString(yg.currDialog, "dateformat");
		String defaultdate = yg.currIni.getString(yg.currDialog, "defaultdate");

		boolean showweeks = yg.currIni.getBoolean(yg.currDialog, "showweeks");

		if (detailfile != null)
			txtDetailFile.setText(detailfile);
		if (dateformat != null)
			txtDateFormat.setText(dateformat);
		if (defaultdate != null)
			dpDefaultDate.setValue(LocalDate.parse(defaultdate));

		setToggleButton(btnShowWeeks, showweeks);

	}

	@Override
	public void saveDialog() {

	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

