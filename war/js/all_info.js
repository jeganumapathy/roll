$(document).ready(function() {
	$("#search").on("keyup", function() {
		var emp_name = $(this).val();
		console.log(emp_name);
		$.get("/app/retrive_emp", {
			name : emp_name
		}, function(data, status) {
			showAlert(data, status);
		});
	});

});
function showAlert(data, status) {
	var value = $.parseJSON(data);
	console.log(value);
	var empName = value.empName;
	var empNo = value.empNo;
	var panNo = value.panNo;
	var ctc_annum = value.ctc_annum;
	var designation = value.designation;
	var department = value.department;
	var startDate = value.startDate;
	console.log(value.empName);
	$("#emp_no").text(empNo);
	$("#emp_name").text(empName);
	$("#pan_no").text(panNo);
	$("#ctc_annum").text(ctc_annum);
	$("#department").text(department);
	$("#designation").text(designation);
	$("#start_date").text(startDate);
}
