<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MainPage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/style.css">
</head>
<body>
<div class="wrapper">
    <jsp:include page="parts/header.jsp"/>
    <main class="main">
        <jsp:include page="parts/sidebar.jsp"/>
        <section class="content">
            <div class="content__table">
                <div class="content__header">
                    <h2>List books table</h2>
                </div>
                <table class="content__table">
                    <thead class="table__head">
                    <tr>
                        <th class="name_rus" title="click to sort"><span class="arrow__nameRus">&#9650;</span>Name book
                        </th>
                        <th>Genre</th>
                        <th>Publish date</th>
                        <th class="total__book">Total book</th>
                        <th class="rest_book" title="click to sort"><span class="arrow__restBook">&#9650;</span>Availability
                        </th>
                    </tr>
                    </thead>
                    <tbody class="table__content">
                    </tbody>
                </table>
                <div class="content__pagination">
                    <ul class="pagination__items">
                    </ul>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="parts/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/jsp/js/main.js"></script>
</div>
</body>
</html>