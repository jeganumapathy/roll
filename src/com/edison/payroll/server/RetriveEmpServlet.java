package com.edison.payroll.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edison.payroll.data.Cache;
import com.edison.payroll.data.EmployeeData;
import com.google.gson.Gson;

public class RetriveEmpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson = null;

	/**
	 * 
	 */
	public RetriveEmpServlet() {
		gson = new Gson();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String emp_name = req.getParameter("name");
		EmployeeData emp = Cache.searchEmpName(emp_name);
		String json = new String();
		if (emp != null) {
			json = gson.toJson(emp);
		} else {
			json = "error";
		}
		resp.getWriter().println(json);
	}

	/*
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cache.putEmpData();
		String json = gson.toJson(Cache.getCache());
		resp.getWriter().println(json);
	}

}
