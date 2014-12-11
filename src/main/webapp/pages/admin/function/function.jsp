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
								<th>父功能</th>
								<th>功能序号</th>
								<th>编辑</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="functions">
								<tr>
									<td><input type="checkbox" name="functionId"
										value='<s:property value="functionId" />' /></td>
									<td><s:property value="functionName" /></td>
									<td><s:property value="functionDesc" /></td>
									<td><s:property value="functionPath" /></td>
									<td><s:property value="functionParent" /></td>
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
</body>
</html>