package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.rkw.IniFile;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class YadGlobal {

	private static YadGlobal singleton = null;

	private YadGlobal() {
		initGlobals();
	}

	private void initGlobals() {

		yadPbVersion = getVersion();
		
		checkImage = new Image(getClass().getResourceAsStream("/images/check.png"));
		
		dialogNames.put("Calendar", "Calendar.fxml");
		dialogNames.put("Color", "Color.fxml");
		dialogNames.put("DnD", "DnD.fxml");
		dialogNames.put("Entry", "Entry.fxml");
		dialogNames.put("File", "File.fxml");
		dialogNames.put("Font", "Font.fxml");
		dialogNames.put("Form", "Form.fxml");
		dialogNames.put("HTML", "Html.fxml"); 
		dialogNames.put("Icons", "Icons.fxml");
		dialogNames.put("Info", "Info.fxml");
		dialogNames.put("List", "List.fxml");
		dialogNames.put("Notebook", "Notebook.fxml");
		dialogNames.put("Notification", "Notification.fxml");
		dialogNames.put("Print", "Print.fxml");
		dialogNames.put("Progress", "Progress.fxml");
		dialogNames.put("Progress Multi", "ProgressMulti.fxml");
		dialogNames.put("Scale", "Scale.fxml");
		
		prjList = new HashMap<String, IniFile>();
		prjBackupList = new HashMap<String, IniFile>();
		
		String d = System.getProperty("user.home");
		
		workDir = new File(d + File.separator + "YadPb" + File.separator + "projects");
		
//		System.out.println(workDir.getAbsolutePath());
		
		if (workDir.exists() == false) {
			workDir.mkdirs();
		}
		
		backupDir = new File(workDir.getAbsolutePath() + File.separator + "backups");
		if (backupDir.exists() == false) {
			backupDir.mkdirs();
		}
		
		File f = new File(workDir.getAbsolutePath() + File.separator + "yadpb.ini");
		if (f.exists() == false) {
			try {
				f.createNewFile();
				
				try {
					FileWriter myWriter = new FileWriter(f.getAbsolutePath());
					myWriter.write("# YadPb project INI file.\n\n[System]\n");
					myWriter.write("\tworkdir = " + workDir.getAbsolutePath() + "\n");
					myWriter.write("\tbackup = true\n\n[Projects]\n");
					myWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		sysIni = new IniFile(f.getAbsolutePath());

		sceneNav = new SceneNav();
		
		vboxes = new HashMap<String, AnchorPane>();
	}

	Alert alert = null;
	public IniFile ini = null;
	public SceneNav sceneNav = null;

	public String yadPbVersion = null;
	public String openPrjName = null;
	
	public ObservableList<String> itemDialogs = null;
	public ObservableList<String> itemProjects = null;
	
	public Image checkImage = null;
	
	public String currProject = null;
	public String currDialog = null;
	public String currMime = null;
	public String currFilter = null;
	public String currColumnType = null;
	public IniFile sysIni = null;
	public IniFile currIni = null;
	public IniFile currBackupIni = null;
	public Map<String, IniFile> prjList = null;
	public Map<String, IniFile> prjBackupList = null;
	public File workDir = null;
	public File backupDir = null;
	
//	public String[] dialogNames = {"Calendar", "Color", "DnD", "Entry", "File", "Font", "Form", "General", "HTML", 
//			"Icons", "Info", "List", "Notebook", "Notification", "Print", "Progress", "Progress Multi", "Scale"};
	
	Map<String, String> dialogNames = new HashMap<String, String>();

	public StackPane dialogStackPane = null;
	
	private Map<String, AnchorPane> vboxes = null;

	public static YadGlobal getInstance() {
		// return SingletonHolder.singleton;
		if (singleton == null) {
			synchronized (YadGlobal.class) {
				singleton = new YadGlobal();
			}
		}
		return singleton;
	}

	public void loadDialog(String dialogName) {
		if (dialogName == null)
			return;
		
		FXMLLoader loader = new FXMLLoader();
		
		String fxml = null;
		boolean err = false;
		
		String[] a = dialogName.split(",");
		if (a.length != 2)
			return;
		
		Iterator<String> it = dialogNames.keySet().iterator();
		
		while(it.hasNext()) {
			String s = it.next();
			
			if (s.equals(a[1]) == true) {
				fxml = dialogNames.get(s);
				break;
			}
		}
		
		if (fxml == null) {
			System.out.println("FXML file for '" + a[1] + "' not found.");
			return;
		}
		
		if (err == true)
			return;
		
		ObservableList<Node> dialogs = dialogStackPane.getChildren();

		try {
			AnchorPane ap = null;
			if (vboxes.containsKey(fxml) == false) {
//				System.out.println("fxml " + fxml);
				ap = (AnchorPane) loader.load(getClass().getResourceAsStream(fxml));
				ap.prefWidthProperty().bind(dialogStackPane.widthProperty());
				vboxes.put(fxml, ap);
			} else {
				ap = vboxes.get(fxml);
			}
			
			Object c = loader.getController();
			
			if (c instanceof DialogInterface) {
				DialogInterface di = (DialogInterface)c;
				di.updateDialog();
			}
			
			dialogs.clear();
			dialogs.add(ap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearStack() {
		ObservableList<Node> dialogs = dialogStackPane.getChildren();
		
		dialogs.clear();
	}

	public String scenePeek() {
		if (sceneNav.sceneQue == null || sceneNav.sceneQue.isEmpty())
			return SceneNav.YADPB;
		else
			return sceneNav.sceneQue.peek();
	}

	public void guiRestart(String msg) {
		String errMsg = String.format("A GUI error occurred.\r\nError loading %s\r\n\r\nRestarting GUI.", msg);
		showAlert("GUI Error", errMsg, AlertType.CONFIRMATION, false);
		System.exit(1);
	}

	public void loadSceneNav(String fxml) {
		if (sceneNav.loadScene(fxml) == true) {
			guiRestart(fxml);
		}
	}
	
	public void closeAlert() {
		if (alert != null) {
			alert.close();
			alert = null;
		}
	}

	public ButtonType showAlert(String title, String msg, AlertType alertType, boolean yesNo) {
		alert = new Alert(alertType);
		alert.getDialogPane().setPrefWidth(725.0);
		for (ButtonType bt : alert.getDialogPane().getButtonTypes()) {
			Button button = (Button) alert.getDialogPane().lookupButton(bt);
			if (yesNo == true) {
				if (button.getText().equals("Cancel"))
					button.setText("No");
				else if (button.getText().equals("OK"))
					button.setText("Yes");
			}
			button.setStyle("-fx-font-size: 28px;");
			button.setPrefWidth(150.0);
		}
		alert.setTitle(title);
		alert.setHeaderText(null);

		alert.setContentText(msg);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");

		ButtonType bt = alert.showAndWait().get();

		alert = null;

		return bt;
	}

	public void Msg(String msg) {
		System.out.println(msg);
	}
	
	private String getVersion() {
		String ver = "Unknown";

		File f = new File("vdate.dat");
		if (f.exists() == true)
			try {
				BufferedReader in = new BufferedReader(new FileReader(f));
				String str;
				ver = null;
				while ((str = in.readLine()) != null) {
					str = str.trim();
					if (str.length() <= 0 || str.charAt(0) == '#')
						continue;

					if (ver == null)
						ver = str + " ";
					else
						ver += str + " ";
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return ver.replace("\"", "");
	}
	
	public boolean copyFile(File in, File out) {
		
		try {
	        FileInputStream fis  = new FileInputStream(in);
	        FileOutputStream fos = new FileOutputStream(out);
	        byte[] buf = new byte[4096];
	        int i = 0;
	        while((i=fis.read(buf))!=-1) {
	            fos.write(buf, 0, i);
	        }
	        fis.close();
	        fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		
		return false;
    }
	
	public void iniUpdate(String name, String value) {
		if (currIni == null || name == null)
			return;
		
		if (value == null || value.length() <= 0) {
			if (currIni.keyExists(currDialog, name) == true)
				currIni.removeValuePair(currDialog, name);
		} else {
			if (currIni.keyExists(currDialog, name) == true) {
				String v = currIni.getString(currDialog, name);
				if (v != null && v.equals(value) == false) {
					currIni.addValuePair(currDialog, name, value);
				}
			} else {
				currIni.addValuePair(currDialog, name, value);
			}
		}
		
		return;
	}
	
	public void backupProject(String prjName) {
		File f = new File(workDir.getAbsolutePath() + File.separator + prjName + ".yadpb");
		File b = new File(workDir.getAbsolutePath() + File.separator + "backups" + File.separator + prjName + ".bkp");
		
		try {
			Calendar c = Calendar.getInstance();
			String dt = String.format("%d/%02d/%02d %02d:%02d:%02d", 
					c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
					c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
			
			FileWriter fw = new FileWriter(b.getAbsolutePath(), true);
			fw.write("\n[[[" + prjName + "]]] " + dt + "\n");
			BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
			String line = null;
			while ((line = br.readLine()) != null) {
				fw.write(line + "\n");
			}
			br.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showGeneral(Button btn) {
		try {
			Stage stage = new Stage();
			stage.setTitle("General Settings for dialog " + currDialog);
//			stage.resizableProperty().setValue(Boolean.FALSE);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneralSettings.fxml"));

			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setScene(new Scene(loader.load()));
			stage.hide();	// Hides the stage repositioning.

			Stage ps = (Stage) btn.getScene().getWindow();

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
	
	public void centerScene(Node node, String fxml, String title, String data) {
		FXMLLoader loader = null;
		try {
			Stage stage = new Stage();
			stage.setTitle(title + " " + currProject);

			loader = new FXMLLoader(getClass().getResource(fxml));

			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setScene(new Scene(loader.load()));
			stage.hide();

			Stage ps = (Stage) node.getScene().getWindow();

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
			
			if (data != null) {
				((DialogInterface)loader.getController()).setData(data);
			}
			

			stage.showAndWait();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
