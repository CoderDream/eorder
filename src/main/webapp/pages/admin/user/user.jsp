<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<title>eOrder</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="../resources/js/jquery.min.js" /></script>
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/listutil.js"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>

<script type="text/javascript">
	function moveOneRoleToLeft() {
		// alert('moveOneToLeft');
		var oListbox1 = document.getElementById("myRoles");
		var oListbox2 = document.getElementById("leftRoles");
		moveOneToLeft(oListbox1, oListbox2);
	}

	function moveOneRoleToRight() {
		// alert('moveOneToLeft');
		var oListbox1 = document.getElementById("myRoles");
		var oListbox2 = document.getElementById("leftRoles");
		moveOneToRight(oListbox1, oListbox2);
	}

	function moveAllRolesToLeft() {
		// alert('moveAllToLeft');
		var oListbox1 = document.getElementById("myRoles");
		var oListbox2 = document.getElementById("leftRoles");

		moveAllToLeft(oListbox1, oListbox2);
	}

	function moveAllRolesToRight() {
		// alert('moveOneToLeft');
		var oListbox1 = document.getElementById("myRoles");
		var oListbox2 = document.getElementById("leftRoles");

		moveAllToRight(oListbox1, oListbox2);
	}

	function save() {
		//alert('call save');
		//获取该页面中的第一个表单元素
		var targetForm = document.getElementById("saveForm");
		//动态修改目标表单的action属性
		targetForm.action = "doStore.action";
		//提交表单
		targetForm.submit();
	}

	function update() {
		//alert('call update');

		var myRolesOptionsObj = document.getElementById("myRoles");
		//alert('getOptions #1');
		var leftRolesOptionsObj = document.getElementById("leftRoles");
		//alert('getOptions #2');
		var myRolesOptions = myRolesOptionsObj.options;
		//alert('getOptions #3');
		var leftRolesOptions = leftRolesOptionsObj.options;
		//alert('getOptions #4');

		var myList = new Array();

		//alert('myRolesOptions.length: ' + myRolesOptions.length);
		for (var i = 0; i < myRolesOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert('myRolesOptions[i].firstChild.nodeValue: ' + myRolesOptions[i].value);
			if (0 != myRolesOptions[i].value) {
				myList.push(myRolesOptions[i].value);
			}
		}
		var leftList = new Array();
		//alert('leftRolesOptions.length: ' + leftRolesOptions.length);
		for (var i = 0; i < leftRolesOptions.length; i++) {
			//alert(cnbook[i].getAttribute("value"));
			//alert(leftRolesOptions[i].value);
			if (0 != leftRolesOptions[i].value) {
				leftList.push(leftRolesOptions[i].value);
			}
		}
		//alert('update ###5');
		document.getElementById("myRolesArray").value = myList;
		document.getElementById("leftRolesArray").value = leftList;

		//alert('update ###');

		//获取该页面中的第一个表单元素
		var targetForm = document.getElementById("updateForm");
		//动态修改目标表单的action属性
		targetForm.action = "doUpdate.action";
		//提交表单
		targetForm.submit();
	}
