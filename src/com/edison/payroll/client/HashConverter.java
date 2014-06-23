package com.edison.payroll.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.edison.payroll.data.EmployeeData;

public class HashConverter {

	public HashMap<Integer, String> coloum = new HashMap<Integer, String>();
	public static HashMap<Emp, Integer> ol = new HashMap<Emp, Integer>();

	public void setHeaderHash(HashMap<Integer, String> coloum) {
		this.coloum = coloum;
	}

	enum Emp {
		ID, NAME, PAN_NO, START_DATE, DEPARTMENT, DESIGNATION, LOCATION
	};

	public static final String TEXT_NO = "No";

	public void iterate() {
		int len = coloum.size();
		String title = new String();
		for (int i = 0; i < len; i++) {
			// if 0 key contains id map all 0 as id
			title = coloum.get(i);
			if ((pf(title, TEXT_NO) && pf(title, "emp"))
					|| (pf(title, "id") && pf(title, "emp"))) {
				ol.put(Emp.ID, i);
			} else if (pf(title, "name") && pf(title, "emp")) {
				ol.put(Emp.NAME, i);
			} else if (pf(title, TEXT_NO) && pf(title, "pan")) {
				ol.put(Emp.PAN_NO, i);
			} else if (pf(title, "start") && pf(title, "date")) {
				ol.put(Emp.START_DATE, i);
			} else if (pf(title, "designation")) {
				ol.put(Emp.DESIGNATION, i);
			} else if (pf(title, "department")) {
				ol.put(Emp.DEPARTMENT, i);
			}
		}
	}

	/**
	 * Pattern finder methods equals to contains method
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean pf(String s1, String s2) {
		return Pattern.compile(Pattern.quote(s2), Pattern.CASE_INSENSITIVE)
				.matcher(s1).find();
	}

	public EmployeeData convertHash(HashMap<Integer, String> temp) {
		EmployeeData mData = new EmployeeData();
		Iterator iterator2 = temp.keySet().iterator();
		while (iterator2.hasNext()) {
			try {
				String key = iterator2.next().toString();
				Integer mInteger = Integer.valueOf(key);
				String value = temp.get(mInteger);
				System.out.println("Temp " + mInteger + ":" + value);
				if (value != null) {
					if (mInteger.equals(ol.get(Emp.ID))) {
						mData.setEmpNo(value);
					} else if (mInteger.equals(ol.get(Emp.NAME))) {
						mData.setEmpName(value);
					} else if (mInteger.equals(ol.get(Emp.PAN_NO))) {
						mData.setPanNo(value);
					} else if (mInteger.equals(ol.get(Emp.START_DATE))) {
						mData.setStartDate(value);
					} else if (mInteger.equals(ol.get(Emp.DESIGNATION))) {
						mData.setDesignation(value);
					} else if (mInteger.equals(ol.get(Emp.DEPARTMENT))) {
						mData.setDepartment(value);
					} else if (mInteger.equals(ol.get(Emp.LOCATION))) {
						mData.setLocation(value);
					}
				}

			} catch (Exception e) {
			}
		}
		return mData;
	}
}
