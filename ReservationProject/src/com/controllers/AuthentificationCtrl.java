package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.beans.Agent;

/**
 * Servlet implementation class AuthentificationCtrl
 */
@WebServlet("/AuthentificationCtrl")
public class AuthentificationCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthentificationCtrl() {
		super();
	}

	/**
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Agent u = new Agent();

		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
try{
		if (u.isAgentr(username, password)) {
			out.write("Home.jsp");
			request.getSession().setAttribute("session", true);
			request.getSession().setAttribute("user", u.getAgent(username));
			request.getSession().setAttribute("url", "/WEB-INF/indexReservation.jsp");
			out.close();
		} else {
			out.write("error");
		}}catch (Exception e) {
System.out.println(e.getMessage());		}
	}
}
