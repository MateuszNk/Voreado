<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Voreado</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
    <script src="https://kit.fontawesome.com/5c6b760006.js" crossorigin="anonymous"></script>
    <!-- default font - to change -->
</head>
<body>
<div class="container">
    <nav class="navbar">
        <a href="${pageContext.request.contextPath}" class="logo">
            <i class="fa-solid fa-newspaper"></i>
            Voreado
        </a>
        <a href="#" class="login-button">Sign In</a>
    </nav>

    <aside class="categories">
        <ul>
            <c:forEach var="category" items="${requestScope.categories}">
                <li>
                    <a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a>
                </li>
            </c:forEach>
        </ul>
    </aside>

    <main>
        <c:forEach var="discovery" items="${requestScope.discoveries}">
            <article class="discovery">
                <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>
                <p class="discovery-details">Added by: Mateusz,
                        ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))}
                </p>
                <a href="<c:out value="${discovery.url}"/>" target="_blank" class="discovery-link">
                    <c:out value="${discovery.url}"/>
                </a>
                <p><c:out value="${discovery.description}"/></p>
                <section class="discovery-bar">
                    <a href="#" class="discovery-link upvote">
                        <i class="fa-solid fa-circle-arrow-up"></i>
                    </a>
                    <p class="discovery-votes">32</p>
                    <a href="#" class="discovery-link downvote">
                        <i class="fa-solid fa-circle-arrow-down"></i>
                    </a>
                </section>
            </article>
        </c:forEach>
    </main>

    <footer>Voreado ®, created and developed by Mateusz Nowak based on JavaStart's course.
        <a href="https://javastart.pl">javastart.pl</a></footer>
</div>
</body>
</html>