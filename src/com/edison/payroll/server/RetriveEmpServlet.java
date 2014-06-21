package com.edison.payroll.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edison.payroll.data.EmployeeData;
import com.edison.payroll.server.service.RetriveFactory;
import com.google.gson.Gson;

public class RetriveEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static List<EmployeeData> c;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		c = RetriveFactory.getInstance().retriveAllEmo();
		String json = new Gson().toJson(c);
		resp.getWriter().println(json);
	}

}
