/*
 * This file is part of YadPb.
 *
 * YadPb is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * YadPb is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with YadPb. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2022-2023, Kelly Wiles <rkwiles@twc.com>
 */

package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.DateDetail;

public class DetailSettingsController implements Initializable {

	@FXML
    private AnchorPane aPane;
	
	@FXML
	private Label lblMessage;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnDetailFile;
    
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnNewDetail;
    
    @FXML
    private TableView<DateDetail> tblDateDetail;


    @FXML
    private TableColumn<DateDetail, String> colDate;

    @FXML
    private TableColumn<DateDetail, String> colDetail;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextArea taDetail;
    
    @FXML
    private TextField txtDetailFile;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private String detailFile = null;
    private String lastDate = null;
    private ObservableList<DateDetail> dd = null;

    @FXML
    void doAdd(ActionEvent event) {
    	String date = getFormattedDate();
    	String detail = taDetail.getText();
    	
    	if (date == null || date.isEmpty() == true || detail == null || detail.isEmpty() == true) {
    		return;
    	}
    	
//    	ObservableList<DateDetail> dd = tblDateDetail.getItems();
    	boolean found = false;
    	for (DateDetail d : dd) {
    		if (d.getDate().equals(date) == true) {
    			found = true;
    			break;
    		}
    	}
    	
    	if (found == false) {
    		dd.add(new DateDetail(date, detail));
    		dpDate.setValue(null);
	    	taDetail.setText("");
	    	lastDate = null;
    	} else {
    		Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");

			messageBox.setContentText("The detail '" + date + "' already exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnCancel.getScene().getWindow();

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
			messageBox.showAndWait();
    	}
    }
    
    @FXML
    void doUpdate(ActionEvent event) {
    	String date = getFormattedDate();
    	String detail = taDetail.getText();
    	
    	if (lastDate == null || lastDate.isEmpty() == true)
    		return;
    	
    	if (date == null || date.isEmpty() == true || detail == null || detail.isEmpty() == true) {
    		return;
    	}
    	
//    	ObservableList<DateDetail> dd = tblDateDetail.getItems();
    	int found = 0;
    	for (DateDetail d : dd) {
    		if (d.getDate().equals(lastDate) == true) {
    			break;
    		}
    		found++;
    	}
    	if (found >= 0) {
    		dd.remove(found);
    		dd.add(new DateDetail(date, detail));
    		
    		dpDate.setValue(null);
	    	taDetail.setText("");
    	} else {
    		Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");

			messageBox.setContentText("The detail '" + date + "' already exists.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnCancel.getScene().getWindow();

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
			messageBox.showAndWait();
    	}
    }
    
    @FXML
    void doNewDetail(ActionEvent event) {
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Detail File for " + yg.currProject);

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailName.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    stage.hide();
		    DetailNameController dnc = loader.getController();
		    
		    // Code to center dialog within parent.
		    Stage ps = (Stage) btnCancel.getScene().getWindow();

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
		    
		    String fn = dnc.getDetailName();
		    if (fn != null && fn.isEmpty() == false) {
		    	txtDetailFile.setText(fn);
		    	yg.iniUpdate("detailfile", txtDetailFile.getText());

				loadTable();
		    }

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void doCancel(ActionEvent event) {
    	detailFile = null;
    	
    	Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doDelete(ActionEvent event) {
    	String date = getFormattedDate();
    	if (date == null || date.isEmpty() == true) {
    		return;
    	}
    	
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
		stage.hide();
		
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);

		messageBox.setContentText("Delete '" + date + "' detail?\n*** This cannot be recovered. ***");
		
		// Code to center dialog within parent.
		Stage ps = (Stage) btnCancel.getScene().getWindow();

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

		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				ObservableList<DateDetail> dd = tblDateDetail.getItems();
		    	for (DateDetail d : dd) {
		    		if (d.getDate().equals(date) == true) {
		    			dd.remove(d);
		    			break;
		    		}
		    	}
		    	
		    	dpDate.setValue(null);
		    	taDetail.setText("");
				lastDate = null;
			} else {
				return;
			}
		});
    }

    @FXML
    void doSave(ActionEvent event) {
    	String fileName = txtDetailFile.getText();
    	if (fileName == null || fileName.isEmpty() == true) {
    		Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");

			messageBox.setContentText("No detail filename give.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) btnCancel.getScene().getWindow();

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
			messageBox.showAndWait();
			
			return;
    	}
    	
    	BufferedWriter writer = null;
    	try {
			writer = new BufferedWriter(new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	for (DateDetail d : dd) {
    		try {
				writer.write(d.getDate() + " " + d.getDetail() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	detailFile = fileName;
    	
    	Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void doDetailFile(ActionEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Date Detail Files", "*.dd"),
				new FileChooser.ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(aPane.getScene().getWindow());
		if (selectedFile != null) {
			txtDetailFile.setText(selectedFile.getAbsolutePath());
			yg.iniUpdate("detailfile", txtDetailFile.getText());

			loadTable();
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tblDateDetail.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        dpDate.setValue(LocalDate.parse(newVal.getDate()));
		        taDetail.setText(newVal.getDetail());
		        lastDate = newVal.getDate();
		        
		    }
		});
		
		dd = tblDateDetail.getItems();
		tblDateDetail.getSortOrder().add(colDate);
		
		colDate.setCellValueFactory(new PropertyValueFactory<DateDetail, String>("date"));
		colDetail.setCellValueFactory(new PropertyValueFactory<DateDetail, String>("detail"));
		        
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());
		
	}
	
	private String getFormattedDate() {
		LocalDate selectedDate = dpDate.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return selectedDate.format(formatter);
	}
	
	public void setDetailFile(String detailFile) {
		if (detailFile != null) {
			txtDetailFile.setText(detailFile);
		
			loadTable();
		}
	}
	
	public String getDetailFile() {
		return detailFile;
	}
	
	public void showMessage(String msg) {
		lblMessage.setText(msg);
		
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	    Runnable clrMessage = new Runnable() {
	        public void run() {
	             Platform.runLater(new Runnable() {
	                 @Override public void run() {
	                     lblMessage.setText("");
	                     executor.shutdown();		// without this program hangs on exit.
	                 }
	             });
	        }
	    };
	    executor.schedule(clrMessage, 2, TimeUnit.SECONDS);
	}
	
	private void loadTable() {
		File f = new File(txtDetailFile.getText());
		
		if (f.exists() == false) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//		System.out.println(txtDetailFile.getText());
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			tblDateDetail.getItems().clear();
//			ObservableList<DateDetail> dd = tblDateDetail.getItems();

			String line = null;
			while ((line = br.readLine()) != null) {
				int x = line.indexOf(" ");
				if (x != -1) {
					String p1 = line.substring(0, x);
					String p2 = line.substring(x+1);
					dd.add(new DateDetail(p1, p2));
				}
				
			}
			br.close();
		} catch (IOException ex2) {
			ex2.printStackTrace();
		}
	}

}
