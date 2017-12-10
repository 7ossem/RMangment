package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherCtlr
 */
@WebServlet("/Home.jsp")
public class DispatcherCtlr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherCtlr() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		boolean session =  (boolean) request.getSession().getAttribute("session");
		if(session) {
			System.out.println((String) request.getSession().getAttribute("url"));
			request.getRequestDispatcher((String) request.getSession().getAttribute("url")).forward(request, response);			
		}else {
		request.getRequestDispatcher((String) request.getSession().getAttribute("http://localhost:8085/ReservationProject/index.jsp")).forward(request, response);
		}
		//PrintWriter out = response.getWriter();
		/*out.println("<html> <body>"
				
				+ "welcome de get"+(String)request.getSession().getAttribute("user")
				+ "</body></html>");
		// out.println("/WEB-INF/admin.jsp").;
		out.close();
*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<html> <body>"
				+ "welcome de post"
				+ "</body></html>");
		// out.println("/WEB-INF/admin.jsp").;
		out.close();

	}

}
