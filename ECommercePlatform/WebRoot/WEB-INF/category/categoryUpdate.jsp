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
        	//iframe中的dg对象
     		//var iframe = parent.$("iframe");
     		//var conWin = iframe[0].contentWindow;
     		//var rows=conWin.$("#dg").datagrid("getSelections");
     		//iframe中的dg对象
     		var dg=parent.$("iframe[title='类别管理']").get(0).contentWindow.$("#dg")//;
     		//获得的该对象选中的行
     		var rows=dg.datagrid("getSelections");
     		//对管理员的下拉列表框进行远程加载
     		$('#cc').combobox({    
			    url:'accountController?type=getAccountLogin',    
			    valueField:'id',    
			    textField:'login',
			    panelHeight:'auto',
			    panelWidth:120,
			    width:120,
			    editable:false
			}); 
     		//完成数据回显
     		$('#ff').form('load',{
     			id:rows[0].id,
     			categoryType:rows[0].type,
     			hot:rows[0].hot,
     			'account.id':rows[0].aid	
     		});
     		
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
        				url: 'categoryController?type=updateCategory',
        		     	success: function(result){
        					if(result="success"){
        						//关闭当前窗体 
        						parent.$("#win").window("close");
        						//重新加载当前记录
        						dg.datagrid("reload");
        					}
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
	        <label for="account">所属管理员:</label>   
			<!-- 远程加载 管理员数据-->
			<input id="cc" name="account.id"/>  
	    </div>  
	    <div>
	    <!--easyui中没有button-->
	      <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">更新类别</a> 
	      <input type="hidden" name="id" />
	    </div>    
	 </form>  
  </body>
</html>
