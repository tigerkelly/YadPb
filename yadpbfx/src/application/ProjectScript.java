package application;

import java.util.ArrayList;
import java.util.List;

import com.rkw.IniFile;

public class ProjectScript {
	
	 private String yad = null;
	 private boolean runFlag = false;
	 private YadGlobal yg = YadGlobal.getInstance();
	
	 public String createDialog(IniFile ini, String dialog) {
		StringBuilder txt = new StringBuilder();
		
		yad = yg.yad.getAbsolutePath();
		
		runFlag = true;
		
		String type = ini.getString(dialog, "type");
		
		switch (type) {
		case "Calendar":
			txt.append( buildCalendar(ini, dialog));
			break;
		case "Color":
			txt.append( buildColor(ini, dialog));
			break;
		case "DnD":
			txt.append( buildDnD(ini, dialog));
			break;
		case "Entry":
			txt.append( buildEntry(ini, dialog));
			break;
		case "File":
			txt.append( buildFile(ini, dialog));
			break;
		case "Font":
			txt.append( buildFont(ini, dialog));
			break;
		case "Form":
			txt.append( buildForm(ini, dialog));
			break;
		case "HTML":
			txt.append( buildHtml(ini, dialog));
			break;
		case "Icons":
			txt.append( buildIcons(ini, dialog));
			break;
		case "TextInfo":
			txt.append( buildInfo(ini, dialog));
			break;
		case "List":
			txt.append( buildList(ini, dialog));
			break;
		case "Notebook":
			txt.append( buildNotebook(ini, dialog));
			break;
		case "Notification":
			txt.append( buildNotification(ini, dialog));
			break;
		case "Paned":
			txt.append( buildPaned(ini, dialog));
			break;
		case "Picture":
			txt.append( buildPicture(ini, dialog));
			break;
		case "Print":
			txt.append( buildPrint(ini, dialog));
			break;
		case "Progress":
			txt.append( buildProgress(ini, dialog));
			break;
		case "Progress Multi":
			txt.append( buildProgressMulti(ini, dialog));
			break;
		case "Scale":
			txt.append( buildScale(ini, dialog));
			break;
		}
		
//		txt.append("\n");
		
		return txt.toString();
	}

	 public String createAllDialogs(IniFile ini) {
		StringBuilder txt = new StringBuilder();
		
		yad = yg.yad.getAbsolutePath();
		
		runFlag = false;
		
		Object[] secs = ini.getSectionNames();
		
		for (Object sec : secs) {
			String s = (String)sec;
			
			if (s.endsWith("-General") == true)
				continue;
			
			String type = ini.getString(s, "type");
			
//			System.out.println("sec " + s + " type " + type);
				
			switch (type) {
			case "Calendar":
				txt.append( buildCalendar(ini, s));
				break;
			case "Color":
				txt.append( buildColor(ini, s));
				break;
			case "DnD":
				txt.append( buildDnD(ini, s));
				break;
			case "Entry":
				txt.append( buildEntry(ini, s));
				break;
			case "File":
				txt.append( buildFile(ini, s));
				break;
			case "Font":
				txt.append( buildFont(ini, s));
				break;
			case "Form":
				txt.append( buildForm(ini, s));
				break;
			case "HTML":
				txt.append( buildHtml(ini, s));
				break;
			case "Icons":
				txt.append( buildIcons(ini, s));
				break;
			case "TextInfo":
				txt.append( buildInfo(ini, s));
				break;
			case "List":
				txt.append( buildList(ini, s));
				break;
			case "Notebook":
				txt.append( buildNotebook(ini, s));
				break;
			case "Notification":
				txt.append( buildNotification(ini, s));
				break;
			case "Paned":
				txt.append( buildPaned(ini, s));
				break;
			case "Picture":
				txt.append( buildPicture(ini, s));
				break;
			case "Print":
				txt.append( buildPrint(ini, s));
				break;
			case "Progress":
				txt.append( buildProgress(ini, s));
				break;
			case "Progress Multi":
				txt.append( buildProgressMulti(ini, s));
				break;
			case "Scale":
				txt.append( buildScale(ini, s));
				break;
			}
			
			txt.append("\n");
		}
		
		return txt.toString();
	}
	
