<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
	<fmt:message key="publica-novo-usuario.titulo" />
</title>


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
			<div class="col">
				<h2>
					<fmt:message key="publica-novo-usuario.cadastro" />
				</h2>

				<c:if test="${mensagem != null}">
					<div class="alert alert-success alert-dismissible fade show">
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
						<span><c:out value="${mensagem}" /></span>
					</div>
				</c:if>

				<form
					action="${pageContext.request.contextPath}/publica?acao=inserir"
					method="post">

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">
							<fmt:message key="publica-novo-usuario.nome" />
						</label>
						<div class="col-sm-5">
							<input class="form-control" type="text" name="nome">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">
							<fmt:message key="publica-novo-usuario.cpf" />
						</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="cpf">
						</div>
					</div>


					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">
							<fmt:message key="publica-novo-usuario.nascimento" />
						</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="nascimento">
						</div>
					</div>


					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">
							<fmt:message key="publica-novo-usuario.email" />
						</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="email">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">
							<fmt:message key="publica-novo-usuario.login" />
						</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="login">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">
							<fmt:message key="publica-novo-usuario.senha" />
						</label>
						<div class="col-sm-2">
							<input class="form-control" type="password" name="password">
						</div>
					</div>

					<input class="btn btn-primary" 
						type="submit"
						value="<fmt:message key="publica-novo-usuario.botao.registrar" />" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>