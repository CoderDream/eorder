<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang='zh-cn'>
<head>
<title>eOrder</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<div class="col-md-9">
		<!--导航标题栏-->
		<div class="row">
			<h3 class="page-header">角色管理</h3>
		</div>

		<form class="eorder-form-usr" role="form">
			<div class="row">
				<div class="col-md-3">
					<input type="text" class="form-control eorder-input" placeholder="用户名">
					<input type="password" class="form-control eorder-input" placeholder="密  码">
					<input type="password" class="form-control eorder-input" placeholder="确认密码">
					<input type="text" class="form-control eorder-input" placeholder="员工号">
					<input type="text" class="form-control eorder-input" placeholder="会员号">
					<a href="#" class="btn btn-default btn-block eorder-btn-login">创建新用户</a>
				</div>
				<div class="col-md-3">
					<h4>已分配角色</h4>
					<select multiple class="form-control eorder-multi-sel">
					</select>
				</div>
				<div class="col-md-2 text-center">
					<button class="btn btn-default eorder-btn-arrow" style="margin-top: 42px">
						<span class="glyphicon glyphicon-backward"></span>
					</button>
					<br />
					<button class="btn btn-default eorder-btn-arrow">
						<span class="glyphicon glyphicon-chevron-left"></span>
					</button>
					<br />
					<button class="btn btn-default eorder-btn-arrow">
						<span class="glyphicon glyphicon-chevron-right"></span>
					</button>
					<br />
					<button class="btn btn-default eorder-btn-arrow">
						<span class="glyphicon glyphicon-forward"></span>
					</button>
				</div>
				<div class="col-md-3">
					<h4>可分配角色</h4>
					<select multiple class="form-control eorder-multi-sel">
						<option>管理员</option>
						<option>菜品编辑</option>
						<option>收银员</option>
						<option>服务员</option>
						<option>用户</option>
					</select>
				</div>
			</div>
		</form>

		<div class="row">
			<h3 class="page-header">角色列表</h3>
		</div>

		<!--列表表格-->
		<div class="row">
			<div class="col-md-10">
				<table class="table table-condensed">
					<s:form id="roleForm" action="doRemove" theme="simple">
						<thead>
							<tr>
								<th></th>
								<th>角色名称</th>
								<th>角色描述</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="roles">
								<tr>
									<td><input type="checkbox" name="roleId"
										value='<s:property value="roleId" />' /></td>
									<td><s:property value="roleName" /></td>
									<td><s:property value="roleDesc" /></td>
									<td><a
										href='<s:url action="doEdit"><s:param name="roleId" value="roleId" /></s:url>'>
											<span class="glyphicon glyphicon-edit">
									</a></span></td>
									<td><a
										href='<s:url action="doRemove"><s:param name="roleId" value="roleId" /></s:url>'>
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
</body>
</html>