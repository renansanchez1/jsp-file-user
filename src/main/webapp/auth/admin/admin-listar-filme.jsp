<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Filmes</title>

<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</head>
<body>

	<jsp:include page="/auth/auth-generica-nav.jsp" />

	<div class="container mt-4">
		<div class="row">
			<div class="col">
				<h2>Lista de Filmes</h2>

				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nome</th>
							<th>Classificação</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="filme" items="${listaFilmes}">
							<tr>
								<td><c:out value="${filme.id}" /></td>
								<td><c:out value="${filme.nome}" /></td>
								<td><c:out value="${filme.classificacao}" /></td>
								<td>
									<a class="btn btn-outline-danger btn-sm"
									   onclick="return confirm('Tem certeza que deseja apagar este filme?');"
									   href="${pageContext.request.contextPath}/auth/admin?acao=apagarFilme&id=${filme.id}">
										Apagar
									</a>

								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
