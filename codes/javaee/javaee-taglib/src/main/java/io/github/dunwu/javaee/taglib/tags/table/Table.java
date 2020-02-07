package io.github.dunwu.javaee.taglib.tags.table;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class Table extends BodyTagSupport {

	private static final long serialVersionUID = 3358444196409845360L;

	/**
	 * 存储列信息
	 */
	private List<Map<String, String>> columns = new ArrayList<Map<String, String>>();

	/**
	 * 存储数据，可能为 集合类型的或者数组类型的
	 */
	private Object items;

	/**
	 * 取排序数据的 URL
	 */
	private String url;

	@Override
	public int doStartTag() throws JspException {
		columns.clear();

		return super.doStartTag();
	}

	@Override
	@SuppressWarnings("unchecked")
	public int doAfterBody() throws JspException {
		try {
			BodyContent bc = getBodyContent();
			JspWriter out = bc.getEnclosingWriter();

			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

			/** 按哪一列排序 */
			String orderName = request.getParameter("orderName");
			/** 按升序还是降序排序 */
			String orderType = request.getParameter("orderType");

			orderType = "desc".equals(orderType) ? "desc" : "asc";

			out.println("<table id=theObjTable");
			out.println("   class=list_table STYLE='table-layout:fixed;' >");
			out.println("   <tr class=tr_title>");
			out.println("   <script>var columns = []; </script>");

			for (int i = 0; i < columns.size(); i++) {
				/** 获取列信息 */
				Map<String, String> column = columns.get(i);

				/** 列头 */
				String label = column.get("label");
				/** 该列对应的 Java Bean 的属性 */
				String property = column.get("property");

				label = label == null ? property : label;

				out.println("<td id='__id_td_" + property + "'>");
				out.println("<font class='resizeDivClass'");
				out.println("   onmousedown='MouseDownToResize(this);');");
				out.println("   onmousemove='MouseMoveToResize(this);'");
				out.println("   onmouseup='MouseUpToResize(this);'></font>");

				out.println("<span onclick=\"sort('" + property + "'); \"");
				out.println(" style=\"cursor: pointer; \">");

				out.println(label);

				if (property.equals(orderName)) {

					out.println("<img src='images/" + orderType + ".gif' border=0/>");
				}

				out.println("</span>");

				out.println("</td>");
				out.println("<script>columns[columns.length] = '__id_td_" + property + "'; </script>");
			}

			out.println("   </tr>");

			if (items != null) {

				/** 遍历所有的数据 */
				for (Object obj : (Iterable) items) {

					out.println("   <tr class=tr_data>");

					for (int i = 0; i < columns.size(); i++) {

						Map<String, String> column = columns.get(i);

						String property = column.get("property");

						String getterStyle = toGetterStyle(property);

						try {
							String getter = "get" + getterStyle;
							String is = "is" + getterStyle;

							Method method = null;

							try {
								/** 获取 getXxx() 形式的方法 */
								method = obj.getClass().getMethod(getter);
							} catch (Exception e) {
							}

							if (method == null) {
								/** 如果没有，获取 isXxx() 形式的方法 */
								method = obj.getClass().getMethod(is);
							}

							method.setAccessible(true);

							/** 获取属性值 */
							Object value = method.invoke(obj);
							out.println("<td><span title='" + value + "'>" + value + "</span></td>");
						} catch (Exception e) {
							throw new JspException(e);
						}
					}
					out.println("   </tr>");
				}
			}

			out.println("</table>");

			out.println("<script>");
			out.println("   var orderName = '" + orderName + "'; ");
			out.println("   var orderType = '" + orderType + "'; ");
			out.println("   function sort(column){");
			out.println("       if(orderName == column){");
			out.println("           location='" + url
				+ "?orderName=' + column + '&orderType=' + (orderType=='asc' ? 'desc' : 'asc'); ");
			out.println("       }");
			out.println("       else{");
			out.println("           location='" + url + "?orderName=' + column + '&orderType=' + orderType; ");
			out.println("       }");
			out.println("   }");
			out.println("</script>");
		} catch (IOException ioe) {
			throw new JspException("Error: " + ioe.getMessage());
		}

		return SKIP_BODY;
	}

	/**
	 * 首字母大写
	 *
	 * @param column
	 * @return
	 */
	public String toGetterStyle(String column) {
		if (column.length() == 1) {
			return column.toUpperCase();
		}

		char ch = column.charAt(0);

		return Character.toUpperCase(ch) + column.substring(1);
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public List<Map<String, String>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<String, String>> columns) {
		this.columns = columns;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
