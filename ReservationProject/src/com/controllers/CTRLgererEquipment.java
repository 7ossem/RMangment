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

/**
 * Servlet implementation class CTRLgererEquipment
 */
@WebServlet({ "/CTRLgererEquipment.jsp", "/gererEquipment" })
public class CTRLgererEquipment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CTRLgererEquipment() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("+++++++++++++++");
		if (request.getParameter("id") != null && request.getParameter("id").equals("gererequipment")) {
			request.getRequestDispatcher("/WEB-INF/gererEquipment.jsp").forward(request, response);

			// response.sendRedirect("/WEB-INF/gererEquipment.jsp");
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
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
		/**
		 * ADD Component
		 */
		
		case "Ajouterequipment":
			Equipments e = new Equipments();
			Equipment eq = new Equipment();
			eq.setNom(request.getParameter("equipment"));
			int res = e.insertEquipment(eq);
			if (res == 1) {
				out.print("<div class='alert alert-success alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
						+ "<strong> Success :</strong>  Ajoute Equipment </div>");
			} else if (res == -1) {
				out.print("<div class='alert alert-warning alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
						+ "<strong> Error :</strong>   Equipment dejat Exist ! </div>");
			} else if (res == 0) {
				out.println("<div class='alert alert-danger alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> "
						+ "<strong> Error :</strong>Error internalt Exption</div> ");
			}
			break;
		/* Get List Of Component to Edit */
		case "GetListSuppEquipmnet":

			Equipments es = new Equipments();
			es.initEquipments();
			List<Equipment> lesupp = es.getListEquipments();
			if (lesupp != null) {
				for (Equipment ess : lesupp) {
					out.print("<tr id ='" + ess.getId() + "'>" + "<td>" + ess.getId() + "</td>" + "<td>" + ess.getNom()
							+ "</td>" + "<td class='text-center'>"
							+ "<a href='#' class='btn btn-danger btn-xs' onclick='ConfirmSupprimer(" + ess.getId()
							+ ");'> <span class='glyphicon glyphicon-remove'></span>Supprimer" + "</a></td>" + "</tr>");
				}
			} else {
				out.print("<tr >" + "<td>Vide </td>" + "<td>Vide </td>" + "<td class='text-center'>"
						+ "<a href='#' class='btn btn-danger btn-xs disabled' > <span class='glyphicon glyphicon-remove'></span>Supprimer"
						+ "</a></td>" + "</tr>");
			}

			break;
		case "SupprimerEquipment":
			Equipments eqs = new Equipments();
			int id = Integer.parseInt(request.getParameter("equipment"));

			if (eqs.deleteEquipment(id) == 1) {
				out.print("<div class='alert alert-success alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
						+ "<strong> Success :</strong>  Supprimer Equipment </div>");
			} else {
				out.println("<div class='alert alert-danger alert-dismissable fade in' >"
						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a> "
						+ "<strong> Error :</strong>Error internalt Exption</div> ");
			}
			break;
		/* Edit Component */
		case "GetListModEquipment":

			Equipments em = new Equipments();
			em.initEquipments();
			List<Equipment> leqmodi = em.getListEquipments();
			if (leqmodi != null) {
				for (Equipment emo : leqmodi) {
					out.print("<tr id ='" + emo.getId() + "'>" + "<td>" + emo.getId() + "</td>" + "<td>" + emo.getNom()
							+ "</td>" + "<td class='text-center'>"
							+ "<a href='#' class='btn btn-warning btn-xs' onclick='ModifierEquipmnet("+emo.getId()+")' >"
							+ " <span class='glyphicon glyphicon-edit'></span> Edit " + "</a></td>" + "</tr>");
				}
			} else {
				out.print("<tr >" + "<td>Vide </td>" + "<td>Vide </td>" + "<td class='text-center'>"
						+ "<a href='#' class='btn btn-warning btn-xs disabled' > <span class='glyphicon glyphicon-remove'></span>Edit"
						+ "</a></td>" + "</tr>");
			}

			break;
		
			/* Edit Component */
			case "EditEquipment":
                 String eqnom = request.getParameter("nom");
                 int eqid = Integer.parseInt(request.getParameter("id"));
                 
				Equipments eqedit = new Equipments();
			
				Equipment editeq =new Equipment(eqid, eqnom);
				int resEdit = eqedit.updateEquipment(editeq);
				if (resEdit == 1){
					out.print("1");
				}else {
					out.print("0");
				}
				
				break;
			case "searchEditEquipment":
                String WordSearch = request.getParameter("WordSearch");
                
				Equipments searcheqedit = new Equipments();
			   List<Equipment> resSearch =searcheqedit.SearchEquipments(WordSearch);
				if (resSearch != null) {
					for (Equipment emo : resSearch) {
						out.print("<tr id ='" + emo.getId() + "'>" + "<td>" + emo.getId() + "</td>" + "<td>" + emo.getNom()
								+ "</td>" + "<td class='text-center'>"
								+ "<a href='#' class='btn btn-warning btn-xs' onclick='ModifierEquipmnet("+emo.getId()+")' >"
								+ " <span class='glyphicon glyphicon-edit'></span> Edit " + "</a></td>" + "</tr>");
					}
				} else {
					out.print("<tr >" + "<td>Vide </td>" + "<td>Vide </td>" + "<td class='text-center'>"
							+ "<a href='#' class='btn btn-warning btn-xs disabled' > <span class='glyphicon glyphicon-remove'></span>Edit"
							+ "</a></td>" + "</tr>");
				}
				break;

		default:
			out.print("Default");
			break;
		}
		}else{
			 out.print("sessionfalse");
	
		}
}
		}
