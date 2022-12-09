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
