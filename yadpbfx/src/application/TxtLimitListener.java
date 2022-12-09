/*
 * This file is part of YadPb.
 *
 * YadPb is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * YadPb is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with YadPb. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2022-2023, Kelly Wiles <rkwiles@twc.com>
 */

package application;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TxtLimitListener implements javafx.beans.value.ChangeListener<String> {

	private int maxLength;
	private TextField textField;

	public TxtLimitListener(TextField textField, int maxLength) {
		this.textField = textField;
		this.maxLength = maxLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	@Override
	public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {

		if (newValue == null) {
			return;
		}

		if (newValue.length() > maxLength) {
			textField.setText(oldValue);
		} else {
			textField.setText(newValue);
		}
	}
}
