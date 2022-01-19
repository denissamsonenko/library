<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/style.css">
    <title>Order</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="parts/header.jsp"/>
    <main class="main">
        <jsp:include page="parts/sidebar.jsp"/>
        <div class="content__order">
            <section class="content__search">
                <div>
                    <h2 class="content__header">Search Details</h2>
                </div>
                <div class="content__generate">
                    <div class="content__search_reader">
                        <div class="search__input">
                            <label for="reader">Search reader:</label>
                            <input type="text" placeholder="Type to search.." id="reader">
                            <div class="icon__input"><i class="fa fa-search"></i></div>
                            <div class="autocomplete__box">
                                <li>text</li>
                            </div>
                        </div>
                    </div>
                    <div class="content__search_book">
                        <div class="search__input">
                            <label for="book">Search book:</label>
                            <input type="text" placeholder="Type to search.." id="book">
                            <div class="icon__input"><i class="fa fa-search"></i></div>
                            <div class="autocomplete__box">
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="content__check">
                <div>
                    <h2 class="content__header">Details order</h2>
                </div>
            </section>
        </div>
    </main>
    <jsp:include page="parts/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/jsp/order.js"></script>
</div>
</body>
</html>