	 private String buildCalendar(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In calendar");
		
		lst.add("--calendar");
		
		String detailfile = ini.getString(sec, "detailfile");
		String dateformat = ini.getString(sec, "dateformat");
		String defaultdate = ini.getString(sec, "defaultdate");

		boolean showweeks = ini.getBoolean(sec, "showweeks");
		
		if (detailfile != null && detailfile.isEmpty() == false)
			lst.add(quote("--details=", detailfile));
		if (dateformat != null && dateformat.isEmpty() == false)
			lst.add(quote("--date-format=", dateformat));
		if (defaultdate != null && defaultdate.isEmpty() == false) {
			String[] a = defaultdate.split("-");
			lst.add(quote("--month=", a[1]));
			lst.add(quote("--day=", a[2]));
			lst.add(quote("--year=", a[0]));
		}
		if (showweeks == true)
			lst.add("--show-weeks");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildColor(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Color");
		
		lst.add("--color");
		
		String palette = ini.getString(sec, "palette");
		String mode = ini.getString(sec, "mode");
		String initcolor = ini.getString(sec, "initcolor");

		boolean gtkpalette = ini.getBoolean(sec, "gtkpalette");
		boolean extra = ini.getBoolean(sec, "extra");
		boolean alpha = ini.getBoolean(sec, "alpha");
		boolean expand = ini.getBoolean(sec, "expand");
		
		if (palette != null && palette.isEmpty() == false)
			lst.add(quote("--details=", palette));
		if (mode != null && mode.isEmpty() == false)
			lst.add(quote("--details=", mode));
		if (initcolor != null && initcolor.isEmpty() == false) {
			lst.add(quote("--init-color=", initcolor.substring(0, 7)));
		}
		
		if (gtkpalette == true)
			lst.add("--gtk-palette");
		if (extra == true)
			lst.add("--extra");
		if (alpha == true)
			lst.add("--alpha");
		if (expand == true)
			lst.add("--expand");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildDnD(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In DnD");
		
		lst.add("--dnd");
		
		
		String command = ini.getString(sec, "command");
		String exitondrop = ini.getString(sec, "exitondrop");
		
		boolean tooltip = ini.getBoolean(sec, "tooltip");
		
		if (command != null && command.isEmpty() == false)
			lst.add(quote("--command=", command));
		if (exitondrop != null && exitondrop.isEmpty() == false)
			lst.add(quote("--exit-on-drop=", exitondrop));
		
		if (tooltip == true)
			lst.add("--tooltip");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildEntry(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Entry");
		
		lst.add("--entry");
		
		String label = ini.getString(sec, "label");
		String text = ini.getString(sec, "text");
		String lefticon = ini.getString(sec, "lefticon");
		String leftaction = ini.getString(sec, "leftaction");
		String righticon = ini.getString(sec, "righticon");
		String rightaction = ini.getString(sec, "rightaction");
		String precision = ini.getString(sec, "precision");
		
		boolean completion = ini.getBoolean(sec, "completion");
		boolean numeric = ini.getBoolean(sec, "numeric");
		boolean numoutput = ini.getBoolean(sec, "numoutput");
		
		if (label != null && label.isEmpty() == false)
			lst.add(quote("--entry-label=", label));
		if (text != null && text.isEmpty() == false)
			lst.add(quote("--entry-text=", text));
		if (lefticon != null && lefticon.isEmpty() == false)
			lst.add(quote("--licon=", lefticon));
		if (leftaction != null && leftaction.isEmpty() == false)
			lst.add(quote("--licon-cmd=", leftaction));
		if (righticon != null && righticon.isEmpty() == false)
			lst.add(quote("--ricon=", righticon));
		if (rightaction != null && rightaction.isEmpty() == false)
			lst.add(quote("--ricon-cmd=", rightaction));
		if (precision != null && precision.isEmpty() == false)
			lst.add(quote("--float-precision=", precision));
		
		if (completion == true)
			lst.add("--completion");
		if (numeric == true)
			lst.add("--numeric");
		if (numoutput == true)
			lst.add("--num-output");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildFile(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In File");
		
		lst.add("--file");
		
		String filename = ini.getString(sec, "filename");
		String sep = ini.getString(sec, "sep");
		String owtext = ini.getString(sec, "owtext");
		
		boolean multiple = ini.getBoolean(sec, "multiple");
		boolean directory = ini.getBoolean(sec, "directory");
		boolean savemode = ini.getBoolean(sec, "savemode");
		boolean quoted = ini.getBoolean(sec, "quoted");
		boolean overwrite = ini.getBoolean(sec, "overwrite");
		
		if (filename != null && filename.isEmpty() == false)
			lst.add(quote("--filename=", filename));
		if (sep != null && sep.isEmpty() == false)
			lst.add(quote("--separator=", sep));
		if (owtext != null && owtext.isEmpty() == false)
			lst.add(quote("--confirm-overwrite=", owtext));
		
		if (multiple == true)
			lst.add("--multiple");
		if (directory == true)
			lst.add("--directory");
		if (savemode == true)
			lst.add("--save");
		if (quoted == true)
			lst.add("--quoted-output");
		if (overwrite == true) {
			if (owtext != null)
				lst.add("--confirm-overwrite=\"" + owtext + "\"");
			else
				lst.add("--confirm-overwrite");
		}
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildFont(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Font");
		
		lst.add("--font");
		
		String fontname = ini.getString(sec, "fontname");
		String sep = ini.getString(sec, "sep");
		
		boolean preview = ini.getBoolean(sec, "preview");
		boolean output = ini.getBoolean(sec, "output");
		boolean quoted = ini.getBoolean(sec, "quoted");
		
		if (fontname != null && fontname.isEmpty() == false)
			lst.add(quote("--fontname=", fontname));
		if (sep != null && sep.isEmpty() == false)
			lst.add(quote("--separator=", sep));
		
		if (preview == true)
			lst.add("--preview");
		if (output == true)
			lst.add("--separate-output");
		if (quoted == true)
			lst.add("--quoted-output");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildForm(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Form");
		
		lst.add("--form");
		
		String fields = ini.getString(sec, "fields");
		String columns = ini.getString(sec, "columns");
		String sep = ini.getString(sec, "sep");
		String isep = ini.getString(sec, "isep");
		String format = ini.getString(sec, "format");
		String outputnum = ini.getString(sec, "outputnum");
		String precision = ini.getString(sec, "precision");
		String align = ini.getString(sec, "align");
		String complete = ini.getString(sec, "complete");
		String focus = ini.getString(sec, "focus");
		
		boolean cycle = ini.getBoolean(sec, "cycle");
		boolean scroll = ini.getBoolean(sec, "scroll");
		boolean outputrow = ini.getBoolean(sec, "outputrow");
		boolean quoted = ini.getBoolean(sec, "quoted");
		
		if (fields != null) {
			String[] flds = fields.split(",");
			
			for (String f : flds) {
				String[] a = f.split("~", -1);
				
				String lbl = null;
				String typ = null;
				String icn = null;
				String tip = null;
				String val = null;
				
				if (a.length == 1) {
					lbl = quote(a[0]);
					typ = "TXT";
				} else if (a.length == 2) {
					lbl = quote(a[0]);
					String[] c = a[1].split("-");
					typ = c[c.length - 1];
				} else if (a.length == 3) {
					lbl = quote(a[0]);
					String[] c = a[1].split("-");
					typ = c[c.length - 1];
					icn = a[2];
				} else if (a.length == 4) {
					lbl = quote(a[0]);
					String[] c = a[1].split("-");
					typ = c[c.length - 1];
					icn = a[2];
					tip = a[3];
				} else if (a.length == 5) {
					lbl = quote(a[0]);
					String[] c = a[1].split("-");
					typ = c[c.length - 1];
					icn = a[2];
					tip = a[3];
					val = a[4];
				}
				
				switch (a[1]) {
				case "Button-BTN":
				case "Button Full-FBTN":
					if (icn != null && icn.isEmpty() == false)
						lbl += isep + icn;
					else
						lbl += isep;
					if (tip != null && tip.isEmpty() == false)
						lbl += isep + quote(tip);
					else
						lbl += isep;
					lst.add("--field=" + lbl + ":" + typ);
					break;
				case "Numeric-NUM":
					lbl +=  ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Checkbox-CHK":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
					
				case "Color-CLR":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Combobox-CB":
				case "Combobox Editable-CBE":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Completion-CE":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Date-DT":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Dir-DIR":
				case "File Create-SFL":
				case "File-FL":
				case "File Multiple-MFL":
				case "Dir Create-CDIR":
				case "Dir Multiple-MDIR":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false) {
						String[] ff = val.split("\\s");
						String p = null;
						for (String fp : ff) {
							if (p == null)
								p = isep + fp;
							else
								p += isep + fp;
						}
						if (p != null && p.isEmpty() == false)
							lbl += " " + quote(p);
					}
					lst.add("--field=" + lbl);
					break;
				case "Font Selector-FN":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Hidden-H":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Label-LBL":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Horizontal-HZ":
					lbl = "\"\":LBL";
					lst.add("--field=" + lbl);
					break;
				case "Read-Only-RO":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Scale-SCL":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					lst.add("--field=" + lbl);
					break;
				case "Text-TXT":
					lbl += ":" + typ;
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					else
						lbl += " \"\"";
					lst.add("--field=" + lbl);
					break;
				case "TextField-TF":
					if (val != null && val.isEmpty() == false)
						lbl += " " + quote(val);
					else
						lbl += " \"\"";
					lst.add("--field=" + lbl);
					break;
				}
			}
		}
		
		if (columns != null && columns.isEmpty() == false)
			lst.add(quote("--columns=", columns));
		if (sep != null && sep.isEmpty() == false)
			lst.add(quote("--separator=", sep));
		if (isep != null && isep.isEmpty() == false)
			lst.add(quote("--item-separator=", isep));
		if (format != null && format.isEmpty() == false)
			lst.add(quote("--date-foramt=", format));
		if (outputnum != null && outputnum.isEmpty() == false)
			lst.add(quote("--focus-field=", outputnum));
		if (precision != null && precision.isEmpty() == false)
			lst.add(quote("--float-precision=", precision));
		if (align != null && align.isEmpty() == false)
			lst.add(quote("--align=", align));
		if (complete != null && complete.isEmpty() == false)
			lst.add(quote("--complete=", complete));
		if (focus != null && focus.isEmpty() == false)
			lst.add(quote("--focus-field=", focus));
		
		if (cycle == true)
			lst.add("--read-cycle");
		if (scroll == true)
			lst.add("--scroll");
		if (outputrow == true)
			lst.add("--output-by-row");
		if (quoted == true)
			lst.add("--quoted-output");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildHtml(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In HTML");
		
		lst.add("--html");
		
		String uri = ini.getString(sec, "uri");
		String mime = ini.getString(sec, "mime");
		String encoding = ini.getString(sec, "encoding");
		String useragent = ini.getString(sec, "useragent");
		String userstyle = ini.getString(sec, "userstyle");
		
		boolean browser = ini.getBoolean(sec, "browser");
		boolean printuri = ini.getBoolean(sec, "printuri");
		
		if (uri != null && uri.isEmpty() == false)
			lst.add(quote("--uri=", uri));
		if (mime != null && mime.isEmpty() == false)
			lst.add(quote("--mime=", mime));
		if (encoding != null && encoding.isEmpty() == false)
			lst.add(quote("--encoding=", encoding));
		if (useragent != null && useragent.isEmpty() == false)
			lst.add(quote("--user-agent=", useragent));
		if (userstyle != null && userstyle.isEmpty() == false)
			lst.add(quote("--user-style=", userstyle));
		
		
		if (browser == true)
			lst.add("--browser");
		if (printuri == true)
			lst.add("--print-uri");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildIcons(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Icons");
		
		lst.add("--icons");
		
		
		String readdir = ini.getString(sec, "readdir");
		String itemwidth = ini.getString(sec, "itemwidth");
		String term = ini.getString(sec, "term");
		
		boolean monitor = ini.getBoolean(sec, "monitor");
		boolean generic = ini.getBoolean(sec, "generic");
		boolean sortbyname = ini.getBoolean(sec, "sortbyname");
		boolean descend = ini.getBoolean(sec, "descend");
		boolean listen = ini.getBoolean(sec, "listen");
		boolean compact = ini.getBoolean(sec, "compact");
		boolean singleclick = ini.getBoolean(sec, "singleclick");
		
		if (readdir != null && readdir.isEmpty() == false)
			lst.add(quote("--read-dir=", readdir));
		if (itemwidth != null && itemwidth.isEmpty() == false)
			lst.add(quote("--item-width=", itemwidth));
		if (term != null && term.isEmpty() == false)
			lst.add(quote("--term=", term));
		
		if (monitor == true)
			lst.add("--monitor");
		if (generic == true)
			lst.add("--generic");
		if (sortbyname == true)
			lst.add("--sort-by-name");
		if (descend == true)
			lst.add("--descend");
		if (listen == true)
			lst.add("--listen");
		if (compact == true)
			lst.add("--compact");
		if (singleclick == true)
			lst.add("--single-click");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildInfo(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Info");
		
		lst.add("--text-info");
		
		
		String filename = ini.getString(sec, "filename");
		String fontname = ini.getString(sec, "fontname");
		String justify = ini.getString(sec, "justify");
		String margins = ini.getString(sec, "margins");
		String language = ini.getString(sec, "language");
		String theme = ini.getString(sec, "theme");
		String fgcolor = ini.getString(sec, "fgcolor");
		String bgcolor = ini.getString(sec, "bgcolor");
		String uricolor = ini.getString(sec, "uricolor");
		
		boolean editable = ini.getBoolean(sec, "editable");
		boolean tail = ini.getBoolean(sec, "tail");
		boolean showcursor = ini.getBoolean(sec, "showcursor");
		boolean showuri = ini.getBoolean(sec, "showuri");
		boolean listen = ini.getBoolean(sec, "listen");
		
		if (filename != null && filename.isEmpty() == false)
			lst.add(quote("--filename=", filename));
		if (fontname != null && fontname.isEmpty() == false)
			lst.add(quote("--fontname=", fontname));
		if (justify != null && justify.isEmpty() == false)
			lst.add(quote("--justify=", justify));
		if (margins != null && margins.isEmpty() == false)
			lst.add(quote("--margins=", margins));
		if (language != null && language.isEmpty() == false)
			lst.add(quote("--lang=", language));
		if (theme != null && theme.isEmpty() == false)
			lst.add(quote("--theme=", theme));
		if (fgcolor != null && fgcolor.isEmpty() == false)
			lst.add(quote("--fore=", fgcolor));
		if (bgcolor != null && bgcolor.isEmpty() == false)
			lst.add(quote("--back=", bgcolor));
		if (uricolor != null && uricolor.isEmpty() == false)
			lst.add(quote("--uri-color=", uricolor));
		
		if (editable == true)
			lst.add("--editable");
		if (tail == true)
			lst.add("--tail");
		if (showcursor == true)
			lst.add("--show-cursor");
		if (showuri == true)
			lst.add("--show-uri");
		if (listen == true)
			lst.add("--listen");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildList(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In List");
		
		lst.add("--list");
		
		
		String columns = ini.getString(sec, "columns");
		String sep = ini.getString(sec, "sep");
		String editablecols = ini.getString(sec, "editablecols");
		String gridlines = ini.getString(sec, "gridlines");
		String printcolumn = ini.getString(sec, "printcolumn");
		String hidecolumn = ini.getString(sec, "hidecolumn");
		String expandcolumn = ini.getString(sec, "expandcolumn");
		String searchcolumn = ini.getString(sec, "searchcolumn");
		String tooltipcolumn = ini.getString(sec, "tooltipcolumn");
		String sepcolumn = ini.getString(sec, "sepcolumn");
		String sepvalue = ini.getString(sec, "sepvalue");
		String limit = ini.getString(sec, "limit");
		String wrapwidth = ini.getString(sec, "wrapwidth");
		String wrapcols = ini.getString(sec, "wrapcols");
		String ellipsize = ini.getString(sec, "ellipsize");
		String ellipsizecols = ini.getString(sec, "ellipsizecols");
		String dclickaction = ini.getString(sec, "dclickaction");
		String selectaction = ini.getString(sec, "selectaction");
		String addaction = ini.getString(sec, "addaction");
		String precision = ini.getString(sec, "precision");
		
		boolean checklist = ini.getBoolean(sec, "checklist");
		boolean radiolist = ini.getBoolean(sec, "radiolist");
		boolean multiple = ini.getBoolean(sec, "multiple");
		boolean editable = ini.getBoolean(sec, "editable");
		boolean noheaders = ini.getBoolean(sec, "noheaders");
		boolean noclick = ini.getBoolean(sec, "noclick");
		boolean noruleshint = ini.getBoolean(sec, "noruleshint");
		boolean noselection = ini.getBoolean(sec, "noselection");
		boolean printall = ini.getBoolean(sec, "printall");
		boolean regexsearch = ini.getBoolean(sec, "regexsearch");
		boolean listen = ini.getBoolean(sec, "listen");
		boolean addontop = ini.getBoolean(sec, "addontop");
		boolean tail = ini.getBoolean(sec, "tail");
		boolean iecformat = ini.getBoolean(sec, "iecformat");
		
		if (columns != null) {
			String[] cols = columns.split(",");
			for (String c : cols) {
				String[] a = c.split(":");
				String[] b = a[1].split("-");
				lst.add("--column=\"" + a[0] + ":" + b[1] + "\"");
			}
		}
		if (sep != null && sep.isEmpty() == false)
			lst.add(quote("--separator=", sep));
		if (editablecols != null && editablecols.isEmpty() == false)
			lst.add(quote("--editable-cols=", editablecols));
		if (gridlines != null && gridlines.isEmpty() == false)
			lst.add(quote("--grid-lines=", gridlines));
		if (printcolumn != null && printcolumn.isEmpty() == false)
			lst.add(quote("--print-column=", printcolumn));
		if (hidecolumn != null && hidecolumn.isEmpty() == false)
			lst.add(quote("--hide-column=", hidecolumn));
		if (expandcolumn != null && expandcolumn.isEmpty() == false)
			lst.add(quote("--expand-column=", expandcolumn));
		if (searchcolumn != null && searchcolumn.isEmpty() == false)
			lst.add(quote("--search-column=", searchcolumn));
		if (tooltipcolumn != null && tooltipcolumn.isEmpty() == false)
			lst.add(quote("--tooltip-column=", tooltipcolumn));
		if (sepcolumn != null && sepcolumn.isEmpty() == false)
			lst.add(quote("--sep-column=", sepcolumn));
		if (sepvalue != null && sepvalue.isEmpty() == false)
			lst.add(quote("--sep-vlaue=", sepvalue));
		if (limit != null && limit.isEmpty() == false)
			lst.add(quote("--limit=", limit));
		if (wrapcols != null && wrapcols.isEmpty() == false)
			lst.add(quote("--wrap-cols=", wrapcols));
		if (wrapwidth != null && wrapwidth.isEmpty() == false)
			lst.add(quote("--wrap-width=", wrapwidth));
		if (ellipsize != null && ellipsize.isEmpty() == false)
			lst.add(quote("--ellipsize=", ellipsize));
		if (ellipsizecols != null && ellipsizecols.isEmpty() == false)
			lst.add(quote("--ellipsize-cols=", ellipsizecols));
		if (dclickaction != null && dclickaction.isEmpty() == false)
			lst.add(quote("--dclick-action=", dclickaction));
		if (selectaction != null && selectaction.isEmpty() == false)
			lst.add(quote("--select-action=", selectaction));
		if (addaction != null && addaction.isEmpty() == false)
			lst.add(quote("--add-action=", addaction));
		if (precision != null && precision.isEmpty() == false)
			lst.add(quote("--float-precision=", precision));
		
		if (checklist == true)
			lst.add("--checklist");
		if (radiolist == true)
			lst.add("--radiolist");
		if (editable == true)
			lst.add("--editable");
		if (multiple == true)
			lst.add("--multiple");
		if (noheaders == true)
			lst.add("--no-headers");
		if (noclick == true)
			lst.add("--no-click");
		if (noruleshint == true)
			lst.add("--no-rules-hint");
		if (noselection == true)
			lst.add("--no-selection");
		if (printall == true)
			lst.add("--print-all");
		if (regexsearch == true)
			lst.add("--regex-search");
		if (listen == true)
			lst.add("--listen");
		if (addontop == true)
			lst.add("--add-on-top");
		if (tail == true)
			lst.add("--tail");
		if (iecformat == true)
			lst.add("--iec-format");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildNotebook(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Notebook");
		
		lst.add("--notebook");
		
		
		String key = ini.getString(sec, "key");
		String tabs = ini.getString(sec, "tabs");
		String tabpos = ini.getString(sec, "tabpos");
		String tabborders = ini.getString(sec, "tabborders");
		String activetab = ini.getString(sec, "activetab");
		
		if (key != null)
			lst.add("--key=" + key);
		if (tabs != null) {
			String[] ts = tabs.split(",");
			
			for (String t : ts) {
				String[] b = t.split("~");
				lst.add("--tabnum=" + b[3]);
				lst.add("--tab=\"" + t + "\"");
			}
		}
		if (tabpos != null && tabpos.isEmpty() == false)
			lst.add(quote("--tab-pos=", tabpos));
		if (tabborders != null && tabborders.isEmpty() == false)
			lst.add(quote("--tab-borders=", tabborders));
		if (activetab != null && activetab.isEmpty() == false)
			lst.add(quote("--active-tab=", activetab));
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildNotification(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Notification");
		
		lst.add("--notification");
		
		
		String command = ini.getString(sec, "command");
		String sep = ini.getString(sec, "sep");
		String isep = ini.getString(sec, "isep");
		String menu = ini.getString(sec, "menu");
		String iconsize = ini.getString(sec, "iconsize");
		
		boolean listen = ini.getBoolean(sec, "listen");
		boolean nomiddle = ini.getBoolean(sec, "nomiddle");
		boolean hidden = ini.getBoolean(sec, "hidden");
		
		if (command != null && command.isEmpty() == false)
			lst.add(quote("--command=", command));
		if (sep != null && sep.isEmpty() == false)
			lst.add(quote("--separator=", sep));
		if (isep != null && isep.isEmpty() == false)
			lst.add(quote("--item-separator=", isep));
		if (menu != null && menu.isEmpty() == false)
			lst.add(quote("--menu=", menu));
		if (iconsize != null && iconsize.isEmpty() == false)
			lst.add(quote("--icon-size=", iconsize));
		
		if (listen == true)
			lst.add("--listen");
		if (nomiddle == true)
			lst.add("--no-middle");
		if (hidden == true)
			lst.add("--hidden");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	 
	 private String buildPaned(IniFile ini, String sec) {
			StringBuilder txt = new StringBuilder();
			List<String> lst = new ArrayList<String>();
			
//			System.out.println("In Print");
			
			lst.add("--paned");
			
			String key = ini.getString(sec, "key");
			String orient = ini.getString(sec, "orient");
			String splitter = ini.getString(sec, "splitter");
			
			if (key != null && key.isEmpty() == false)
				lst.add(quote("--key=", key));
			if (orient != null && orient.isEmpty() == false)
				lst.add(quote("--orient=", orient));
			if (splitter != null && splitter.isEmpty() == false)
				lst.add(quote("--splitter=", splitter));
			
			if (runFlag == false)
				txt.append("# Dialog '" + sec + "'\n" + yad);
			else
				txt.append(yad + " ");
			
			for (String s : lst) {
				txt.append(s + " ");
			}

			String general = buildGeneral(ini, sec);
			
			if (general != null)
				txt.append(general);
			
			if (runFlag == false)
				txt.append("\n# -----\n");
			
			return txt.toString();
	 }
	 
	 private String buildPicture(IniFile ini, String sec) {
			StringBuilder txt = new StringBuilder();
			List<String> lst = new ArrayList<String>();
			
//			System.out.println("In Print");
			
			lst.add("--picture");
			
			String size = ini.getString(sec, "size");
			String increment = ini.getString(sec, "increment");
			String filename = ini.getString(sec, "filename");
			
			if (size != null && size.isEmpty() == false)
				lst.add(quote("--size=", size));
			if (increment != null && increment.isEmpty() == false)
				lst.add(quote("--inc=", increment));
			if (filename != null && filename.isEmpty() == false)
				lst.add(quote("--filename=", filename));
			
			if (runFlag == false)
				txt.append("# Dialog '" + sec + "'\n" + yad);
			else
				txt.append(yad + " ");
			
			for (String s : lst) {
				txt.append(s + " ");
			}

			String general = buildGeneral(ini, sec);
			
			if (general != null)
				txt.append(general);
			
			if (runFlag == false)
				txt.append("\n# -----\n");
			
			return txt.toString();
	 }
	
	 private String buildPrint(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Print");
		
		lst.add("--print");
		
		
		String type = ini.getString(sec, "type");
		String filename = ini.getString(sec, "filename");
		String fontname = ini.getString(sec, "fontname");
		
		boolean headers = ini.getBoolean(sec, "headers");
		
		if (type != null && type.isEmpty() == false)
			lst.add(quote("--type=", type));
		if (filename != null && filename.isEmpty() == false)
			lst.add(quote("--filename=", filename));
		if (fontname != null && fontname.isEmpty() == false)
			lst.add(quote("--fontname=", fontname));
		
		if (headers == true)
			lst.add("--headers");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}

		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildProgress(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Progress");
		
		lst.add("--progress");
		
		
		String progresstext = ini.getString(sec, "progresstext");
		String percentage = ini.getString(sec, "percentage");
		String enablelogtext = ini.getString(sec, "enablelogtext");
		
		boolean rtl = ini.getBoolean(sec, "rtl");
		boolean enablelog = ini.getBoolean(sec, "enablelog");
		boolean autoclose = ini.getBoolean(sec, "autoclose");
		boolean autokill = ini.getBoolean(sec, "autokill");
		boolean pulsate = ini.getBoolean(sec, "pulsate");
		boolean logontop = ini.getBoolean(sec, "logontop");
		boolean logexpand = ini.getBoolean(sec, "logexpand");
		boolean logheight = ini.getBoolean(sec, "logheight");
		
		if (progresstext != null && progresstext.isEmpty() == false)
			lst.add(quote("--progress-text=", progresstext));
		if (percentage != null && percentage.isEmpty() == false)
			lst.add(quote("--percentage=", percentage));
		if (enablelog == true) {
			if (enablelogtext != null && enablelogtext.isEmpty() == false)
				lst.add(quote("--enable-log=", enablelogtext));
			else
				lst.add("--enable-log");
		}
		
		
		if (rtl == true)
			lst.add("--rtl");
		if (autoclose == true)
			lst.add("--auto-close");
		if (autokill == true)
			lst.add("--auto-kill");
		if (pulsate == true)
			lst.add("--pulsate");
		if (logontop == true)
			lst.add("--log-on-top");
		if (logexpand == true)
			lst.add("--log-expand");
		if (logheight == true)
			lst.add("--log-height");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildProgressMulti(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Progress Multi");
		
		lst.add("--multi-progress");
		
		
		String bars = ini.getString(sec, "bars");
		String watchbar = ini.getString(sec, "watchbar");
		String align = ini.getString(sec, "align");
		
		boolean autoclose = ini.getBoolean(sec, "autoclose");
		boolean autokill = ini.getBoolean(sec, "autokill");
		
		if (bars != null) {
			String[] s = bars.split(",");
			
			for (String b : s) {
				String[] d = b.split(":");
				lst.add(quote("--bar=", d[0] + ":" + d[1]));
			}
		}
			
		if (watchbar != null && watchbar.isEmpty() == false)
			lst.add(quote("--watch-bar=", watchbar));
		if (align != null && align.isEmpty() == false)
			lst.add(quote("--align=", align));
		
		if (autoclose == true)
			lst.add("--auto-close");
		if (autokill == true)
			lst.add("--auto-kill");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildScale(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In Scale");
		
		lst.add("--scale");
		
		String marks = ini.getString(sec, "marks");
		int initvalue = 0;
		int minvalue = 0;
		int stepsize = 0;
		int pagevalue = 0;
		if (ini.keyExists(sec, "initvalue"))
			initvalue = ini.getInt(sec, "initvalue");
		if (ini.keyExists(sec, "minvalue"))
			minvalue = ini.getInt(sec, "minvalue");
		if (ini.keyExists(sec, "stepsize"))
			stepsize = ini.getInt(sec, "stepsize");
		if (ini.keyExists(sec, "pagevlaue"))
			pagevalue = ini.getInt(sec, "pagevalue");
		
		boolean printpartial = ini.getBoolean(sec, "printpartial");
		boolean hidevalue = ini.getBoolean(sec, "hidevalue");
		boolean vertical = ini.getBoolean(sec, "vertical");
		boolean invert = ini.getBoolean(sec, "invert");
		boolean incbuttons = ini.getBoolean(sec, "incbuttons");
		
		if (marks != null) {
			String[] mks = marks.split(",");
			
			for (String mark : mks) {
				String[] a = mark.split(":");
				if (a.length == 2) {
					lst.add(quote("--mark=", a[0] + ":" + a[1]));
				} else {
					lst.add(quote("--mark=", a[0]));		// NAME is optional
				}
			}
		}
		
		lst.add("--init-value=" + initvalue);
		lst.add("--min-value=" + minvalue);
		lst.add("--step-size=" + stepsize);
		lst.add("--page-value=" + pagevalue);
		
		if (printpartial == true)
			lst.add("--print-partial");
		if (hidevalue == true)
			lst.add("--hide-value");
		if (vertical == true)
			lst.add("--vertical");
		if (invert == true)
			lst.add("--invert");
		if (incbuttons == true)
		lst.add("--inc-buttons");
		
		if (runFlag == false)
			txt.append("# Dialog '" + sec + "'\n" + yad);
		else
			txt.append(yad + " ");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		String general = buildGeneral(ini, sec);
		
		if (general != null)
			txt.append(general);
		
		if (runFlag == false)
			txt.append("\n# -----\n");
		
		return txt.toString();
	}
	
	 private String buildGeneral(IniFile ini, String sec) {
		StringBuilder txt = new StringBuilder();
		List<String> lst = new ArrayList<String>();
		
//		System.out.println("In General");
		
		String title = ini.getString(sec + "-General", "title");
		String icon = ini.getString(sec + "-General", "icon");
//		String geometry = ini.getString(sec + "-General", "geometry");
		String timeoutsecs = ini.getString(sec + "-General", "timeoutsecs");
		String buttons = ini.getString(sec + "-General", "buttons");
		String text = ini.getString(sec + "-General", "text");
		String image = ini.getString(sec + "-General", "image");
		String width = ini.getString(sec + "-General", "width");
		String height = ini.getString(sec + "-General", "height");
		String posx = ini.getString(sec + "-General", "posx");
		String posy = ini.getString(sec + "-General", "posy");
		String plug = ini.getString(sec + "-General", "plug");
		String tabnum = ini.getString(sec + "-General", "tabnum");
		String expandertext = ini.getString(sec + "-General", "expandertext");
		String borders = ini.getString(sec + "-General", "borders");
		String imagepath = ini.getString(sec + "-General", "imagepath");
		String rest = ini.getString(sec + "-General", "rest");
		String response = ini.getString(sec + "-General", "response");
		String gtkrc = ini.getString(sec + "-General", "gtkrc");
		String spelllang = ini.getString(sec + "-General", "spelllang");
		String signal = ini.getString(sec + "-General", "signal");
		
		String timeoutposition = ini.getString(sec + "-General", "timeoutposition");
		String btnlayout = ini.getString(sec + "-General", "btnlayout");
		String textalign = ini.getString(sec + "-General", "textalign");
		String hscroll = ini.getString(sec + "-General", "hscroll");
		String vscroll = ini.getString(sec + "-General", "vscroll");
		
		Boolean killparent = ini.getBoolean(sec + "-General", "killparent");
		Boolean imageontop = ini.getBoolean(sec + "-General", "imageontop");
		Boolean undecorated = ini.getBoolean(sec + "-General", "undecorated");
		Boolean fullscreen = ini.getBoolean(sec + "-General", "fullscreen");
		Boolean noescape = ini.getBoolean(sec + "-General", "noescape");
		Boolean nobuttons = ini.getBoolean(sec + "-General", "nobuttons");
		Boolean center = ini.getBoolean(sec + "-General", "center");
		Boolean maximized = ini.getBoolean(sec + "-General", "maximized");
		Boolean expander = ini.getBoolean(sec + "-General", "expander");
		Boolean nomarkup = ini.getBoolean(sec + "-General", "nomarkup");
		Boolean escapeok = ini.getBoolean(sec + "-General", "escapeok");
		Boolean alwaysprint = ini.getBoolean(sec + "-General", "alwaysprint");
		Boolean sticky = ini.getBoolean(sec + "-General", "sticky");
		Boolean fixed = ini.getBoolean(sec + "-General", "fixed");
		Boolean mouse = ini.getBoolean(sec + "-General", "mouse");
		Boolean ontop = ini.getBoolean(sec + "-General", "ontop");
		Boolean skiptaskbar = ini.getBoolean(sec + "-General", "skiptaskbar");
		Boolean splash = ini.getBoolean(sec + "-General", "splash");
		Boolean nofocus = ini.getBoolean(sec + "-General", "nofocus");
		Boolean closeonfocus = ini.getBoolean(sec + "-General", "closeonfocus");
		Boolean selectable = ini.getBoolean(sec + "-General", "selectable");
		Boolean enablespell = ini.getBoolean(sec + "-General", "enablespell");
		
		if (title != null && title.isEmpty() == false)
			lst.add(quote("--title=", title));
		if (icon != null && icon.isEmpty() == false)
			lst.add(quote("--icon=", icon));
		if (timeoutsecs != null && timeoutsecs.isEmpty() == false)
			lst.add(quote("--timeout=", timeoutsecs));
		if (text != null && text.isEmpty() == false)
			lst.add(quote("--text=", text));
		if (image != null && image.isEmpty() == false)
			lst.add(quote("--image=", image));
		if (width != null && width.isEmpty() == false)
			lst.add(quote("--width=", width));
		if (height != null && height.isEmpty() == false)
			lst.add(quote("--height=", height));
		if (posx != null && posx.isEmpty() == false)
			lst.add(quote("--posx=", posx));
		if (posy != null && posy.isEmpty() == false)
			lst.add(quote("--posy=", posy));
		if (plug != null && plug.isEmpty() == false)
			lst.add(quote("--plug=", plug));
		if (tabnum != null && tabnum.isEmpty() == false)
			lst.add(quote("--tabnum=", tabnum));
		if (borders != null && borders.isEmpty() == false)
			lst.add(quote("--borders=", borders));
		if (imagepath != null && imagepath.isEmpty() == false)
			lst.add(quote("--image-path=", imagepath));
		if (rest != null && rest.isEmpty() == false)
			lst.add(quote("--rest=", rest));
		if (response != null && response.isEmpty() == false)
			lst.add(quote("--response=", response));
		if (gtkrc != null && gtkrc.isEmpty() == false)
			lst.add(quote("--gtkrc=", gtkrc));
		if (spelllang != null && spelllang.isEmpty() == false)
			lst.add(quote("--spell-lang=", spelllang));
		if (btnlayout != null && btnlayout.isEmpty() == false)
			lst.add(quote("--buttons-layout=", btnlayout));
		if (textalign != null && textalign.isEmpty() == false)
			lst.add(quote("--text-align=", textalign));
		if (hscroll != null && hscroll.isEmpty() == false)
			lst.add(quote("--hscroll-policy=", hscroll.toLowerCase()));
		if (vscroll != null && vscroll.isEmpty() == false)
			lst.add(quote("--vscroll-policy=", vscroll.toLowerCase()));
		if (timeoutposition != null && timeoutposition.isEmpty() == false && timeoutsecs != null && timeoutsecs.isEmpty() == false)
			lst.add(quote("--timeout-indicator=", timeoutposition));
		
		
		if (buttons != null) {
			
			String[] a = buttons.split(",");

			for (String s : a) {
				String[] b = s.split("!");
				
				lst.add(quote("--button=", b[0] + "\\!" + b[1] + "\\!" + b[2]));
//				System.out.println("--button=\"" + b[0] + "\\!" + b[1] + "\\!" + b[2] + "\"");
			}
		}
		
		if (killparent == true) {
			if (signal == null)
				lst.add("--kill-parent");
			else
				lst.add("--kill-parent=" + signal);
		}
		if (undecorated == true)
			lst.add("--undecorated");
		if (fullscreen == true)
			lst.add("--full-screen");
		if (noescape == true)
			lst.add("--no-escape");
		if (nobuttons == true)
			lst.add("--no-buttons");
		if (center == true)
			lst.add("--center");
		if (maximized == true)
			lst.add("--maximized");
		if (expander == true) {
			if (expandertext == null)
				lst.add("--expander");
			else
				lst.add("--expander=" + expandertext);
		}
		
		if (nomarkup == true)
			lst.add("--no-markup");
		if (escapeok == true)
			lst.add("--escape-ok");
		if (alwaysprint == true)
			lst.add("--always-print-result");
		if (sticky == true)
			lst.add("--sticky");
		if (fixed == true)
			lst.add("--fixed");
		if (mouse == true)
			lst.add("--mouse");
		if (imageontop == true)
			lst.add("--image-on-top");
		if (ontop == true)
			lst.add("--on-top");
		if (skiptaskbar == true)
			lst.add("--skip-taskbar");
		if (splash == true)
			lst.add("--splash");
		if (nofocus == true)
			lst.add("--no-focus");
		if (closeonfocus == true)
			lst.add("--close-on-focus");
		if (selectable == true)
			lst.add("--selectable");
		if (enablespell == true)
			lst.add("--enable-spell");
		
		for (String s : lst) {
			txt.append(s + " ");
		}
		
		return txt.toString();
	}
	
	private  String quote(String s) {

		s = s.replaceAll("\\|", "\\\\|");
		s = s.replaceAll("\\!", "\\\\!");
		
		if (s.indexOf(" ") != -1) {
			s = "\"" + s + "\"";
		}
		
		return s;
	}
	
	private  String quote(String s1, String s2) {
		String r = null;

		s2 = s2.replaceAll("\\|", "\\\\|");
		s2 = s2.replaceAll("\\!", "\\\\!");
		
		if (s2.indexOf(" ") != -1) {
			r = s1 + "'" + s2 + "'";
		} else {
			r = s1 + s2;
		}
		
		return r;
	}
}
