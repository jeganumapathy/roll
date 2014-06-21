package com.edison.payroll.server.service;

import com.edison.payroll.data.Constants;
import com.edison.payroll.data.EmployeeData;
import com.edison.payroll.data.LoginData;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService implements Constants {

	static {
		factory().register(LoginData.class);
		factory().register(EmployeeData.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
