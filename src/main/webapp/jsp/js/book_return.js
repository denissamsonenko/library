(function () {
    const button = document.querySelector('#findOrder');
    const inputSearch = document.querySelector('#order');
    const autocompleteBox = document.querySelector(".autocomplete__box");
    const error = document.querySelector('.error');
    const orderInput = document.querySelector('.order__input');
    const bookInput = document.querySelector('.order__input');
    const form = document.querySelector('form');

    button.addEventListener('click', findOrder);
    form.addEventListener('submit', closeOrder);

    function findOrder(e) {
        e.preventDefault();
        error.innerText = '';
        autocompleteBox.innerHTML = '';
        const email = inputSearch.value.trim().toUpperCase();
        if (email) {
            searchOrder(email).then(data => renderAutocompleteOrder(data))
        } else {
            error.innerText = 'Type full email to search';
        }
    }

    function renderAutocompleteOrder(data) {
        autocompleteBox.classList.add('active');
        if (data.orders.idOrder !== 0) {
            const li = document.createElement('li');
            li.innerHTML = `Name: ${data.reader.name} surname: ${data.reader.surname}`;
            autocompleteBox.appendChild(li);
            li.addEventListener('click', function () {
                fillOrders(data);
                autocompleteBox.classList.remove('active');
                inputSearch.value = '';
            });
        } else {
            const li = document.createElement('li');
            li.innerHTML = 'not found';
            autocompleteBox.appendChild(li);
        }
    }

    function fillOrders(data) {
        orderInput.innerHTML = '';
        bookInput.innerHTML = '';
        const order = `
                   <div class="personal__detail">
                   <input type="hidden" name="idOrder" value="${data.orders.idOrder}">
                   <div><span>Name: </span>${data.reader.name}</div>
                   <div><span>Surname: </span>${data.reader.surname}</div>  
                   <div><span>Email: </span>${data.reader.email}</div> 
                   <div><span>Birth Date: </span>${data.reader.birthDate}</div>          
                   </div>
                   <div>
                       <label for="date" class="label">Expire date</label>
                       <input type="date" id="date" name="expireDate" value="${data.orders.dateExpire}" class="input">
                   </div>
                   <div>
                        <label for="fee" class="label">Fee</label>
                        <input type="number" id="fee" name="fee" value="${data.orders.fine}" class="input" placeholder="${data.orders.fine}">
                   </div>
                   <div>
                         <label for="totalPrice" class="label">Total Price</label>
                         <input type="number" id="totalPrice" name="totalPrice" value="${data.orders.finishPrice}" class="input" placeholder="${data.orders.finishPrice}">
                   </div>`;

        orderInput.insertAdjacentHTML('afterbegin', order);

        for (let book of data.bookCopyDto) {
            const bookHtml = `
                        <div class="book__details">
                        <h3>Book name: ${book.nameRus}</h3>
                        <input type="hidden" name="idBook" id="idBook" value="${book.copyBooks.id}" class="input">
                        <div>
                              <label for="notes" class="label">Notes</label>
                              <textarea name="notes" id="notes" class="input"></textarea>
                        </div>
                        <div>
                             <label for="rating" class="label">Rating</label>
                             <input type="number" name="rating" id="rating" class="input" min="0" max="5">
                        </div>
                        <div class="file__item">
                             <input multiple="" type="file" id="fileBook" name="fileCopy" accept=".jpg, .jpeg, .png"
                                       class="file__book">
                             <div class="file__button">Upload</div>
                        </div>
                        </div>`;

            bookInput.insertAdjacentHTML('beforeend', bookHtml);
        }
    }

    async function searchOrder(email) {

        try {
            const response = await fetch(`
    http://localhost:8081/lib/controller?command=send_order&email=${email}`);
            if (response.ok) {
                return await response.json();
            }
        } catch
            (error) {
            console.error(error.message)
        }
    }

    async function closeOrder(e) {
        e.preventDefault();
        let formData = new FormData(form);
        try {
            const response = await fetch('http://localhost:8081/lib/controller?command=close_order', {
                method: 'POST',
                body: formData,
            });
            if (response.ok) {
                return await response.text();
            }
        } catch (error) {
            console.error(error.message)
        }
    }
})