document.addEventListener('DOMContentLoaded', function () {
    const genresList = document.getElementById('form__genres');
    const spanPlus = document.querySelector('.add__element');
    const inputAuthor = document.querySelector('.author__input');
    const fileAuthor = document.querySelector('.file__item._author');
    const authorForm = document.querySelector('.author__form');
    const form = document.querySelector('form');

    spanPlus.addEventListener('click', addAuthorField);
    form.addEventListener('submit', sendBooks);

    async function getGenres() {
        try {
            const response = await fetch('http://localhost:8081/lib/controller?command=send_genres');
            return await response.json();
        } catch (error) {
            console.log(error)
        }

    }

    (function renderGenre() {
        getGenres().then(value => {
            value.forEach(genre => printGenres(genre))
        })
    })()

    function printGenres({genreId, genreName}) {
        const label = document.createElement('label');
        const inputCheckBox = document.createElement('input');
        const span = document.createElement('span');

        label.for = 'genre';
        label.className = 'container';
        label.innerText = `${genreName}`;

        inputCheckBox.name = 'genre';
        inputCheckBox.type = 'checkbox';
        inputCheckBox.id = 'genre';
        inputCheckBox.value = genreId;
        inputCheckBox.className = 'input__checkbox _req';

        span.className = 'checkmark';

        label.append(inputCheckBox, span);
        genresList.appendChild(label);
    }

    function addAuthorField() {
        const newInput = inputAuthor.cloneNode(false);
        const newFile = fileAuthor.cloneNode(true);


        const deleteAuthor = document.createElement('span');
        const addSpanError = document.createElement('span');
        addSpanError.className = 'error';
        deleteAuthor.innerHTML = '&minus;';
        deleteAuthor.className = 'delete__element';
        deleteAuthor.addEventListener('click', deleteAuthorField);

        newInput.className = 'author__input_clone _req input';
        newInput.autocomplete = 'off';
        newFile.className = 'file__item';

        authorForm.append(newInput, addSpanError, newFile, deleteAuthor);
    }

    function deleteAuthorField() {
        this.previousElementSibling.remove();
        this.previousElementSibling.remove();
        this.previousElementSibling.remove();
        this.removeEventListener('click', deleteAuthorField);
        this.remove();
    }

    async function sendBooks(e) {
        e.preventDefault();
        let formData = new FormData(form);

        if (formValidate() === 0) {
            try{
                const response = await fetch('http://localhost:8081/lib/controller?command=create_book', {
                    method: 'POST',
                    body: formData,
                });
            }catch (error) {
                console.log(error)
            }finally {
                form.reset()
            }
        }
    }

    function formValidate() {
        let error = 0;
        let formReq = document.querySelectorAll('._req');
        let checkboxes = getArrayValidation(formReq);
        let text = '';

        for (let i = 0; i < formReq.length; i++) {
            const input = formReq[i];
            formRemoveError(input);
            if (input.value.trim() === '') {
                text = 'should not be empty';
                formAddError(input, text);
                error++;
            } else {
                switch (input.id) {
                    case 'pricePerDay':
                        text = 'Wrong format (e.g. 1.01)';
                        if (!moneyTest(input)) {
                            formAddError(input, text);
                            error++;
                        }
                        break;
                    case 'price':
                        text = 'Wrong format (e.g. 1.01)';
                        if (!moneyTest(input)) {
                            formAddError(input, text);
                            error++;
                        }
                        break;
                    case 'quantity':
                        if (Number.parseInt(input.value) < 0) {
                            text = 'can not be less then 0';
                            formAddError(input, text);
                            error++;
                        }
                        break;
                    case 'genre':
                        if (checkboxes.length === 0) {
                            text = 'You should choose genre';
                            formAddError(input, text);
                            error++;
                        }
                        break;
                }
            }
        }
        return error;
    }

    function getArrayValidation(input) {
        let array = [];
        for (let i = 0; i < input.length; i++) {
            if (input[i].type === 'checkbox') {
                if (input[i].checked) {
                    array.push(input[i]);
                }
            }
        }
        return array;
    }

    function moneyTest(input) {
        return /^\d+[.]\d{2}$/.test(input.value);
    }

    function formAddError(input, text) {
        if (input.type === 'file') {
            input = input.nextElementSibling;
            input.nextElementSibling.innerHTML = `${text}`;
        } else if (input.type === 'checkbox') {
            input = input.nextElementSibling;
        } else {
            input.nextElementSibling.innerHTML = `${text}`;
        }
        input.parentElement.classList.add('_error');
        input.classList.add('_error');
    }

    function formRemoveError(input) {
        if (input.type === 'file') {
            input = input.nextElementSibling;
            input.nextElementSibling.innerHTML = '';
        } else if (input.type === 'checkbox') {
            input = input.nextElementSibling;
        } else {
            input.nextElementSibling.innerHTML = '';
        }
        input.parentElement.classList.remove('_error');
        input.classList.remove('_error');
    }
})