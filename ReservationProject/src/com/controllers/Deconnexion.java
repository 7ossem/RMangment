package com.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deconnexion
 */

@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("doget Deconnxion Controller");
	
	try {
	request.getSession().removeAttribute("user");
	request.getSession().removeAttribute("url");
	request.getSession().setAttribute("session","false");
	request.getRequestDispatcher((String) request.getSession().getAttribute("/index.jsp")).forward(request, response);
	
	} catch (Exception e) {
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}


	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("url");
		request.getSession().setAttribute("session", false);
		request.getRequestDispatcher((String) request.getSession().getAttribute("/index.jsp")).forward(request, response);
		
		} catch (Exception e) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}	
	}

}
