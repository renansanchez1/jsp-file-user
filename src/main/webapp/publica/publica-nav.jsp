<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Home</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href="#">
						<fmt:message key="publica-nav.home" />
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/publica?acao=novo">
						<fmt:message key="publica-nav.novo" />
					</a>
				</li>
				<!--  aula 18 
				<li class="nav-item">
					<a class="nav-link"
						href="${pageContext.request.contextPath}/auth/admin?acao=listar">
						<fmt:message key="publica-nav.listar" />
					</a>
				</li>
				-->
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/I18nControle?lingua=en_US">
						<fmt:message key="publica-nav.ingles" />
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/I18nControle?lingua=pt_BR">
						<fmt:message key="publica-nav.portugues" />
					</a>
				</li>
				
				<!-- Aula 16 -->
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/login?acao=formLogin">
						<fmt:message key="publica-nav.login" />
					</a>
				</li>
			</ul>
		</div>
	</div>
</nav>