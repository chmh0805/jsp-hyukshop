package com.shop.shop.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	public static void back(HttpServletResponse response, String msg) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('" + msg + "');");
		out.print("history.back();");
		out.print("</script>");
		out.flush();
	}
}
