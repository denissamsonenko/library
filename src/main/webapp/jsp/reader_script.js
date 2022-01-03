document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    form.addEventListener('submit', createReader);


    const pop = function (text) {
        return function () {
            this.nextElementSibling.innerText = `${text}`;
            this.nextElementSibling.classList.toggle("show");
        }
    }

    async function createReader(e) {
        e.preventDefault();

        let error = formValidate(form);

        let formData = new FormData(form);

        if (error === 0) {
            const response = await fetch('http://localhost:8081/lib/controller?command=create_reader', {
                method: 'POST',
                body: formData,
            });
            if (response.ok) {
                const result = await response.text();
                form.reset();
            } else {
                console.log('Error')
            }
        } else {
            console.log('Error')
        }
    }

    function formValidate(form) {
        let error = 0;
        let formReq = document.querySelectorAll('._req');

        for (let i = 0; i < formReq.length; i++) {
            const input = formReq[i];
            const text = ''
            formRemoveError(input, text);

            if (input.id === 'email') {
                input.autocomplete = 'chrome-off';
                input.display = 'none';
                if (emailTest(input)) {
                    const text = 'invalid email';
                    formAddError(input, text)
                    error++;
                }
            } else if (input.value === '') {
                const text = 'field must not be empty';
                formAddError(input, text)
                error++;
            }
        }
        return error;
    }

    function formAddError(input, text) {
        input.parentElement.classList.add('_error')
        input.classList.add('_error')
        input.addEventListener('click', pop(text));
    }

    function formRemoveError(input, text) {
        input.parentElement.classList.remove('_error');
        input.classList.remove('_error');
        input.removeEventListener('click', pop(text));
    }

    function emailTest(input) {
        return !/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i.test(input.value);
    }
})

