<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuários</title>


<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>


</head>
<body>
    <!--  remove a nav pública -->
	<jsp:include page="/auth/auth-generica-nav.jsp" />

	<div class="container">
		<div class="row">
			
			<div class="col">
				<h2>
					<fmt:message key="admin-listar-usuario.lista" />
				</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th><fmt:message key="admin-listar-usuario.id" /></th>
							<th><fmt:message key="publica-novo-usuario.nome" /></th>
							<th><fmt:message key="publica-novo-usuario.cpf" /></th>
							<th><fmt:message key="publica-novo-usuario.nascimento" /></th>
							<th><fmt:message key="publica-novo-usuario.email" /></th>
							<th><fmt:message key="admin-listar-usuario.ativado" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="usuario" items="${listaUsuarios}">
							<tr>
								<td><c:out value="${usuario.id}" /></td>
								<td><c:out value="${usuario.nome}" /></td>
								<td><c:out value="${usuario.cpf}" /></td>
								<td><fmt:formatDate value='${usuario.dataNascimento}'
										type='date' pattern='dd/MM/yyyy' /></td>
								<td><c:out value="${usuario.email}" /></td>

								<td>
									
									<c:choose>
									  <c:when test="${usuario.ativo=='true'}">
									    <span> 
									    	<fmt:message key="admin-listar-usuario.ativo" />
									    </span>
									  </c:when>									  
									  <c:otherwise>
									    <span> 
									    	<fmt:message key="admin-listar-usuario.naoativo" />
									    </span>
									  </c:otherwise>
									</c:choose>	
								</td>
								<!-- aula 14 -->
								<td>
									<c:forEach var="papel" items="${usuario.papeis}">
										<span><c:out value="${papel.tipoPapel}"/></span><br/>
									</c:forEach>
								</td>
								<!-- fim aula 14 -->
								<td> 																			
									<a class="btn btn-outline-danger btn-sm"
									onclick="return confirm('<fmt:message key="admin-listar-usuario.confirmacaoapagar" />');"
									href="${pageContext.request.contextPath}/auth/admin?acao=apagar&id=<c:out value="${usuario.id}" />">
									<fmt:message key="admin-listar-usuario.botaoapagar" /></a>
									
									<!-- aula 13 -->
									<a class="btn btn-outline-primary btn-sm"
									href="${pageContext.request.contextPath}/auth/admin?acao=iniciarEditarPapel&id=<c:out value="${usuario.id}" />"><fmt:message
											key="admin-listar-usuario.botaopapel" /></a>
									<!-- fim aula 13 -->
									
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