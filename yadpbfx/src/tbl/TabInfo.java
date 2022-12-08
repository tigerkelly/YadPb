package tbl;

public class TabInfo {

	private String text = null;
	private String icon = null;
	private String tooltip = null;
	private String linked = null;
	
	public TabInfo() {
		super();
	}
	
	public TabInfo(String text, String icon, String tooltip, String linked) {
		super();
		this.text = text;
		this.icon = icon;
		this.tooltip = tooltip;
		this.linked = linked;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getLinked() {
		return linked;
	}
	public void setLinked(String linked) {
		this.linked = linked;
	}
}
