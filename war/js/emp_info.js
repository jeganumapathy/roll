$(document).ready(postData());

function postData() {
	$("#target").click(
			function(e) {
				e.preventDefault();
				var emp_name = $('#emp_name').val();
				var emp_no = $('#emp_no').val();
				var pan_no = $('#pan_no').val();
				var ctc_annum = $('#ctc_annum').val();
				var department = $('#department').val();
				var designation = $('#designation').val();
				var start_date = $('#start_date').val();
				var all_required = false;
				if (start_date == null && pan_no == null && emp_name == null
						&& emp_no == null) {
				} else {
				}
				// check for validation
				$.post("/app/store", {
					emp_name : emp_name,
					emp_no : emp_no,
					pan_no : pan_no,
					ctc_annum : ctc_annum,
					department : department,
					designation : designation,
					start_date : start_date
				}, function(data, status) {
					showAlert(data, status);
				});
				return false;
			});
}
function showAlert(data, status) {
	$("#main").hide();
	alert(status);
	$("#result").html("<p>" + status + "</p>");
}
$(function() {
	$('#datetimepicker1').datetimepicker({
		pickTime : false
	});
});

$(function() {
	$('#datetimepicker2').datetimepicker({
		pickTime : false
	});

});

$(function() {
	$('#defaultForm')
			.bootstrapValidator(
					{
						// live: 'disabled',
						message : 'This value is not valid',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							emp_name : {
								message : 'The Employee name is not valid',
								validators : {
									notEmpty : {
										message : 'The Employee name is required and cannot be empty'
									},
									stringLength : {
										min : 4,
										max : 30,
										message : 'The Employee name must be more than 4 and less than 30 characters long'
									},
									regexp : {
										regexp : /^[a-zA-Z]+$/,
										message : 'The Employee name can only consist of alphabetical'
									}
								}
							},
							emp_no : {
								validators : {
									notEmpty : {
										message : 'The Employee No is required and cannot be empty'
									},
									regexp : {
										regexp : /^[a-zA-Z0-9]+$/,
										message : 'The Employee No can only consist of alphabetical, number'
									}
								}
							},
							pan_no : {
								validators : {
									notEmpty : {
										message : 'The PAN No is required and cannot be empty'
									},
									regexp : {
										regexp : /^[a-zA-Z0-9]+$/,
										message : 'The PAN No can only consist of alphabetical, number'
									}
								}
							},
							ctc_annum : {
								validators : {
									notEmpty : {
										message : 'The CTC No is required and cannot be empty'
									},
									regexp : {
										regexp : /^[0-9]+$/,
										message : 'The CTC No can only contain  number'
									}
								}
							},
							start_date : {
								validators : {
									notEmpty : {
										message : 'The start date  is required and cannot be empty'
									},
									date : {
										format : 'MM/DD/YYYY'
									}
								}
							}
						}
					});
});