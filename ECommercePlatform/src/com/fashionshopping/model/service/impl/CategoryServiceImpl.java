package com.fashionshopping.model.service.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fashionshopping.model.bean.Category;
import com.fashionshopping.model.condb.ConMysql;
import com.fashionshopping.model.service.CategoryService;


public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	
    
	public CategoryServiceImpl(){
		super();
	}
	
	public static void main(String[] args) {
		CategoryServiceImpl cs=new CategoryServiceImpl();

//		//查询
//		String sql="select * from category where id=?";
//		String[] paras={"3"};
//		String className="com.fashionshopping.model.bean.Category";
//	    ArrayList<Category> arr=cs.get(className,sql, paras);
//        Category c=arr.get(0);
//	    System.out.println(c.toString());
//		//增加
//		String sql="update category set type=? where id=?";
//	    String paras[]={"Youth leisure","1"};
//		cs.save(sql, paras);
//		
//		cs.update(sql, paras);
		//查询categoty集合测试
//		ArrayList<Category> arr=cs.query();
//		for(int i=0;i<arr.size();i++){
//	      Category c=arr.get(i);
//	      System.out.println(c.toString());	
//		}
		//将category集合转换为json格式测试
//		JSONArray json=cs.hashmapToJson();
//		System.out.println(json);
//		cs.getDateJson();
//		int i=cs.getRowCount();
//		//查询行数
//		System.out.println(i);
		//cs.deleteCategoryByIds("2,4");
		ArrayList<Category> testa = cs.getSelectedCategory("儿");
		System.out.println(testa.size()+"---------------");
	}
//	//查询类别信息
//    public ArrayList<Category> query(){
//    	String sql="select * from category ";
//		String[] paras={};
//		String className="com.fashionshopping.model.bean.Category";
//	    ArrayList<Category> arr=this.get(className,sql, paras);
//	    return arr;
//	}
    //根据类名查询数据库中该类的记录条数（行数）
    public int getRowCount() {
		int rowCount=0;
		try{
			 //得到数据库连接
	        ConMysql connection=new ConMysql();
	        con=connection.getCon();
	        String sql="select count(*) from category";
	        ps=con.prepareStatement(sql); 
	        rs=ps.executeQuery();
	        if(rs.next()){
	        	rowCount=rs.getInt(1);
	        }
       }catch(Exception e){
     	  e.printStackTrace();
       }finally{
     	  this.close();
       }
		return rowCount;
	}   
    //根据id删除类别信息
    public Boolean deleteCategoryByIds(String ids){
       Boolean b=false;
       try{
			 //得到数据库连接
	        ConMysql connection=new ConMysql();
	        con=connection.getCon();
	        String sql="delete from category where id in ("+ids+")";
	        ps=con.prepareStatement(sql); 
	       // ps.setString(1, ids);
	        int i=ps.executeUpdate();
	        if(i!=0){
	        	b=true;
	        }
	        System.out.println("delete ok");
      }catch(Exception e){
    	  e.printStackTrace();
      }finally{
    	  this.close();
      }
       return b;	
    }
    //根据输入类型查询类别信息
    public ArrayList<Category> getSelectedCategory(String categoryType){
    	   ArrayList<Category> arr=new ArrayList<Category>();
    	   try{
  			 //得到数据库连接
  	        ConMysql connection=new ConMysql();
  	        con=connection.getCon();
  	        String sql="select c.id,c.type,c.hot,a.login from category c left join account as a on a.id=c.aid where type like ?";
  	        ps=con.prepareStatement(sql); 
  	        ps.setString(1,"%"+categoryType+"%");
  	        rs=ps.executeQuery();
  	        while(rs.next()){
  	        	Category c=new Category();
  	        	c.setId(rs.getInt(1));
  	        	c.setType(rs.getString(2));
  	        	c.setHot(rs.getBoolean(3));
  	        	//c.setAid(rs.getInt(4));
  	        	c.setLogin(rs.getString(4));
  	        	arr.add(c);
  	        }
        }catch(Exception e){
      	  e.printStackTrace();
        }finally{
      	   this.close();
        }
    	 return arr;
    }
    //按照查询类型得到行数
    public int getRowCountBySelected(String categoryType) {
		int rowCount=0;
		try{
			 //得到数据库连接
	        ConMysql connection=new ConMysql();
	        con=connection.getCon();
	        String sql="select count(*) from category where type like '%"+categoryType+"%' ";
	        ps=con.prepareStatement(sql); 
	        //ps.setString(1, categoryType);	        
	        rs=ps.executeQuery();
	        if(rs.next()){
	        	rowCount=rs.getInt(1);
	        }
       }catch(Exception e){
     	  e.printStackTrace();
       }finally{
     	  this.close();
       }
		return rowCount;
	}
   //添加类别信息
    public void addCategory(String category,Boolean b){
    	try{
            //得到数据库连接
    	 //System.out.println("Dao层："+ category);
           ConMysql connection=new ConMysql();
           con=connection.getCon();
           String sql="insert into category (type,hot) values(?,?)";
           ps=con.prepareStatement(sql);
           ps.setString(1, category);
           ps.setBoolean(2, b);
           ps.executeUpdate();
           //System.out.println("insert OK");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          //关闭资源
          this.close();
        }
    }
    //得到类别集合信息
    public ArrayList<Category> getByPageAndPagesize( int pageNow,int pageSize){
    	ArrayList<Category> result =new ArrayList<Category>();
		try{
            //得到数据库连接
           ConMysql connection=new ConMysql();
           con=connection.getCon();
           String sql="select c.id,c.type,c.hot,a.id,a.login from category c left join account a on c.aid=a.id limit ?,?";
           ps=con.prepareStatement(sql);
           ps.setInt(1, pageSize*(pageNow-1));
           ps.setInt(2, pageSize);
           rs=ps.executeQuery();
           while(rs.next()){
        	   Category c=new Category();
        	   c.setId(rs.getInt(1));
        	   c.setType(rs.getString(2));
        	   c.setHot(rs.getBoolean(3));
        	   c.setAid(rs.getInt(4));
        	   c.setLogin(rs.getString(5));
        	   result.add(c);
           }
          }catch(Exception e){
        	  e.printStackTrace();
          }finally{
        	  this.close();
          }		
		return result;
    }
}
