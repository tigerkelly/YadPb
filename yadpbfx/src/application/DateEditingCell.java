package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import tbl.DateDetail;

class DateEditingCell extends TableCell<DateDetail, String> {

	private DatePicker datePicker;

	public DateEditingCell() {
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			createDatePicker();
			setText(null);
			setGraphic(datePicker);
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();

		setText(getDate());
		setGraphic(null);
	}

	@Override
	public void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (datePicker != null) {
					datePicker.setValue(LocalDate.parse(getDate()));
				}
				setText(null);
				setGraphic(datePicker);
			} else {
				setText(getDate());
				setGraphic(null);
			}
		}
	}

	private void createDatePicker() {
		datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setValue(LocalDate.parse(getDate()));
		datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		datePicker.setOnAction((e) -> {
			System.out.println("Committed: " + datePicker.getValue().toString());
			
//			commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		});
//        datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//            if (!newValue) {
//            	System.out.println("newValue " + newValue);
////                commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//            }
//        });
	}

	private String getDate() {
//		return getItem() == null ? LocalDate.now() : getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return "2022-11-29";
	}
}
