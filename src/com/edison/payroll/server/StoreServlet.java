package com.edison.payroll.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edison.payroll.data.Constants;
import com.edison.payroll.data.EmployeeData;
import com.edison.payroll.data.TemplateText;
import com.edison.payroll.server.service.StoreFactory;

public class StoreServlet extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;
	private TemplateText template;

	public StoreServlet() {
		template = new TemplateText();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		EmployeeData employeeData = new EmployeeData();
		employeeData.setEmpName(req.getParameter(EMP_NAME));
		String emp_no = req.getParameter(EMP_NO);
		employeeData.setEmpNo(emp_no);
		String ctc_annum = req.getParameter(CTC_ANNUM);
		employeeData.setCtc_annum(ctc_annum);
		String depart = req.getParameter(DEPARTMENT);
		employeeData.setDepartment(depart);
		String desig = req.getParameter(DESIGNATION);
		employeeData.setDesignation(desig);
		String startDate = req.getParameter(START_DATE);
		employeeData.setStartDate(startDate);
		employeeData.setPanNo(req.getParameter(PAN_NO));
		String result = StoreFactory.store(employeeData);
		res.getWriter().println(
				template.getTemplate(this, "<h1>Successfully stored</h1>"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

}
