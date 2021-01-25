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
import com.shop.shop.domain.product.dto.DetailProdRespDto;
import com.shop.shop.domain.product.dto.IndexDto;
import com.shop.shop.domain.product.dto.InsertReqDto;
import com.shop.shop.domain.qna.dto.DetailQnaDto;
import com.shop.shop.domain.user.User;
import com.shop.shop.service.FavorService;
import com.shop.shop.service.ProductService;
import com.shop.shop.service.QnaService;

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
			List<IndexDto> productList30 = productService.상품전체보기(30);
			request.setAttribute("productList30", productList30);
			dis = request.getRequestDispatcher("/main.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("detail")) {
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			// 상품 정보 받아오기 시작
			DetailProdRespDto prodDto = productService.상품상세보기(prodNo);
			request.setAttribute("prodDto", prodDto);
			// 상품 정보 받아오기 끝
			
			// 찜 여부 받아오기 시작
			HttpSession session = request.getSession();
			User principal = (User) session.getAttribute("principal");
			if (principal != null) {
				FavorService favorService = new FavorService();
				int userId = principal.getId();
				boolean isFavor = favorService.찜여부(userId, prodNo);
				request.setAttribute("isFavor", isFavor);
			}
			// 찜 여부 받아오기 끝
			
			// Q&A 갯수, 리스트 받아오기 시작
			QnaService qnaService = new QnaService();
			int countQna = qnaService.글갯수(prodNo);
			List<DetailQnaDto> qnaList = qnaService.글목록(prodNo);
			request.setAttribute("countQna", countQna);
			request.setAttribute("qnaList", qnaList);
			// Q&A 갯수, 리스트 받아오기 끝

			dis = request.getRequestDispatcher("/product/product-page.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("insertPage")) {
			dis = request.getRequestDispatcher("/product/newproduct.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("insert")) {
			BufferedReader br = request.getReader();
			String data = br.readLine();
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			
			InsertReqDto reqDto = gson.fromJson(data, InsertReqDto.class);
			int result = productService.상품등록(reqDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();
			
		}

	}
}