package com.fashionshopping.model.service.impl;


import java.util.ArrayList;

import com.fashionshopping.model.bean.Account;
import com.fashionshopping.model.bean.EchoAccount;
import com.fashionshopping.model.condb.ConMysql;
import com.fashionshopping.model.service.AccountService;


//自身的业务逻辑


public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
   ArrayList<Account> result=new  ArrayList<Account>();
    //管理员登录功能	
   //得到数据回显时用户登录名
   public ArrayList<EchoAccount> getAccountLogin(){
	   ArrayList<EchoAccount> result=new  ArrayList<EchoAccount>(); 
	  try{
			 //得到数据库连接
	        ConMysql connection=new ConMysql();
	        con=connection.getCon();
	        String sql="select id,login from account ";
	        ps=con.prepareStatement(sql); 
	        //ps.setString(1, categoryType);
	        
	        rs=ps.executeQuery();
	        while(rs.next()){
               EchoAccount a=new EchoAccount();
               a.setId(rs.getInt(1));
               a.setLogin(rs.getString(2));
               result.add(a);
	        }
     }catch(Exception e){
   	  e.printStackTrace();
     }finally{
   	  this.close();
     }
	  return result;   
   }
}
