package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.beans.Journee;
import com.model.beans.Journees;
import com.model.beans.Peroide;
import com.model.beans.Peroides;
import com.model.beans.Salles;

/**
 * Servlet implementation class CTRLgererReservation
 */
@WebServlet({ "/CTRLgererReservation", "/gererReservation" })
public class CTRLgererReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CTRLgererReservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/indexReservation.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String choix = request.getParameter("choix");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		switch (choix) {
		case "FormSearch":
			Journees days = new Journees();
			days.initJournees();
			List<Journee> ljournee = days.getListJournees();

			Peroides ps = new Peroides();
			ps.initPeriodes();
			List<Peroide> lPeroides = ps.getListPeroides();

			out.print(
					"<div class='panel-body'> <form action='javascript:void(0);' class='form-inline pull-left' role='form'>"
							+ "<div class='form-group'> <label class='filter-col' style='margin-right: 0;'for='Jour-reservation'>Jours :</label>"
							+ " <select id='Jour-reservation' class='form-control'>");
			for (Journee j : ljournee) {
				out.print("<option value='" + j.getId() + "'>" + j.getJour() + "</option>");
			}

			out.print("</select></div> &nbsp;&nbsp;" + "<!-- form group [search] -->"
					+ "<div class='form-group'><label class='filter-col' style='margin-right: 0;' for='time-reservation'> Houraire :</label> "
					+ "<select id='time-reservation' class='form-control'>");
			for (Peroide p : lPeroides) {
				out.print("<option value='" + p.getId() + "'>" + p.getStartTime() + " -- " + p.getEndTime()
						+ "</option>");
			}
			out.print("</select>" + " </div>"
					+ "<div class='form-group'><label class='filter-col' style='margin-right: 0;'for='capacite'> capacite : &nbsp; </label>"
					+ "<input class='form-control' id='capacites' type='number' value='0'max='200' min='0' /></div>"
					+ "	   &nbsp;&nbsp;&nbsp;<div class='form-group dropdown'>"
					+ "<button class='btn btn-primary dropdown-toggle' type='button' data-toggle='dropdown'>Dropdown Examplespan  <span class='caret'></span></button>"
					+ "<ul class='dropdown-menu'>" + "<li>"
					+ "<div class='' style='padding: 15px; padding-bottom: 10px;'>"
					+ "<form class='form-horizontal' method='post'" + " accept-charset='UTF-8'><div class='form-group'>"
					+ "<label class='col-sm-4 control-label'>Focused</label> <div"
					+ "class='col-sm-8'><input id='sp_uname' class='form-control'"
					+ "type='number' name='' placeholder='NBR Equipment ..' />" + " </div></div> </form>" + "</li>"
					+ "</ul></div>"
					+ "&nbsp;&nbsp;&nbsp; <button type='submit' onclick='SearchSalleAdvanced();' class='btn btn-success' value='Search'> <span class='glyphicon glyphicon-search' ></span> Search"
					+ "</button>" + "</form></div></div>");
			break;
		case "SearchSalleAdvanced":
			
			int id_jour =Integer.parseInt(request.getParameter("jour_id"));
			int time_id= Integer.parseInt(request.getParameter("time_id"));
			int capacite= Integer.parseInt(request.getParameter("capacite"));
			
			Salles s =new Salles();
			
			
			break;

		default:
			break;
		}
	}

}
