package com.fashionshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.fashionshopping.model.bean.Category;
import com.fashionshopping.model.service.impl.CategoryServiceImpl;

public class categoryController extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置回复编码格式为utf-8
		response.setContentType("text/html;charset=UTF-8");
		//设置请求编码格式为utf-8
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		CategoryServiceImpl cs=new CategoryServiceImpl();
		JSONObject jobj = new JSONObject();
		if("query".equals(type)){//首次分类信息显示
			//得到参数并转为int型
			String s_pageNow=request.getParameter("page");
			String s_pageSize=request.getParameter("rows");
			int pageNow=Integer.parseInt(s_pageNow);
			int pageSize=Integer.parseInt(s_pageSize);
			cs=new CategoryServiceImpl();
			//String className="com.fashionshopping.model.bean.Category";
	        ArrayList<Category> result=cs.getByPageAndPagesize(pageNow, pageSize);
	        //得到分类总行数
	        int i=cs.getRowCount();
	        Integer total=Integer.valueOf(i);
	        jobj = new JSONObject();//new一个JSON  
	        jobj.accumulate("total",total);//total代表一共有多少数据  
	        jobj.accumulate("rows", result);//row是代表显示的页的数据  
	        //转化为JSOn格式 
	        out.write(jobj.toString());	
		}else if("delete".equals(type)){//删除分类功能实现
			
			String ids=request.getParameter("ids");
			System.out.println(ids);
			Boolean b=cs.deleteCategoryByIds(ids);
			if(b){
				out.write("success");
			}			
		}else if("getInputCategory".equals(type)){//查询分类功能实现
			String categoryType=request.getParameter("categoryType");
			String s_pageSize=request.getParameter("rows");
			int pageSize=Integer.parseInt(s_pageSize);
			System.out.println(categoryType);
			ArrayList<Category> result=cs.getSelectedCategory(categoryType);
			System.out.println(result);
			int i=cs.getRowCountBySelected(categoryType);
	        Integer total=Integer.valueOf(i);
			if (result!=null){
				System.out.println("ok "+total);
			}
			jobj = new JSONObject();
			jobj.accumulate("total",total);
			jobj.accumulate("rows", result);
			out.write(jobj.toString());
		}else if("showAddCategoryPage".equals(type)){//显示添加分类页面
			request.getRequestDispatcher("WEB-INF/category/categoryAdd.jsp").forward(request, response);
		}else if("addCategory".equals(type)){//添加分类功能实现
	        String categoryType=request.getParameter("categoryType");
	        System.out.println(categoryType);
	        String isHot=request.getParameter("hot");
	        System.out.println(categoryType+"  "+isHot);
	        Boolean hot=false;
	        if("true".equals(isHot)){
	        	 hot=true;
	        }
	        cs.addCategory(categoryType, hot);
	        out.write("success");
		}else if("showUpdateCategoryPage".equals(type)){
			request.getRequestDispatcher("WEB-INF/category/categoryUpdate.jsp").forward(request, response);	
		}else if("updateCategory".equals(type)){
			String ishot=null;
			//获得参数
			String categoryType=request.getParameter("categoryType");
			String hot=request.getParameter("hot");
			if("true".equals(hot)){
				ishot="1";	
			}else{
				ishot="0";
			}
			String accountId=request.getParameter("account.id");
			String id=request.getParameter("id");
			System.out.println(categoryType+"  "+hot+" "+accountId+" "+id);
			String paras[]={categoryType,ishot,accountId,id};
			String sql="update category set type=?,hot=?,aid=? where id=?";
		    cs.update(sql, paras);
			out.write("success");
		}
		
	}

}
