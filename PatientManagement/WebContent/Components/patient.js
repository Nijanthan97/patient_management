$(document).ready(function() {

	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}

	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();
	if (status != true) {

		$("#alertError").text(status);
		$("#alertError").show();

		return;
	}

	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "patientAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

	// $("#formItem").submit();

});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "patientAPI",
		type : "DELETE",
		data : "PID=" + $(this).data("pid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

$(document)
		.on("click",".btnUpdate",function(event) {
					$("#hidItemIDSave").val(
							$(this).closest("tr").find('#hidItemIDUpdate')
									.val());
					$("#pName").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#gender").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#phone").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#address")
							.val($(this).closest("tr").find('td:eq(3)').text());
					$("#age").val(
							$(this).closest("tr").find('td:eq(4)').text());
					$("#bloodGroup").val(
							$(this).closest("tr").find('td:eq(5)').text());
					$("#nextOfKin").val(
							$(this).closest("tr").find('td:eq(6)').text());

				});

// CLIENT-MODEL================================================================
function validateItemForm() {
	// holderName
	if ($("#pName").val().trim() == "") {

		return "Insert Patient Name.";
	}
	// gender
	if ($('input[name="gender"]:checked').length === 0) {
		return "Select gender.";
	}
	// cardNo-------------------------------
	if ($("#phone").val().trim() == "") {

		return "Insert mobile Number.";
	}

	var temcard = $("#phone").val().trim();
	if (!(/07[1,2,5,6,7,8][0-9]{7}/)
			.test(temcard)) {

		return "Enter the valid mobile number.";

	}

	// cvv------------------------
	if ($("#address").val().trim() == "") {

		return "Insert address .";
	}

	
	// total------------------------
	if ($("#age").val().trim() == "") {

		return "Enter your age.";
	}

	var tmpPrice = $("#age").val().trim();
	if (!$.isNumeric(tmpPrice)) {
		return "Enter numerical value.";
	}
	
	
	if ($("#bloodGroup").val().trim() == "") {

		return "Insert bloodGroup .";
	}
	
	if ($("#nextOfKin").val().trim() == "") {

		return "Insert nextOfKin .";
	}
	
	
	return true;
	
	
}