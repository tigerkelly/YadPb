package tbl;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Project {

	private final SimpleStringProperty project;
	private final SimpleStringProperty description;
	private CheckBox action;
	
	public Project(String project, String description, Boolean action) {
		super();
		this.project = new SimpleStringProperty(project);
		this.description = new SimpleStringProperty(description);
		this.action = new CheckBox();
	}

	public String getProject() {
		return project.get();
	}
	public String getDescription() {
		return description.get();
	}
	public CheckBox getAction() {
		return action;
	}
	public void setAction(CheckBox action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return "Project [project=" + project + ", description=" + description + ", action=" + action + "]";
	}
}
