<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>User</title>
</head>
<body>
	<h2>
		<s:if test="null == user">
            新增功能
            <s:form action="doStore">
				<s:textfield name="user.username" label="用户名称" cssStyle="width: 300px;" />
				<s:textfield name="user.password" label="用户密码" cssStyle="width: 300px;" />
				<s:textfield name="user.cellphone" label="手机号" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:if>
		<s:else>
            修改功能
            <s:form action="doUpdate">
				<s:hidden id="userId" name="userId" value="%{user.userId}" />
				<s:textfield name="user.username" label="用户名称" cssStyle="width: 300px;" />
				<s:textfield name="user.cellphone" label="手机号" cssStyle="width: 300px;" />
				<s:submit />
			</s:form>
		</s:else>
	</h2>
</body>
</html>