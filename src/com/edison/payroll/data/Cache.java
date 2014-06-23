package com.edison.payroll.data;

import java.util.ArrayList;
import java.util.List;

import com.edison.payroll.server.service.RetriveFactory;

/**
 * @author jegan
 * 
 *         Temp storage of all data
 */
public class Cache {

	private static final int INTIAL_CAPACITY = 100;
	private static List<EmployeeData> empCache = new ArrayList<EmployeeData>(
			INTIAL_CAPACITY);;

	public static void clearEmpCache() {
		if (!empCache.isEmpty()) {
			empCache.clear();
		}
	}

	public static void putEmpData() {
		if (empCache.isEmpty()) {
			empCache = RetriveFactory.getInstance().retriveAllEmo();
		}
	}

	public static List<EmployeeData> getCache() {
		return empCache;
	}

	public static EmployeeData searchEmpName(String empName) {
		if (!empCache.isEmpty()) {
			for (EmployeeData emp : empCache) {
				if (emp.getEmpName().equalsIgnoreCase(empName)) {
					return emp;
				}
			}
		}
		return null;
	}
}
