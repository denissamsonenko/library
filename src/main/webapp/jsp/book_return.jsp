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
<%--                    <form method="post">--%>
<%--                        <div class="label">--%>
<%--                            Reader--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <label for="date" class="label">Expire date</label>--%>
<%--                            <input type="date" id="date" name="expireDate" class="input">--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <label for="fee" class="label">Fee</label>--%>
<%--                            <input type="number" id="fee" name="fee" class="input">--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <label for="totalPrice" class="label">Total Price</label>--%>
<%--                            <input type="number" id="totalPrice" name="totalPrice" class="input">--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <div class="label">Name book</div>--%>
<%--                            <input type="hidden" name="idBook" id="idBook" class="input">--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <div>--%>
<%--                                <label for="notes" class="label">Notes</label>--%>
<%--                                <textarea name="notes" id="notes" class="input"></textarea>--%>
<%--                            </div>--%>
<%--                            <div>--%>
<%--                                <label for="rating" class="label">Rating</label>--%>
<%--                                <input type="number" name="rating" id="rating" class="input">--%>
<%--                            </div>--%>
<%--                            <div class="file__item">--%>
<%--                                <input multiple="" type="file" id="fileBook" name="fileCopy" accept=".jpg, .jpeg, .png"--%>
<%--                                       class="file__book">--%>
<%--                                <div class="file__button">Upload</div>--%>
<%--                                <span class="error"></span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <button type="submit" class="button">Close order</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
                </div>
            </div>
            <%--        <div class="content__order">--%>
            <%--            <section class="content__search">--%>
            <%--                <div>--%>
            <%--                    <h2 class="content__header">Search Details</h2>--%>
            <%--                </div>--%>
            <%--                <div class="content__generate">--%>
            <%--                    <div class="content__search_item">--%>
            <%--                        <div class="search__input">--%>
            <%--                            <label for="reader">Search reader:</label>--%>
            <%--                            <input type="text" placeholder="Type to search.." id="reader">--%>
            <%--                            <div class="icon__input"><i class="fa fa-search"></i></div>--%>
            <%--                            <div class="autocomplete__box">--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                    <div class="content__search_item">--%>
            <%--                        <div class="search__input">--%>
            <%--                            <label for="book">Search book:</label>--%>
            <%--                            <input type="text" placeholder="Type to search.." id="book">--%>
            <%--                            <div class="icon__input"><i class="fa fa-search"></i></div>--%>
            <%--                            <div class="autocomplete__box">--%>
            <%--                            </div>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                    <div class="content__search_date">--%>
            <%--                        <div class="search__date">--%>
            <%--                            <label for="dateReturn">Count days:</label>--%>
            <%--                            <select name="dateReturn" id="dateReturn">--%>

            <%--                            </select>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </section>--%>
            <%--            <section class="order">--%>
            <%--                <form method="post" action="controller">--%>
            <%--                    <div>--%>
            <%--                        <h3>Personal Details:</h3>--%>
            <%--                    </div>--%>
            <%--                    <div class="personal_details">--%>
            <%--                    </div>--%>
            <%--                    <div class="date"></div>--%>
            <%--                    <div>--%>
            <%--                        <h3>Order details:</h3>--%>
            <%--                    </div>--%>
            <%--                    <table>--%>
            <%--                        <thead>--%>
            <%--                        <tr>--%>
            <%--                            <th>№</th>--%>
            <%--                            <th>Book Name</th>--%>
            <%--                            <th>Price Per Day</th>--%>
            <%--                            <th>Days</th>--%>
            <%--                            <th>Price</th>--%>
            <%--                        </tr>--%>
            <%--                        </thead>--%>
            <%--                        <tbody class="table__body">--%>
            <%--                        </tbody>--%>
            <%--                        <tfoot>--%>
            <%--                        <tr>--%>
            <%--                            <td colspan="4">Discount</td>--%>
            <%--                            <td class="table__discount">0</td>--%>
            <%--                        </tr>--%>
            <%--                        <tr>--%>
            <%--                            <td colspan="4">Total Price</td>--%>
            <%--                            <td class="book__price">0</td>--%>
            <%--                        </tr>--%>
            <%--                        </tfoot>--%>
            <%--                    </table>--%>
            <%--                    <div>--%>
            <%--                        <span class="error"></span>--%>
            <%--                    </div>--%>
            <%--                    <div>--%>
            <%--                        <button type="submit" id="create" class="button">Create order</button>--%>
            <%--                        <button id="print" class="button">Print order</button>--%>
            <%--                        <button id="clean" class="button">Clean order</button>--%>
            <%--                    </div>--%>
            <%--                </form>--%>

            <%--            </section>--%>
            <%--        </div>--%>
        </section>
    </main>
    <jsp:include page="parts/footer.jsp"/>
    <script src="${pageContext.request.contextPath}/jsp/js/book_return.js"></script>
</div>
</body>
</html>