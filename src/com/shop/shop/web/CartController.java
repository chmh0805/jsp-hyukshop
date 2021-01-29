package com.shop.shop.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.shop.shop.domain.cart.dto.CartAllDto;
import com.shop.shop.domain.cart.dto.CartDto;
import com.shop.shop.domain.user.User;
import com.shop.shop.service.CartService;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService;
       
    public CartController() {
        super();
        this.cartService = new CartService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		if (cmd.equals("cartAdd")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			CartDto cartDto = gson.fromJson(data, CartDto.class);
			
			int result = cartService.장바구니등록(cartDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();
				
		} else if (cmd.equals("cartRmv")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			CartDto cartDto = gson.fromJson(data, CartDto.class);
			
			int result = cartService.장바구니해제(cartDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();

		} else if (cmd.equals("cartList")) {
			HttpSession session = request.getSession();
			User principal = (User) session.getAttribute("principal");
			int userId = principal.getId();
			
			List<Integer> favorProdIdList = cartService.찜불러오기(userId);
			request.setAttribute("favorProdIdList", favorProdIdList);

			List<CartAllDto> cartDtoList = cartService.장바구니불러오기(userId);
			request.setAttribute("cartDtoList", cartDtoList);
			
			RequestDispatcher dis = request.getRequestDispatcher("/user/shopping-cart.jsp");
			dis.forward(request, response);

		}

	}
}