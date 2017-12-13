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
		// consulter salles
		case "ConsulterListSalles":

			System.out.println("** ConsulterListSalles  **");
			//
			Salles csalles = new Salles();
			//
			csalles = csalles.initSalles();
			List<Salle> clist = csalles.getListSalles();

			if (clist != null) {
				for (Salle cs : clist) {
					System.out.println("Num" + cs.getNum());
					out.print("<tr id ='" + cs.getNum() + "'>" + "<td>" + cs.getNum() + "</td>" + "<td>"
							+ cs.getCapacite() + "</td>");
					out.print("<td><ul class='list-group'>");

					List<Equipment> seqlist = cs.getEquipments();
					if (seqlist != null) {
						out.print("<div class='panel-group'> <div class='panel panel-info'><div class='panel-heading'>"
								+ "<h4 class='panel-title'><a data-toggle='collapse' href='#collapse1" + cs.getNum()
								+ "'><span class='glyphicon glyphicon-chevron-down'></span>Collapsible list group</a>"
								+ "</h4></div> <div id='collapse1" + cs.getNum()
								+ "' class='panel-collapse collapse'>");

						for (Equipment eqs : seqlist) {
							out.print("<li class='list-group-item'><span class='badge'>14</span>" + eqs.getNom()
									+ "</li>");
						}
						if (csalles.verifierAccupation(cs.getNum()) == 1) {
							out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
									+ "<a href='#' class='btn btn-info btn-md disabled' "
									+ "> <span class='glyphicon glyphicon-info'></span>deja Reserver" + "</a></td>"
									+ "</tr>");
						}else {
							out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
									+ "<a href='#' class='btn btn-default btn-md disabled' "
									+ "> <span class='glyphicon glyphicon-info'></span>Salle Libre" + "</a></td>"
									+ "</tr>");
						}

					}
				}

			} else {
				out.println("vide");
			}

			break;

		/* Search Salles a Supprimer */
		case "SearchSalle":
			Salles SearchSS = new Salles();
			// List<Salle>

			int num = Integer.parseInt(request.getParameter("word"));
			SearchSS = SearchSS.SearchSalles(num);
			List<Salle> listSearchSS = SearchSS.getListSalles();
			if (listSearchSS != null) {

				for (Salle css : listSearchSS) {
					System.out.println("Num" + css.getNum());
					out.print("<tr>" + "<td>" + css.getNum() + "</td>" + "<td>" + css.getCapacite() + "</td>");
					out.print("<td><ul class='list-group'>");

					List<Equipment> seqlist = css.getEquipments();
					if (seqlist != null) {
						out.print("<div class='panel-group'> <div class='panel panel-info'><div class='panel-heading'>"
								+ "<h4 class='panel-title'><a data-toggle='collapse' href='#collapse1" + (css.getNum()+1)
								+ "'><span class='glyphicon glyphicon-chevron-down'></span> &nbsp;&nbsp;list Equipment </a>"
								+ "</h4></div> <div id='collapse1" + (css.getNum()+1)
								+ "' class='panel-collapse collapse'>");
						for (Equipment eqs : seqlist) {
							out.print("<li class='list-group-item'><span class='badge'>14</span>" + eqs.getNom()
									+ "</li>");
						}
						System.out.println(SearchSS.verifierAccupation(css.getNum()));
						if (SearchSS.verifierAccupation(css.getNum()) == 1) {
							out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
									+ "<a href='#' class='btn btn-danger btn-md disabled' "
									+ "> <span class='glyphicon glyphicon-remove'></span>Supprimer" + "</a></td>"
									+ "</tr>");
						} else {
							out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
									+ "<a href='#' class='btn btn-danger btn-md' "
									+ "> <span class='glyphicon glyphicon-remove '></span>Supprimer" + "</a></td>"
									+ "</tr>");
						}
					} else {
						out.print("Vide");
					}
				}

			}

			break;
		default:
			break;
		}
	}

}
