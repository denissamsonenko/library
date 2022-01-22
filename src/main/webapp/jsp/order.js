const autocompleteBox = document.querySelectorAll(".autocomplete__box");
const searchInputBook = document.querySelector('#book');
const tableBody = document.querySelector('.table__body');
const select = document.querySelector('select');
const totalPrice = document.querySelector('.book__price');
const discountCell = document.querySelector('.table__discount');


document.addEventListener('DOMContentLoaded', init);
searchInputBook.addEventListener('keyup', dynamicSearchBook);
select.addEventListener('change', changeDay)

function changeDay(e) {
    console.log(e.target.value);
}

function init() {
    for (let i = 1; i < 31; i++) {
        const element = `<option>${i}</option>`
        select.insertAdjacentHTML('beforeend', element);
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
        discountCell.innerText = 10;
        total = total - (total * 10 / 100);

    } else if (getAllPrice.length > 4 && getAllPrice.length <= 5) {
        discountCell.innerText = 15;
        total = total - (total * 15 / 100);
    } else {
        discountCell.innerText = 0;
    }

    totalPrice.innerText = `${total.toFixed(2)}`;
}

function getPrice(days, dailyPrice) {
    return (Number(days) * Number(dailyPrice)).toFixed(2);
}

function deleteBook(e) {
    e.target.parentElement.remove();
    setTotalPrice();
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

// document.addEventListener('DOMContentLoaded', function () {
//     window.onload = setDate;
//     const searchInputReader = document.querySelector('#reader');
//     const autocompleteBox = document.querySelectorAll(".autocomplete__box");
//     const searchInputBook = document.querySelector('#book');
//     const divInsBook = document.querySelector('.book__input');
//     const divInsReader = document.querySelector('.reader__input');
//     const dateInput = document.querySelector('#dateIssue');
//     const dateReturn = document.querySelector('#dateReturn');
//     const form = document.querySelector('form');
//     const errorSpan = document.querySelector('.error');
//     // const print = document.querySelector('.print')
//
//     // print.addEventListener('click', printPage)
//     searchInputReader.addEventListener('keyup', dynamicSearchReader);
//     searchInputBook.addEventListener('keyup', dynamicSearchBook);
//     form.addEventListener('submit', createOrder);
//
//
//     function setDate() {
//         const currentDay = new Date();
//         dateInput.value = currentDay.toISOString().slice(0, 10);
//         dateReturn.setAttribute('min', dateInput.value);
//         currentDay.setMonth(currentDay.getMonth() + 1);
//         dateReturn.setAttribute('max', currentDay.toISOString().slice(0, 10));
//         dateReturn.addEventListener('change', function () {
//             console.log('jkj')
//         })
//     }
//
//     function dynamicSearchBook(e) {
//         autocompleteBox[1].innerHTML = '';
//         if (e.target.value.length > 2) {
//             searchBook(e.target.value.toLowerCase()).then(values => renderAutocompleteBook(values));
//             autocompleteBox[1].classList.add('active');
//         } else {
//             autocompleteBox[1].classList.remove('active');
//         }
//     }
//
//     function renderAutocompleteBook(array) {
//         if (array.length > 0) {
//             for (let value of array) {
//                 const li = document.createElement('li');
//                 li.innerHTML = `book N: ${value.copyBooks.id} - name: ${value.nameRus}`;
//                 autocompleteBox[1].appendChild(li);
//                 li.addEventListener('click', function (e) {
//                     createInputBook(value);
//                     autocompleteBox[1].classList.remove('active');
//                     searchInputBook.value = '';
//                 });
//             }
//         } else {
//             const li = document.createElement('li');
//             li.innerHTML = 'no results';
//             autocompleteBox[1].appendChild(li);
//         }
//     }
//
//     function createInputBook(data) {
//         const elem = document.querySelectorAll('#bookId');
//         let arrayId = [];
//         elem.forEach(item => arrayId.push(+item.value));
//
//         if (!arrayId.includes(data.copyBooks.id)) {
//             const div = document.createElement("div");
//             const html = `<input id="bookId" type="hidden" name="bookId" value="${data.copyBooks.id}">
//                       <input name="nameRus" type="text" readonly class="input" value=" book N:${data.copyBooks.id} name:${data.nameRus}">`;
//             div.insertAdjacentHTML('afterbegin', html);
//             const deleteField = document.createElement('span');
//             deleteField.innerHTML = '&minus;';
//             deleteField.className = 'delete__element';
//             deleteField.addEventListener('click', function () {
//                 this.parentElement.remove();
//             })
//             div.append(deleteField);
//             divInsBook.append(div);
//         }
//     }
//
//     function dynamicSearchReader(e) {
//         autocompleteBox[0].innerHTML = '';
//         if (e.target.value.length > 2) {
//             searchReader(e.target.value).then(values => renderAutocompleteReader(values));
//             autocompleteBox[0].classList.add('active');
//         } else {
//             autocompleteBox[0].classList.remove('active');
//         }
//     }
//
//     function renderAutocompleteReader(array) {
//         if (array.length > 0) {
//             for (let value of array) {
//                 const li = document.createElement('li');
//                 li.innerHTML = `email: ${value.reader.email} - surname: ${value.reader.surname} - name: ${value.reader.name}`;
//                 autocompleteBox[0].appendChild(li);
//                 li.addEventListener('click', function () {
//                     createInputReader(value)
//                     autocompleteBox[0].classList.remove('active');
//                     searchInputReader.value = '';
//                 });
//             }
//         } else {
//             const li = document.createElement('li');
//             li.innerHTML = 'no results';
//             autocompleteBox[0].appendChild(li);
//         }
//     }
//
//     function createInputReader(data) {
//         const div = document.createElement("div");
//         const html = `<input id="readerId" type="hidden" name="readerId" value="${data.reader.readerId}">
//                       <input name="readerName" type="text" readonly class="input" value=" name: ${data.reader.surname} surname: ${data.reader.surname}">`;
//         div.insertAdjacentHTML('afterbegin', html);
//         const deleteField = document.createElement('span');
//         deleteField.innerHTML = '&minus;';
//         deleteField.className = 'delete__element';
//         deleteField.addEventListener('click', function () {
//             this.parentElement.remove();
//         });
//         div.append(deleteField);
//         divInsReader.append(div);
//     }
//
//     function validateReader(formData) {
//         errorSpan.innerHTML = '';
//         if (formData.getAll('readerId').length === 1) {
//             return true;
//         } else {
//             errorSpan.innerHTML = `Field reader should be 1`;
//             return false;
//         }
//     }
//
//     function validateBook(formData) {
//         errorSpan.innerHTML = '';
//         if (formData.getAll('bookId').length <= 5 && formData.has('bookId')) {
//             return true;
//         } else {
//             errorSpan.innerHTML = `Book count should be less then 5 and more then 1 `;
//             return false;
//         }
//     }
//
//     function validateDate(formData) {
//         errorSpan.innerHTML = '';
//         if (formData.get('dateReturn')) {
//             return true;
//         } else {
//             errorSpan.innerHTML = `Fill in date returning book`;
//             return false;
//         }
//     }
//
//     async function searchReader(surname) {
//         try {
//             const response = await fetch(`http://localhost:8081/lib/controller?command=search_reader_surname&surname=${surname}`);
//             if (response.ok) {
//                 return await response.json();
//             }
//         } catch (error) {
//             console.error(error.message)
//         }
//     }
//
//     async function searchBook(bookName) {
//         try {
//             const response = await fetch(`http://localhost:8081/lib/controller?command=search_book_name&name=${bookName}`);
//             if (response.ok) {
//                 return await response.json();
//             }
//         } catch (error) {
//             console.error(error.message)
//         }
//     }
//
//     async function createOrder(e) {
//         e.preventDefault();
//         let formData = new FormData(form);
//
//         if (validateBook(formData) && validateReader(formData) && validateDate(formData)) {
//             try {
//                 const response = await fetch('http://localhost:8081/lib/controller?command=create_order', {
//                     method: 'POST',
//                     body: formData,
//                 });
//                 if (response.ok) {
//                     return await response.json();
//                 }
//             } catch (error) {
//                 console.error(error.message)
//             }
//         }
//     }
//
//     // function printPage() {
//     //     const printwin = window.open("");
//     //     printwin.document.write(document.querySelector(".content__check").innerHTML);
//     //     printwin.focus();
//     //     printwin.print();
//     //     // print.close();
//     // }
// })