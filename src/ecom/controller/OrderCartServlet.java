package ecom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.model.Cart;
import ecom.technique.ManagedBooks;
import ecom.technique.OutOfStockException;

/**
 * Servlet implementation class OrderCartServlet
 */
@WebServlet({"/OrderCartServlet", "/orderCart"})
public class OrderCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cart.getInstance().orderCart();
		} catch (OutOfStockException e) {
			request.setAttribute("msg", e.getMessage());
			e.printStackTrace();
		}
		
		List<ManagedBooks> list = Manager.getInstance().displayBooks();
		request.setAttribute("modele", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
