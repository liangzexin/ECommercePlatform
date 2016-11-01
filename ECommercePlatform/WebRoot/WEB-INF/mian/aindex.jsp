<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ include file="/public/head.jspf" %>
    <style type="text/css">
        #menu{
            width:200px;
           /*  border:1px solid red; */
        }
        #menu ul{
           list-style: none;
           padding: 0px;
           margin: 0px;
        }
        #menu ul li{
           border-bottom:1px solid #fff;   
        }
         #menu ul li a{
         /*设置a标签 的宽 ，和内间距 先转换为块级元素     */
          display:block;
          background-color:#008792;
          color:#fff;
          padding:5px;
          text-decoration: none;
        }
        #menu ul li a:hover{
           background-color:#00a6ac;
        }
     </style>
     <script type="text/javascript">
          $(function(){
          
            $("a[title]").click(function(){
               //1判断当前右边是否已有相应的tag，如没有，则新建，如有，则切换
               var text=$(this).text();
               var href=$(this).attr("title");
               if($("#tt").tabs("exists",text)){
                  //alert("存在")
                  $("#tt").tabs("select",text)
               }else{
                  $("#tt").tabs("add",{
                    title:text,
                    closable:true,
                    content:'<iframe title=' + text + ' src="testController?type=tocategory" frameborder="0" height="100%" width="100%" />'
                    //href:通过url地址加载远程页面，但仅仅是页面的body部分
                    //href:'********'
                    
                  });
               }
            });
          });   
     </script>
  </head>  
 <body class="easyui-layout">   
 
   
    <div data-options="region:'north',title:'易购后台管理系统',split:true" style="height:100px;"></div>   
    <div data-options="region:'west',title:'系统操作'" style="width:200px;">
	   <!-- 此处显示的是系统菜单 -->
	    <div id="menu" class="easyui-accordion" data-options="fit:true">   
		    <div title="基本操作" data-options="iconCls:'icon-save'" ">   
		        <ul>
       				<li><a href="#" title="testController?type=tocategory">类别管理</a></li>
      				<li><a href="#" title="productController?type=query">商品管理</a></li>
   			    </ul>   
		    </div>   
		    <div title="其他操作" data-options="iconCls:'icon-save'" ">   
		        <ul>
       				<li><a href="#">类别管理</a></li>
      				<li><a href="#">商品管理</a></li>
   			    </ul>   
		    </div> 
		</div> 
    </div>   
    <div data-options="region:'center',title:'后台操作页面'" style="padding:1px;background:#fff;">
        <div id="tt" class="easyui-tabs" data-options="fit:true">   
            <div title="系统缺省页面" style="padding:10px;">   
                                        此处以后显示相应的系统信息（包括操作系统的类型，当前项目的域名，硬件的相关配置，或者显示报表）    
            </div>     
		</div>        
    </div> 
    <div id="win" data-options="collapsible:false,minimizable:false,maximizable:false,modal:true"></div>  
</body> 

</html>
