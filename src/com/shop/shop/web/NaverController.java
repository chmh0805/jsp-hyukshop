package com.shop.shop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.shop.domain.naver.dto.NaverDto;
import com.shop.shop.domain.user.NaverUser;
import com.shop.shop.domain.user.User;
import com.shop.shop.service.NaverService;

@WebServlet("/naver_oauth")
public class NaverController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NaverService naverService;
       
    public NaverController() {
        super();
        this.naverService = new NaverService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		NaverDto naverDto = naverService.getDto(request, response);
		session.setAttribute("naver_token", naverDto.getAccess_token());
		
		NaverUser naverUser = naverService.getUserInfo(naverDto.getAccess_token());
		
		long naverId = naverUser.getId();
		User userEntity = naverService.네이버로그인(naverId);
		
		if (userEntity == null) {
			request.setAttribute("naverId", naverId);
			request.setAttribute("name", naverUser.getName());
			request.setAttribute("email", naverUser.getEmail());
			request.setAttribute("phone", naverUser.getMobile());
			session.setAttribute("principal", userEntity);
			RequestDispatcher dis = request.getRequestDispatcher("/user/naverJoin.jsp");
			dis.forward(request, response);
		} else {
			session.setAttribute("principal", userEntity);
			RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
			dis.forward(request, response);
		}
		
	}
	
}
	
	
	