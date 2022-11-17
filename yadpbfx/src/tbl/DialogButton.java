package tbl;

public class DialogButton {

	private String id = null;
	private String text = null;
	private String icon = null;
	private String tooltip = null;
	
	public DialogButton() {
		super();
	}
	
	public DialogButton(String id, String text, String icon, String tooltip) {
		super();
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.tooltip = tooltip;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
