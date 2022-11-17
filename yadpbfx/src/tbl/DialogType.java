package tbl;

public class DialogType {

	
	private String text = null;
	private String type = null;
	
	public DialogType() {
		super();
	}
	
	public DialogType(String text, String type) {
		super();
		this.text = text;
		this.type = type;
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

	@Override
	public String toString() {
		return "TextType [text=" + text + ", type=" + type + "]";
	}
}
