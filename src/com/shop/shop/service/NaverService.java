package com.shop.shop.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shop.shop.domain.naver.NaverDao;
import com.shop.shop.domain.naver.dto.NaverDto;
import com.shop.shop.domain.user.NaverUser;
import com.shop.shop.domain.user.User;
import com.shop.shop.util.Script;

public class NaverService {
	private NaverDao naverDao;
	
	public NaverService() {
		this.naverDao = new NaverDao();
	}
	
	public User 네이버로그인(long naverId) {
		return naverDao.findByNaverId(naverId);
	}
	
	public NaverDto getDto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("error") != null) { // 에러가 있으면 로그인이 안된 것 !!
			Script.back(response, "네이버 로그인에 실패하였습니다.");
			return null;
		}
		String urlSource = "https://nid.naver.com/oauth2.0/token?";
		String grant_type = "authorization_code"; // 발급:authorization_code / 갱신:refresh_token / 삭제:delete
		String client_id = "EnduV23LJzy3ERKxeAyL";
		String client_secret = "iKeEhYUd_a";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		StringBuffer sb = new StringBuffer();
		sb.append(urlSource);
		sb.append("grant_type="+grant_type);
		sb.append("&client_id="+client_id);
		sb.append("&client_secret="+client_secret);
		sb.append("&code="+code);
		sb.append("&state="+state);
		String reqURL = sb.toString();
		String result = "";
		String input = "";
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		Gson gson = new Gson();
		
		while ((input = br.readLine()) != null) {
			result += input;
		}
		NaverDto naverDto = gson.fromJson(result, NaverDto.class);
		
		return naverDto;
	}
	
	public NaverUser getUserInfo(String naver_token) throws IOException {
		String urlSource = "https://openapi.naver.com/v1/nid/me";
		URL url = new URL(urlSource);
		String line = "";
		String result = "";
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "Bearer "+naver_token);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = br.readLine()) != null) {
			result += line;
		}
		System.out.println(result);
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        
        JsonObject properties = element.getAsJsonObject().get("response").getAsJsonObject();
        int id = properties.getAsJsonObject().get("id").getAsInt();
		NaverUser naverUser = gson.fromJson(properties, NaverUser.class);
		naverUser.setId(id);
		
		return naverUser;
	}
}
