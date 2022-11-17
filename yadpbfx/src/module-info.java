module yadpbfx {
	requires javafx.controls;
	requires javafx.fxml;
	requires IniFile;
	requires javafx.graphics;
	requires javafx.base;
	requires org.controlsfx.controls;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
	opens tbl to javafx.fxml, javafx.base;
}
