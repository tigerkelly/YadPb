package tbl;

public class LinkedInfo {

	private String dialog;
	private String tabnum;
	
	public LinkedInfo(String dialog, String tabnum) {
		this.dialog = dialog;
		this.tabnum = tabnum;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public String getTabnum() {
		return tabnum;
	}

	public void setTabnum(String tabnum) {
		this.tabnum = tabnum;
	}

	@Override
	public String toString() {
		return "LinkedInfo [dialog=" + dialog + ", tabnum=" + tabnum + "]";
	}
}
