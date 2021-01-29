package com.shop.shop.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.shop.shop.domain.qna.dto.DetailQnaRespDto;
import com.shop.shop.domain.review.dto.DetailReviewRespDto;
import com.shop.shop.domain.user.User;
import com.shop.shop.service.CartService;
import com.shop.shop.service.FavorService;
import com.shop.shop.service.ProductService;
import com.shop.shop.service.QnaService;
import com.shop.shop.service.ReviewService;

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
			
			// 찜 여부, 카트 여부 받아오기 시작
			HttpSession session = request.getSession();
			User principal = (User) session.getAttribute("principal");
			if (principal != null) {
				int userId = principal.getId();
				
				FavorService favorService = new FavorService();
				boolean isFavor = favorService.찜여부(userId, prodNo);
				request.setAttribute("isFavor", isFavor);
				
				CartService cartService = new CartService();
				boolean isCart = cartService.장바구니여부(userId, prodNo);
				request.setAttribute("isCart", isCart);
			}
			// 찜 여부, 카트 여부 받아오기 끝
			
			// 리뷰 갯수, 리스트 받아오기 시작
			ReviewService reviewService = new ReviewService();
			int countReview = reviewService.글갯수(prodNo);
			List<DetailReviewRespDto> reviewList = reviewService.상세페이지글목록(prodNo);
			request.setAttribute("countReview", countReview);
			request.setAttribute("reviewList", reviewList);
			// 리뷰 갯수, 리스트 받아오기 끝
			
			// Q&A 갯수, 리스트 받아오기 시작
			QnaService qnaService = new QnaService();
			int countQna = qnaService.글갯수(prodNo);
			List<DetailQnaRespDto> qnaList = qnaService.상세페이지글목록(prodNo);
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
			
		} else if (cmd.equals("search")) {
			List<IndexDto> searchedProductList = new ArrayList<>();
			String keyword = request.getParameter("keyword");
			String compNo = request.getParameter("compNo");
			if (keyword != null) {
				searchedProductList = productService.상품키워드찾기(keyword);
				request.setAttribute("searchedProductList", searchedProductList);
			}
			if (compNo != null) {
				searchedProductList = productService.상품회사코드찾기(Integer.parseInt(compNo));
				request.setAttribute("searchedProductList", searchedProductList);
			}
			if (searchedProductList.size() == 0) {
				List<IndexDto> productList30 = productService.상품전체보기(30);
				request.setAttribute("productList30", productList30);
			}
			
			dis = request.getRequestDispatcher("/product/search.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("rank")) {
			List<IndexDto> rankedProductList = productService.상품순위();
			request.setAttribute("rankedProductList", rankedProductList);
			
			dis = request.getRequestDispatcher("/product/rank.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("category")) {
			List<IndexDto> allProductList = productService.상품전체보기();
			request.setAttribute("allProductList", allProductList);
			
			dis = request.getRequestDispatcher("/product/categories.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("cateListDefault")) {
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			
			List<IndexDto> allProductList = productService.상품전체보기();
			String data = gson.toJson(allProductList);
			out.print(data);
			out.flush();
			
		} else if (cmd.equals("cateSort")) {
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			
			int compId = Integer.parseInt(request.getParameter("compId"));
			List<IndexDto> sortedProductList = productService.상품회사코드찾기(compId);
			String data = gson.toJson(sortedProductList);
			out.print(data);
			out.flush();
			
		} else if (cmd.equals("soldCountUpdate")) {
			int prodId = Integer.parseInt(request.getParameter("prodId"));
			productService.구매수증가(prodId);
			
		}

	}
}