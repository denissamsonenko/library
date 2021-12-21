<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/style.css">
    <title>Document</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="parts/header.jsp"/>
    <main class="main">
        <jsp:include page="parts/sidebar.jsp"/>
        <section class="content">
            <div class="content__form">
                <div>
                    <h2 class="content__header">Registration new books</h2>
                </div>
                <div>
                    <form action="controller" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="create_book">
                        <div>
                            <label for="nameRus">Name Rus</label>
                            <input type="text" id="nameRus">
                        </div>
                        <div>
                            <label for="nameOrigin">Origin name</label>
                            <input type="text" id="nameOrigin">
                        </div>
                        <div>
                            <label for="pricePerDay">Price Per Day</label>
                            <input type="number" id="pricePerDay" placeholder="1.01" step="0.01" min="0">
                        </div>
                        <div>
                            <label for="price">Price</label>
                            <input type="number" id="price" placeholder="1.01" step="0.01" min="0">
                        </div>
                        <div>
                            <label for="quantity">Book Count</label>
                            <input type="number" id="quantity" min="0" placeholder="1" step="1">
                        </div>
                        <div>
                            <label for="pageNumber">Page Count</label>
                            <input type="number" id="pageNumber" min="0" placeholder="1" step="1">
                        </div>
                        <div>
                            <label for="publishDate">Publish year</label>
                            <input type="number" id="publishDate" min="1900" max="2099" step="1" value="2016">
                        </div>
                        <div>
                            <label for="file">Images</label>
                            <input multiple type="file" id="file" name="file" accept=".jpg, .jpeg, .png">
                        </div>
                        <div>
                            <label for="authors">Authors</label>
                            <input type="text" id="authors">
                        </div>
                        <fieldset>
                            <legend>Choose Genre</legend>
                            <div>
                                <input type="checkbox" id="genre" name="genre" class="input__label">
                                <label for="genre" class="label__checkbox">Genre</label>
                            </div>
                        </fieldset>
                        <div>
                            <button type="submit" class="button">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </main>
    <jsp:include page="parts/footer.jsp"/>
</div>
</body>
</html>