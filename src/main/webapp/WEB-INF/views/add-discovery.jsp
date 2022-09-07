<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign in - Voreado</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/add-discovery-form.css">
</head>

<body>
<div class="container">
    <%@ include file="../segments/header.jspf" %>

    <form action="${pageContext.request.contextPath}/discovery/add" method="post" class="discovery-form">
        <h2 class="user-form-title">Add new discovery</h2>
        <input name="title" placeholder="Title" required>
        <input name="url" placeholder="URL" type="url" required>
        <select name="categoryId">
            <c:forEach var="category" items="${requestScope.categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>
        <textarea name="description" placeholder="Description"></textarea>
        <button class="discovery-form-button">Add</button>
    </form>

    <%@ include file="../segments/footer.jspf" %>
</div>
</body>
</html>
