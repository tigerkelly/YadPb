package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.rkw.IniFile;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tbl.DialogType;
import tbl.Projectx;

public class YadPbController implements Initializable, DialogInterface {

//	private VBox dialogPane = null;
	private YadGlobal yg = YadGlobal.getInstance();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private VBox calendar;

	@FXML
	private HBox hbox;
	
	@FXML
	private Label lblTitle;

	@FXML
	private AnchorPane aPane;

	@FXML
	private ListView<String> lstProjects;

	@FXML
	private ListView<String> lstDialogs;

	@FXML
	private Label lblVersion;
	
	@FXML
	private TextArea taData;

	@FXML
	private StackPane stackPane;

	@FXML
    void onAbout(ActionEvent event) {
		try {
		    Stage stage = new Stage();
		    stage.setTitle("Common File Types");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/about.txt", 300.0, 225.0, false);
		    
		    Stage ps = (Stage) aPane.getScene().getWindow();

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
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
	@FXML
    void onHelp(ActionEvent event) {

    }
	
	@FXML
    void onYadManual(ActionEvent event) {
		try {
		    Stage stage = new Stage();
		    stage.setTitle("Yad Man Page");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/yad_manual.txt", 1130.0, 900.0, false);
		    
		    Stage ps = (Stage) aPane.getScene().getWindow();

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
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
	
	@FXML
    void onYadCmd(ActionEvent event) {
		try {
		    Stage stage = new Stage();
		    stage.setTitle("Yad Command line");
		    
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("FileViewer.fxml"));
		    
		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    FileViewerController controller = loader.<FileViewerController>getController();
		    controller.setFileName("txts/yad_cmd.txt", 1130.0, 900.0, false);
		    
		    Stage ps = (Stage) aPane.getScene().getWindow();

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
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void onBackups(ActionEvent event) {
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Backups");

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("Backups.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    stage.hide();
		    
		    // Code to center dialog within parent.
		    Stage ps = (Stage) lblTitle.getScene().getWindow();

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

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void onMouseDrag(MouseEvent event) {

    }

    @FXML
    void onNewProject(ActionEvent event) {
    	doNewProject();
    }

    @FXML
    void onOpenProject(ActionEvent event) {
    	doOpenProject();
    }

    @FXML
    void onQuit(ActionEvent event) {
    	Stage stage = (Stage) aPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onSettings(ActionEvent event) {
    	try {
		    Stage stage = new Stage();
		    stage.setTitle("Settings");

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    stage.hide();
		    
		    // Code to center dialog within parent.
		    Stage ps = (Stage) lblTitle.getScene().getWindow();

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

		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

	public void setDialogNav(Node node) {
		stackPane.getChildren().setAll(node);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		lblVersion.setText(yg.yadPbVersion);
		
		aPane.getStylesheets().add(getClass().getResource("application.css").toString());

		yg.itemProjects = FXCollections.observableArrayList();
		lstProjects.setItems(yg.itemProjects);
		yg.itemDialogs = FXCollections.observableArrayList();
		lstDialogs.setItems(yg.itemDialogs);

		lstDialogs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
//				System.out.println("new " + newVal + " old " + oldVal);
				if (newVal != null) {
					String[] a = newVal.split(",");
					yg.currDialog = a[0];
					yg.loadDialog(newVal);
				}
			}
		});

		lstProjects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldVal, String newVal) {
//				System.out.println("new " + newVal + " old " + oldVal);
				if (newVal != null && newVal.equals(yg.currProject) == false) {
					yg.currIni = yg.prjList.get(newVal);
					yg.currBackupIni = yg.prjBackupList.get(newVal);
					yg.currProject = newVal;

					loadProject(newVal);

					yg.clearStack();
				}
			}
		});
		
		ContextMenu cm = new ContextMenu();
		
		MenuItem mProjectTitle = new MenuItem("Project Menu");
		mProjectTitle.setDisable(true);
		
		MenuItem mOpenProject = new MenuItem("Open Project");
		mOpenProject.setOnAction((ActionEvent e) -> {
			
			doOpenProject();
		});
		MenuItem mNewProject = new MenuItem("New Project");
		
		mNewProject.setOnAction((ActionEvent e) -> {
			
			doNewProject();
			
		});
		MenuItem mPlugs = new MenuItem("Manage Plugs");
		
		mPlugs.setOnAction((ActionEvent e) -> {
			
			doManagePlugs();
			
		});

		MenuItem mCreateScript = new MenuItem("Show Commands");
		mCreateScript.setOnAction((ActionEvent e) -> {
			ProjectScript ps = new ProjectScript();
			String dlg = ps.createAllDialogs(yg.currIni);

			if (dlg != null && dlg.isEmpty() == false) {
				
				yg.centerScene(lstProjects, "OpenScript.fxml", "Project Comamnds for", dlg);
			}
			
		});
		
		MenuItem mProjectData = new MenuItem("Project Data");
		mProjectData.setOnAction((ActionEvent e) -> {
			String data = yg.currIni.stringFile(null);
			
			if (data != null && data.isEmpty() == false) {
				data = data.replaceAll("\\t", "    ");
				yg.centerScene(lstProjects, "OpenData.fxml", "Project Data for", data);
			}
			
		});
		
		
		
		MenuItem mDeleteProject = new MenuItem("Delete Project");
		mDeleteProject.setOnAction((ActionEvent e) -> {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonData.NO);
			messageBox.getButtonTypes().setAll(yesButton, noButton);
			
			String prj = lstProjects.getSelectionModel().getSelectedItem();

			messageBox.setContentText("Delete '" + prj + "' project?\n*** This cannot be recovered. ***");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) lblTitle.getScene().getWindow();

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
					File f = new File(yg.workDir + File.separator + prj + ".yadpb");
					if (f.exists() == true) {
						f.delete();
					}
					
					lstProjects.getItems().remove(prj);
					yg.prjList.remove(prj);
					yg.prjBackupList.remove(prj);
					
					yg.sysIni.removeValuePair("Projects", prj);
					yg.sysIni.writeFile(true);
					
					lstDialogs.getItems().clear();
					
				} else {
					return;
				}
			});
		});
		
