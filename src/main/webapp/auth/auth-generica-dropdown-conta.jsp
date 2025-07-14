
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul class="nav navbar-nav ms-auto">
		<li class="nav-item dropdown">
			<a href="#"
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown"> 
				<span><c:out value="${sessionScope.usuarioLogado.usuario.nome}" /></span>
			</a>
			<div class="dropdown-menu dropdown-menu-end">
				<a class="dropdown-item" 
					href="#"> 
					<fmt:message key="auth-generica-dropdown-conta-edit" />
				</a> 
				<a href="#" class="dropdown-item">
					<fmt:message key="auth-generica-dropdown-conta-edit-password" />
				</a>
				<div class="dropdown-divider"></div>
					<a href="${pageContext.request.contextPath}/login?acao=logout" class="dropdown-item">
						<fmt:message key="auth-generica-dropdown-conta-logout" />
					</a>
			</div>
		</li>
</ul>

