package com.edison.payroll.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.io.IOUtils;

public class TemplateText {

	public String getTemplate(HttpServlet servlet, String result)
			throws IOException {
		ServletContext context = servlet.getServletContext();
		String textIndex = "class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">";
		InputStream is = context.getResourceAsStream("/temp.txt");
		StringWriter writer = new StringWriter();
		IOUtils.copy(is, writer, "UTF-8");
		String template = writer.toString();
		template = template.replace("<template/>", result);
		return template;
	}
}
