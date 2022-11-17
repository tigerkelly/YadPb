package tbl;

public class FileFilter {

	
	private String name = null;
	private String filter = null;
	
	public FileFilter() {
		super();
	}
	
	public FileFilter(String name, String filter) {
		super();
		this.name = name;
		this.filter = filter;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}

	@Override
	public String toString() {
		return "FileFilter [name=" + name + ", filter=" + filter + "]";
	}	
}
