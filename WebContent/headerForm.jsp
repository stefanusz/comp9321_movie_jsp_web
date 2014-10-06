<c:if test = "${role != 'admin'}">
<% 
	request.getSession().setAttribute("message", "Sorry you are not authorized to see this page. You have been redirected.");
	RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
	rd.forward(request, response);
	
%>
</c:if>

<c:out value=" ${message} " />
<% 
	request.getSession().setAttribute("message", "");
%>