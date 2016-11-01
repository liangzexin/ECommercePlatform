<%@page import="com.fashionshopping.model.bean.Category"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
     <%@ include file="/public/head.jspf" %>
	<style type="text/css">
        body{
          margin:1px;
        }
        .searchbox {
		  margin: -3;
		}
     </style>
     <script type="text/javascript">
        $(function(){
            $('#dg').datagrid({  
	            //请求数据的url  
			    url:'categoryController', 
			    queryParams:{type:'query'},
			    //提示信息
	            loadMsg:'请耐心等待......',
	            //queryParams:{type:''},
	            //指定id字段为标识字段，在删除更新时有用
	            idField:'id',
	            //显示斑马线
	            striped:true,
	            //
	            nowrap:true,
	            //自动适应列，不会出现水平滚动条
	            fitColumns:true,
	            //只能选择单行
	            singleSelect:false,
	            //显示分页栏
	            pagination: true,  
                rownumbers: true,
                pageSize:'5',
                pageList:[5,10,15,20],
	            //工具栏
	            toolbar: [ {
						iconCls: 'icon-add',
						text:'添加类别',
						handler: function(){
                           parent.$("#win").window({
                        	   title:'添加类别',
                        	   width:280,
                        	   height:200,
                        	   content:'<iframe src="categoryController?type=showAddCategoryPage" frameborder="0" height="100%" width="100%" />'
                           });
						}
				   },'-',{
						iconCls: 'icon-edit',
						text:'更新类别',
						handler: function(){
							var rows=$("#dg").datagrid("getSelections");
							  if(rows.length==0){
								  
								  $.messager.show({
										title:'错误提示',
										msg:'请选择一条要更新的记录',
										timeout:2000,
										showType:'slide'
									});
							    
							  }else if(rows.length!=1){
								  $.messager.show({
										title:'错误提示',
										msg:'一次只能更新一条记录',
										timeout:2000,
										showType:'slide'
									});
							  }else{
								  //弹出更新页面
								  parent.$("#win").window({
		                        	   title:'更新类别',
		                        	   width:320,
		                        	   height:260,
		                        	   content:'<iframe src="categoryController?type=showUpdateCategoryPage" frameborder="0" height="100%" width="100%" />'
		                           });  
							  }

						}
				   },'-',{
						iconCls: 'icon-remove',
						text:'删除类别',
						handler: function(){
						  var rows=$("#dg").datagrid("getSelections");
						  if(rows.length==0){
						    $.messager.show({
								title:'错误提示',
								msg:'请至少选中一条记录',
								timeout:2000,
								showType:'slide'
							});
						    
						  }else{
						  //提示是否确认删除
							 $.messager.confirm('确认对话框', '确定要删除所选记录吗？', function(r){
									if (r){
									//此处是删除逻辑实现
									   //1.获取选中记录的id
									   var ids="";
									   for(var i=0;i<rows.length;i++){
									       ids +=rows[i].id+",";
									   }
									   ids=ids.substring(0, ids.lastIndexOf(","));
									   //2.发送ajax请求
									   $.post("categoryController",{type:"delete",ids:ids},function(result){
									      //
									      if(result=="success"){
									    	//取消选中的所有行
									    	 $('#dg').datagrid('uncheckAll');
									    	 alert("--删除成功--"); 
									    	 $('#dg').datagrid('reload');
									    	 
									      }else{
									    	  $.messager.show({	// show error message
													title: '删除异常',
													msg: 删除失败
												});
									      }
									   },"text");   
									}
								});
						   }
						}
				   },'-',{
						//查询分类
						text:"<input id='ss' name='search'/>",	
				   }],	            
	            frozenColumns:[[
	             {field:'xyz',checkbox:true},
	             {field:'id',title:'编号',width:100}
	            ]],
			    columns:[[         
			        {field:'type',title:'类别名称',width:100,
			            //格式化当前列的值，返回的是最终的数据
				        formatter: function(value,row,index){
							return "<span title=" + value + ">" + value + "</span>";
						}
			        },    
			        {field:'hot',title:'热点',width:100,align:'right',formatter: function(value,row,index){
							if(value){
							    return "<input type='checkbox' disabled='disabled' checked='checked'/>";
							}else{
							    return "<input type='checkbox' disabled='true' />";
							}
						}
			       /* //设置当前单元格的样式，返回的字符串直接交给style属性
			           styler: function(value,row,index){
			           console.info("val:"+value+",row:"+row+",index:"+index);
							if (value < 15){
								return 'color:red;';
							}
						}*/ 
			        },
			        {field:'aid',title:'所属管理员id',hidden:true},
			        {field:'login',title:'所属管理员',width:100,align:'right'}
			    ]]    
			}); 
			//把普通文本框转化为搜索文本框
			$('#ss').searchbox({
			    //触发查询时间
				searcher:function(value,name){ 
				  //获取当前查询的关键字，通过dg加载相应的信息
				  //alert(value + "," + name) 
				  $('#dg').datagrid('load',{
						type:'getInputCategory',
						categoryType:value,
					});
				}, 
				prompt:'请输入查询字段' 
			});  
        });
     </script>
  </head>
  <body>
    <table id="dg"></table> 
  </body>
</html>
