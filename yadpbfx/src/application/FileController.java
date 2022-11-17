package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.FileFilter;
import tbl.MimeFilter;

public class FileController implements Initializable, DialogInterface {

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
    private Button btnFilterAdd;

    @FXML
    private Button btnFilterDelete;
    
    @FXML
    private Button btnMimeAdd;

    @FXML
    private Button btnMimeDelete;
    
    @FXML
    private TableColumn<FileFilter, String> colFilterName;

    @FXML
    private TableColumn<FileFilter, String> colFilterFilter;

    @FXML
    private TableColumn<MimeFilter, String> colMimeFilter;

    @FXML
    private TableColumn<MimeFilter, String> colMimeName;

    @FXML
    private TableView<MimeFilter> tblMimeFilter;

    @FXML
    private TableView<FileFilter> tblFileFilter;

    @FXML
    private TextField txtFilterFilter;

    @FXML
    private TextField txtFilterName;
    
    @FXML
    private ToggleButton btnDirectory;

    @FXML
    private ToggleButton btnMultiple;

    @FXML
    private ToggleButton btnOverwrite;

    @FXML
    private ToggleButton btnQuoted;

    @FXML
    private ToggleButton btnSaveMode;

    @FXML
    private TextField txtFileName;

    @FXML
    private TextField txtOverwriteText;

    @FXML
    private TextField txtSeparator;
    
    @FXML
    private TextField txtMimeFilter;

    @FXML
    private TextField txtMimeName;
    
    private YadGlobal yg = YadGlobal.getInstance();
    private java.util.List<String> filterNames = new ArrayList<String>();
    private java.util.List<String> mimeNames = new ArrayList<String>();
    
