$(document).ready(function() {
	$.post("/app/retrive_emp", {}, function(data, status) {
		showAlert(data, status);
	});
});
function showAlert(data, status) {
	console.log(data);
	createJSONArray(data);
	buildHtmlTable();

	//
}

function createJSONArray(data) {

	var jsonObj = $.parseJSON(data);
	console.log("into json array" + jsonObj.length);
	var status = "";
	var jsonArr = [];
	$.each(jsonObj, function(index, value) {
		console.log(value);
		var empName = value.empName;
		var empNo = value.empNo;
		var start_date = value.startDate;
		var end_date = value.endDate;
		var paid_till = value.startDate;
		jsonArr.push({
			"Employee-Name" : empName,
			"Employee-No" : empNo,
			"Start-Date" : start_date,
			"End-Date" : end_date,
			"Paid-Till" : paid_till,
			"Offer Generated" : "No"
		});
	});
	myList = jsonArr;
}

// Used for active and inactive all button function
$(function() {
	$(".dropdown-menu li a").click(function() {
		$(".btn:first-child").text($(this).text());
		$(".btn:first-child").val($(this).text());
		// load the test
	});

});

var myList = [];
// Builds the HTML Table out of myList.
function buildHtmlTable() {
	var columns = addAllColumnHeaders(myList);

	for ( var i = 0; i < myList.length; i++) {
		var row$ = $('<tr/>');
		for ( var colIndex = 0; colIndex < columns.length; colIndex++) {
			var cellValue = myList[i][columns[colIndex]];

			if (cellValue == null) {
				cellValue = "";
			}

			row$.append($('<td/>').html(cellValue));
		}
		$("#excelDataTable").append(row$);
	}
}

// Adds a header row to the table and returns the set of columns.
// Need to do union of keys from all records as some records may not contain
// all records
function addAllColumnHeaders(myList) {
	var columnSet = [];
	var headerTr$ = $('<tr/>');
	for ( var i = 0; i < myList.length; i++) {
		var rowHash = myList[i];
		for ( var key in rowHash) {
			if ($.inArray(key, columnSet) == -1) {
				columnSet.push(key);
				headerTr$.append($('<th/>').html(key));
			}
		}
	}
	$("#excelDataTable").append(headerTr$);
	return columnSet;
}