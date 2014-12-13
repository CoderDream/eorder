<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
pageContext.setAttribute("base",request.getContextPath());
pageContext.setAttribute("static",AppPropertiesHolder.getStaticWebappContext(request));
pageContext.setAttribute("reportWebappContext",request.getContextPath());
%>