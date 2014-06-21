package com.edison.payroll.server.service;

import static com.edison.payroll.server.service.OfyService.ofy;

import com.edison.payroll.data.AbstractDatastore;
import com.edison.payroll.data.Constants;
import com.edison.payroll.data.EmployeeData;
import com.edison.payroll.data.LoginData;

public class StoreFactory implements Constants {
	private static StoreFactory instance;

	public static final int STORE_LIMIT = 100;

	/**
	 * Retrieve a instance for store factory
	 * 
	 * @return
	 */
	public static StoreFactory getInstance() {
		if (instance == null) {
			instance = new StoreFactory();
		}
		return instance;
	}

	public static String store(AbstractDatastore dataStore) {
		if (dataStore instanceof LoginData) {
			LoginData obj = (LoginData) dataStore;
			ofy().save().entity(obj).now();
		} else if (dataStore instanceof EmployeeData) {
			EmployeeData obj = (EmployeeData) dataStore;
			ofy().save().entity(obj).now();
		}
		return "Succces";
	}

}
