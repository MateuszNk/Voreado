<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="pl">
<head>
  <meta charset="UTF-8">
  <title>${requestScope.category.name} - Voreado</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">
  <script src="https://kit.fontawesome.com/5c6b760006.js" crossorigin="anonymous"></script>
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

    <main>
      <h1>${requestScope.category.name}</h1>
      <p>${requestScope.category.description}</p>
      <c:forEach var="discovery" items="${requestScope.discoveries}">
        <article class="discovery">
          <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>
          <p class="discovery-details">Dodane przez: Mateusz, ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
          <a href="<c:out value="${discovery.url}"/>" target="_blank" class="discovery-link">
            <c:out value="${discovery.url}"/>
          </a>
          <p><c:out value="${discovery.description}"/></p>
          <section class="discovery-bar">
            <section class="discovery-bar">
              <a href="#" class="discovery-link upvote">
                <i class="fa-solid fa-circle-arrow-up"></i>
              </a>
              <p class="discovery-votes">32</p>
              <a href="#" class="discovery-link downvote">
                <i class="fa-solid fa-circle-arrow-down"></i>
              </a>
            </section>
          </section>
        </article>
      </c:forEach>
    </main>
  </div>
</body>
</html>
