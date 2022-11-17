package tbl;

public class TextType {

	
	private String text = null;
	private String type = null;
	private String values = null;
	
	public TextType() {
		super();
	}
	
	public TextType(String text, String type, String values) {
		super();
		this.text = text;
		this.type = type;
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
	
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "TextType [text=" + text + ", type=" + type + ", values" + values + "]";
	}
}
