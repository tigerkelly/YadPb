package tbl;

public class MimeFilter {

	
	private String name = null;
	private String mime = null;
	
	public MimeFilter() {
		super();
	}
	
	public MimeFilter(String name, String mime) {
		super();
		this.name = name;
		this.mime = mime;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}

	@Override
	public String toString() {
		return "FileFilter [name=" + name + ", mime=" + mime + "]";
	}	
}
