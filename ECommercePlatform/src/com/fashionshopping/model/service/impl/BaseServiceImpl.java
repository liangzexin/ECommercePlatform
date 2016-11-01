package com.fashionshopping.model.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionshopping.model.condb.ConMysql;
import com.fashionshopping.model.service.BaseService;
/**
 * 公共模块的抽取
 * @author Administrator
 *
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {
	protected Connection con;
	protected  PreparedStatement ps;
	protected  ResultSet rs;
	private Class clazz;
	public BaseServiceImpl(){
		ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class) type.getActualTypeArguments()[0];
	}
    //添加对象信息
	@Override
	public void save(String sql,String paras[]) {
		// TODO Auto-generated method stub
		try{
            //得到数据库连接
           ConMysql connection=new ConMysql();
           con=connection.getCon();
           ps=con.prepareStatement(sql);
           for(int i=0;i<paras.length;i++){
        	   ps.setString(i+1, paras[i]);
           }
           ps.executeUpdate();
           System.out.println("insert OK");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          //关闭资源
          this.close();
        }
	}
    //修改信息
	@Override
	public void update(String sql,String paras[]) {
		// TODO Auto-generated method stub
		try{
            //得到数据库连接
           ConMysql connection=new ConMysql();
           con=connection.getCon();
           ps=con.prepareStatement(sql);
           for(int i=0;i<paras.length;i++){
        	   ps.setString(i+1, paras[i]);
           }
           ps.executeUpdate(); 
           System.out.println("update OK");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          //关闭资源
          this.close();
        }
		
	}
    //根据id删除信息
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		try{
            //得到数据库连接
           ConMysql connection=new ConMysql();
           con=connection.getCon();
           String sql="delete from "+clazz.getSimpleName()+" where id=?";
           ps=con.prepareStatement(sql);
           ps.setInt(1, id);
           ps.executeUpdate(); 
           System.out.println("delete OK");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
          //关闭资源
          this.close();
        }
	}
	//将数据结果集中的一行数据转换为对应的对象，并将入到数组集合中
	public ArrayList<Object> tran(ResultSet rs,String className){
		ArrayList<Object> result = new ArrayList<Object>();
		try {
			Class<?> clsFlag = Class.forName(className);
			while(rs.next()){
				Object o = clsFlag.newInstance();
				Field[] fields = clsFlag.getDeclaredFields();
				Method[] methods = clsFlag.getMethods();  
		        for(int i = 0; i < fields.length; i++){ 
		    	   Field field = fields[i];
		    	   Object data = rs.getObject(field.getName());
		    	   String methodName = "SET" + field.getName().toUpperCase();
		    	   for(Method md : methods){
		    		   if(methodName.equals(md.getName().toUpperCase())){
		    			   md.invoke(o, data);
		    		   }
		    	   }
		    	   
		       }
		       result.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;    
    }
		
    //获得对象信息
	@Override
	public ArrayList<T> get(String className,String sql,String paras[]) {
		// TODO Auto-generated method stub
		ArrayList<Object> result =null;
		try{
            //得到数据库连接
           ConMysql connection=new ConMysql();
           con=connection.getCon();
           ps=con.prepareStatement(sql);
           for(int i=0;i<paras.length;i++){
        	   ps.setString(i+1, paras[i]);
           }
           rs=ps.executeQuery();
           //调用tran函数将行数据转换为对象
           result=tran(rs,className);
          }catch(Exception e){
        	  e.printStackTrace();
          }finally{
        	  this.close();
          }
		
		return (ArrayList<T>) result;
	}
	//根据页数获得对象信息
		@Override
		public ArrayList<T> get(String className,int pageNow,int pageSize)  {
			// TODO Auto-generated method stub
			ArrayList<Object> result =null;
			try{
	            //得到数据库连接
	           ConMysql connection=new ConMysql();
	           con=connection.getCon();
	           String sql="select * from "+clazz.getSimpleName()+" limit ?,?";
	           ps=con.prepareStatement(sql);
	           ps.setInt(1, pageSize*(pageNow-1));
	           ps.setInt(2, pageSize);
	           rs=ps.executeQuery();
	           result=tran(rs,className);
	          }catch(Exception e){
	        	  e.printStackTrace();
	          }finally{
	        	  this.close();
	          }		
			return (ArrayList<T>) result;
		}
    //资源关闭
	@Override
	public void close() {
		// TODO Auto-generated method stub
		 try {
             if(rs!=null)rs.close();
             if(ps!=null)ps.close();
             if(con!=null)con.close();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
	}
	

}
