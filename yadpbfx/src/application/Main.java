package application;

import java.io.IOException;
import java.util.ArrayList;

import com.rkw.IniFile;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;

public class Main extends Application {

	private Pane mainPane = null;
	private YadGlobal yg = YadGlobal.getInstance();

	@Override
	public void start(Stage primaryStage) {
		try {
			Rectangle2D sb = Screen.getPrimary().getBounds();
//		    System.out.println(sb);
		    if(sb.getWidth() >= 1300.0) {
		    	primaryStage.setX((sb.getWidth() - 1300.0) / 2.0);
		    }
		    if(sb.getHeight() >= 1220.0) {
		    	primaryStage.setY(sb.getHeight() - 1220.0);
		    }
//			primaryStage.getIcons().add(new Image("/images/bloodhound.png"));
			primaryStage.setScene(createScene(loadMainPane()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() {
//		System.out.println("*** Yad Project Builder is Ending. ***");
		// Save file

		SceneInfo si = yg.sceneNav.fxmls.get(yg.scenePeek());
		if (si.controller instanceof RefreshScene) {
			RefreshScene c = (RefreshScene) si.controller;
			c.leaveScene();
		}

		Object[] objs = yg.prjList.keySet().toArray();

		java.util.List<String> saveList = new ArrayList<String>();

		for (Object o : objs) {
			String s = (String) o;
			IniFile ini = yg.prjList.get(s);
			if (ini.getChangedFlag() == true)
				saveList.add(s);
		}

		if (saveList.size() > 0) {
			Alert messageBox = new Alert(Alert.AlertType.CONFIRMATION);
			Stage stage = (Stage)messageBox.dialogPaneProperty().get().getScene().getWindow();
			
			messageBox.setTitle("Warning");
			ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
			ButtonType noButton = new ButtonType("No", ButtonData.NO);
			messageBox.getButtonTypes().setAll(yesButton, noButton);

			messageBox.setContentText("Some projects have changed, save the projects?");
			
			// Code to center dialog within parent.
			Stage ps = (Stage) mainPane.getScene().getWindow();

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
					
					yg.centerScene(mainPane, "ProjectsToSave.fxml", "Save Project(s)", null);
					
				} else if (type.getButtonData() == ButtonData.NO) {
				}
			});
		}
	}

	/**
	 * Loads the main fxml layout. Sets up the vista switching VistaNavigator. Loads
	 * the first vista into the fxml layout.
	 *
	 * @return the loaded pane.
	 * @throws IOException if the pane could not be loaded.
	 */
//    @SuppressWarnings("resource")
	private Pane loadMainPane() throws IOException {
//		yg.Msg("*** Yad Project Builder is Starting. ***");

		FXMLLoader loader = new FXMLLoader();

		mainPane = (Pane) loader.load(getClass().getResourceAsStream(SceneNav.MAIN)); // SceneNav

		SceneNavController mainController = loader.getController();

		yg.sceneNav.setMainController(mainController);
		yg.sceneNav.loadScene(SceneNav.YADPB);

		return mainPane;
	}

	/**
	 * Creates the main application scene.
	 *
	 * @param mainPane the main application layout.
	 *
	 * @return the created scene.
	 */
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);

		scene.getStylesheets().setAll(getClass().getResource("application.css").toExternalForm());

		return scene;
	}
}
