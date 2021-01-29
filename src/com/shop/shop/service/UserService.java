package com.shop.shop.service;

import java.util.List;

import com.shop.shop.domain.user.User;
import com.shop.shop.domain.user.UserDao;
import com.shop.shop.domain.user.dto.CheckoutRespDto;
import com.shop.shop.domain.user.dto.JoinUser;
import com.shop.shop.domain.user.dto.KakaoJoinUser;
import com.shop.shop.domain.user.dto.NaverJoinUser;
import com.shop.shop.domain.user.dto.UpdateUser;

public class UserService {
	private UserDao userDao;
	
	public UserService() {
		this.userDao = new UserDao();
	}
	
	public User 회원정보수정요청(int userId, String password) {
		return userDao.findByUserIdPassword(userId, password);
	}
	
	public boolean 비밀번호변경여부조회(int id, String password) {
		return userDao.findForChangeCheck(id, password);
	}
	
	public int 회원정보수정(UpdateUser updateUser) {
		return userDao.update(updateUser);
	}
	
	public int 회원가입(NaverJoinUser naverJoinUser) {
		return userDao.insert(naverJoinUser);
	}
	
	public int 회원가입(KakaoJoinUser kakaoJoinUser) {
		return userDao.insert(kakaoJoinUser);
	}
	
	public int 회원가입(JoinUser joinUser) {
		return userDao.insert(joinUser);
	}
	
	public User 로그인(String username, String password) {
		return userDao.login(username, password);
	}
	
	public CheckoutRespDto 구매회원정보(int id) {
		return userDao.findById(id);
	}
	
	public List<Integer> 장바구니번호리스트(int id) {
		return userDao.findForCartList(id);
	}
}
