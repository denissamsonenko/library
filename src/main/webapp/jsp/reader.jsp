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
            <div class="content__form">
                <div>
                    <h2 class="content__header">Registration new reader</h2>
                </div>
                <div>
                    <form action="controller" method="post" novalidate>
                        <input type="hidden" name="command" value="create_reader">
                        <div class="input__pop">
                            <label for="name" class="label">Name*</label>
                            <input type="text" name="name" id="name" autocomplete="middleName" class="input _req">
                            <span class="popupError"></span>
                        </div>
                        <div class="input__pop">
                            <label for="surname" class="label">Surname*</label>
                            <input type="text" name="surname" id="surname" autocomplete="surname" class="input _req">
                            <span class="popupError"></span>
                        </div>
                        <div>
                            <label for="middleName" class="label">Middle name</label>
                            <input type="text" name="middleName" id="middleName" autocomplete="middleName"
                                   class="input">
                        </div>
                        <div>
                            <label for="passport" class="label">Passport</label>
                            <input type="text" name="passport" id="passport" autocomplete="passport" class="input">
                        </div>
                        <div class="input__pop">
                            <label for="birthDate" class="label">BirthDate*</label>
                            <input type="date" name="birthDate" id="birthDate" autocomplete="birthDate"
                                   min="1920-01-01" max="2022-12-31" class="input _req">
                            <span class="popupError"></span>
                        </div>
                        <div class="input__pop">
                            <label for="email" class="label">Email*</label>
                            <input type="email" name="email" id="email" placeholder="user@example.gov"
                                   class="input _req">
                            <span class="popupError"></span>
                        </div>
                        <div>
                            <label for="address" class="label">Address</label>
                            <input type="text" name="address" id="address" autocomplete="new-password" class="input">
                        </div>
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
<script src="${pageContext.request.contextPath}/jsp/js/reader_script.js"></script>
</body>
</html>