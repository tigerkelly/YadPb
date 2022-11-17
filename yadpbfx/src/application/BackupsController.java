package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.rkw.IniFile;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tbl.Projectx;

public class BackupsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane aPane;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreateProject;

    @FXML
    private TextArea taData;

    @FXML
    private TreeView<String> treeBackups;
    
    private YadGlobal yg = YadGlobal.getInstance();
    
    @FXML
    void treeSelectItem(MouseEvent event) {
    	TreeItem<String> ti = treeBackups.getSelectionModel().getSelectedItem();
    	
    	if (ti != null) {
    		if (Character.isDigit(ti.getValue().charAt(0))) {
    			btnCreateProject.setDisable(false);
    			TreeItem<String> parent = ti.getParent();
    			
    			File f = new File(yg.workDir.getAbsolutePath() + File.separator + "backups" + File.separator + parent.getValue() + ".bkp");
    			
    			try {
					BufferedReader br = new BufferedReader(new FileReader(f));
					String st = null;
					while ((st = br.readLine()) != null) {
						if (st.startsWith("[[[") == true) {
							int x = st.lastIndexOf(']');
							String d = null;
							if (x >= 0) {
								d = st.substring(x + 2);
								
								if (d.equals(ti.getValue()) == true) {
									taData.setText("");
									while ((st = br.readLine()) != null) {
										if (st.startsWith("[[[") == true)
											break;
										taData.appendText(st + "\n");
									}
								}
							}
						}
					}
					br.close();
    			} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    		}
    	} else {
			btnCreateProject.setDisable(true);
		}
    }

    @FXML
    void doCancel(ActionEvent event) {
    	Stage stage = (Stage) aPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void doCreateProject(ActionEvent event) {
    	TreeItem<String> ti = treeBackups.getSelectionModel().getSelectedItem();
		String dt = (String)ti.getValue();
    	createProject(dt);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		treeBackups.setShowRoot(false);
		
		TreeItem<String> root = new TreeItem<String>("Backups");
		
		File bkps = new File(yg.workDir.getAbsolutePath() + File.separator + "backups");
		
		File[] files = bkps.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".bkp");
		    }
		});
		
		for (File f : files) {
			String s = f.getName();
			String name = s.substring(0, s.lastIndexOf('.'));
			TreeItem<String> ti = new TreeItem<String>(name);
			root.getChildren().add(ti);
        
			String st = null;
	        try {
	        	BufferedReader br = new BufferedReader(new FileReader(f));
				while ((st = br.readLine()) != null) {
					if (st.startsWith("[[[") == true) {
						int x = st.lastIndexOf(']');
						String d = null;
						if (x >= 0) {
							d = st.substring(x + 2);
							TreeItem<String> t = new TreeItem<String>(d);
							ti.getChildren().add(t);
						}
					}
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		treeBackups.setRoot(root);
		
	}
	
	private void createProject(String backupDate) {
		try {
		    Stage stage = new Stage();
		    stage.setTitle("Project Script for " + yg.currProject);

		    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjectNew.fxml"));

		    stage.initModality(Modality.APPLICATION_MODAL);

		    stage.setScene(new Scene(loader.load()));
		    stage.hide();
		    ProjectNewController pnc = loader.getController();
		    
		    // Code to center dialog within parent.
		    Stage ps = (Stage) btnCreateProject.getScene().getWindow();

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
				
				try {
					f.createNewFile();
					FileWriter w1 = new FileWriter(f.getAbsolutePath());
					w1.write("# Created by the YadPb program.\n# For project '" + prj.getProject() + "'\n# Description: " + prj.getDescription() + 
							"\n# Created from backup: " + backupDate + "\n\n");
					w1.write(taData.getText());
				    w1.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
				File b = new File(yg.workDir + File.separator + "backups" + File.separator + f.getName());
				try {
					b.createNewFile();
					FileWriter w2 = new FileWriter(b.getAbsolutePath());
					w2.write("# Created by the YadPb program.\n# For project '" + prj.getProject() + "'\n# Description: " + prj.getDescription() + 
							"\n# Created from backup: " + backupDate + "\n\n");
					w2.write(taData.getText());
					w2.close();
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
		    }

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

