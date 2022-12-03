package tbl;

public class TextType {

	
	private String text = null;
	private String type = null;
	private String icon = null;
	private String tooltip = null;
	private String values = null;
	
	public TextType() {
		super();
	}
	
	public TextType(String text, String type, String icon, String tooltip, String values) {
		super();
		this.text = text;
		this.type = type;
		this.icon = icon;
		this.tooltip = tooltip;
		this.values = values;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "TextType [text=" + text + ", type=" + type + ", icon=" + icon + ", tooltip=" + tooltip + ", values="
				+ values + "]";
	}
}
