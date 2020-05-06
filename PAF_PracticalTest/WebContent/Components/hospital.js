$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	
	$("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateHospitalForm();
	
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var method = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "HospitalsAPI",
		type : method,
		data : $("#formHospital").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalSaveComplete(response.responseText, status);
		}
	});
});

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	
    $("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
	$("#MOHcode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ManagerName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#HospitalName").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Address").val($(this).closest("tr").find('td:eq(3)').text());
	$("#TPnumber").val($(this).closest("tr").find('td:eq(4)').text());
	$("#Location").val($(this).closest("tr").find('td:eq(5)').text());
});

function onHospitalSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divHospitalsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}

//DELETE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		url : "HospitalsAPI",
		type : "DELETE",
		data : "HospitalID=" + $(this).data("hospitalid"),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalDeleteComplete(response.responseText, status);
		}
	});
});

function onHospitalDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divHospitalsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} 
	else
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//VALIDATION====================================
function validateHospitalForm()
{
	// MOHcode
    if ($("#MOHcode").val().trim() == "") {

        return "Insert the MOH Code ";

    }

    // ManagerName
    if ($("#ManagerName").val().trim() == "") {

        return "Insert the Manager Name ";

    }
    
//  //cannot be consist of numbers
//    var regexp = /[^a-zA-Z]/g;
//	
//	if ($("#ManagerName").val().trim().match(regexp))
//	{
//		return "Hospital Name cannot be consist of numbers";
//	}

    // HospitalName
    if ($("#HospitalName").val().trim() == "") {

        return "Insert the Hospital Name ";

    }
    
//    //cannot be consist of numbers
//    var regexp = /[^a-zA-Z]/g;
//	
//	if ($("#HospitalName").val().trim().match(regexp))
//	{
//		return "Hospital Name cannot be consist of numbers";
//	}

    // Address
    if ($("#Address").val().trim() == "") {

        return "Insert the Hospital Address.";

    }
    // TPnumber
    if ($("#TPnumber").val().trim() == "") {

        return "Insert the Hospital TP number.";

    }
    var TPnumber = $("#TPnumber").val().trim();
    
    // is numerical value
	if (!$.isNumeric(TPnumber))
	{
		return "Insert a numerical value for Telephonr Number.";
	}
  
	//length validate
	if ($("#TPnumber").val().length > 10){
		return "More than 10 digits are included! in Mobile No"
	}
	if ($("#TPnumber").val().length < 10){
		return "Please enter 10 digits for Mobile No"
		
	}
	// Location
    if ($("#Location").val().trim() == "") {

        return "Insert the Hospital Location.";

    }


    return true;
}

//Scroll Button
$("#buttonscroll").click(function() {
    $('html, body').animate({
        scrollTop: $("#divHospitalsGrid").offset().top
    }, 1500);
});