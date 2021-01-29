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
import com.shop.shop.domain.favor.dto.FavorCompListReqDto;
import com.shop.shop.domain.favor.dto.FavorDto;
import com.shop.shop.domain.favor.dto.FavorReqDto;
import com.shop.shop.domain.product.dto.IndexDto;
import com.shop.shop.domain.user.User;
import com.shop.shop.service.FavorService;
import com.shop.shop.service.ProductService;

@WebServlet("/favor")
public class FavorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavorService favorService;
       
    public FavorController() {
        super();
        this.favorService = new FavorService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		if (cmd.equals("favAdd")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			FavorDto favorDto = gson.fromJson(data, FavorDto.class);
			
			int result = favorService.찜등록(favorDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();
				
		} else if (cmd.equals("favRmv")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			FavorDto favorDto = gson.fromJson(data, FavorDto.class);
			
			int result = favorService.찜해제(favorDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();

		} else if (cmd.equals("favorList")) {
			HttpSession session = request.getSession();
			User principal = (User) session.getAttribute("principal");
			int userId = principal.getId();
			
			List<FavorReqDto> favorList = favorService.찜불러오기(userId);
			request.setAttribute("favorList", favorList);
			if (favorList != null) {
				List<FavorCompListReqDto> favorCompList = favorService.찜한회사목록(userId);
				request.setAttribute("favorCompList", favorCompList);
			}
			
			if (favorList.size() == 0) {
				ProductService productService = new ProductService();
				List<IndexDto> productList30 = productService.상품전체보기(30);
				request.setAttribute("productList30", productList30);
			}
			
			RequestDispatcher dis = request.getRequestDispatcher("/user/favorite.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("compSort")) {
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			int userId = Integer.parseInt(request.getParameter("userId"));
			int compId = Integer.parseInt(request.getParameter("compId"));
			List<FavorReqDto> favorList = favorService.찜불러오기(userId, compId);
			String data = gson.toJson(favorList);
			out.print(data);
			out.flush();
			
		}  else if (cmd.equals("compDefault")) {
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			int userId = Integer.parseInt(request.getParameter("userId"));
			List<FavorReqDto> favorList = favorService.찜불러오기(userId);
			String data = gson.toJson(favorList);
			out.print(data);
			out.flush();
			
		}

	}
}