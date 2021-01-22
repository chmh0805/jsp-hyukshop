package com.shop.shop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.shop.util.Logout;
import com.shop.shop.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
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
		
		if (cmd.equals("joinForm")) {
			dis = request.getRequestDispatcher("/user/join.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("loginForm")) {
			dis = request.getRequestDispatcher("/user/login.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("login")) {
			dis = request.getRequestDispatcher("/shop");
			dis.forward(request, response);
			
		} else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			int result = 500;
			// 카카오 로그아웃 시작
			if (session.getAttribute("kakao_token") != null) {
				String kakao_token = (String) session.getAttribute("kakao_token");
				result = Logout.kakaoLogout(kakao_token);
				System.out.println("카카오 로그아웃 응답코드 : " + result);
			}
			// 카카오 로그아웃 끝
			// 네이버 로그아웃 시작
			if (session.getAttribute("naverDto") != null) {
				String naver_token = (String) session.getAttribute("naver_token");
				result = Logout.naverLogout(naver_token);
				System.out.println("네이버 로그아웃 응답코드 : " + result);
			}
			// 네이버 로그아웃 끝
			// 로그아웃 공통 시작
			if (result == 200) {
				session.invalidate();
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "로그아웃 실패");
			}
			// 로그아웃 공통 끝
			
		} else if (cmd.equals("mypage")) {
			dis = request.getRequestDispatcher("/user/mypage.jsp");
			dis.forward(request, response);
			
		}
		
	}

}
