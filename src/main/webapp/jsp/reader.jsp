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
                    <h2 class="content__header">Registration new reader</h2>
                </div>
                <div>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="create_reader">
                        <div>
                            <label for="surname">Surname</label>
                            <input type="text" name="surname" id="surname">
                        </div>
                        <div>
                            <label for="name">Name</label>
                            <input type="text" name="name" id="name">
                        </div>
                        <div>
                            <label for="middleName">Middle name</label>
                            <input type="text" name="middleName" id="middleName">
                        </div>
                        <div>
                            <label for="passport">Passport</label>
                            <input type="text" name="passport" id="passport">
                        </div>
                        <div>
                            <label for="birthDate">BirthDate</label>
                            <input type="date" name="birthDate" id="birthDate"
                                   value="2021-01-01" min="1900-01-01" max="2022-12-31">
                        </div>
                        <div>
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" placeholder="user@example.gov">
                        </div>
                        <div>
                            <label for="address">Address</label>
                            <input type="text" name="address" id="address">
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
</body>
</html>