</script>
<body>
	<!--head navigation bar -->
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<!-- 商家名称-->
			<a class="navbar-brand">eOrder订餐系统</a>
			<!--自适应mobile设备菜单开关, Web后台可以不管-->
			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!-- 标题栏 -->
			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<!--标题栏里面需要展现的item-->
					<li><p class=navbar-text>欢迎: 管理员</p></li>
				</ul>
			</div>
		</div>
	</div>

	<!--页面主体部分-->
	<div class="container-fluid">
		<div class="row">
			<!--左侧菜单 begin-->
			<div class="col-md-2">
				<div class="panel-group" id="eorder_menu" role="tablist"
					aria-multiselectable="true">
					<s:iterator value="menulist" id="banner">
						<!-- 用户权限管理模块 -->
						<div class="panel panel-default">
							<!--菜单头-->
							<div class="panel-heading" role="tab" id="usr_mgt">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#eorder_menu"
										href="#usr_mgt_list" aria-expanded="true" aria-controls="usr_mgt_list">
										<s:property value="#banner.functionName" />
									</a>
								</h4>
							</div>
							<div class="panel-collapse collapse in" id="usr_mgt_list" role="tabpanel"
								aria-labelledby="usr_mgt">
								<!--菜单内容-->
								<div class="panel-body">
									<div class="list-group">
										<s:iterator value="#banner.list" id="subBanner">
											<a class="list-group-item"
												href='<%=basePath%><s:property value="#subBanner.link" />'
												target="main"><s:property value="#subBanner.functionName" /></a>
										</s:iterator>

									</div>
								</div>
							</div>
						</div>
					</s:iterator>
				</div>
			</div>
			<!--左侧菜单 end-->

			<div class="col-md-9">
				<!--导航标题栏-->
				<div class="row">
					<h3 class="page-header">用户管理</h3>
				</div>

				<!--列表表格-->
				<div class="row">
					<div class="col-md-3">
						<s:if test="null == userId">
							<h4>新增功能</h4>
							<s:form class="eorder-form-usr" id="saveForm" action="doStore">
								<s:hidden id="roleId" name="roleId" />
								<s:hidden id="myRolesArray" name="myRolesArray" />
								<s:hidden id="leftRolesArray" name="leftRolesArray" />
								<input type="text name=" id="username" name="username"
									class="form-control eorder-input" placeholder="用户名" />
								<input type="password" id="password" name="password"
									class="form-control eorder-input" placeholder="密  码">
								<input type="text" id="cellphone" name="cellphone"
									class="form-control eorder-input" placeholder="手机号码">
								<a href="#" onclick="save();"
									class="btn btn-default btn-block eorder-btn-login">创建新用户</a>
							</s:form>
						</s:if>
						<s:else>
							<h4>修改功能</h4>
							<s:form class="eorder-form-usr" id="updateForm" action="doUpdate">
								<s:hidden id="userId" name="userId" value="%{userId}" />
								<s:hidden id="roleId" name="roleId" />
								<s:hidden id="myRolesArray" name="myRolesArray" />
								<s:hidden id="leftRolesArray" name="leftRolesArray" />
								<input type="text" id="username" name="username"
									class="form-control eorder-input" placeholder="用户名" value="${username}">
								<input type="password" id="password" name="password"
									class="form-control eorder-input" placeholder="密  码"
									value="${password}">
								<input type="text" id="cellphone" name="cellphone"
									class="form-control eorder-input" placeholder="手机号码"
									value="${cellphone}">
								<a href="#" onclick="update();"
									class="btn btn-default btn-block eorder-btn-login">修改用户信息</a>
							</s:form>
						</s:else>
					</div>

					<div class="col-md-3">
						<h4>已分配角色</h4>
						<select id="myRoles" name="myRoles" multiple
							class="form-control eorder-multi-sel">

							<s:iterator value="myRoles" id="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</s:iterator>
						</select>
					</div>
					<div class="col-md-2 text-center">
						<button class="btn btn-default eorder-btn-arrow" style="margin-top: 42px"
							onclick="moveAllRolesToLeft();">
							<span class="glyphicon glyphicon-backward"></span>
						</button>
						<br />
						<button class="btn btn-default eorder-btn-arrow"
							onclick="moveOneRoleToLeft();">
							<span class="glyphicon glyphicon-chevron-left"></span>
						</button>
						<br />
						<button class="btn btn-default eorder-btn-arrow"
							onclick="moveOneRoleToRight();">
							<span class="glyphicon glyphicon-chevron-right"></span>
						</button>
						<br />
						<button class="btn btn-default eorder-btn-arrow"
							onclick="moveAllRolesToRight();">
							<span class="glyphicon glyphicon-forward"></span>
						</button>
					</div>
					<div class="col-md-3">
						<h4>可分配角色</h4>
						<select id="leftRoles" name="leftRoles" multiple
							class="form-control eorder-multi-sel">
							<s:iterator value="leftRoles" id="role">
								<option value="${role.roleId}">${role.roleName}</option>
							</s:iterator>
						</select>
					</div>
				</div>

				<br>

				<div class="row">
					<h3 class="page-header">用户列表</h3>
				</div>

				<!--列表表格-->
				<div class="row">
					<div class="col-md-10">
						<table class="table table-condensed">
							<s:form id="userForm" action="doRemove" theme="simple">
								<thead>
									<tr>
										<th></th>
										<th>用户名</th>
										<th>角色</th>
										<th>手机号码</th>
										<th>用户等级</th>
										<th>编辑</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="uservos">
										<tr>
											<td><s:hidden id="userId" name="userId" value="%{userId}" /></td>
											<td><s:property value="username" /></td>
											<td><s:property value="roleName" /></td>
											<td><s:property value="cellphone" /></td>
											<td><s:property value="levelName" /></td>
											<td><a
												href='<s:url action="doEdit"><s:param name="userId" value="userId" /></s:url>'>
													<span class="glyphicon glyphicon-edit">
											</a></span></td>
											<td><a
												href='<s:url action="doRemove"><s:param name="userId" value="userId" /></s:url>'>
													<span class="glyphicon glyphicon-trash">
											</a></span></td>
										</tr>
									</s:iterator>
								</tbody>
							</s:form>
						</table>

						<!--表格分页-->
						<nav class="pull-right" style="margin-right: -17px">
							<ul class="pagination">
								<li><a href="#">&laquo;</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</nav>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!--页面底部footer-->
	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<p class="navbar-text pull-left">Powered by eOrder</p>
		</div>
	</div>

</body>
</html>