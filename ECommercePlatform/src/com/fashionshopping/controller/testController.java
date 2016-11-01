package com.fashionshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fashionshopping.model.bean.Category;
import com.fashionshopping.model.service.impl.CategoryServiceImpl;

public class testController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		if(type.equals("toTestPage")){
			request.getRequestDispatcher("WEB-INF/mian/aindex.jsp").forward(request, response);
			
		}else if(type.equals("tocategory")){
			request.getRequestDispatcher("WEB-INF/category/category.jsp").forward(request, response);
	        
		}
		
	}

}
