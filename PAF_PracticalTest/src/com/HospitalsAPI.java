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


/**
 * Servlet implementation class HospitalsAPI
 */
@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Hospital hospitalObj = new Hospital();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		String output = hospitalObj.insertHospital(
				
				request.getParameter("MOHcode"),
                request.getParameter("ManagerName"),
                request.getParameter("HospitalName"),
                request.getParameter("Address"),
                request.getParameter("TPnumber"),
                request.getParameter("Location")

				
				);
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = hospitalObj.updateHospital(

                paras.get("hidHospitalIDSave").toString(),
                paras.get("MOHcode").toString(),
                paras.get("ManagerName").toString().replace('+', ' '),
                paras.get("HospitalName").toString().replace('+', ' '),
                paras.get("Address").toString().replace('+', ' ').replaceAll("%2F", "/"),
                paras.get("TPnumber").toString(),
                paras.get("Location").toString().replace('+', ' ')


        );

        response.getWriter().write(output);
}

}
