package com.edison.payroll.server.service;

import java.util.List;
import static com.edison.payroll.server.service.OfyService.ofy;

import com.edison.payroll.data.EmployeeData;
import com.edison.payroll.data.LoginData;

public class RetriveFactory {
	private static RetriveFactory instance;
	public static final int READ_LIMIT = 500;

	/**
	 * Retrieve a instance for store factory
	 * 
	 * @return
	 */
	public static RetriveFactory getInstance() {
		if (instance == null) {
			instance = new RetriveFactory();
		}
		return instance;
	}

	public boolean retriveLogin(LoginData data) {
		List<LoginData> c = ofy().load().type(LoginData.class)
				.limit(READ_LIMIT).list();
		for (LoginData loginData : c) {
			if (data != null && data.username != null) {
				if (data.username.equals(loginData.username)
						&& data.password.equals(loginData.password)) {
					return true;
				}
			}
		}
		return false;
	}

	public void deleteAdmin() {
		ofy().delete().type(LoginData.class).id(111L).now();
	}

	public EmployeeData retriveEmpFromName(String empName) {
		EmployeeData emp = ofy().load().type(EmployeeData.class)
				.filter("empName", empName).first().now();
		return emp;
	}

	public EmployeeData retriveEmpFromid(int empNo) {
		EmployeeData emp = ofy().load().type(EmployeeData.class)
				.filter("empNo", empNo).first().now();
		return emp;
	}

	public List<EmployeeData> retriveAllEmo() {
		List<EmployeeData> c = ofy().load().type(EmployeeData.class)
				.limit(READ_LIMIT).list();
		return c;
	}

}
