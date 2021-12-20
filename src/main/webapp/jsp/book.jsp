<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookPage</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/style.css">
</head>
<body>
<div class="wrapper">
    <jsp:include page="parts/header.jsp"/>
    <main class="main">
        <jsp:include page="parts/sidebar.jsp"/>
        <section class="content__form">
            <form action="controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="create_book">
                <div>
                    <label for="nameRus">Name Rus</label>
                    <input type="text" id="nameRus">
                </div>
                <div>
                    <label for="originName">Origin name</label>
                    <input type="text" id="originName">
                </div>
                <div>
                    <label for="pricePerDay">Price Per Day</label>
                    <input type="text" id="pricePerDay">
                </div>
                <div>
                    <label for="genre">Genre</label>
                    <input type="text" id="genre">
                </div>
                <div>
                    <label for="price">Price</label>
                    <input type="text" id="price">
                </div>
                <div>
                    <label for="count">Book Count</label>
                    <input type="text" id="count">
                </div>
                <div>
                    <label for="pageCount">Page Count</label>
                    <input type="text" id="pageCount">
                </div>
                <div>
                    <label for="publishDate">Page Count</label>
                    <input type="date" id="publishDate">
                </div>
                <div>
                    <label for="file">Images</label>
                    <input multiple type="file" id="file" name="file">
                </div>
                <div>
                    <button type="submit">Submit</button>
                </div>
            </form>
        </section>
    </main>
    <jsp:include page="parts/footer.jsp"/>
</div>
</body>
</html>