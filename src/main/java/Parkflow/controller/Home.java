package Parkflow.controller;

import Parkflow.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "Uma mensagemsinha";
		
		MaintenanceRepository maintenancesRepo = new MaintenanceRepository();
		
		List<Maintenance> maintenances = maintenancesRepo.GetNextMaintenances();
		
		request.setAttribute("maintenances", maintenances);
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		System.out.println(action);
			
		String cnpj = request.getParameter("cnpj");
		String observations = request.getParameter("observation");
		String _date = request.getParameter("date");
		String kind =  request.getParameter("ikind");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		CompanyRepository cRepo = new CompanyRepository();
		Company selectedCompany = cRepo.GetByCnpj(cnpj);

		Maintenance body = new Maintenance(0, kind, observations, LocalDate.parse(_date, formatter), selectedCompany.getId());

		if(action.equals("UPDATE")) {
			System.out.println("Update no id "+ Integer.parseInt(request.getParameter("id")));
			body.setId(Integer.parseInt(request.getParameter("id")));
		}
			
		MaintenanceRepository mRepo = new MaintenanceRepository();
		action = action.toUpperCase();
		if(action.equals("ADD")) {
			System.out.println("----add ----");
			mRepo.Add(body);
		}
		else if(action.equals("UPDATE")) {

			System.out.println("----update ----");
			mRepo.Update(body);
		}	
		else if(action.equals("DELETE")) {
			System.out.println("----delete ----");
			mRepo.Delete(Integer.parseInt(request.getParameter("id")));
		}
		System.out.println(action + " Successfully");
		response.sendRedirect(request.getContextPath() + "/home"); 

	}
}
