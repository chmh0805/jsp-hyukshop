package com.shop.shop.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.shop.domain.product.dto.CheckoutProdDto;
import com.shop.shop.domain.user.User;
import com.shop.shop.domain.user.dto.CheckoutRespDto;
import com.shop.shop.domain.user.dto.JoinUser;
import com.shop.shop.domain.user.dto.KakaoJoinUser;
import com.shop.shop.domain.user.dto.NaverJoinUser;
import com.shop.shop.domain.user.dto.UpdateUser;
import com.shop.shop.service.KakaoService;
import com.shop.shop.service.NaverService;
import com.shop.shop.service.ProductService;
import com.shop.shop.service.UserService;
import com.shop.shop.util.Logout;
import com.shop.shop.util.SHA256;
import com.shop.shop.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;
	
    public UserController() {
        super();
        this.userService = new UserService();
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
		System.out.println(request.getRequestURI());
		
		if (cmd.equals("joinForm")) {
			dis = request.getRequestDispatcher("/user/join.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("loginForm")) {
			dis = request.getRequestDispatcher("/user/login.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			int result = 500;
			// 카카오 로그아웃 시작
			if (session.getAttribute("kakao_token") != null) {
				String kakao_token = (String) session.getAttribute("kakao_token");
				result = Logout.kakaoLogout(kakao_token);
				System.out.println("kakaoId : " + result);
				System.out.println("카카오 로그아웃 응답코드 : " + result);
			}
			// 카카오 로그아웃 끝
			// 네이버 로그아웃 시작
			if (session.getAttribute("naverDto") != null) {
				String naver_token = (String) session.getAttribute("naver_token");
				result = Logout.naverLogout(naver_token);
				System.out.println("naverId : " + result);
				System.out.println("네이버 로그아웃 응답코드 : " + result);
			}
			// 네이버 로그아웃 끝
			// 회원 로그아웃 시작
			if (session.getAttribute("principal") != null) {
				System.out.println("logout pincipal : " + session.getAttribute("principal"));
				result = 200;
			}
			// 회원 로그아웃 끝
			// 로그아웃 공통 시작
			if (result == 200) {
				session.invalidate();
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "로그아웃 실패");
			}
			// 로그아웃 공통 끝
			
		} else if (cmd.equals("checkAgain")) {
			dis = request.getRequestDispatcher("/user/checkAgain.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("mypage")) {
			HttpSession session = request.getSession();
			User userEntity = (User) session.getAttribute("principal");
			int userId = userEntity.getId();
			String password = SHA256.toSHA256(request.getParameter("password"));
			
			User userUpdate = userService.회원정보수정요청(userId, password);
			if (userUpdate == null) {
				Script.back(response, "비밀번호가 틀렸습니다.");
			} else {
				request.setAttribute("userUpdate", userUpdate);
				dis = request.getRequestDispatcher("/user/mypage.jsp");
				dis.forward(request, response);
			}
			
		} else if (cmd.equals("update")) {
			int userId = Integer.parseInt(request.getParameter("id"));
			String inputPassword = request.getParameter("password");
			UpdateUser updateUser = null;
			if (userService.비밀번호변경여부조회(userId, inputPassword)) {
				updateUser = UpdateUser.builder()
						.id(Integer.parseInt(request.getParameter("id")))
						.email(request.getParameter("email"))
						.phone(request.getParameter("phone"))
						.address(request.getParameter("address"))
						.password(SHA256.toSHA256(request.getParameter("password")))
						.build();
			} else {
				updateUser = UpdateUser.builder()
						.id(Integer.parseInt(request.getParameter("id")))
						.email(request.getParameter("email"))
						.phone(request.getParameter("phone"))
						.address(request.getParameter("address"))
						.password(request.getParameter("password"))
						.build();
			}
			int result = userService.회원정보수정(updateUser);
			
			if (result == 1) {
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "회원정보 수정에 실패하였습니다.");
			}
			
		} else if (cmd.equals("naverJoin")) {
			NaverJoinUser naverJoinUser = NaverJoinUser.builder()
					.naverId(Long.parseLong(request.getParameter("naverId")))
					.name(request.getParameter("name"))
					.email(request.getParameter("email"))
					.phone(request.getParameter("phone"))
					.address(request.getParameter("address"))
					.password(SHA256.toSHA256(request.getParameter("password")))
					.build();
			int result = userService.회원가입(naverJoinUser);
			
			if (result == 1) {
				NaverService naverService = new NaverService();
				User userEntity = naverService.네이버로그인(naverJoinUser.getNaverId());
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "회원가입 실패");
			}
			
		} else if (cmd.equals("kakaoJoin")) {
			KakaoJoinUser kakaoJoinUser = KakaoJoinUser.builder()
					.kakaoId(Long.parseLong(request.getParameter("kakaoId")))
					.name(request.getParameter("name"))
					.email(request.getParameter("email"))
					.phone(request.getParameter("phone"))
					.address(request.getParameter("address"))
					.password(SHA256.toSHA256(request.getParameter("password")))
					.build();
			int result = userService.회원가입(kakaoJoinUser);
			
			if (result == 1) {
				KakaoService kakaoService = new KakaoService();
				User userEntity = kakaoService.카카오로그인(kakaoJoinUser.getKakaoId());
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "회원가입 실패");
			}
			
		} else if (cmd.equals("join")) {
			JoinUser joinUser = JoinUser.builder()
					.name(request.getParameter("name"))
					.username(request.getParameter("username"))
					.email(request.getParameter("email"))
					.phone(request.getParameter("phone"))
					.address(request.getParameter("address"))
					.password(SHA256.toSHA256(request.getParameter("password")))
					.build();
			
			int result = userService.회원가입(joinUser);
			if (result == 1) {
				User userEntity = userService.로그인(joinUser.getUsername(), joinUser.getPassword());
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response, "회원가입 실패");
			}
			
		} else if (cmd.equals("login")) {
			User userEntity = userService.로그인(request.getParameter("username"), SHA256.toSHA256(request.getParameter("password")));
			if (userEntity == null) {
				Script.back(response, "로그인 실패");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity);
				dis = request.getRequestDispatcher("/index.jsp");
				dis.forward(request, response);
			}
			
		} else if (cmd.equals("directBuy")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			CheckoutRespDto userInfo = userService.구매회원정보(userId);
			request.setAttribute("userInfo", userInfo);
			
			int prodId = Integer.parseInt(request.getParameter("prodId"));
			ProductService productService = new ProductService();
			CheckoutProdDto prodInfo = productService.구매상품정보(prodId);
			request.setAttribute("prodInfo", prodInfo);
			
			dis = request.getRequestDispatcher("/user/check-out.jsp");
			dis.forward(request, response);
			
		} else if (cmd.equals("cartBuy")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			CheckoutRespDto userInfo = userService.구매회원정보(userId);
			request.setAttribute("userInfo", userInfo);
			
			List<Integer> cartList = userService.장바구니번호리스트(userId);
			ProductService productService = new ProductService();
			List<CheckoutProdDto> prodList = productService.구매상품정보(cartList);
			request.setAttribute("prodList", prodList);

			dis = request.getRequestDispatcher("/user/check-out.jsp");
			dis.forward(request, response);

		}
		
	}

}
