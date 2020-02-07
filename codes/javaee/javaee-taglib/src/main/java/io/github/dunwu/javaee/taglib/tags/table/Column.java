package io.github.dunwu.javaee.taglib.tags.table;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class Column extends TagSupport {

	private static final long serialVersionUID = 5119493903438602864L;

	private String property;

	private String label;

	private String type;

	public int doStartTag() throws JspException {
		if (!(this.getParent() instanceof Table)) {
			throw new JspException("Column must be inside Table. ");
		}

		Map<String, String> column = new HashMap<String, String>();

		column.put("label", label);
		column.put("property", property);
		column.put("type", type);

		Table table = (Table) this.getParent();

		table.getColumns().add(column);

		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