		SeparatorMenuItem sep = new SeparatorMenuItem();

		cm.getItems().addAll(mProjectTitle, sep, mOpenProject, mNewProject, mCreateScript, mPlugs, mProjectData, mDeleteProject);
		
		cm.setOnShowing(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				int idx = lstProjects.getSelectionModel().getSelectedIndex();
				if (idx < 0) {
					mCreateScript.setDisable(true);
					mProjectData.setDisable(true);
					mDeleteProject.setDisable(true);
					mPlugs.setDisable(true);
				} else {
					mCreateScript.setDisable(false);
					mProjectData.setDisable(false);
					mDeleteProject.setDisable(false);
					mPlugs.setDisable(false);
				}
			}
		});

		lstProjects.setContextMenu(cm);

		ContextMenu cm2 = new ContextMenu();
		MenuItem mDialogTitle = new MenuItem("Dialog Menu");
		mDialogTitle.setDisable(true);
		
		MenuItem mNewDialog = new MenuItem("New Dialog");
		mNewDialog.setOnAction((ActionEvent e) -> {
			try {
			    Stage stage = new Stage();
			    stage.setTitle("New Dialog for " + yg.currProject);

			    FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogNew.fxml"));

			    stage.initModality(Modality.APPLICATION_MODAL);

			    stage.setScene(new Scene(loader.load()));
			    stage.hide();
			    DialogNewController dnc = loader.getController();
			    
			    dnc.setData(yg.currProject);
			    
			    // Code to center dialog within parent.
			    Stage ps = (Stage) lblTitle.getScene().getWindow();

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
			    
			    DialogType dt = dnc.getDialog();
			    if (dt != null) {
					
					java.util.List<String> lst = new ArrayList<String>();
					
					for (String s : lstDialogs.getItems())
						lst.add(s);
					
					lst.add(dt.getText() + "," + dt.getType());
					
					lstDialogs.getItems().clear();
					
					for (String s : lst)
						lstDialogs.getItems().add(s);
					
					String name = dt.getText();
					String type = dt.getType();
					
					if (yg.currIni.sectionExists(name) == false) {
						yg.currIni.addSection(name);
						yg.currIni.addSection(name + "-General");
						
						yg.currIni.addValuePair(name, "type", type);
						
						Object[] keys = yg.defaults.getSectionKeys("General");
						
						if (keys != null && keys.length > 0) {
							// Add common general settings.
							for (Object o : keys) {
								String k = (String)o;
								String v = yg.defaults.getString("General", k);
								yg.currIni.addValuePair(name + "-General", k, v);
							}
						}
						
						keys = yg.defaults.getSectionKeys(type);
						if (keys != null && keys.length > 0) {
							for (Object o : keys) {
								String k = (String)o;
								String v = yg.defaults.getString(type, k);
								yg.currIni.addValuePair(name, k, v);
							}
						}
						
						keys = yg.defaults.getSectionKeys(type + "-General");
						if (keys != null && keys.length > 0) {
							// Add general settings per dialog type.
							for (Object o : keys) {
								String k = (String)o;
								String v = yg.defaults.getString(type + "-General", k);
								yg.currIni.addValuePair(name + "-General", k, v);
							}
						}
					}
					
					lstDialogs.getSelectionModel().select(dt.getText() + "," + dt.getType());
			    }

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});
		
		MenuItem mDeleteDialog = new MenuItem("Delete Dialog");
		mDeleteDialog.setOnAction((ActionEvent e) -> {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonData.NO);
			messageBox.getButtonTypes().setAll(yesButton, noButton);

			String dialog = lstDialogs.getSelectionModel().getSelectedItem();

			messageBox.setContentText("Delete '" + dialog + "' dialog?\n*** Use project managent to recover. ***");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) lblTitle.getScene().getWindow();

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
					String[] a = dialog.split(",");
					yg.currIni.removeSection(a[0]);
					yg.currIni.removeSection(a[0] + "-General");
					
					int idx = lstDialogs.getSelectionModel().getSelectedIndex();
					lstDialogs.getItems().remove(idx);
					
				} else {
					return;
				}
			});
		});
		
		MenuItem mDialogScript = new MenuItem("Show Command");
		mDialogScript.setOnAction((ActionEvent e) -> {
			
			if (yg.yad == null) {
				Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
				
				Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
				stage.hide();
				
				messageBox.setTitle("Warning");
//				ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
//				messageBox.getDialogPane().getButtonTypes().add(type);

				messageBox.setContentText("Use the Settings screen to add yad executable.\nIt was not found during startup.");
				
				// Code to center dialog within parent.
				Stage ps = (Stage) lblTitle.getScene().getWindow();

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
			
			ProjectScript ps = new ProjectScript();
			
			String type = yg.currIni.getString(yg.currDialog, "type");
			
			if (type.equals("Notebook") == true || type.equals("Paned") == true) {
				String key = yg.currIni.getString(yg.currDialog, "key");
				if (key != null && key.isEmpty() == false) {
					// Search for the linked dialogs for this Notebook or Paned dialog.
					Object[] secs = yg.currIni.getSectionNames();
				
					for (Object sec : secs) {
						String s = (String)sec;
						if (s.endsWith("-General") == true || s.equals("YadPb-Plugs") == true)
							continue;
						
						if (s.equals(yg.currDialog) == true)
							continue;
						
						String plug = yg.currIni.getString(sec + "-General", "plug");
						if (plug == null || plug.isEmpty() == true || plug.equals(key) == false)
							continue;
						
						String txt = ps.createDialog(yg.currIni, s);
						if (txt != null && txt.isEmpty() == false)
							taData.appendText(txt + "\n\n");
					}
				}
			}
			
			String dlg = ps.createDialog(yg.currIni, yg.currDialog);
			
//			System.out.println("dlg " + dlg);
			
			if (dlg != null && dlg.isEmpty() == false)
				taData.appendText(dlg + "\n\n");
			
		});
		
		MenuItem mRunDialog = new MenuItem("Run Dialog");
		mRunDialog.setOnAction((ActionEvent e) -> {
			
			if (yg.yad == null) {
				Alert messageBox = new Alert(Alert.AlertType.INFORMATION);
				
				Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
				stage.hide();
				
				messageBox.setTitle("Warning");
//				ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
//				messageBox.getDialogPane().getButtonTypes().add(type);

				messageBox.setContentText("Use the Settings screen to add yad executable.\nIt was not found during startup.");
				
				// Code to center dialog within parent.
				Stage ps = (Stage) lblTitle.getScene().getWindow();

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
			
			ProjectScript ps = new ProjectScript();
			String dlg = ps.createDialog(yg.currIni, yg.currDialog);
			
//			System.out.println("dlg " + dlg);
			
			if (dlg != null && dlg.isEmpty() == false)
				taData.appendText(dlg + "\n\n");
			
			runDialog(dlg);
		});

		SeparatorMenuItem sep2 = new SeparatorMenuItem();
		SeparatorMenuItem sep3 = new SeparatorMenuItem();
		SeparatorMenuItem sep4 = new SeparatorMenuItem();
		
		cm2.getItems().addAll(mDialogTitle, sep2, mNewDialog, mDeleteDialog, sep3, mDialogScript, sep4, mRunDialog);
		
		cm2.setOnShowing(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent e) {
				if (lstProjects.getSelectionModel().getSelectedIndex() >= 0) {
					if (lstDialogs.getSelectionModel().getSelectedIndex() >= 0)
						mDeleteDialog.setDisable(false);
					else
						mDeleteDialog.setDisable(true);
					mNewDialog.setDisable(false);
					mRunDialog.setDisable(false);
				} else {
					mNewDialog.setDisable(true);
					mDeleteDialog.setDisable(true);
					mRunDialog.setDisable(true);
				}
			}
		});

		lstDialogs.setContextMenu(cm2);

		yg.dialogStackPane = stackPane;
		stackPane.prefWidthProperty().bind(aPane.widthProperty());

	}

	private int loadProject(String prjName) {

		File f = new File(yg.workDir + File.separator + prjName + ".yadpb");
		if (!f.exists()) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			stage.hide();
			
			messageBox.setTitle("Warning");
			ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
			messageBox.getDialogPane().getButtonTypes().add(type);

			messageBox.setContentText("The project '" + prjName + "' cannot be opened.");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) lblTitle.getScene().getWindow();

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
			return 1;
		}
		if (yg.sysIni.getBoolean("System", "backup")) {
			File b = new File(yg.backupDir.getAbsolutePath() + File.separator + f.getName());
			if (!yg.prjList.containsKey(prjName)) {
				yg.copyFile(f, b);
			}
		}

		if (yg.prjList.containsKey(prjName)) {
			yg.currIni = yg.prjList.get(prjName);
		} else {
			yg.prjList.put(prjName, new IniFile(f.getAbsolutePath()));
			yg.prjBackupList.put(prjName, new IniFile(f.getAbsolutePath()));

			yg.currIni = yg.prjList.get(prjName);
		}

		if (yg.currIni == null) {
			return 1;
		}

		processProject();

		return 0;
	}

	private int processProject() {

		yg.itemDialogs.clear();

		Object[] obj = yg.currIni.getSectionNames();

		for (Object o : obj) {
			String s = (String)o;

			if (s.endsWith("-General"))
				continue;

			String type = yg.currIni.getString(s, "type");
			yg.itemDialogs.add(s + "," + type);
		}

		return 0;
	}
	
	private void doNewProject() {
		try {
		    Stage stage = new Stage();
		    stage.setTitle("New Project");

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectNew.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    stage.hide();
		    ProjectNewController pnc = loader.getController();
		    
		    // Code to center dialog within parent.
		    Stage ps = (Stage) lblTitle.getScene().getWindow();

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
		    
		    Projectx prj = pnc.getProject();
		    if (prj != null) {
		    	File f = new File(yg.workDir.getAbsolutePath() + File.separator + prj.getProject() + ".yadpb");
				
				Calendar c = Calendar.getInstance();
				String createDate = String.format("%d/%02d/%02d %02d:%02d:%02d",
						c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
						c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
				
				try {
					f.createNewFile();
					FileWriter myWriter = new FileWriter(f.getAbsolutePath());
					myWriter.write("# Created by the YadPb program.\n# For project '" + prj.getProject() + "'\n# Description: " + prj.getDescription() + "\n# Created: " + createDate);
				    myWriter.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				File b = new File(yg.workDir + File.separator + "backups" + File.separator + f.getName());
				try {
					b.createNewFile();
					FileWriter myWriter = new FileWriter(b.getAbsolutePath());
					myWriter.write("# Created by the YadPb program.\n# For project '" + prj.getProject() + "'\n# Description: " + prj.getDescription() + "\n# Created: " + createDate);
				    myWriter.close();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
				
				yg.sysIni.addValuePair("Projects", prj.getProject(), prj.getDescription());
				
				yg.sysIni.writeFile(true);
				
				yg.prjList.put(prj.getProject(), new IniFile(f.getAbsolutePath()));
				yg.prjBackupList.put(prj.getProject(), new IniFile(b.getAbsolutePath()));
				
				yg.currIni = yg.prjList.get(prj.getProject());
				yg.currBackupIni = yg.prjBackupList.get(prj.getProject());
				
				yg.currProject = prj.getProject();
				
				lstProjects.getItems().add(prj.getProject());
				
				ObservableList<String> prjs = lstProjects.getItems();
				
			
				for (String s : prjs) {
					if (s.equals(prj.getProject()) == true) {
						lstProjects.getSelectionModel().select(s);
						break;
					}
				}
				
				processProject();
		    }

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void doOpenProject() {
		yg.centerScene(lblTitle, "ProjectOpen.fxml", "Open a Project", null);
		if (yg.openPrjName != null) {
	    	if (!yg.itemProjects.contains(yg.openPrjName))
	    		yg.itemProjects.add(yg.openPrjName);
	    	lstProjects.getSelectionModel().select(yg.itemProjects.size() - 1);
	    	loadProject(yg.openPrjName);
	    }
	}
	
	private void doManagePlugs() {
		yg.centerScene(lblTitle, "PlugManager.fxml", "Plug Manager", yg.currProject);
	}
	
	private void runDialog(String dlg) {
		
		File f = new File(System.getProperty("user.home") + File.separator + "YadPb" + File.separator + "test.sh");
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write("#!/usr/bin/bash\n# Created by YadPb.\n\n" + dlg);
			w.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		f.setExecutable(true);
		
//		System.out.println("dlg " + dlg);
//		String[] a = dlg.split(" ");
		
		try {
			ProcessBuilder pb = new ProcessBuilder(f.getAbsolutePath());
			
			pb.inheritIO();
			pb.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateDialog() {
		
	}

	@Override
	public void saveDialog() {
		
	}

	@Override
	public void setData(String data) {
		
	}

}
