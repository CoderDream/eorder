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
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>

<script type="text/javascript">
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
					<h3 class="page-header">功能管理</h3>
				</div>

				<div class="row">
					<div class="col-md-3">
						<s:if test="null == functionId">
							<h4>新增功能</h4>
							<s:form class="eorder-form-usr" id="saveForm" action="doStore">
								<input type="text" id="functionName" name="functionName"
									class="form-control eorder-input" placeholder="功能名称" />
								<input type="text" id="functionDesc" name="functionDesc"
									class="form-control eorder-input" placeholder="功能描述" />
								<input type="text" id="functionPath" name="functionPath"
									class="form-control eorder-input" placeholder="功能路径" />
								<input type="text" id="functionParent" name="functionParent"
									class="form-control eorder-input" placeholder="上级功能" />
								<input type="text" id="functionOrder" name="functionOrder"
									class="form-control eorder-input" placeholder="功能排序" />
								<a href="#" onclick="save();"
									class="btn btn-default btn-block eorder-btn-login">创建新功能</a>
							</s:form>
						</s:if>
						<s:else>
							<h4>修改功能</h4>
							<s:form class="eorder-form-usr" id="updateForm" action="doUpdate">
								<s:hidden id="functionId" name="functionId" value="%{functionId}" />
								<input type="text" id="functionName" name="functionName"
									class="form-control eorder-input" placeholder="功能名称"
									value="${functionName}" />
								<input type="text" id="functionDesc" name="functionDesc"
									class="form-control eorder-input" placeholder="功能描述"
									value="${functionDesc}" />
								<input type="text" id="functionPath" name="functionPath"
									class="form-control eorder-input" placeholder="功能路径"
									value="${functionPath}" />
								<input type="text" id="functionParent" name="functionParent"
									class="form-control eorder-input" placeholder="上级功能"
									value="${functionParent}" />
								<input type="text" id="functionOrder" name="functionOrder"
									class="form-control eorder-input" placeholder="功能排序"
									value="${functionOrder}" />
								<a href="#" onclick="update();"
									class="btn btn-default btn-block eorder-btn-login">修改功能信息</a>
							</s:form>
						</s:else>
					</div>
				</div>

				<br>

				<div class="row">
					<h3 class="page-header">功能列表</h3>
				</div>

				<!--列表表格-->
				<div class="row">
					<div class="col-md-10">
						<table class="table table-condensed">
							<s:form id="functionForm" action="doRemove" theme="simple">
								<thead>
									<tr>
										<th></th>
										<th>功能名称</th>
										<th>功能描述</th>
										<th>功能路径</th>
										<th>上级功能ID</th>
										<th>上级功能名称</th>
										<th>功能排序</th>
										<th>编辑</th>
										<th>删除</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="functionvos">
										<tr>
											<td><s:hidden id="functionId" name="functionId"
													value="%{functionId}" /></td>
											<td><s:property value="functionName" /></td>
											<td><s:property value="functionDesc" /></td>
											<td><s:property value="functionPath" /></td>
											<td><s:property value="functionParent" /></td>
											<td><s:property value="functionParentName" /></td>
											<td><s:property value="functionOrder" /></td>
											<td><a
												href='<s:url action="doEdit"><s:param name="functionId" value="functionId" /></s:url>'>
													<span class="glyphicon glyphicon-edit">
											</a></span></td>
											<td><a
												href='<s:url action="doRemove"><s:param name="functionId" value="functionId" /></s:url>'>
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