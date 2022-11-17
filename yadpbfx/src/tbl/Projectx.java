package tbl;

public class Projectx {

	private String project;
	private String description;
	
	public Projectx() {
		
	}

	public Projectx(String project, String description) {
		super();
		this.project = project;
		this.description = description;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Projects [project=" + project + ", description=" + description + "]";
	}
	
	
}
