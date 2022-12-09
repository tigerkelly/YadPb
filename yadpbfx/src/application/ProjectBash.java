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

import java.util.Calendar;

import com.rkw.IniFile;

public class ProjectBash {

	private ProjectScript ps = null;
	private IniFile ini = null;
	
	public ProjectBash(IniFile ini) {
		this.ini = ini;
		
		ps = new ProjectScript();
	}
	
	public String buildBashArray(String dialog) {
		StringBuilder txt = new StringBuilder();
		
		if (ini.sectionExists(dialog) == false)
			return null;

		String dlg = ps.createDialog(ini, dialog, false);
		
		txt.append("arr_" + dialog + "=(\n");
		
		String[] tokens = dlg.split("--");
		for (String token : tokens) {
			if (token != null && token.isEmpty() == false)
				txt.append("\t--" + token + "\n");
		}
		
		txt.append(")\n\n");
		
		return txt.toString();
	}
	
	public String buildBashArrays() {
		StringBuilder txt = new StringBuilder();
		
		Object[] secs = ini.getSectionNames();
		
		for (Object sec : secs) {
			String s = (String)sec;
			if (s.endsWith("-General") == true)
				continue;
			
			String dlg = ps.createDialog(ini, s, false);
			
			txt.append("arr_" + s + "=(\n");
			
			String[] tokens = dlg.split("--");
			for (String token : tokens) {
				if (token != null && token.isEmpty() == false)
					txt.append("\t--" + token + "\n");
			}
			
			txt.append(")\n\n");
		}
		
		Calendar c = Calendar.getInstance();
		String d = String.format("%d-%02d-%02d %02d:%02d:%02d",
				c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR), c.get(Calendar.MINUTE), c.get(Calendar.SECOND));
		return "#!/usr/bin/bash\n# Created: " + d + "\n# To use a bash array use the following\n# yad \"${arr_Calendar[@]}\"\n\n" + txt.toString();
	}
}
