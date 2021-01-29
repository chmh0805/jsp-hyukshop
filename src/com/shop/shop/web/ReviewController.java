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

import com.google.gson.Gson;
import com.shop.shop.domain.product.dto.DetailProdRespDto;
import com.shop.shop.domain.review.dto.DetailReviewDto;
import com.shop.shop.domain.review.dto.DetailReviewRespDto;
import com.shop.shop.domain.review.dto.InsertReviewDto;
import com.shop.shop.service.ProductService;
import com.shop.shop.service.ReviewService;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReviewService reviewService;
       
    public ReviewController() {
        super();
        this.reviewService = new ReviewService();
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

		if (cmd.equals("reviewWrite")) {
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			request.setAttribute("prodNo", prodNo);
			
			dis = request.getRequestDispatcher("/product/reviewwrite.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("insertReview")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			
			InsertReviewDto dto = gson.fromJson(data, InsertReviewDto.class);
			int result = reviewService.글등록(dto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();

		} else if (cmd.equals("openReviewDetail")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			
			DetailReviewDto dto = reviewService.글상세보기(reviewId);
			request.setAttribute("reviewId", dto.getId());

			request.setAttribute("detailReviewDto", dto);
			dis = request.getRequestDispatcher("/product/reviewdetail.jsp");
			dis.forward(request, response);

		} else if (cmd.equals("reviewAll")) {
			ProductService productService = new ProductService();
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			// 상품 정보 받아오기 시작
			DetailProdRespDto prodDto = productService.상품상세보기(prodNo);
			request.setAttribute("prodDto", prodDto);
			// 상품 정보 받아오기 끝
			// 리뷰 리스트 받아오기 시작
			List<DetailReviewRespDto> reviewList = reviewService.글전체목록(prodNo);
			request.setAttribute("reviewList", reviewList);
			// 리뷰 리스트 받아오기 끝
			
			dis = request.getRequestDispatcher("/product/reviewAll.jsp");
			dis.forward(request, response);
			
		}

	}
}