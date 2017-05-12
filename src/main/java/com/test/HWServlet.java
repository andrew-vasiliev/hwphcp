package com.test;

import java.io.IOException;
import java.io.PrintWriter;


import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sap.security.um.user.User;
import com.sap.security.um.user.UserProvider;


/**
 * Servlet implementation class HWServlet
 */
@WebServlet("/")
public class HWServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HWServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String strUser = request.getRemoteUser();
	    PrintWriter pw = response.getWriter();
	    if (strUser != null) {

	      try {
		      InitialContext ctx = new InitialContext();
		      
		      UserProvider userProvider = (UserProvider) ctx.lookup("java:comp/env/user/Provider");
		      User user = null;
		      
		      if (request.getUserPrincipal() != null) {
		           user = userProvider.getUser(request.getUserPrincipal().getName());

		           pw.println("Hello, " + user.getName());
		           pw.println("User name: " + user.getAttribute("firstname") + " " + user.getAttribute("lastname"));
		           pw.println("Email: " + user.getAttribute("email"));		           

		      }
	      } catch (Exception e) {
	    	  pw.println("Something whent wrong");
	    	  pw.println(e.getLocalizedMessage());
		  }
	      
	    } else {
	    	pw.println("User is not logged");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
