package com.shop.shop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.shop.domain.product.Product;
import com.shop.shop.service.ProductService;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
    public ProductController() {
        super();
        this.productService = new ProductService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		RequestDispatcher dis; 
		
		if (cmd.equals("main")) {
			List<Product> productList30 = productService.상품전체보기(30);
			request.setAttribute("productList30", productList30);
			dis = request.getRequestDispatcher("/main.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("detail")) {
			dis = request.getRequestDispatcher("/product/product-page.jsp");
			dis.forward(request, response);
		}

	}
}