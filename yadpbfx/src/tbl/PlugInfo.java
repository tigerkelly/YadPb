package tbl;

public class PlugInfo {

	
	private String name = null;
	private String num = null;
	
	public PlugInfo() {
		super();
	}
	
	public PlugInfo(String name, String num) {
		super();
		this.name = name;
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "PlugInfo [name=" + name + ", num=" + num + "]";
	}
}
