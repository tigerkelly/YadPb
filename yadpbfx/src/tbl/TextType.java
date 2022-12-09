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
