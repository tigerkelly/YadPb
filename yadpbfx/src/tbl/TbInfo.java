package tbl;

public class TbInfo {

	private String name;
	private boolean flag;
	
	public TbInfo(String name, boolean flag) {
		super();
		this.name = name;
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "TbInfo [name=" + name + ", flag=" + flag + "]";
	}
	
}
