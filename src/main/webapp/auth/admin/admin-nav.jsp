<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Home</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
		
			 <ul class="nav navbar-nav">
                    
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                        	<fmt:message key="admin-nav-management" />
                        </a>
                        <div class="dropdown-menu">
                            <a href="${pageContext.request.contextPath}/auth/admin?acao=listar" class="dropdown-item">
                            	<fmt:message key="admin-nav-users" />
                            </a>
                        </div>
                    </li>
                </ul>
                
			<jsp:include page="/auth/auth-generica-dropdown-conta.jsp" />
		</div>
	</div>
</nav></html>