<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Zaloguj się - Voreado</title>
    <%@ include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forms.css">
</head>
<body>
    <div class="container">
        <%@ include file="../segments/header.jspf" %>

        <form action="j_security_check" method="post" class="user-form">
            <h2 class="user-form-title">Zaloguj się na Voreado</h2>
            <input name="j_username" placeholder="Nazwa użytkownika" required>
            <input name="j_password" placeholder="Hasło" type="password" required>
            <button class="user-form-button">Zaloguj się</button>
            <p>Nie masz jeszcze konta? <a href="${pageContext.request.contextPath}/signup">Zarejestruj się</a></p>
        </form>

        <%@ include file="../segments/footer.jspf" %>
    </div>
</body>
</html>
