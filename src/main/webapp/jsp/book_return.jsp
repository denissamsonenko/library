<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="RU">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/style.css">
    <title>Order close</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="parts/header.jsp"/>
    <main class="main">
        <jsp:include page="parts/sidebar.jsp"/>
        <section class="content__search">
            <div>
                <h2 class="content__header">Search Details</h2>
            </div>
            <div class="content__generate">
                <div class="content__search_box">
                    <div class="search__input">
                        <label for="order">Search order:</label> <span class="error"></span>
                        <input type="text" placeholder="Type to search.." id="order">
                        <div class="icon__input"><i class="fa fa-search"></i></div>
                        <div class="autocomplete__box"></div>
                    </div>
                </div>
                <div class="content__search_button">
                    <button type="submit" id="findOrder" class="button">Search order</button>
                </div>
            </div>

            <div>
                <div>
                    <h2>Order data</h2>
                </div>
                <div class="form__order">
                    <form method="post">
                        <div class="order__input">
                        </div>
                        <div class="book__input">
                        </div>
                        <div>
                            <button type="submit" class="button">Close order</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="parts/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/jsp/js/book_return.js"></script>
</div>
</body>
</html>