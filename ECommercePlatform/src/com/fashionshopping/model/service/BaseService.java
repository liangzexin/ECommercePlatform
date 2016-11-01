package com.fashionshopping.model.service;

import java.util.ArrayList;
import java.util.List;

import com.fashionshopping.model.bean.Category;

public interface BaseService<T> {
	   
	   public void close();
	   //向数据库添加（保存）数据
	   public void save(String sql,String paras[]);
	   //更新数据库数据
	   public void update(String sql,String paras[]);
	   //根据Id删除对象
	   public void delete(int id);
	   //根据类名查询相应类的数据库中的数据集
	   public ArrayList<T> get(String className,String sql,String paras[]);
	   //按照页数和页数大小得到相应的对象记录
	   public ArrayList<T> get(String className,int pageNow,int pageSize);  

}
