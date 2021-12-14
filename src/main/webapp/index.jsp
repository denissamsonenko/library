<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="wrapper">
    <header class="header">
        <a href="" class="header__logo">Logo</a>
        <nav class="header__menu">
            <!-- <ul class="header__list">
               <li>
                   <a href="" class="header__link">Menu </a>
               </li>
               <li>
                   <a href="" class="header__link">Menu </a>
               </li>
               <li>
                   <a href="" class="header__link">Menu </a>
               </li>
               <li>
                   <a href="" class="header__link">Menu </a>
               </li>

           </ul> -->
            <div class="header__burger">
                <span></span>
            </div>
        </nav>
    </header>
    <main class="main">
        <aside class="sidebar">
            <nav class="sidebar__menu">
                <ul class="sidebar__list">
                    <li>
                        <a href="" class="sidebar__link">Reg new book</a>
                    </li>
                    <li>
                        <a href="lib?command=reader_form" class="sidebar__link">Reg new reader</a>
                    </li>
                    <li>
                        <a href="" class="sidebar__link">Give book</a>
                    </li>
                    <li>
                        <a href="" class="sidebar__link">Receive book</a>
                    </li>
                </ul>
            </nav>
        </aside>
        <section class="content">
            <h1 class="content__title">Some header</h1>
            <c:choose>
                <c:when test="${param.command == 'reader_form'}">
                    <jsp:include page="jsp/reader.jsp"/>
                </c:when>
                <c:when test="${param.command == 'create_reader'}">
                    <jsp:include page="jsp/book.jsp"/>
                </c:when>
            </c:choose>
        </section>
    </main>
    <footer class="footer">
        <div class="footer__copy">Copyright, 2021</div>
        <div class="footer__text">
            Lorem ipsum dolor sit amet.
        </div>
    </footer>
</div>
</body>
</html>