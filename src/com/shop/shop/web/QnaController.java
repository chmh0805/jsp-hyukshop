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
import com.shop.shop.domain.qna.dto.AllQnaDto;
import com.shop.shop.domain.qna.dto.DetailQnaDto;
import com.shop.shop.domain.qna.dto.InsertQnaDto;
import com.shop.shop.service.ProductService;
import com.shop.shop.service.QnaService;
import com.shop.shop.util.Script;

@WebServlet("/qna")
public class QnaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QnaService qnaService;
       
    public QnaController() {
        super();
        this.qnaService = new QnaService();
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

		if (cmd.equals("qnawrite")) {
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			request.setAttribute("prodNo", prodNo);
			
			dis = request.getRequestDispatcher("/product/qnawrite.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("insertQna")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			
			InsertQnaDto dto = gson.fromJson(data, InsertQnaDto.class);
			int result = qnaService.글등록(dto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();

		} else if (cmd.equals("openQnaDetail")) {
			int qnaId = Integer.parseInt(request.getParameter("qnaId"));
			
			DetailQnaDto dto = qnaService.글상세보기(qnaId);
			request.setAttribute("qnaId", dto.getId());

			if (!(dto.getPassword().equals(""))) {
				dis = request.getRequestDispatcher("/product/qnaCheckAgain.jsp");
				dis.forward(request, response);
			} else {
				request.setAttribute("detailQnaDto", dto);
				dis = request.getRequestDispatcher("/product/qnadetail.jsp");
				dis.forward(request, response);
			}

		} else if (cmd.equals("qnaDetail")) {
			int qnaId = Integer.parseInt(request.getParameter("qnaId"));
			String inputPassword = request.getParameter("password");
			
			DetailQnaDto dto = qnaService.글상세보기(qnaId);
			
			if (inputPassword.equals(dto.getPassword())) {
				request.setAttribute("detailQnaDto", dto);
				dis = request.getRequestDispatcher("/product/qnadetail.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "비밀번호가 틀렸습니다.");
			}
			
		} else if (cmd.equals("qnaAll")) {
			ProductService productService = new ProductService();
			int prodNo = Integer.parseInt(request.getParameter("prodNo"));
			// 상품 정보 받아오기 시작
			DetailProdRespDto prodDto = productService.상품상세보기(prodNo);
			request.setAttribute("prodDto", prodDto);
			// 상품 정보 받아오기 끝
			// qna 리스트 받아오기 시작
			List<AllQnaDto> qnaList = qnaService.글전체목록(prodNo);
			request.setAttribute("qnaList", qnaList);
			// qna 리스트 받아오기 끝
			
			dis = request.getRequestDispatcher("/product/qnaAll.jsp");
			dis.forward(request, response);
			
		}

	}
}