<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Filme</title>
    <link href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/auth/auth-generica-nav.jsp" />

    <div class="container mt-4">
        <div class="row">
            <div class="col">
                <h2>Cadastrar Novo Filme</h2>

                <c:if test="${mensagem != null}">
                    <div class="alert alert-success alert-dismissible fade show">
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        <span><c:out value="${mensagem}" /></span>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/auth/admin?acao=inserirFilme" method="post">
                    <input type="hidden" name="formularioEnviado" value="true" />
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Nome do Filme</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="nome" value="" required />
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Classificação</label>
                        <div class="col-sm-2">
                            <input class="form-control" type="text" name="classificacao" value="" required />
                        </div>
                    </div>
                    <input class="btn btn-primary" type="submit" value="Cadastrar Filme" />
                </form>
            </div>
        </div>
    </div>
</body>
</html>