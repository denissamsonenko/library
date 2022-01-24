<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jsp/css/style.css">
    <title>Book</title>
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
                    <form action="controller" method="post" enctype="multipart/form-data" novalidate>
                        <input type="hidden" name="command" value="create_book">
                        <div>
                            <label for="nameRus" class="label">Name Rus*</label>
                            <input type="text" id="nameRus" name="nameRus" class="input _req">
                            <span class="error"></span>
                        </div>
                        <div>
                            <label for="nameOrigin" class="label">Origin name</label>
                            <input type="text" id="nameOrigin" name="nameOrigin" class="input">
                        </div>
                        <div>
                            <label for="pricePerDay" class="label">Price Per Day*</label>
                            <input type="number" id="pricePerDay" name="pricePerDay" placeholder="1.01" step="0.01"
                                   min="0" class="input _req">
                            <span class="error"></span>
                        </div>
                        <div>
                            <label for="price" class="label">Price*</label>
                            <input type="number" id="price" name="price" placeholder="1.01" step="0.01" min="0"
                                   class="input _req">
                            <span class="error"></span>
                        </div>
                        <div>
                            <label for="quantity" class="label">Book Count*</label>
                            <input type="number" id="quantity" name="quantity" min="0" placeholder="1" step="1"
                                   class="input _req">
                            <span class="error"></span>
                        </div>
                        <div>
                            <label for="pageNumber" class="label">Page Count</label>
                            <input type="number" id="pageNumber" name="pageNumber" min="0" placeholder="1" step="1"
                                   class="input">
                        </div>
                        <div>
                            <label for="publishDate" class="label">Publish year</label>
                            <input type="number" id="publishDate" name="publishDate" min="1900" max="2099" step="1"
                                   placeholder="2016" class="input">
                        </div>
                        <div>
                            <label for="fileBook" class="label">Book images*</label>
                            <div class="file__item">
                                <input multiple type="file" id="fileBook" name="file" accept=".jpg, .jpeg, .png"
                                       class="file__book _req">
                                <div class="file__button">Upload</div>
                                <span class="error"></span>
                            </div>
                        </div>
                        <div class="author__form">
                                <label for="authors" class="label">Authors*</label>
                                <input type="text" id="authors" name="authors" class="input author__input _req"
                                       autocomplete="off">
                                <span class="error"></span>
                                <div class="file__item _author">
                                    <input type="file" id="fileAuthor" name="fileAuthor" accept=".jpg, .jpeg, .png"
                                           class="input__file">
                                    <div class="file__button">Upload</div>
                                </div>
                                <span class="add__element">&plus;</span>
                        </div>

                        <fieldset id="form__genres">
                            <legend>Choose Genre*</legend>
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
    <script src="${pageContext.request.contextPath}/jsp/js/book_create.js"></script>
</div>
</body>
</html>