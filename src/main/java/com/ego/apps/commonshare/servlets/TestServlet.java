package com.ego.apps.commonshare.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			response.getOutputStream().print("Hello World!");
		} catch (IOException e) {
		}
	}
}
