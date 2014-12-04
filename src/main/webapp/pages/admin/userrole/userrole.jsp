<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="../resources/js/moo.fx.js" type="text/javascript"></script>
<script src="../resources/js/moo.fx.pack.js" type="text/javascript"></script>
<script src="../resources/js/utils.js" type="text/javascript"></script>
<script src="../resources/js/inputtransferselect.js" type="text/javascript"></script>
<script src="../resources/js/optiontransferselect.js" type="text/javascript"></script>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

table thead tr th {
	border: 1px solid black;
	padding: 3px;
	background-color: #cccccc;
}

table tbody tr td {
	border: 1px solid black;
	padding: 3px;
}
</style>
</head>
<script type="text/javascript">
	function loadRoles() {
		//alert('call loadRoles');
		var t = document.getElementById("user");
		//alert("t: " + t);
		//alert("t: " + t.options[t.selectedIndex].value);
		var a = t.options[t.selectedIndex].value
		//alert('a: ' + a);
		document.getElementById("roleId").value = a;
		//alert('b: ' + document.getElementById("roleId").value);
		//val selectedRoleName = document.getElementById("role").options[t.selectedIndex].value;
		//alert('selectedRoleName: ' + selectedRoleName);
		//var roleId = document.getElementById("roleId");
		//alert('roleId value: ' + roleId.value);
		//roleId.value = selectedRoleName; 
		//alert('OK');
		//var roleObject = document.getElementById("role");
		//alert('roleObject: ' + roleObject);
		//var optionsObject = document.getElementById("role").options[selectIndex];
		//alert('optionsObject: ' + optionsObject);
		//var textValue = document.getElementById("role").options[selectIndex].text;
		//alert('textValue: ' + textValue);
		
		//alert(pageNow.value);
		//获取该页面中的第一个表单元素
		var targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "doLoad.action";
		//提交表单
		targetForm.submit();
	}
	
	function updateRoles() {
		//alert('call loadRoles');
		//var t = document.getElementById("role");
		//alert("t: " + t);
		//alert("t: " + t.options[t.selectedIndex].value);
		//var a = t.options[t.selectedIndex].value
		//alert('a: ' + a);
		//document.getElementById("roleId").value = a;
		//alert('b: ' + document.getElementById("roleId").value);
		//val selectedRoleName = document.getElementById("role").options[t.selectedIndex].value;
		//alert('selectedRoleName: ' + selectedRoleName);
		//var roleId = document.getElementById("roleId");
		//alert('roleId value: ' + roleId.value);
		//roleId.value = selectedRoleName; 
		//alert('OK');
		//var roleObject = document.getElementById("role");
		//alert('roleObject: ' + roleObject);
		//var optionsObject = document.getElementById("role").options[selectIndex];
		//alert('optionsObject: ' + optionsObject);
		//var textValue = document.getElementById("role").options[selectIndex].text;
		//alert('textValue: ' + textValue);
		
		//alert(pageNow.value);
		
		var myRolesOptionsObj = document.getElementById("myRoles");
		//alert('getOptions #1');
		var leftRolesOptionsObj = document.getElementById("leftRoles");
		//alert('getOptions #2');
		var myRolesOptions = myRolesOptionsObj.options;
		//alert('getOptions #3');
		var leftRolesOptions = leftRolesOptionsObj.options;
		//alert('getOptions #4');
		//var cnbook = document.getElementByName("cnbook");
		//alert("cnbook: " + cnbook);
		//alert("cnbook: " + cnbook.length);
		
		//var cnbookFlag = "";
		var myList = new Array();

		//alert('myRolesOptions.length: ' + myRolesOptions.length);
		for (var i = 0; i < myRolesOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(myRolesOptions[i].value);
			if(0 != myRolesOptions[i].value) {
				myList.push(myRolesOptions[i].value);
			}
		}
		var leftList = new Array();
		for (var i = 0; i < leftRolesOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(leftRolesOptions[i].value);
			if(0 != leftRolesOptions[i].value) {
				leftList.push(leftRolesOptions[i].value);
			}
		}
		document.getElementById("myRolesArray").value = myList;
		document.getElementById("leftRolesArray").value = leftList;	
		
		// 
		//alert('doUpdate');
		//获取该页面中的第一个表单元素
		var targetForm = document.forms[0];
		//动态修改目标表单的action属性
		targetForm.action = "doUpdate.action";
		//提交表单
		targetForm.submit();
	}
	
</script>
<body>
	<div width="1020" style="height: 604px; background-color: #FFFFFF">
		<s:form id="roleForm" action="#" method="post" theme="simple">
			<s:hidden id="roleId" name="roleId"/>
			<s:hidden id="myRolesArray" name="myRolesArray"/>
			<s:hidden id="leftRolesArray" name="leftRolesArray"/>
			<table cellspacing="0">
				<tr>
					<td><s:select 
							label="用户" 
							list="users" 
							id="user" 
							name="user" 
							listKey="userId"
							listValue="username" 
							value="userId"
							onchange="javascript:loadRoles();" 
							multiple="ture" 
							size="10"/>
					</td>
					<td><s:optiontransferselect
							id="myRoles"
							doubleId="leftRoles"
							label="拥有的功能" 
							name="myRoles" 
							list="myRoles" 
							listKey="roleId" 
							listValue="roleName" 
							doubleName="leftRoles" 
							doubleList="leftRoles" 
							doubleListKey="roleId"  
							doubleListValue="roleName"
							/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" value="更新角色功能关系" onclick="updateRoles();"/></td></tr>
			</table>
		</s:form>
	</div>

</body>
</html>