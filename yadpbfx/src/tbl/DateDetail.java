package tbl;

public class DateDetail {

	
	private String date = null;
	private String detail = null;
	
	public DateDetail() {
		super();
	}
	
	public DateDetail(String date, String detail) {
		super();
		this.date = date;
		this.detail = detail;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "DateDetail [date=" + date + ", detail=" + detail + "]";
	}
}
