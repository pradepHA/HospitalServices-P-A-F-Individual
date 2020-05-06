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
<link href="Views/css/main.css" rel="stylesheet" media="all">

</head>
<body>
<div class="container">
<h1>Hospital Management</h1><br><br>
	<div class="row">
		<div class="col-6">
			<h2>Adding or Updating a Hospital </h2><br>
						
			<form id="formHospital" name="formHospital" method="post" action="hospital.jsp">
					 
				MOH Code:
				<input id="MOHcode" name="MOHcode" type="text" placeholder="		Enter MOH Code....(ex-GOV####)" class="form-control form-control-sm">
				<br><br>
				 
				Manager Name:
				<input id="ManagerName" name="ManagerName" type="text" placeholder="		Enter Manager Name .... " class="form-control form-control-sm">
				<br><br>
				
				Hospital Name:
				<input id="HospitalName" name="HospitalName" type="text" placeholder="		Enter Hospital Name .... " class="form-control form-control-sm">
				<br><br>
				 
				Address:
				<input id="Address" name="Address" type="text" placeholder="		Enter Hospital Address .... " class="form-control form-control-sm">
				<br><br>
								
				TP Number:
				<input id="TPnumber" name="TPnumber" type="text" placeholder="		Enter Telephone Number .... " class="form-control form-control-sm">
				<br><br>
				
				Location:
				<input id="Location" name="Location" type="text" placeholder="		Enter Hospital Location .... " class="form-control form-control-sm">
				<br><br>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div><br>
				
				<input id="btnSave" name="btnSave" type="button" value="Save" class="btn1 btn-primary" >
				<a href="hospital.jsp" class="btn1 btn-danger" onclick="" type="button" style="text-align: center" >Cancel</a>
				<input type="hidden" id="hidHospitalIDSave" name="hidHospitalIDSave" value="">
			</form><br><br><br>
			
		        	       	
			 <h3>List of Hospitals that Registered</h3>
			 
            <div id="divHospitalsGrid">
				<%
					Hospital HospitalObj = new Hospital();
					out.print(HospitalObj.readHospital());
				%>
			</div>
           <!--  </table> -->
         	</div>
            </div>
                                            
			
		</div>
	</div>
</div>
</body>
</html>