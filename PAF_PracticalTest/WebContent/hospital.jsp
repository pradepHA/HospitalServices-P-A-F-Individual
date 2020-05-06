<%@page import="com.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/hospital.js"></script>

</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
			<h1>Hospital Management</h1>
			<!-- <div class="pull-right">
			<div class="col-xs-12">
            <a id="buttonscroll" style="color: #FAFAFA" class="btn btn-success"
           	type="button">View Registered Hospitals</a>
            </div>
            </div> -->
			
			<form id="formHospital" name="formHospital" method="post" action="hospital.jsp">

				MOH Code:
				<input id="MOHcode" name="MOHcode" type="text" class="form-control form-control-sm">
				<br>
				 
				Manager Name:
				<input id="ManagerName" name="ManagerName" type="text" class="form-control form-control-sm">
				<br>
				
				Hospital Name:
				<input id="HospitalName" name="HospitalName" type="text" class="form-control form-control-sm">
				<br>
				 
				Address:
				<input id="Address" name="Address" type="text" class="form-control form-control-sm">
				<br>
								
				TP Number:
				<input id="TPnumber" name="TPnumber" type="text" class="form-control form-control-sm">
				<br>
				
				Location:
				<input id="Location" name="Location" type="text" class="form-control form-control-sm">
				<br>
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
				<a href="hospital.jsp" class="btn btn-danger" onclick="" type="button">Cancel</a>
				<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>

			<!-- START card -->

            <!-- <div id="view" class="card-header">
            	<div class="card-title">View Hospitals
            	</div>
            </div> -->
            <div class="card-body">
              	<div class="row">
                	<h3>List of Hospitals that Registered</h3>
               	</div>

	       		<div class="clearfix"></div>
            </div>
                <div class="card-body">


            <div id="divHospitalsGrid">
				<%
					Hospital HospitalObj = new Hospital();
					out.print(HospitalObj.readHospital());
				%>
			</div>
           <!--  </table> -->
         	</div>
            </div>
                                            <!-- END card -->
			
		</div>
	</div>
</div>
</body>
</html>