package ecom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ecom.model.Cart;
import ecom.technique.ManagedBooks;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet({"/AddToCartServlet", "/addToCart"})
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ManagedBooks> list = Manager.getInstance().displayBooks();
		request.setAttribute("modele", list);
		
		ManagedBooks book = null;
		String bookId = request.getParameter("bookId");
		int qty = Integer.parseInt(request.getParameter("number"));
		book = (ManagedBooks) request.getSession().getAttribute(bookId);
		
		Manager.getInstance().addToCart(book, qty);
		
		Map<ManagedBooks, Integer> map = Cart.getInstance().getCart();
		int i = 0;
		for (Map.Entry<ManagedBooks, Integer> entry : map.entrySet())
		{
			request.setAttribute("key" + Integer.toString(i), entry.getKey());
			request.setAttribute("value" + Integer.toString(i), entry.getValue());
		}
		request.setAttribute("delivery", Cart.getInstance().getDelivery());
		request.setAttribute("totalPrice", Cart.getInstance().getTotalPrice());
		request.setAttribute("totalPriceDelivery", Cart.getInstance().getTotalPriceDelivery());
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
