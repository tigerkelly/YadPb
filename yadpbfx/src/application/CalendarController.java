package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private Button btnCreateDetail;

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
//    private boolean changesMade = false;

    @FXML
    void doDateFormat(ActionEvent event) {
    	yg.iniUpdate("dateformat", txtDateFormat.getText());
    }
    
    @FXML
    void doDefaultDate(ActionEvent event) {
    	yg.iniUpdate("defaultdate", dpDefaultDate.getValue().toString());
    }

    @FXML
    void doFileDialog(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Date Detail Files", "*.dd"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
    	File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
        if (selectedFile != null) {
        	txtDetailFile.setText(selectedFile.getAbsolutePath());
        	yg.iniUpdate("detailfile", txtDetailFile.getText());
        }
    }

    @FXML
    void doGeneral(ActionEvent event) {

    	yg.showGeneral(btnGeneral);
    }
    
    @FXML
    void doCreateDetail(ActionEvent event) {

    	try {
		    Stage stage = new Stage();
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailSettings.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);
		    stage.resizableProperty().setValue(Boolean.FALSE);

		    stage.setScene(new Scene(loader.load()));
		    DetailSettingsController controller = loader.<DetailSettingsController>getController();
		    if (txtDetailFile.getText().isEmpty() == false)
		    	controller.setDetailFile(txtDetailFile.getText());
		    else
		    	controller.setDetailFile(null);
		    
		    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		        @Override
		        public void handle(final WindowEvent event) {
		        	controller.showMessage("Please use 'Save' or 'Cancel' buttons.");
		            event.consume();	// So they can not use X button to close dialog
		        }
		    });
		    
		    Stage ps = (Stage) btnGeneral.getScene().getWindow();

			ChangeListener<Number> widthListener = (observable, oldValue, newValue) -> {
				double stageWidth = newValue.doubleValue();
				stage.setX(ps.getX() + ps.getWidth() / 2 - stageWidth / 2);
			};
			ChangeListener<Number> heightListener = (observable, oldValue, newValue) -> {
				double stageHeight = newValue.doubleValue();
				stage.setY(ps.getY() + ps.getHeight() / 2 - stageHeight / 2);
			};

			stage.widthProperty().addListener(widthListener);
			stage.heightProperty().addListener(heightListener);

			// Once the window is visible, remove the listeners
			stage.setOnShown(e2 -> {
				stage.widthProperty().removeListener(widthListener);
				stage.heightProperty().removeListener(heightListener);
			});
			
		    stage.showAndWait();
		    
		    String fn = controller.getDetailFile();
		    if (fn != null && fn.isEmpty() == false)
		    	txtDetailFile.setText(fn);
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
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
		
	}

}

