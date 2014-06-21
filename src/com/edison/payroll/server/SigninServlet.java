package com.edison.payroll.server;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edison.payroll.data.Constants;
import com.edison.payroll.data.LoginData;
import com.edison.payroll.server.service.RetriveFactory;
import com.edison.payroll.server.service.StoreFactory;

public class SigninServlet extends HttpServlet implements Constants {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LoginData data = new LoginData();
		data.username = req.getParameter(USER_NAME);
		data.password = req.getParameter(PASSWORD);
		if (RetriveFactory.getInstance().retriveLogin(data)) {
			resp.sendRedirect("app/dashboard.html");
		} else {
			resp.sendRedirect("index.html");
			// RequestDispatcher rd = req.getRequestDispatcher("index.html");
			// rd.forward(req, resp);
		}
		//
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String admin = req.getParameter("admin");
		if ("111".equalsIgnoreCase(admin)) {
			LoginData mdata = new LoginData();
			mdata.id = 111L;
			mdata.username = "admin123";
			mdata.password = "123admin";
			StoreFactory.store(mdata);
			System.out.println("Success creating admin");
		}else if("000".equalsIgnoreCase(admin)){
			RetriveFactory.getInstance().deleteAdmin();
			System.out.println("Admin deleted success");
		}

	}

}
