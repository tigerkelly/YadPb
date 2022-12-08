package application;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringTokenizer;
import org.apache.commons.text.matcher.StringMatcherFactory;

import com.rkw.IniFile;

public class ProjectBash {

	private ProjectScript ps = null;
	private IniFile ini = null;
	
	public ProjectBash(IniFile ini) {
		this.ini = ini;
		
		ps = new ProjectScript();
	}
	
	public String buildBashScript() {
		StringBuilder txt = new StringBuilder();
		Map<String, String> linked = new HashMap<String, String>();
		
		Object[] secs = ini.getSectionNames();
		
		// Find all Notebook and Paned dialogs.
		for (Object sec : secs) {
			String type = ini.getString(sec, "type");
			if (type != null && (type.equals("Notebook") == true || type.equals("Paned") == true)) {
				String key = ini.getString(sec, "key");
				linked.put(key, null);
			}
		}
		
		// Update each linked dialog with either Notebook or Paned dialog
		for (Object sec : secs) {
//			String type = ini.getString(sec, "type");
			String plug = ini.getString(sec + "-General", "plug");
			
			if (plug != null && plug.isEmpty() == false) {
//				String v = linked.get(key);
			}
		}
		
		for (Object sec : secs) {
			String s = (String)sec;
			if (s.endsWith("-General") == true)
				continue;
			
			String plug = ini.getString(sec + "-General", "plug");
			String type = ini.getString(sec, "type");
			
			if (plug != null && plug.isEmpty() == false &&
					(type.equals("Notebook") == false && type.equals("Paned") == false)) {
				continue;
				
			}
			String dlg = ps.createDialog(ini, s);
			
			txt.append("func_" + s + "() {\n\t");
			
			StringTokenizer st = new StringTokenizer(dlg);
			st.setDelimiterMatcher(StringMatcherFactory.INSTANCE.spaceMatcher());
			st.setQuoteMatcher(StringMatcherFactory.INSTANCE.doubleQuoteMatcher());
			String[] tokens = st.getTokenArray();
			int len = 0;
			boolean firstTime = true;
			for (int i = 0; i < st.size(); i++ ) {
				if (firstTime == true) {
					firstTime = false;
					txt.append(tokens[i]);
				} else {
					txt.append(" " + tokens[i]);
				}
				
				len += tokens[i].length();
				
				if (len >= 70) {
					len = 0;
					if ((i + 1) < tokens.length)
						txt.append(" \\\n\t");
				}
			}
			
//			Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(dlg);
			
//			Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(dlg);
			
//			int len = 0;
//			String xx = null;
//			while (m.find()) {
//				String t = null;
//				if (m.group(1) != null)
//					t = m.group(1);
//				else
//					t = m.group(2);
////				System.out.println(t);
//				if (xx == null)
//					xx = t;
//				else
//					xx += " " + t;
//				
//				len += t.length();
//				
//				if (len >= 70) {
//					len = 0;
//					xx += " \\\n\t";
//				}
//			}
//			
//			txt.append(xx);
			
			txt.append("\n}\n\n");
		}
		
		return "#!/usr/bin/bash\n#\n\n" + txt.toString();
	}
}
