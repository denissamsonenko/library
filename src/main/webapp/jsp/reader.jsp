<div class="content__form">
    <form method="post" action="lib">
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
                   value="2018-07-22" min="2018-01-01" max="2018-12-31">
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
            <button type="submit">Submit</button>
        </div>
    </form>
</div>
