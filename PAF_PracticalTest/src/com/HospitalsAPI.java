package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//(Pradeep H.A.T)
@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Hospital hospitalObj = new Hospital();
       
  
    public HospitalsAPI() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		String output = hospitalObj.insertHospital(
				
				request.getParameter("MOHcode"),
                request.getParameter("ManagerName"),
                request.getParameter("HospitalName"),
                request.getParameter("Address"),
                request.getParameter("TPnumber").replace("0","0"),
                request.getParameter("Location")

				
				);
		
		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = hospitalObj.updateHospital(

                paras.get("hidHospitalIDSave").toString(),
                paras.get("MOHcode").toString(),
                paras.get("ManagerName").toString().replace('+', ' '),
                paras.get("HospitalName").toString().replace('+', ' '),
                paras.get("Address").toString().replace('+', ' ').replaceAll("%2F", "/").replace("."," ").replaceAll("%2C", ",").replaceAll("%0D%0A", " "),
                paras.get("TPnumber").toString(),
                paras.get("Location").toString().replace('+', ' ').replaceAll("%2F", "/")


        );

        response.getWriter().write(output);
}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = hospitalObj.deleteHospital(paras.get("HospitalID").toString());
		
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map(Pradeep H.A.T)
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		}
		catch (Exception e)
		{
		}
		
		return map;
	}
}
