document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const email = document.querySelector('#email');

    form.addEventListener('submit', createReader);
    email.addEventListener('change', checkEmail);

    async function getEmail() {
        try {
            const response = await fetch('http://localhost:8081/lib/controller?command=send_email');
            return await response.json();
        } catch (error) {
            console.log(error)
        }
    }

    async function createReader(e) {
        e.preventDefault();
        let formData = new FormData(form);

        if (formValidate() === 0) {
            try {
                const response = await fetch('http://localhost:8081/lib/controller?command=create_reader', {
                    method: 'POST',
                    body: formData,
                });
                if (response.ok) {
                    // location.href = 'http://localhost:8081/lib/controller?command=reader_add_page';
                }
            } catch (error) {
                console.log(error)
            } finally {
                form.reset();
            }

        }
    }

    function formValidate() {
        let error = 0;
        let formReq = document.querySelectorAll('._req');

        for (let i = 0; i < formReq.length; i++) {
            const input = formReq[i];
            formRemoveError(input);
            if (input.value === '') {
                const text = 'field must not be empty';
                formAddError(input, text);
                error++;
            } else {
                // TODO: do smth with validation
                switch (input.id) {
                    case 'email':
                        input.autocomplete = 'chrome-off';
                        if (emailTest(input)) {
                            const text = 'invalid email';
                            formAddError(input, text)
                            error++;
                        }
                    // TODO: check existing email in database and notify client
                        // if (checkEmail()) {
                        //     const text = 'Change email';
                        //     formAddError(input, text)
                        //     error++
                        // }
                        break;
                }
            }
        }
        return error;
    }


    // function checkEmail(){
    //     let array = [];
    //      getEmail().then(function (value) {
    //          array = value;
    //     })
    //    return array.includes(email.value.trim().toUpperCase());
    // }

    function formAddError(input, text) {
        input.parentElement.classList.add('_error')
        input.classList.add('_error')
        input.nextElementSibling.innerText = `${text}`;
        input.nextElementSibling.classList.add('show');
    }

    function formRemoveError(input) {
        input.parentElement.classList.remove('_error');
        input.classList.remove('_error');
        input.nextElementSibling.innerText = '';
        input.nextElementSibling.classList.remove('show');
    }

    function emailTest(input) {
        return !/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i.test(input.value);
    }
})