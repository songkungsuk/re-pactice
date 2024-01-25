package com.web.test.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.web.test.service.TestService;
import com.web.test.vo.TestVO;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestService testService = new TestService();
	private Gson gson = new Gson();

	public TestServlet() {
		super();
		System.out.println("memory Create");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println(uri);
		if (uri.equals("list")) {
			
			gotoJSP(request, response, uri);

		} else if (uri.equals("view")) {
			gotoJSP(request, response, uri);
			
		} else if (uri.equals("view-data")) {

			System.out.println("go view-data");
			response.setContentType("application/json;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			int ctNum = Integer.parseInt(request.getParameter("ctNum"));
			TestVO one = testService.getTest(ctNum);
			String json = gson.toJson(one);
			out.print(json);

		} else if (uri.equals("update")) {
			gotoJSP(request, response, uri);

		} else {
			response.setContentType("application/json;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			List<TestVO> tests = testService.getTests();
			String json = gson.toJson(tests);
			out.print(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/") + 1);
		System.out.println(uri);
		
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String str = "";
		int result = 0;
		
		while((str=br.readLine()) != null) {
			sb.append(str);
		}
		TestVO test = gson.fromJson(sb.toString(), TestVO.class);
		System.out.println(test);
		if (uri.equals("insert")) {
			result += testService.insertTest(test);
			System.out.println("insert");
			out.print(result);
		} else if (uri.equals("update")) {
			result += testService.updateTest(test);
			out.print(result);
		} else if (uri.equals("delete")) {
			result += testService.deleteTest(test.getCtNum());
			out.print(result);
		}

	}

	
	public void gotoJSP(HttpServletRequest request, HttpServletResponse response, String uri) throws ServletException, IOException {
		String prefix = "/WEB-INF/views/";
		
		uri = prefix + uri + ".jsp";
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		
		rd.forward(request, response);
	}
}
