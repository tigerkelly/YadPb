package tbl;

public class MarkValue {

	
	private String text = null;
	private String value = null;
	
	public MarkValue() {
		super();
	}
	
	public MarkValue(String text, String value) {
		super();
		this.text = text;
		this.value = value;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MarkValue [text=" + text + ", value=" + value + "]";
	}

	
}
