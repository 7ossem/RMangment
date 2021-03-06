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
import com.model.beans.Equipments;
import com.model.beans.Equipmentsalle;
import com.model.beans.Equipmentsalles;
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
		boolean session =false;
		try {
		 session =	(boolean)request.getSession().getAttribute("session");
		} catch (Exception e) {
		
		}
		if(session){
			
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
					// System.out.println("Num" + cs.getNum());
					out.print("<tr id ='" + cs.getNum() + "'>" + "<td>" + cs.getNum() + "</td>" + "<td>"
							+ cs.getCapacite() + "</td>");
					out.print("<td><ul class='list-group'>");

					List<Equipment> seqlist = cs.getEquipments();
					if (seqlist != null) {
						out.print("<div class='panel-group'> <div class='panel panel-info'><div class='panel-heading'>"
								+ "<h4 class='panel-title'><a data-toggle='collapse' href='#collapse1" + cs.getNum()
								+ "'><span class='glyphicon glyphicon-chevron-down'></span>&nbsp;&nbsp;Numbre d Equipment de salle "
								+ cs.getNum() + "</a>" + "</h4></div> <div id='collapse1" + cs.getNum()
								+ "' class='panel-collapse collapse'>");

						for (Equipment eqs : seqlist) {
							out.print("<li class='list-group-item'><span class='badge'>"
									+ (new Equipmentsalles()).getNumbreEquipment(cs.getNum(), eqs.getId()) + "</span>"
									+ eqs.getNom() + "</li>");
						}
						if (csalles.verifierAccupation(cs.getNum()) == 1) {
							out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
									+ "<a href='#' class='btn btn-info btn-md disabled' "
									+ "> <span class='glyphicon glyphicon-info'></span>deja Reserver" + "</a></td>"
									+ "</tr>");
						} else {
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
			try {
				int num = Integer.parseInt(request.getParameter("word"));
				SearchSS = SearchSS.SearchSalles(num);
				List<Salle> listSearchSS = SearchSS.getListSalles();
				if (listSearchSS != null) {
					for (Salle css : listSearchSS) {
						// System.out.println("Num" + css.getNum());
						out.print("<tr id='supptr" + css.getNum() + "'>" + "<td>" + css.getNum() + "</td>" + "<td>"
								+ css.getCapacite() + "</td>");
						out.print("<td><ul class='list-group'>");
						List<Equipment> seqlist = css.getEquipments();
						if (seqlist != null) {
							out.print(
									"<div class='panel-group'> <div class='panel panel-info'><div class='panel-heading'>"
											+ "<h4 class='panel-title'><a data-toggle='collapse' href='#collapse1"
											+ (css.getNum() + 1)
											+ "'><span class='glyphicon glyphicon-chevron-down'></span> &nbsp;&nbsp;list Equipment </a>"
											+ "</h4></div> <div id='collapse1" + (css.getNum() + 1)
											+ "' class='panel-collapse collapse'>");
							for (Equipment eqs : seqlist) {
								out.print("<li class='list-group-item'><span class='badge'>"
										+ (new Equipmentsalles()).getNumbreEquipment(css.getNum(), eqs.getId())
										+ "</span>" + eqs.getNom() + "</li>");
							}
							// System.out.println(SearchSS.verifierAccupation(css.getNum()));
							if (SearchSS.verifierAccupation(css.getNum()) == 1) {
								out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
										+ "<a href='#' class='btn btn-danger btn-md disabled' "
										+ "> <span class='glyphicon glyphicon-remove'></span>Supprimer" + "</a></td>"
										+ "</tr>");
							} else {
								out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
										+ "<a href='#' class='btn btn-danger btn-md'  onclick='ConfirmSalle("
										+ css.getNum() + ");'"
										+ "> <span class='glyphicon glyphicon-remove '></span>Supprimer" + "</a></td>"
										+ "</tr>");
							}
						} else {
							out.print("Vide");
						}
					}
				}

			} catch (Exception e) {
				out.print("<tr><td colspan='4'><div class='alert alert-danger alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
						+ "<strong>Error : </strong> Entrer num de salle correct!</div></td></tr>");
			}
			break;
		/* supprimer salle */
		case "supprimersalle":
			System.out.println("** SupprimerSalle  **");
			
			try {
				Salles suppalles = new Salles();
				int idsalle = Integer.parseInt(request.getParameter("idsalle"));
				int res = suppalles.deleteSalle(idsalle);
				System.out.println("REs " + res);
				if (res == 1) {
					out.print("success");
				} else {
					out.print("failed");
				}
			} catch (Exception e) {
				out.print("Exception failed");
				System.out.println(e.getMessage());
			}
			break;
		/* ajouter salle */
		case "ajoutersalle":
			try {
				int nums = Integer.parseInt(request.getParameter("num"));
				int caps = Integer.parseInt(request.getParameter("capacite"));
				Salles ajsalle = new Salles();
				int resaj = ajsalle.insertSalle(new Salle(nums, caps));
				System.out.println(resaj+"++++++++++");
				if (resaj == 1) {
					out.print("1");
				} 
				if (resaj == -1) {
					out.print("-1");
				} 
				if (resaj == 0) {
					out.print("0");
				}
			} catch (Exception e) {
				out.print("Contriller Exception");
				System.out.println(e.getMessage());
			}
			break;
		case "SearchSalleAjEq":
			Salles SearchAjEq = new Salles();
			// List<Salle>
			try {
				int num = Integer.parseInt(request.getParameter("word"));
				SearchSS = SearchAjEq.SearchSalles(num);
				List<Salle> listSearchSS = SearchSS.getListSalles();
				if (listSearchSS != null) {
					for (Salle css : listSearchSS) {
						// System.out.println("Num" + css.getNum());
						out.print("<tr id='supptr" + css.getNum() + "'>" + "<td>" + css.getNum() + "</td>" + "<td>"
								+ css.getCapacite() + "</td>");
						out.print("<td><ul class='list-group'>");
						List<Equipment> seqlist = css.getEquipments();
						if (seqlist != null) {
							out.print(
									"<div class='panel-group'> <div class='panel panel-info'><div class='panel-heading'>"
											+ "<h4 class='panel-title'><a data-toggle='collapse' href='#collapse1"
											+ (css.getNum() + 1)
											+ "'><span class='glyphicon glyphicon-chevron-down'></span> &nbsp;&nbsp;list Equipment </a>"
											+ "</h4></div> <div id='collapse1" + (css.getNum() + 1)
											+ "' class='panel-collapse collapse in'>");
							for (Equipment eqs : seqlist) {
								out.print("<li class='list-group-item'><span class='badge'>"
										+ (new Equipmentsalles()).getNumbreEquipment(css.getNum(), eqs.getId())
										+ "</span>" + eqs.getNom() + "</li>");
							}
							// System.out.println(SearchSS.verifierAccupation(css.getNum()));
							if (SearchSS.verifierAccupation(css.getNum()) == 1) {
								out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
										+ "<a href='#' class='btn btn-info btn-md disabled' "
										+ "> <span class='glyphicon glyphicon-add'></span>Aj-Equipment" + "</a></td>"
										+ "</tr>");
							} else {
								out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
										+ "<a href='#' class='btn btn-warning btn-md'  data-toggle='modal' data-target='#myModalAddEq' "
										+ "onclick='setSalleID("+css.getNum()+")'"
										+ "> <span class='glyphicon glyphicon-add '></span>Aj-Equipment" + "</a></td>"
										+ "</tr>");
							}
						} else {
							out.print("Vide");
						}
					}
				}

			} catch (Exception e) {
				out.print("<tr><td colspan='4'><div class='alert alert-danger alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
						+ "<strong>Error : </strong> Entrer num de salle correct!</div></td></tr>");
			}

			break;
			/* Get List Of Componenet */
		case  "GetListequipment" :
			
			Equipments equis =new Equipments();
			equis.initEquipments();
			List<Equipment> listeq = equis.getListEquipments();
			if (listeq != null ){
				for (Equipment e : listeq){
					out.print("	<option value='"+e.getId()+"'>"+e.getNom()+"</option>");
				}
			}else {
				out.print("Internal Error");
			}
			break;
			/* Modifier ou Ajouter equipment de la salle */
			
		case "AjouterEquipmnetSalle":
			System.out.println("+++++ AjouterEquipmnetSalle+++++++");
				int numbereq=Integer.parseInt(request.getParameter("numbereq"));
				int typeequipment = Integer.parseInt(request.getParameter("typeequipment"));
				int idsalle =Integer.parseInt(request.getParameter("id_salle"));
				  Equipmentsalles eqs =new Equipmentsalles();
				  int res=eqs.updateEquipmentsalle(new Equipmentsalle(idsalle, typeequipment, numbereq));
				  if (res == 1){
					  out.print("1");
				  }else {
					  out.print("0");	  
				  }
			
			break;
		/* Get Sall By Num */
		case "getSalle":
			Salles gsalle = new Salles();
			try {
				int num = Integer.parseInt(request.getParameter("word"));
				SearchSS = gsalle.SearchSalles(num);
				List<Salle> listSearchSS = SearchSS.getListSalles();
				if (listSearchSS != null) {
					for (Salle css : listSearchSS) {
						out.print("<tr id='supptr" + css.getNum() + "'>" + "<td>" + css.getNum() + "</td>" + "<td>"
								+ css.getCapacite() + "</td>");
						out.print("<td><ul class='list-group'>");
						List<Equipment> seqlist = css.getEquipments();
						if (seqlist != null) {
							out.print(
									"<div class='panel-group'> <div class='panel panel-info'><div class='panel-heading'>"
											+ "<h4 class='panel-title'><a data-toggle='collapse' href='#collapse1"
											+ (css.getNum() + 1)
											+ "'><span class='glyphicon glyphicon-chevron-down'></span> &nbsp;&nbsp;list Equipment </a>"
											+ "</h4></div> <div id='collapse1" + (css.getNum() + 1)
											+ "' class='panel-collapse collapse in'>");
							for (Equipment eqss : seqlist) {
								out.print("<li class='list-group-item'><span class='badge'>"
										+ (new Equipmentsalles()).getNumbreEquipment(css.getNum(), eqss.getId())
										+ "</span>" + eqss.getNom() + "</li>");
							}
							// System.out.println(SearchSS.verifierAccupation(css.getNum()));
							if (SearchSS.verifierAccupation(css.getNum()) == 1) {
								out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
										+ "<a href='#' class='btn btn-info btn-md disabled' "
										+ "> <span class='glyphicon glyphicon-add'></span>Aj-Equipment" + "</a></td>"
										+ "</tr>");
							} else {
								out.print("</ul>   </div> </div> </div></div></td>" + "<td class='text-center'>"
										+ "<a href='#' class='btn btn-warning btn-md'  data-toggle='modal' data-target='#myModalAddEq' "
										+ "onclick='setSalleID("+css.getNum()+")'"
										+ "> <span class='glyphicon glyphicon-add '></span>Aj-Equipment" + "</a></td>"
										+ "</tr>");
							}
						} else {
							out.print("Vide");
						}
					}
				}

			} catch (Exception e) {
				out.print("<tr><td colspan='4'><div class='alert alert-danger alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
						+ "<strong>Error : </strong> Entrer num de salle correct!</div></td></tr>");
			}

			break;

		default:
			break;
		}
	}else{
		 out.print("sessionfalse");
	}
	}
}
