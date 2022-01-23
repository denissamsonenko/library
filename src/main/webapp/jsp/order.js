(function () {
    const autocompleteBox = document.querySelectorAll(".autocomplete__box");
    const searchInputBook = document.querySelector('#book');
    const tableBody = document.querySelector('.table__body');
    const select = document.querySelector('select');
    const totalPrice = document.querySelector('.book__price');
    const discountCell = document.querySelector('.table__discount');
    const searchInputReader = document.querySelector('#reader');
    const person = document.querySelector('.personal_details');
    const date = document.querySelector('.date');
    const button = document.querySelector('#print');
    const clean = document.querySelector('#clean');
    const errorSpan = document.querySelector('.error');
    const form = document.querySelector('form');

    document.addEventListener('DOMContentLoaded', init);
    searchInputBook.addEventListener('keyup', dynamicSearchBook);
    searchInputReader.addEventListener('keyup', dynamicSearchReader);
    select.addEventListener('change', changeDay);
    button.addEventListener('click', printPage);
    clean.addEventListener('click', cleanOrder);
    form.addEventListener('submit', createOrder);


    function changeDay(e) {
        date.innerHTML = '';
        const currentDay = new Date();
        currentDay.setDate(currentDay.getDate() + Number(e.target.value));
        const day = currentDay.toISOString().slice(0, 10);
        const returnDay = `<div>Date returning:</div>
                        <input type="hidden" name="returnDate" value="${day}">
                        <div>${day}</div>`;

        date.insertAdjacentHTML('beforeend', returnDay);
    }

    function init() {
        for (let i = 0; i < 31; i++) {
            const element = `<option>${i}</option>`
            select.insertAdjacentHTML('beforeend', element);
        }
    }

    function dynamicSearchReader(e) {
        autocompleteBox[0].innerHTML = '';
        if (e.target.value.length > 2) {
            searchReader(e.target.value).then(values => renderAutocompleteReader(values));
            autocompleteBox[0].classList.add('active');
        } else {
            autocompleteBox[0].classList.remove('active');
        }
    }

    function renderAutocompleteReader(array) {
        if (array.length > 0) {
            for (let value of array) {
                const li = document.createElement('li');
                li.innerHTML = `email: ${value.reader.email} - surname: ${value.reader.surname}`;
                autocompleteBox[0].appendChild(li);
                li.addEventListener('click', function () {
                    fillPersonalDetails(value);
                    autocompleteBox[0].classList.remove('active');
                    searchInputReader.value = '';
                });
            }
        } else {
            const li = document.createElement('li');
            li.innerHTML = 'not found';
            autocompleteBox[0].appendChild(li);
        }
    }

    function fillPersonalDetails(data) {
        person.innerHTML = '';
        if (data.orderStatus === 'ACTIVE') {
            const error = `<span class="error"> Can not add reader, exist uncompleted order</span>`;
            person.insertAdjacentHTML('afterbegin', error);
        } else {
            const details = `<div>${data.reader.name} ${data.reader.surname}</div> 
                         <input type="hidden" name="readerId" value="${data.reader.readerId}">
                         <div>${data.reader.email}</div>`;
            person.insertAdjacentHTML('afterbegin', details);
        }
    }

    function dynamicSearchBook(e) {
        autocompleteBox[1].innerHTML = '';
        if (e.target.value.length > 2) {
            searchBook(e.target.value.toUpperCase()).then(values => renderAutocompleteBook(values));
            autocompleteBox[1].classList.add('active');
        } else {
            autocompleteBox[1].classList.remove('active');
        }
    }

    function renderAutocompleteBook(books) {
        if (books.length > 0) {
            for (let book of books) {
                const li = document.createElement('li');
                li.innerHTML = `book N: ${book.copyBooks.id} - name: ${book.nameRus}`;
                autocompleteBox[1].appendChild(li);
                li.addEventListener('click', function () {
                    fillTable(book);
                    setTotalPrice();
                    autocompleteBox[1].classList.remove('active');
                    searchInputBook.value = '';
                });
            }
        } else {
            const li = document.createElement('li');
            li.innerHTML = 'not found';
            autocompleteBox[1].appendChild(li);
        }
    }

    function fillTable(book) {
        const tr = document.createElement('tr');
        const tdId = document.createElement('td');
        tdId.innerHTML = `${book.copyBooks.id} <input type="hidden" name="bookId" value="${book.copyBooks.id}"></<input>`;
        tdId.addEventListener('click', deleteBook);
        const tdName = document.createElement('td');
        tdName.innerText = `${book.nameRus}`;
        const tdPriceDay = document.createElement('td');
        tdPriceDay.innerText = `${book.pricePerDay}`;
        const days = document.createElement('td');
        days.innerText = `${select.value}`;
        const price = document.createElement('td');
        const priceOfBook = getPrice(days.innerText, tdPriceDay.innerText);
        price.innerText = `${priceOfBook}`;
        price.className = 'priceOfBook';
        tr.append(tdId, tdName, tdPriceDay, days, price);
        tableBody.append(tr)
    }

    function setTotalPrice() {
        let total = 0;
        const getAllPrice = document.querySelectorAll('.priceOfBook');

        for (let allPriceElement of getAllPrice) {
            total += Number(allPriceElement.innerText);
        }

        if (getAllPrice.length > 2 && getAllPrice.length <= 4) {
            discountCell.innerHTML = '10<input type="hidden" name="discount" value="10">';
            total = total - (total * 10 / 100);

        } else if (getAllPrice.length > 4 && getAllPrice.length <= 5) {
            discountCell.innerHTML = `15<input type="hidden" name="discount" value="15">`;
            total = total - (total * 15 / 100);
        } else {
            discountCell.innerHTML = `0<input type="hidden" name="discount" value="0">`;
        }
        totalPrice.innerHTML = `${total.toFixed(2)}<input type="hidden" name="advancePrice" value="${total.toFixed(2)}"/>`;
    }

    function getPrice(days, dailyPrice) {
        return (Number(days) * Number(dailyPrice)).toFixed(2);
    }

    function deleteBook(e) {
        e.target.parentElement.remove();
        setTotalPrice();
    }

    function validateBook(formData) {
        errorSpan.innerHTML = '';
        if (formData.getAll('bookId').length <= 5 && formData.has('bookId')) {
            return true;
        } else {
            errorSpan.innerHTML = `Book count should be less then 5 and more then 1 `;
            return false;
        }
    }

    function validateReader(formData) {
        errorSpan.innerHTML = '';
        if (formData.getAll('readerId').length === 1) {
            return true;
        } else {
            errorSpan.innerHTML = `Check reader, you should choose reader`;
            return false;
        }
    }

    function hasDuplicates(formData) {
        let arrayBook = formData.getAll('bookId');
        let valuesSoFar = [];
        for (let i = 0; i < arrayBook.length; ++i) {
            let value = arrayBook[i];
            if (valuesSoFar.indexOf(value) !== -1) {
                errorSpan.innerHTML = `Duplicates book, remove!`;
                return true;
            }
            valuesSoFar.push(value);
        }

        return false;
    }

    async function searchBook(bookName) {
        try {
            const response = await fetch(`http://localhost:8081/lib/controller?command=search_book_name&name=${bookName}`);
            if (response.ok) {
                return await response.json();
            }
        } catch (error) {
            console.error(error.message)
        }
    }

    async function searchReader(surname) {
        try {
            const response = await fetch(`http://localhost:8081/lib/controller?command=search_reader_surname&surname=${surname}`);
            if (response.ok) {
                return await response.json();
            }
        } catch (error) {
            console.error(error.message)
        }
    }

    async function createOrder(e) {
        e.preventDefault();
        let formData = new FormData(form);

        if (validateBook(formData) && !hasDuplicates(formData) && validateReader(formData)) {
            try {
                const response = await fetch('http://localhost:8081/lib/controller?command=create_order', {
                    method: 'POST',
                    body: formData,
                });
                if (response.ok) {
                    cleanOrder();
                    return await response.text();
                }
            } catch (error) {
                console.error(error.message)
            }
        }
    }

    function printPage(e) {
        e.preventDefault();
        const style = '<link type="text/css" rel="stylesheet" href="http://localhost:8081/lib/jsp/print.css" >';
        const print = window.open('', '', 'left=50,top=50,width=800,height=640,toolbar=0,scrollbars=1,status=0');
        print.document.write(style);
        print.document.write(document.querySelector('.order').innerHTML);
        print.setTimeout(function () {
            print.focus();
            print.print();
            print.close()
        }, 300)
    }

    function cleanOrder(e) {
        if (e) {
            e.preventDefault();
        }
        select.value = 0;
        tableBody.innerHTML = '';
        person.innerHTML = '';
        date.innerHTML = '';
        totalPrice.innerHTML = '';
        discountCell.innerHTML = '';
    }
})()