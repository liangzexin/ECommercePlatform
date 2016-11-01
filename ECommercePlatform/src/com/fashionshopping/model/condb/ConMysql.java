package com.fashionshopping.model.condb;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConMysql {
	private Connection con=null;
    private static String className="com.mysql.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/fashionshopping?useUnicode=true&characterEncoding=UTF-8";
    private static String user="root";
    private static String password="";
    public Connection getCon(){
        try {
            Class.forName(className);
            con=DriverManager.getConnection(url, user, password);  
            
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return con;
    }

}
