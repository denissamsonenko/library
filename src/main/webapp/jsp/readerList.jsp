<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/style.css">
    <title>Document</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="parts/header.jsp"/>
    <main class="main">
        <jsp:include page="parts/sidebar.jsp"/>
        <section class="content">
            <div class="content__table">
                <div class="content__header">
                    <h2>List readers table</h2>
                </div>
                <table class="content__table">
                    <thead class="table__head">
                    <tr>
                        <th class="surname" title="click to sort"><span class="table__arrow">&#9650;</span>Surname</th>
                        <th>Name</th>
                        <th>Birth Date</th>
                        <th>Address</th>
                        <th>Email</th>
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
</div>
<script src="${pageContext.request.contextPath}/jsp/js/reader_list_script.js"></script>
</body>
</html>