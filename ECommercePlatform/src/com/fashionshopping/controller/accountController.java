package com.fashionshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fashionshopping.model.bean.Account;
import com.fashionshopping.model.bean.Category;
import com.fashionshopping.model.bean.EchoAccount;
import com.fashionshopping.model.service.impl.AccountServiceImpl;

public class accountController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//test
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		JSONArray json = new JSONArray();
		//JSONObject jobj = new JSONObject();
		AccountServiceImpl as=new AccountServiceImpl();
		if("getAccountLogin".equals(type)){
			ArrayList<EchoAccount> result=as.getAccountLogin();
			for(EchoAccount ea : result){
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", ea.getId());
				jsonObj.put("login", ea.getLogin());
				json.add(jsonObj);
			}
			//jobj = new JSONObject();//new一个JSON 
			//json.fromObject(result);
			//jobj.accumulate("rows", result);//row是代表显示的页的数据  
	        //转化为JSOn格式 
			System.out.println(result);
			System.out.println(json.toString());
	        out.write(json.toString());
			
		}
		
	}

}
