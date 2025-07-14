<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title><fmt:message key="publica-login-titulo" /></title>

<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
	
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:include page="/publica/publica-nav.jsp" />
	<div class="container">
		
		<div class="row">
			<div class="col-6">
			
				<c:if test="${mensagem != null}">
				 	<div class="alert alert-danger alert-dismissible fade show">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<span><c:out value="${mensagem}" /></span>
				</div>
				</c:if>	
			
				<form action="${pageContext.request.contextPath}/login?acao=login" method="post">
					<div class="form-group">
						<fmt:message key="publica-login-usuario" />
						<input class="form-control" type="text" name="login"> 
					</div>
					<div class="form-group">
						<fmt:message key="publica-login-senha" />
						<input class="form-control" type="text" name="senha">
					</div>
					<input  class="btn btn-primary" type="submit" 
							value="<fmt:message key="publica-login-botao" />"/>					
				</form>
			</div>
			
		</div>
	</div>
</body>
</html>