    @FXML
    void doMimeAdd(ActionEvent event) {
    	String name = txtMimeName.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No mime name given.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
		String mime = txtMimeFilter.getText();
		if (mime == null || mime.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No mime given.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
//		System.out.println(name);
		
		if (mimeNames.contains(name) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + name + "' is already exists.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
		tblMimeFilter.getItems().add(new MimeFilter(name, mime));
		
		txtMimeName.setText("");
		txtMimeFilter.setText("");
		mimeNames.add(name);
		
		saveMimes();
    }
    
    @FXML
    void doMimeDelete(ActionEvent event) {
    	String name = txtMimeName.getText();
		
		if (name == null || name.length() == 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No mime name given.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
		int n = 0;
		for (String f : mimeNames) {
			if (f.equals(name) == true) {
				break;
			}
			n++;
		}
		
		if (n >= mimeNames.size())
			return;
		
		MimeFilter mf = tblMimeFilter.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + name + "'?");
		
		// Code to center dialog within parent.
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("mf " + mf);
				tblMimeFilter.getItems().remove(mf);
				mimeNames.remove(mf.getName());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtMimeName.setText("");
		txtMimeFilter.setText("");
		
		saveMimes();
    }

    @FXML
    void onOverwriteText(KeyEvent event) {
    	yg.iniUpdate("overwritetext", txtOverwriteText.getText());
    }

    @FXML
    void onFileName(KeyEvent event) {
    	yg.iniUpdate("filename", txtFileName.getText());
    }

    @FXML
    void onSeparator(KeyEvent event) {
    	yg.iniUpdate("sep", txtSeparator.getText());
    }
    
    @FXML
    void doFilterAdd(ActionEvent event) {
    	String name = txtFilterName.getText();
		if (name == null || name.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
		String filter = txtFilterFilter.getText();
		if (filter == null || filter.length() <= 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No filter given.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
//		System.out.println(name);
		
		if (filterNames.contains(name) == true) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("The field name '" + name + "' is already exists.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
		tblFileFilter.getItems().add(new FileFilter(name, filter));
		
		txtFilterName.setText("");
		txtFilterFilter.setText("");
		filterNames.add(name);
		
		saveFilters();
    }

    @FXML
    void doFilterDelete(ActionEvent event) {
    	String name = txtFilterName.getText();
		
		if (name == null || name.length() == 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("OK", ButtonData.OK_DONE);
			messageBox.getButtonTypes().setAll(yesButton);

			messageBox.setContentText("No name given.");
			
			// Code to center dialog within parent.
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
			
			messageBox.showAndWait();
			return;
		}
		
		int n = 0;
		for (String f : filterNames) {
			if (f.equals(name) == true) {
				break;
			}
			n++;
		}
		
		if (n >= filterNames.size())
			return;
		
		FileFilter ff = tblFileFilter.getItems().get(n);
		
		Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
		messageBox.setTitle("Warning");
		ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
		ButtonType noButton = new ButtonType("No", ButtonData.NO);
		messageBox.getButtonTypes().setAll(yesButton, noButton);
		
		messageBox.setContentText("Delete '" + name + "'?");
		
		// Code to center dialog within parent.
		Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
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
		
		messageBox.showAndWait().ifPresent(type -> {
			if (type.getButtonData() == ButtonData.YES) {
//				System.out.println("ff " + ff);
				tblFileFilter.getItems().remove(ff);
				filterNames.remove(ff.getName());
			} else if (type == ButtonType.NO) {
				return;
			}
		});
		
		txtFilterName.setText("");
		txtFilterFilter.setText("");
		
		saveFilters();
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
    void doGeneral(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	yg.showGeneral(btnGeneral);
    }
    
    @FXML
    void doMimeTypes(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Common Mime Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/sorted_mime_types.txt", 0.0, 0.0, true);
		    
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
		    
		    if (yg.currMime != null) {
		    	txtMimeFilter.setText(yg.currMime);
		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    @FXML
    void doFileFilter(ActionEvent event) {
//    	System.out.println(lblDialog.getText());
    	
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Common File Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/sorted_file_types.txt", 0.0, 0.0, true);
		    
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
		    
		    if (yg.currFilter != null) {
		    	txtFilterFilter.setText(yg.currFilter);
		    }
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	tblFileFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtFilterName.setText(newVal.getName());
		        txtFilterFilter.setText(newVal.getFilter());
		    }
		});
    	
    	colFilterName.setCellValueFactory(new PropertyValueFactory<FileFilter, String>("name"));
		colFilterFilter.setCellValueFactory(new PropertyValueFactory<FileFilter, String>("filter"));
		
		colFilterName.setCellFactory(TextFieldTableCell.forTableColumn());
		colFilterName.setOnEditCommit((TableColumn.CellEditEvent<FileFilter, String> t) -> {
	        ((FileFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
	        filterNames.remove(t.getOldValue());
	        filterNames.add(t.getNewValue());
	        saveMimes();
	    });
		
		colFilterFilter.setCellFactory(TextFieldTableCell.forTableColumn());
//		colFilterFilter.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setFilter(e.getNewValue()));
		colFilterFilter.setOnEditCommit((TableColumn.CellEditEvent<FileFilter, String> t) -> {
	        ((FileFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setFilter(t.getNewValue());
	        saveFilters();
	    });
		
		tblMimeFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal != null) {
		        txtMimeName.setText(newVal.getName());
		        txtMimeFilter.setText(newVal.getMime());
		    }
		});
    	
    	colMimeName.setCellValueFactory(new PropertyValueFactory<MimeFilter, String>("name"));
		colMimeFilter.setCellValueFactory(new PropertyValueFactory<MimeFilter, String>("mime"));
		
		colMimeName.setCellFactory(TextFieldTableCell.forTableColumn());
		colMimeName.setOnEditCommit((TableColumn.CellEditEvent<MimeFilter, String> t) -> {
	        ((MimeFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
	        mimeNames.remove(t.getOldValue());
	        mimeNames.add(t.getNewValue());
	        saveMimes();
	    });
		
		colMimeFilter.setCellFactory(TextFieldTableCell.forTableColumn());
//		colMimeFilter.setOnEditCommit(e->e.getTableView().getItems().get(e.getTablePosition().getRow()).setMime(e.getNewValue()));
		colMimeFilter.setOnEditCommit((TableColumn.CellEditEvent<MimeFilter, String> t) -> {
	        ((MimeFilter) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
	        saveMimes();
	    });
	}
    
    private void saveFilters() {
    	ObservableList<FileFilter> obs = tblFileFilter.getItems();
    
    	String filters = null;
    	for (FileFilter ff : obs) {
    		if (filters == null)
    			filters = ff.getName() + "|" + ff.getFilter();
    		else
    			filters += "," + ff.getName() + "|" + ff.getFilter();
    	}
    	
    	if (filters != null)
    		yg.iniUpdate("filefilters", filters);
    }
    
    private void saveMimes() {
    	ObservableList<MimeFilter> obs = tblMimeFilter.getItems();
        
    	String mimes = null;
    	for (MimeFilter mf : obs) {
    		if (mimes == null)
    			mimes = mf.getName() + "|" + mf.getMime();
    		else
    			mimes += "," + mf.getName() + "|" + mf.getMime();
    	}
    	
    	if (mimes != null)
    		yg.iniUpdate("mimefilters", mimes);
    }

	@Override
	public void updateDialog() {
		String filename = yg.currIni.getString(yg.currDialog, "filename");
		String sep = yg.currIni.getString(yg.currDialog, "sep");
		String owtext = yg.currIni.getString(yg.currDialog, "overwritetext");
		String filefilters = yg.currIni.getString(yg.currDialog, "filefilters");
		String mimefilters = yg.currIni.getString(yg.currDialog, "mimefilters");
		
		boolean multiple = yg.currIni.getBoolean(yg.currDialog, "multiple");
		boolean directory = yg.currIni.getBoolean(yg.currDialog, "directory");
		boolean savemode = yg.currIni.getBoolean(yg.currDialog, "savemode");
		boolean quoted = yg.currIni.getBoolean(yg.currDialog, "quoted");
		boolean overwrite = yg.currIni.getBoolean(yg.currDialog, "overwrite");
	
		if (filefilters != null) {
			filterNames.clear();
			
			String[] a = filefilters.split(",");
			
			for (String s : a) {
				String[] b = s.split("\\|");
				
				tblFileFilter.getItems().add(new FileFilter(b[0], b[1]));
				
				filterNames.add(b[0]);
			}
		}
		
	
		if (mimefilters != null) {
			mimeNames.clear();
			
			String[] a = mimefilters.split(",");
			
			for (String s : a) {
				String[] b = s.split("\\|");
				
				tblMimeFilter.getItems().add(new MimeFilter(b[0], b[1]));
				
				mimeNames.add(b[0]);
			}
		}
		
		if (filename != null)
			txtFileName.setText(filename);
		if (sep != null)
			txtSeparator.setText(sep);
		if (owtext != null)
			txtOverwriteText.setText(owtext);
		
		setToggleButton(btnMultiple, multiple);
		setToggleButton(btnDirectory, directory);
		setToggleButton(btnSaveMode, savemode);
		setToggleButton(btnQuoted, quoted);
		setToggleButton(btnOverwrite, overwrite);
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		// TODO Auto-generated method stub
		
	}

}

