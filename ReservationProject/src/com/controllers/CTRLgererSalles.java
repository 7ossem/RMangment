package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.beans.Equipment;
import com.model.beans.Salle;
import com.model.beans.Salles;

/**
 * Servlet implementation class CTRLgererSalles
 */
@WebServlet({ "/CTRLgererSalles", "/gerersalles" })
public class CTRLgererSalles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CTRLgererSalles() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/gererSalles.jsp").forward(request, response);
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
		case "ConsulterListSalles":
		Salles csalles =new Salles();
		Salle cs =new Salle();
		List<Salle> clist =csalles.getListSalles();
		  if(clist != null){
			  for (Salle ccslist : clist) {
					out.print("<tr id ='" + ccslist.getNum() + "'>" + "<td>" + ccslist.getCapacite() + "</td>");
					out.print("<td><ul class='list-group'>");
				
					List<Equipment> seqlist= ccslist.getEquipments();
			                  for(Equipment eqs : seqlist){
			                	  out.print("<li class='list-group-item'><span class='badge'>14</span>"+eqs.getNom()+"</li>");
			                  }
					 out.print("</ul></td>" 
							+ "<td class='text-center'>"
							+ "<a href='#' class='btn btn-danger btn-xs' "
							+ "> <span class='glyphicon glyphicon-remove'></span>Action" + "</a></td>" + "</tr>");
				}
		  }
		
			break;
		default:
			break;
		}
	}

}
