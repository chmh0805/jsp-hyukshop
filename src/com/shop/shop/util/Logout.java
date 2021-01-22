package com.shop.shop.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class Logout {
	public static int kakaoLogout(String kakao_token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer "+kakao_token);
			return conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}
	
	
	public static int naverLogout(String naver_token) { // 로그아웃 성공 : 200을 리턴
		String urlSource = "https://nid.naver.com/oauth2.0/token";
		String grant_type = "delete"; // 발급:authorization_code / 갱신:refresh_token / 삭제:delete
		String client_id = "EnduV23LJzy3ERKxeAyL";
		String client_secret = "iKeEhYUd_a";
		String access_token = naver_token;
		String service_provider = "NAVER";
		StringBuffer sb = new StringBuffer();
		sb.append(urlSource+"?");
		sb.append("grant_type="+grant_type);
		sb.append("&client_id="+client_id);
		sb.append("&client_secret="+client_secret);
		sb.append("&access_token="+access_token);
		sb.append("&service_provider="+service_provider);
		String reqURL = sb.toString();
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			return conn.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}
}
