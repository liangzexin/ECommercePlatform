<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
     <%@ include file="/public/head.jspf" %>
     <style type="text/css">
      form div{
        margin:5px;
      }
     </style>
     <script type="text/javascript">
        $(function(){
        	$("input[name=type]").validatebox({
        		required:true,
        		missingMessage:'请输入类别名称',
        	});
        	//窗体刚弹出时默认表单禁用验证
        	$("#ff").form("disableValidation");
        	//注册button的事件
        	$("#btn").click(function(){
        		//开启表单验证
        		$("#ff").form("enableValidation");
        		//如果验证成功，则提交数据
        		if($("#ff").form("validate")){
        			//调用submit方法，提交数据
        			$('#ff').form('submit', {
        				url: 'categoryController?type=addCategory',
        		     	success: function(result){
        					//关闭当前窗体 
        					if(result="success"){
        						parent.$("#win").window("close");
        						//parent.$("iframe[title=]")
        						var iframe = parent.$("iframe");
        						var conWin = iframe[0].contentWindow;
        						conWin.$("#dg").datagrid("reload");
        					}
        					//parent.$("#win").window("close");
        					//刷新页面
        					//var rows=parent.$("iframe[title='类别管理']").contents().find("#dg").datagrid("getSelections");
        					//var rows=parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg")//.datagrid("reload");
        					//alert(rows);
        				}
        			});
        		}
        	});
        });
     </script>
  </head> 
  <body >
      <form id="ff" method="post">   
	    <div>   
	        <label for="type">类别名称:</label>   
	        <input type="text" name="categoryType" />   
	    </div>   
	    <div>   
	        <label for="hot">是否热点:</label>
	                         热点<input type="radio" name="hot" value="true" />              
	                         非热点<input type="radio" name="hot" value="false" checked="checked" />  
	    </div>  
	    <div>
	    <!--easyui中没有button-->
	      <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加类别</a> 
	    </div>    
	 </form>  
  </body>
</html>
