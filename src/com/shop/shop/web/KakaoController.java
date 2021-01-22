package com.shop.shop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.shop.domain.kakao.dto.KakaoDto;
import com.shop.shop.domain.user.KakaoUser;
import com.shop.shop.domain.user.User;
import com.shop.shop.service.KakaoService;

@WebServlet("/kakao_oauth")
public class KakaoController  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	KakaoService kakaoService;
       
    public KakaoController() {
        super();
        kakaoService = new KakaoService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		KakaoDto kakaoDto = kakaoService.getDto(request.getParameter("code"), request, response);
		session.setAttribute("kakao_token", kakaoDto.getAccess_token());
		
		KakaoUser kakaoUser = kakaoService.getUserInfo(kakaoDto.getAccess_token());
		
		long kakaoId = kakaoUser.getId();
		User userEntity = kakaoService.카카오로그인(kakaoId);
		if (userEntity == null) {
			request.setAttribute("kakaoId", kakaoUser.getId());
			request.setAttribute("name", kakaoUser.getNickname());
			RequestDispatcher dis = request.getRequestDispatcher("/user/kakaoJoin.jsp");
			dis.forward(request, response);
		} else {
			session.setAttribute("principal", userEntity);
			RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
			dis.forward(request, response);
		}
		
	}
	
	

	
}