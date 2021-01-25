package com.shop.shop.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shop.shop.domain.qna.dto.InsertQnaDto;
import com.shop.shop.service.QnaService;

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

		}

	}
}