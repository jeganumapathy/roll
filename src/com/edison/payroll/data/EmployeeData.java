package com.edison.payroll.data;

import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@Embed
public class EmployeeData extends AbstractDatastore {
	@Id
	public Long id;
	private String empName;
	private String empNo;
	private String firstName;
	private String lastName;
	private String panNo;
	private String ctc_annum;
	private String department;
	private String designation;
	private String location;
	private String startDate;
	private String bank_account_name;
	private String bank_account_no;
	private String account_type;
	private String ifsc_code;
	private String ctc_template;
	private String salary_effective_date;
	private boolean isOfferGenerated;
	private State emp_state = State.ACTIVE;

	public static enum State {
		ACTIVE, INACTIVE
	}

	public String getBank_account_name() {
		return bank_account_name;
	}

	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}

	public String getBank_account_no() {
		return bank_account_no;
	}

	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getCtc_template() {
		return ctc_template;
	}

	public void setCtc_template(String ctc_template) {
		this.ctc_template = ctc_template;
	}

	public String getSalary_effective_date() {
		return salary_effective_date;
	}

	public void setSalary_effective_date(String salary_effective_date) {
		this.salary_effective_date = salary_effective_date;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getCtc_annum() {
		return ctc_annum;
	}

	public void setCtc_annum(String ctc_annum) {
		this.ctc_annum = ctc_annum;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public State getEmp_sate() {
		return emp_state;
	}

	public void setEmp_sate(State emp_sate) {
		this.emp_state = emp_sate;
	}

	public boolean isOfferGenerated() {
		return isOfferGenerated;
	}

	public void setOfferGenerated(boolean isOfferGenerated) {
		this.isOfferGenerated = isOfferGenerated;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
