package com.shop.shop.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shop.shop.domain.kakao.KakaoDao;
import com.shop.shop.domain.kakao.dto.KakaoDto;
import com.shop.shop.domain.user.KakaoUser;
import com.shop.shop.domain.user.User;
import com.shop.shop.util.Script;

public class KakaoService {
	private KakaoDao kakaoDao;
	
	public KakaoService() {
		this.kakaoDao = new KakaoDao();
	}
	
	public User 카카오로그인(long kakaoId) {
		return kakaoDao.findByKakaoId(kakaoId);
	}

	public KakaoUser getUserInfo (String access_token) throws IOException {
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		String result = "";
		String line = "";
		URL url = new URL(reqURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Authorization", "Bearer " + access_token);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = br.readLine()) != null) {
			result += line;
		}
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        
        int id = element.getAsJsonObject().get("id").getAsInt();
        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        KakaoUser kakaoUser = gson.fromJson(properties, KakaoUser.class);
        kakaoUser.setId(id);
        
        return kakaoUser;
	}
	
	
	public KakaoDto getDto (String authorize_code, HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader br;
		
		if (request.getParameter("error") != null) {
			Script.back(response, "에러발생");
			return null;
		}
		
		String client_id = "70b588e15fb703e050cb0b079505506f";
		String redirect_uri = "http://localhost/shop/kakao_oauth";
		String code = authorize_code;
//		String client_secret = "wRdhBCOcIcKZG3rRD8qCOYq8Rudk3V82";

		StringBuilder sb = new StringBuilder();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id="+client_id);
		sb.append("&redirect_uri="+redirect_uri);
		sb.append("&code="+code);
		
		final String AUTH_HOST = "https://kauth.kakao.com";
		final String tokenRequestURL = AUTH_HOST + "/oauth/token";
		
		URL url = new URL(tokenRequestURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(sb.toString());
		writer.flush();
		
		int respCode = conn.getResponseCode();
		
		if (respCode == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		Gson gson = new Gson();
		KakaoDto kakaoDto = gson.fromJson(br.readLine(), KakaoDto.class);
		
		return kakaoDto;
	}
}
