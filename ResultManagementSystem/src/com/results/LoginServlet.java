package com.results;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.results.dao.UserDAO;
import com.results.entity.User;

@WebServlet("/loginPost")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {	
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		UserDAO userDao = new UserDAO();
		try {
			User user = userDao.checkLogin(userId, password);
			String destPage = "login.jsp";

			if (user != null) {
				HttpSession session = request.getSession();
				switch (user.getRole()) {
				case "ADMIN":
					destPage = "upload.jsp";
					break;
				case "STUDENT":
					user = userDao.fetchResult(user);
					destPage = "results.jsp";
					break;
				case "COMPANY":
					Map<String, List<User>> eligibleUsers = userDao.fetchCompanyWiseResults(user.getClassName());
					destPage = "company.jsp";
					session.setAttribute("eligibleUserMap", eligibleUsers);
					break;
				case "TPO":
					Map<String, List<User>> users = userDao.fetchAllResults();
					destPage = "placement.jsp";
					session.setAttribute("userMap", users);
					break;
				}
				session.setAttribute("user", user);
			} else {
				String message = "Invalid email/password";
				request.setAttribute("message", message);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);

		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
