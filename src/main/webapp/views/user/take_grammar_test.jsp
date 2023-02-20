<%@ page import="java.util.List" %>
<%@ page import="com.tafakkoor.englishlearningplatform.domains.Variants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dilgan
  Date: 17/02/23
  Time: 00:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Font Awesome -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
        rel="stylesheet"
/>
<!-- Google Fonts -->
<link
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
        rel="stylesheet"
/>
<!-- MDB -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
        rel="stylesheet"
/>
<html>
<head>
    <title>Grammar Test</title>

</head>
<body>

<section class="vh-100 gradient-custom" style="margin-bottom: 20px">
    <div class="container py-5 h-75">
        <div class="row d-flex justify-content-center align-items-center h-75">
            <div class="card bg-dark text-white " style="border-radius: 1rem; margin-bottom: 25px;margin-top: 10px">
                <form class="card-body p-5 text-center" method="post" action="/grammar/test">
                    <input name="grammarId" value="${grammarId}" type="hidden">
                    <table class="table table-light table-borderless border-primary">
                        <thead class="table-danger">
                        <tr>
                            <th scope="col">Questions</th>
                            <th colspan="4" scope="colgroup"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${questionList}" var="question">
                            <tr>
                                <th scope="col">${question.getTitle()}</th>
                                <c:forEach items="${variantList}" var="variant">
                                    <c:if test="${variant.getQuestions().getId() == question.getId()}">
                                        <td>

                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="${question.getId()}"
                                                       id="${question.getId()}"/>
                                                <label class="form-check-label"
                                                       for="${question.getId()}">${variant.getVariant()}</label>
                                            </div>
                                        </td>
                                    </c:if>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="d-grid gap-2"  style="margin-bottom: 25px">
                        <button href="http://localhost:8080/grammar/test" class="btn btn-primary" type="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"
></script>
</body>
</html>
