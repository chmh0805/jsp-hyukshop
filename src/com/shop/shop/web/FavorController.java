package com.shop.shop.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.shop.shop.domain.product.dto.FavorDto;
import com.shop.shop.service.FavorService;

@WebServlet("/favor")
public class FavorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavorService favorService;
       
    public FavorController() {
        super();
        this.favorService = new FavorService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");

		if (cmd.equals("favAdd")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			FavorDto favorDto = gson.fromJson(data, FavorDto.class);
			
			int result = favorService.찜등록(favorDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();
				
		} else if (cmd.equals("favRmv")) {
			Gson gson = new Gson();
			BufferedReader br = request.getReader();
			PrintWriter out = response.getWriter();
			String data = br.readLine();
			FavorDto favorDto = gson.fromJson(data, FavorDto.class);
			
			int result = favorService.찜해제(favorDto);
			
			if (result == 1) {
				out.print("200");
			} else {
				out.print("500");
			}
			out.flush();
				
		}

	}
}