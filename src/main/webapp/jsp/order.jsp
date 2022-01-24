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
                    <div class="content__search_item">
                        <div class="search__input">
                            <label for="reader">Search reader:</label>
                            <input type="text" placeholder="Type to search.." id="reader">
                            <div class="icon__input"><i class="fa fa-search"></i></div>
                            <div class="autocomplete__box">
                            </div>
                        </div>
                    </div>
                    <div class="content__search_item">
                        <div class="search__input">
                            <label for="book">Search book:</label>
                            <input type="text" placeholder="Type to search.." id="book">
                            <div class="icon__input"><i class="fa fa-search"></i></div>
                            <div class="autocomplete__box">
                            </div>
                        </div>
                    </div>
                    <div class="content__search_date">
                        <div class="search__date">
                            <label for="dateReturn">Count days:</label>
                            <select name="dateReturn" id="dateReturn">

                            </select>
                        </div>
                    </div>
                </div>
            </section>
            <section class="order">
                <form method="post" action="controller">
                    <div>
                        <h3>Personal Details:</h3>
                    </div>
                    <div class="personal_details">
                    </div>
                    <div class="date"></div>
                    <div>
                        <h3>Order details:</h3>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <th>№</th>
                            <th>Book Name</th>
                            <th>Price Per Day</th>
                            <th>Days</th>
                            <th>Price</th>
                        </tr>
                        </thead>
                        <tbody class="table__body">
                        </tbody>
                        <tfoot>
                        <tr>
                            <td colspan="4">Discount</td>
                            <td class="table__discount">0</td>
                        </tr>
                        <tr>
                            <td colspan="4">Total Price</td>
                            <td class="book__price">0</td>
                        </tr>
                        </tfoot>
                    </table>
                    <div>
                        <span class="error"></span>
                    </div>
                    <div>
                        <button type="submit" id="create" class="button">Create order</button>
                        <button id="print" class="button">Print order</button>
                        <button id="clean" class="button">Clean order</button>
                    </div>
                </form>

            </section>
        </div>
    </main>
    <jsp:include page="parts/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/jsp/js/order.js"></script>
</div>
</body>
</html>