const searchInputReader = document.querySelector('#reader');
const autocompleteBox = document.querySelectorAll(".autocomplete__box");
const searchInputBook = document.querySelector('#book');


searchInputReader.addEventListener('keyup', dynamicSearchReader);
searchInputBook.addEventListener('keyup', dynamicSearchBook);

function dynamicSearchBook(e) {
    autocompleteBox[1].innerHTML = '';
    if (e.target.value.length > 2) {
        searchBook(e.target.value).then(values => renderAutocompleteBook(values));
        autocompleteBox[1].classList.add('active');
    } else {
        autocompleteBox[1].classList.remove('active');
    }
}
function renderAutocompleteBook(array) {
    if (array.length > 0) {
        for (let value of array) {
            const li = document.createElement('li');
            li.innerHTML = `book N: ${value.idCopy} - name: ${value.nameRus}`;
            autocompleteBox[1].appendChild(li);
            li.addEventListener('click', function () {
                // add to order list
                //clean input
                autocompleteBox[1].classList.remove('active');
            });
        }
    } else {
        const li = document.createElement('li');
        li.innerHTML = 'no results';
        autocompleteBox[1].appendChild(li);
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
            li.innerHTML = `email: ${value.email} - name: ${value.name}`;
            autocompleteBox[0].appendChild(li);
            li.addEventListener('click', function () {
                // add to order list
                //clean input
                autocompleteBox[0].classList.remove('active');
            });
        }
    } else {
        const li = document.createElement('li');
        li.innerHTML = 'no results';
        autocompleteBox[0].appendChild(li);
    }
}

async function searchReader(email) {
    try {
        const response = await fetch(`http://localhost:8081/lib/controller?command=search_reader_email&email=${email}`);
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


